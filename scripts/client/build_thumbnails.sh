#!/bin/bash

TAKE_SNAPSHOTS=$1

source ./config.sh

if [ "$TAKE_SNAPSHOTS" == "take_snapshots" ]
then
	sh take_cell_snapshots.sh $SNAPSHOT_DIR
fi

mkdir -p $THUMB_DIR

for entry in "$SNAPSHOT_DIR"/*
do
	thumb=$THUMB_DIR/$(basename $entry)
	
	entry=$(realpath $entry)
	thumb=$(realpath $thumb)
	
	$IMAGE_MAGICK_CONVERT $entry -resize $THUMB_SIZE^ -gravity "NORTH" -quality $IMG_QUALITY -extent $THUMB_SIZE $thumb
	
	echo $entry $thumb
	
done

sh ./build_thumb_spriteplate.sh

if [ "$TAKE_SNAPSHOTS" != "take_snapshots" ]
then
	echo
	#rm -rf $SNAPSHOT_DIR
fi




