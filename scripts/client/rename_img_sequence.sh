#!/bin/bash

STAGE_DIR=./image_stage
OUTPUT_DIR="../../project/war/img/cell_content"

NAME=$1

for file in `ls *.fas | sort -V`; do
	echo $file;
done;



