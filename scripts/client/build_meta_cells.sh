#!/bin/bash

SNAPSHOT_DIR="./meta_temp"
OUT_DIR="../../project/war/img/cell_content/meta"

source ./config.sh

#sh take_cell_snapshots.sh $SNAPSHOT_DIR

META_COUNT=4
sub_cell_dim=1
let grid_size=$GRID_SIZE
COMPONENT_DIR=$SNAPSHOT_DIR

FINAL_IMG_SIZE=${CELL_SIZE}x${CELL_SIZE}

TEMP_IMAGE=$SNAPSHOT_DIR/temp.png
BLANK_IMAGE=$SNAPSHOT_DIR/blank.png
BLANK_V_PADDING=$SNAPSHOT_DIR/blank_v_padding.png
BLANK_H_PADDING=$SNAPSHOT_DIR/blank_h_padding.png
convert -size $FINAL_IMG_SIZE xc:none $BLANK_IMAGE
convert -size ${CELL_PADDING}x${CELL_SIZE} xc:none $BLANK_V_PADDING
convert -size $((${CELL_SIZE}*2+${CELL_PADDING}*2))x${CELL_PADDING} xc:none $BLANK_H_PADDING


for meta_level_seed in `seq 1 $META_COUNT`;
do
	sub_cell_dim=$(( sub_cell_dim*2 ))
	grid_size=$(( grid_size/2 ))
	
	OUT_DIR_SUB="$OUT_DIR/$sub_cell_dim"
	rm -rf $OUT_DIR_SUB
	mkdir -p $OUT_DIR_SUB
	
	echo "Compiling size $sub_cell_dim cells..."
	
	for n in `seq 1 $grid_size`;
	do
		for m in `seq 1 $grid_size`;
		do
			let m_less_1=m-1
			let n_less_1=n-1
			
			component_m=$(( m_less_1 * 2 ))
			component_n=$(( n_less_1 * 2 ))
			
			EXTENSION=jpg
			if (("$sub_cell_dim" > "2" ))
			then
				EXTENSION=png
			fi
			
			top_left_img=$COMPONENT_DIR/${component_m}x${component_n}.$EXTENSION
			top_right_img=$COMPONENT_DIR/$((${component_m}+1))x${component_n}.$EXTENSION
			bot_left_img=$COMPONENT_DIR/${component_m}x$((${component_n}+1)).$EXTENSION
			bot_right_img=$COMPONENT_DIR/$((${component_m}+1))x$((${component_n}+1)).$EXTENSION
			
			exist_count=0
			
			if [ -f "$top_left_img" ]
			then
				exist_count=$((${exist_count}+1))
			else
				top_left_img=$BLANK_IMAGE
			fi
			
			if [ -f "$top_right_img" ]
			then
				exist_count=$((${exist_count}+1))
			else
				top_right_img=$BLANK_IMAGE
			fi
			
			if [ -f "$bot_left_img" ]
			then
				exist_count=$((${exist_count}+1))
			else
				bot_left_img=$BLANK_IMAGE
			fi
			
			if [ -f "$bot_right_img" ]
			then
				exist_count=$((${exist_count}+1))
			else
				bot_right_img=$BLANK_IMAGE
			fi
			
			if [ "$exist_count" -eq "0" -o "$exist_count" -eq "1" ]
			then
				continue;
			fi
			
			out_img=$OUT_DIR_SUB/${m_less_1}x${n_less_1}.png
			
			convert.exe "$top_left_img" "$BLANK_V_PADDING" +append "$out_img"
			convert.exe "$out_img" "$top_right_img" +append "$out_img"
			convert.exe "$out_img" "$BLANK_V_PADDING" +append "$out_img"
			convert.exe "$out_img" "$BLANK_H_PADDING" -append "$out_img"
			
			convert.exe "$bot_left_img" "$BLANK_V_PADDING" +append "$TEMP_IMAGE"
			convert.exe "$TEMP_IMAGE" "$bot_right_img" +append "$TEMP_IMAGE"
			convert.exe "$TEMP_IMAGE" "$BLANK_V_PADDING" +append "$TEMP_IMAGE"
			convert.exe "$TEMP_IMAGE" "$BLANK_H_PADDING" -append "$TEMP_IMAGE"
			
			convert.exe "$out_img" "$TEMP_IMAGE" -append "$out_img"
			convert.exe $out_img -resize $FINAL_IMG_SIZE^ -background "#00000000" $out_img
			
		done
	done
	
	COMPONENT_DIR=$OUT_DIR_SUB
done

#rm -rf $SNAPSHOT_DIR



