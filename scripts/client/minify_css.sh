#!/bin/bash

SWARM_CSS_DIR=../../project/war/r.app
DK_CSS_DIR=../../project/src/com/dougkoellmer/shared/css


java -jar ../../project/lib/swarm/tools/yuicompressor-2.4.8.jar $DK_CSS_DIR/cell_content.css -o $DK_CSS_DIR/cell_content.temp.css

cat $SWARM_CSS_DIR/min.css $DK_CSS_DIR/cell_content.temp.css > $SWARM_CSS_DIR/min.temp.css

rm $SWARM_CSS_DIR/min.css
rm $DK_CSS_DIR/cell_content.temp.css

cp $SWARM_CSS_DIR/min.temp.css $SWARM_CSS_DIR/min.css
rm $SWARM_CSS_DIR/min.temp.css