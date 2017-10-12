# -*- coding: utf-8 -*-
import json
import os
from lightoj import settings
# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html


class LightojPipeline(object):
    def open_spider(self, spider):
        self.tags = {}

    def process_item(self, item, spider):
        if 'tags' in item:
            self.tags[item['pid']] = item['tags']
        else:
            data = json.dumps(item)+'\n'
            with open('data.json', 'a') as f:
                f.write(data)
        return item

    def close_spider(self, spider):
        self.all_tags = set()
        for p in self.tags:
            for tag in self.tags[p]:
                self.all_tags.add(tag)

        with open('data.json', 'r+') as f:
            if not os.path.exists('codes'):
                os.mkdir('codes')
            os.chdir('codes')
            spider.logger.info("Saving files in codes folder...")
            for line in f:
                sub = json.loads(line)
                self.save_code(sub, spider)
            f.seek(0)
            f.truncate()
        os.chdir('..')

    def save_code(self, sub, spider):
        code = "/**\n"
        code += " * Author    : {}\n".format(settings.USER.split('@')[0])
        code += " * Lang      : {}\n".format(sub['lang'])
        code += " * Date      : {}\n".format(sub['date'])
        code += " * Problem   : {}\n".format(sub['name'])
        code += " * CPU       : {}\n".format(sub['cpu'])
        code += " * Memory    : {}\n".format(sub['mem'])
        code += "**/\n"
        code += sub['code']
        for tag in self.all_tags:
            if tag in self.tags[sub['pid']]:
                self.save_to_folder(tag, sub['lang'], sub['name'], code, sub['subid'], spider)

    def save_to_folder(self, tag, lang, name, code, subid, spider):
        if not os.path.exists(tag):
            os.mkdir(tag)
        os.chdir(tag)

        if lang == 'C++':
            ext = '.cpp'
        elif lang == 'JAVA':
            ext = '.java'
        elif lang == 'C':
            ext = '.c'
        else:
            ext = 'unknown'
        filebase = 'LightOJ ' + name
        filename = filebase + ext
        v = 1
        while os.path.exists(filename):
            v += 1
            filename = filebase + ' v' + str(v) + ext
        with open(filename, 'w') as f:
            f.write(code)
            spider.done.append(subid)
        os.chdir('..')
