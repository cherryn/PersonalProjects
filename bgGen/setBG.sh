#!/bin/sh

# get background pic
./getInfo.py | java makePic
gconftool-2 -t str -s /desktop/gnome/background/picture_filename ~/.bgGen/desktop.jpg

