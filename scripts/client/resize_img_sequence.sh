#!/bin/bash

STAGE_DIR=./image_stage
OUTPUT_DIR="../../project/war/img/cell_content"

QUALITY=90%
WIDTH=700

count=0;
for file in `ls $STAGE_DIR | sort -V -r`; do

	file="$STAGE_DIR/$file"
	
	convert.exe $file -resize $WIDTH -quality $QUALITY $file
	
done