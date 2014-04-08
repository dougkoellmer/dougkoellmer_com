#!/bin/bash

STAGE_DIR=./image_stage
OUTPUT_DIR="../../project/war/img/cell_content"
NAME=$1
TYPE=$2

count=0;
for file in `ls $STAGE_DIR | sort -V -r`; do

	if [[ $TYPE == strip ]]
	then
		newName="${NAME}.strip_${count}.jpg"
	elif [[ $TYPE == solo ]]
	then
		newName="${NAME}.solo.jpg"
	fi
	
	file="$STAGE_DIR/$file"
	dest="$OUTPUT_DIR/$newName"
	cp $file $dest
	let "count++"
done