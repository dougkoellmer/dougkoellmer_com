#!/bin/bash

SNAPSHOT_DIR="./meta_temp"
OUT_DIR="../../project/war/img/cell_content/meta"

source ./config.sh

#sh take_cell_snapshots.sh $SNAPSHOT_DIR

META_COUNT=4
sub_cell_dim=1
let grid_size=$GRID_SIZE
COMPONENT_DIR=$SNAPSHOT_DIR

for meta_level_seed in `seq 1 $META_COUNT`;
do
	sub_cell_dim=$(( sub_cell_dim*2 ))
	grid_size=$(( grid_size/2 ))
	
	OUT_DIR_SUB="$OUT_DIR/$sub_cell_dim"
	mkdir -p $OUT_DIR_SUB
	
	for n in `seq 1 $grid_size`;
	do
		for m in `seq 1 $grid_size`;
		do
			let m_less_1=m-1
			let n_less_1=n-1
			
			component_m=$(( m_less_1 * 2 ))
			component_n=$(( n_less_1 * 2 ))
			
			top_left_img=$COMPONENT_DIR/${component_m}x${component_n}.jpg
			top_right_img=$COMPONENT_DIR/$((component_m+1))x${component_n}.jpg
			bot_left_img=$COMPONENT_DIR/${component_m}x$((component_n+1)).jpg
			bot_right_img=$COMPONENT_DIR/$((component_m+1))x$((component_n+1)).jpg
			
			exist_count=0
			
			if [ -f "$top_left_img" ]
			then
				exist_count=$((exist_count+1))
			fi
			if [ -f "$top_right_img" ]
			then
				exist_count=$((exist_count+1))
			fi
			if [ -f "$bot_left_img" ]
			then
				exist_count=$((exist_count+1))
			fi
			if [ -f "$bot_right_img" ]
			then
				exist_count=$((exist_count+1))
			fi
			
			if [ "$exist_count" -eq "0" -o "$exist_count" -eq "1" ]
			then
				continue;
			fi
			
			out_img=$OUT_DIR_SUB/${m_less_1}x${n_less_1}.jpg
			
			if [ -f "$top_left_img" -a -f "$top_right_img" ]
			then
				convert.exe "$top_left_img" "$top_right_img" +append $out_img
			fi
			
		done
	done
	
	COMPONENT_DIR=$OUT_DIR_SUB
done

#rm -rf $SNAPSHOT_DIR



