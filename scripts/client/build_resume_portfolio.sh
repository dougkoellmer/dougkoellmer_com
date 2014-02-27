#!/bin/bash

java -jar ../../tools/htmlcompressor-1.5.3.jar --remove-intertag-spaces --compress-css --compress-js -t html -o ../../project/war/resume/index.html ../../project/war/resume/index_debug.html

java -jar ../../tools/htmlcompressor-1.5.3.jar --remove-intertag-spaces --compress-css --compress-js -t html -o ../../project/war/portfolio/index.html ../../project/war/portfolio/index_debug.html

RESUME_IMG=../../project/war/img/cell_content/resume.jpg
../../tools/wkhtmltopdf/wkhtmltoimage.exe --width 880 ../../project/war/resume/index.html $RESUME_IMG
convert.exe $RESUME_IMG -resize 512 -quality 90% $RESUME_IMG

#! Need to change --page-height if I add/remove any more stuff...ugh
../../tools/wkhtmltopdf/wkhtmltopdf.exe --print-media-type -T 0mm -B 0mm -L 0mm -R 0mm --zoom 2.5 --page-width 260 --page-height 330 ../../project/war/resume/index.html ../../project/war/resume.pdf

cp ../../project/war/resume.pdf ../../external/Resume_DougKoellmer.pdf


