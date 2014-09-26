#!/bin/bash

#TODO Get some of these from code or as command line arguments (which should also be from code upstream).

source ./config.sh

OUT_DIR=$1
HTTP_ERROR_CODE=404
SERVER=http://127.0.0.1:8888/r.preview/splash
CROP_OFFSET=3

mkdir -p $OUT_DIR


for n in `seq 1 $GRID_SIZE`;
do
	for m in `seq 1 $GRID_SIZE`;
	do
		let m_less_1=m-1
		let n_less_1=n-1
		
		CELL="22x17"
		#CELL="${m_less_1}x${n_less_1}"
		
		URL="http://127.0.0.1:8888/home.html"
		URL="$SERVER/$CELL"
		
		RESPONSE_CODE=`curl -s -o /dev/null -I -w "%{http_code}" $URL`
		
		if [ "$RESPONSE_CODE" != "$HTTP_ERROR_CODE" ]
		then
			
			OUT_IMG="$OUT_DIR/${CELL}.jpg"
			IMG_SIZE=$CELL_SIZE
			let IMG_SIZE=$IMG_SIZE+$CROP_OFFSET
			$SNAPSHOT_TOOL --javascript-delay 2000 --quality $IMG_QUALITY --crop-x $CROP_OFFSET --crop-y $CROP_OFFSET --disable-smart-width --width $IMG_SIZE --height $IMG_SIZE $URL $OUT_IMG
		fi
		
		break
	done
	
	break
	
done