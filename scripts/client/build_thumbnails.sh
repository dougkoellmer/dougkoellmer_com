#!/bin/bash

SEARCH_DIR="../../project/war/img/cell_content"

for entry in "$SEARCH_DIR"/*
do
	if [[ $entry == *.splash.* || $entry == *.strip_0.* || $entry == *.solo.* ]]
	then
	
		thumb=$(echo $entry | sed -re 's/\b(splash|strip_0|solo)\b/thumb/g')
		thumb=${thumb/cell_content/cell_content\/thumbs}
		
		gravity="North"
		
		if [[ $entry == *rose* ]]
		then
			gravity="NorthWest"
		elif [[ $entry == *glasses_holder* || $entry == *milkman* ]]
		then
			gravity="Center"
		elif [[ $entry == *backscratcher_1* ]]
		then
			gravity="East"
		elif [[ $entry == *bow_1* ]]
		then
			gravity="South"
		fi
		
		convert.exe $entry -resize 64x64^ -gravity $gravity -extent 64x64 -quality 100% $thumb
	fi
done



