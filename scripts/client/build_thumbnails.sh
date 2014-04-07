#!/bin/bash

SEARCH_DIR="../../project/war/img/cell_content"

for entry in "$SEARCH_DIR"/*
do
	if [[ $entry == *.splash.* -o $entry == *.strip_0.* ]]
	then
		thumb=${entry/splash/thumb}
		thumb=${thumb/cell_content/"cell_content/thumbs"}
		
		convert.exe $entry -resize 64x64^ -gravity north -extent 64x64 -quality 100% $thumb
	elif [[  ]]
	then
	
	fi
done



