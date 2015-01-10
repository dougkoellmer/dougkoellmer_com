#!/bin/bash

#TODO Get some of these from code or as command line arguments (which should also be from code upstream).


source ./config.sh

OUT_DIR=$1
HTTP_ERROR_CODE=404
SERVER=http://127.0.0.1:8888/r.preview/splash
# Can't be fucked figuring out proper sed or whatever.
SERVER_ESCAPED="http:\/\/127.0.0.1:8888\/r.preview\/splash"
CROP_OFFSET=3

for n in `seq 1 $GRID_SIZE`;
do
	for m in `seq 1 $GRID_SIZE`;
	do
		let m_less_1=m-1
		let n_less_1=n-1
		
		#CELL="17x15"
		CELL="${m_less_1}x${n_less_1}"
		
		URL="$SERVER/$CELL?take_snapshot"
		URL_ESCAPED="$SERVER_ESCAPED\/$CELL?take_snapshot"
		
		echo "curling ${URL}..."
		
		RESPONSE_CODE=`curl -s -o /dev/null -I -w "%{http_code}" $URL`
		
		if [ "$RESPONSE_CODE" != "$HTTP_ERROR_CODE" ]
		then
			
			#OUT_IMG="$OUT_DIR/${CELL}.jpg"
			#IMG_SIZE=$CELL_SIZE
			#let IMG_SIZE=$IMG_SIZE+$CROP_OFFSET
			
			
			FILE=$OUT_DIR/package.json
			cp nodewebkit_snapshot_config.json $OUT_DIR/package.json
			
			# Normally should be able to use sed with -i to replace inline, but sed messes up
			# file permissions so we have to jump through some hoops.
			sed -E "s/\{\{url\}\}/$URL_ESCAPED/g" $FILE > "$FILE.tmp"
			rm $FILE
			cat "$FILE.tmp" > $FILE
			chmod 777 "$FILE.tmp"
			rm "$FILE.tmp"
			
			echo "SNAPPING"
			$SNAPSHOT_TOOL
		fi
	done
	
done