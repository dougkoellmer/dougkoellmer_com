#!/bin/bash

SNAPSHOT_DIR="./thumb_temp"
OUT_DIR="../../project/war/img/cell_content/meta/1"
CELL=22x17

source ./config.sh

sh take_cell_snapshots.sh $SNAPSHOT_DIR

mkdir -p $OUT_DIR

cp $SNAPSHOT_DIR/$CELL.jpg $OUT_DIR



