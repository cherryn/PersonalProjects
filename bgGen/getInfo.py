#!/usr/bin/python
from urllib2 import urlopen
from urllib2 import build_opener
import xml.etree.ElementTree as eTree
import xml.dom.minidom as dom

xml = urlopen("http://www.xkcd.com/atom.xml")

root = eTree.parse(xml).getroot()

comic = root[4].getchildren()

title=comic[0].text
date=comic[2].text
url=comic[3].text
html=comic[4].text

page=dom.parseString(html)
img = page.getElementsByTagName("img")[0]

img_url = img.getAttributeNode('src').nodeValue
alt_text= img.getAttributeNode('alt').nodeValue



f = open("tmp.jpg","wb")
openr = build_opener()
f.write(openr.open(img_url).read())
f.close()

## NOTE: File has to be saved BEFORE print out,
##       otherwise Java may try to open before
##       written.
print title
print url
alt_text = alt_text.split(' ')
print ' '.join(alt_text[:8])
print ' '.join(alt_text[8:20])
print ' '.join(alt_text[20:30])
print ' '.join(alt_text[20:])
