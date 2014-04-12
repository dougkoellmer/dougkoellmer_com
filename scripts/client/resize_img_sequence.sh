#!/bin/bash

STAGE_DIR=./image_stage
OUTPUT_DIR="../../project/war/img/cell_content"

QUALITY=95%
WIDTH=$1

echo "RESIZING"

count=0;
for file in `ls $STAGE_DIR | sort -V -r`; do

	file="$STAGE_DIR/$file"
	
	convert.exe $file -resize $WIDTH -quality $QUALITY $file
	
done