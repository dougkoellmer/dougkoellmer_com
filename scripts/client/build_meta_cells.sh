#!/bin/bash

#TODO Get these from code or as command line arguments (which should also be from a script upstream).
HTTP_ERROR_CODE=404
GRID_SIZE=32
CELL_SIZE=512
CELL_PADDING=16
SERVER=http://127.0.0.1:8888/r.preview/splash
SNAPSHOT_TOOL=../../tools/wkhtmltopdf/wkhtmltoimage.exe
OUT_DIR=../../project/war/img/cell_content/meta
CROP_OFFSET=3

OUT_DIR_SUB="$OUT_DIR/1"
rm -rf $OUT_DIR_SUB
mkdir -p $OUT_DIR_SUB
			
for n in `seq 1 $GRID_SIZE`;
do
	for m in `seq 1 $GRID_SIZE`;
	do
		#CELL="${m}x${n}"
		CELL="16x14"
		URL="$SERVER/$CELL"
		CODE=`curl -s -o /dev/null -I -w "%{http_code}" $URL`
		
		if [ "$CODE" != "$HTTP_ERROR_CODE" ]
		then
			
			OUT_IMG="$OUT_DIR_SUB/${CELL}.jpg"
			IMG_SIZE=$CELL_SIZE
			let IMG_SIZE=$IMG_SIZE+$CROP_OFFSET
			$SNAPSHOT_TOOL --javascript-delay 500 --crop-x $CROP_OFFSET --crop-y $CROP_OFFSET --disable-smart-width --width $IMG_SIZE --height $IMG_SIZE $URL $OUT_IMG
		fi
		
		break
	done
	
	break
	
done