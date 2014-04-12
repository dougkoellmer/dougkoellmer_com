#!/bin/bash

if [ $# -eq 3 ]
then
  sh ./resize_img_sequence.sh $3
fi

sh ./rename_img_sequence.sh $1 $2
sh ./build_thumbnails.sh
sh ./build_cell_sizes.sh