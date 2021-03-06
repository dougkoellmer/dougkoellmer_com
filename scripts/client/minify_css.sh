#!/bin/bash

SWARM_CSS_DIR=../../project/war/dougkoellmer_com
DK_CSS_DIR=../../project/src/com/dougkoellmer/shared/css


source ./config_app_version.sh

## mega hack to make sure spinner sprite plate uses app version
SPINNER_CSS=$DK_CSS_DIR/spinner.css
sed -E "s/url\('(.*)'\)/url('\1?v=$CURRENT_VERSION')/g" $SPINNER_CSS > "$SPINNER_CSS.temp"
chmod 777 "$SPINNER_CSS.temp"
cat "$SPINNER_CSS.temp" > $DK_CSS_DIR/spinner.temp.css
rm "$SPINNER_CSS.temp"


java -jar ../../project/lib/swarm/tools/yuicompressor-2.4.8.jar $DK_CSS_DIR/cell_content.css -o $DK_CSS_DIR/cell_content.temp.css
java -jar ../../project/lib/swarm/tools/yuicompressor-2.4.8.jar $DK_CSS_DIR/spinner.temp.css -o $DK_CSS_DIR/spinner.temp.css



cat $SWARM_CSS_DIR/min.css $DK_CSS_DIR/cell_content.temp.css $DK_CSS_DIR/spinner.temp.css > $SWARM_CSS_DIR/min.temp.css

rm $SWARM_CSS_DIR/min.css
rm $DK_CSS_DIR/cell_content.temp.css
rm $DK_CSS_DIR/spinner.temp.css

cp $SWARM_CSS_DIR/min.temp.css $SWARM_CSS_DIR/min.css
rm $SWARM_CSS_DIR/min.temp.css