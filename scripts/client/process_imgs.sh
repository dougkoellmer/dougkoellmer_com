#!/bin/bash

sh ./resize_img_sequence.sh
sh ./rename_img_sequence.sh $1 $2
sh ./build_thumbnails.sh
sh ./build_cell_sizes.sh