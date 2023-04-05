#!/bin/bash

source ./config.sh

PAGE_HEIGHT=639
DEBUG_HEIGHT=${1:-$PAGE_HEIGHT}

java -jar ../../tools/htmlcompressor-1.5.3.jar --remove-intertag-spaces --compress-css --compress-js -t html -o ../../project/war/resume/index.html ../../project/war/resume/index_debug.html

java -jar ../../tools/htmlcompressor-1.5.3.jar --remove-intertag-spaces --compress-css --compress-js -t html -o ../../project/war/portfolio/index.html ../../project/war/portfolio/index_debug.html

SPLASH_QUALITY=90%

RESUME_IMG=../../project/war/img/cell_content/resume.splash.jpg
../../tools/wkhtmltopdf/wkhtmltoimage --width 880 $LOCAL_DEV_SERVER/resume/ $RESUME_IMG
$IMAGE_MAGICK_CONVERT $RESUME_IMG -resize 512 -quality $SPLASH_QUALITY $RESUME_IMG

PORTFOLIO_IMG=../../project/war/img/cell_content/portfolio.splash.jpg
../../tools/wkhtmltopdf/wkhtmltoimage --width 992 $LOCAL_DEV_SERVER/portfolio/ $PORTFOLIO_IMG
$IMAGE_MAGICK_CONVERT $PORTFOLIO_IMG -resize 512 -quality $SPLASH_QUALITY $PORTFOLIO_IMG

#! Need to change --page-height if I add/remove any more stuff...ugh
../../tools/wkhtmltopdf/wkhtmltopdf --print-media-type -T 0mm -B 0mm -L 0mm -R 0mm --zoom 2.5 --page-width 370 --page-height $PAGE_HEIGHT $LOCAL_DEV_SERVER/resume/ ../../project/war/resume.pdf

cp ../../project/war/resume.pdf ../../external/Resume_DougKoellmer.pdf


