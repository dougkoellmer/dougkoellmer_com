#!/bin/bash

java -jar ../../tools/htmlcompressor-1.5.3.jar --remove-intertag-spaces --compress-css --compress-js -t html -o ../../project/war/resume/index.html ../../project/war/resume/index_debug.html

java -jar ../../tools/htmlcompressor-1.5.3.jar --remove-intertag-spaces --compress-css --compress-js -t html -o ../../project/war/portfolio/index.html ../../project/war/portfolio/index_debug.html

#! Need to change --page-height if I add/remove any more stuff...ugh
../../tools/wkhtmltopdf/wkhtmltopdf.exe --print-media-type -T 0mm -B 0mm -L 0mm -R 0mm --zoom 1.4 --page-width 150 --page-height 183.5 ../../project/war/resume/index.html ../../project/war/resume.pdf

cp ../../project/war/resume.pdf ../../external/Resume_DougKoellmer.pdf