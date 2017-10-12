# -*- coding: utf-8 -*-
import scrapy
import json
from bs4 import BeautifulSoup


class LojSpider(scrapy.Spider):
    name = 'loj'
    allowed_domains = ['lightoj.com']
    start_urls = ['http://lightoj.com/login_main.php']

    my_sub_url = 'http://lightoj.com/volume_usersubmissions.php'
    forum_url = 'http://lightoj.com/forum_showproblem.php?problem='

    def __init__(self, *args, **kwargs):
        super(LojSpider, self).__init__(*args, **kwargs)
        with open('done.json', 'w+') as f:
            text = f.read()
        if text:
            self.done = json.loads(text)
        else:
            self.done = []

    def closed(self, reason):
        with open('done.json', 'w') as f:
            json.dump(self.done, f)

    def parse(self, response):
        return scrapy.FormRequest.from_response(
            response,
            formdata={'myuserid': self.settings.get('USER'),
                      'mypassword': self.settings.get('PASS')},
            callback=self.after_login
        )

    def after_login(self, response):
        if 'login_main.php' in response.text:
            self.logger.error("Login failed...")
            return
        self.logger.info("Log in successful...")
        yield scrapy.Request(self.my_sub_url, callback=self.my_sub)

    def my_sub(self, response):
        return scrapy.FormRequest.from_response(
            response,
            formdata={'user_password': self.settings.get('PASS')},
            callback=self.parse_allsub
        )

    def parse_allsub(self, response):
        trs = response.css('#mytable3 tr')
        for tr in trs[1:]:  # skipping the first row
            ver = tr.css('div::text').extract_first().strip()
            a = tr.css('a')[0]
            if ver == 'Accepted':
                yield response.follow(a, callback=self.parse_sub)

    def parse_sub(self, response):
        soup = BeautifulSoup(response.text, 'html5lib')
        tr = soup.find('table', id='mytable3').find_all('tr')[1]
        tds = tr.find_all('td')
        code = soup.find('textarea').text
        subid = tr.find('th').text.strip()
        date = tds[0].text.strip()
        name = tds[2].text.strip()
        lang = tds[3].text.strip()
        cpu = tds[4].text.strip()
        mem = tds[5].text.strip()
        pid = name.split('-')[0].strip()

        self.logger.info('yielding code for problem {}'.format(pid))
        yield {
            'name': name,
            'pid': pid,
            'subid': subid,
            'date': date,
            'lang': lang,
            'cpu': cpu,
            'mem': mem,
            'code': code,
        }
        self.done.append(pid)

        # To get problem tags
        forum_page = self.forum_url+pid
        request = scrapy.Request(forum_page, callback=self.parse_rdr)
        request.meta['pid'] = pid
        yield request

    def parse_rdr(self, response):
        href = 'http://lightoj.com/' + response.text.split("'")[1]
        request = scrapy.Request(href, self.parse_forum)
        request.meta['pid'] = response.meta['pid']
        yield request

    def parse_forum(self, response):
        tb = response.css('#mytable3')[1]
        div = tb.css('tr th div')[1]
        tags = div.css('a::text').extract()
        pid = response.meta['pid']
        self.logger.info('yielding tags of {}'.format(pid))

        yield {
            'pid': pid,
            'tags': tags
        }
