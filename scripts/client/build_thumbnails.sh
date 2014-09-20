#!/bin/bash

SNAPSHOT_DIR="./thumb_temp"
THUMB_DIR="../../project/war/img/cell_content/thumbs/auto"

sh take_cell_snapshots.sh $SNAPSHOT_DIR

THUMB_SIZE=96x96

for entry in "$SNAPSHOT_DIR"/*
do
	thumb=$THUMB_DIR/$(basename $entry)
	
	entry=$(realpath $entry)
	thumb=$(realpath $thumb)
		
	convert.exe $entry -resize $THUMB_SIZE^ -gravity "NORTH" -extent $THUMB_SIZE $thumb
	
	#echo $entry $thumb
	
done

rm -rf $SNAPSHOT_DIR



