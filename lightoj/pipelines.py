# -*- coding: utf-8 -*-
import json
# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html


class LightojPipeline(object):
    def open_spider(self, spider):
        with open('tags.json', 'w+') as f:
            content = f.read()
        if content:
            self.tags = json.loads(content)
        else:
            self.tags = {}

    def process_item(self, item, spider):
        if 'tags' in item:
            self.tags[item['pid']] = item['tags']
        else:
            data = json.dumps(item)+'\n'
            with open('data.json', 'a') as f:
                f.write(data)

        return item
