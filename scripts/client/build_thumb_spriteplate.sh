#!/bin/bash

source ./config.sh

MOD_COUNT=10

out_file=$THUMB_DIR/thumb_plate.jpg

all_files=""
for n in `seq 1 $GRID_SIZE`; do
	for m in `seq 1 $GRID_SIZE`; do
		
		let m_less_1=m-1
		let n_less_1=n-1
		
		echo $m_less_1;
		
		potentialThumb=$THUMB_DIR/${m_less_1}x${n_less_1}.jpg

		if [ -f $potentialThumb ]; then
			all_files="$all_files $potentialThumb"
			let found_count=found_count+1
		fi
	done
done

all_files="$all_files $out_file"

$IMAGE_MAGICK_MONTAGE -geometry $THUMB_SIZE -tile 10 $all_files