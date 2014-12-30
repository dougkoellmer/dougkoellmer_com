#!/bin/bash

# "Builds" all the JS, taking minified GWT output, and cat-ing that with minified native support JS
# like History.JS, CodeMirror, Modernizr, etc. Then updates resource version in HTML (or JSP) file.

# Minify support libraries.
SWARM_SCRIPTS="../../project/lib/swarm/scripts/client"
cd $SWARM_SCRIPTS
sh ./minify_codemirror.sh
sh ./minify_history.sh
sh ./minify_fastclick_js.sh
sh ./minify_utils_js.sh
cd -

# cat support libraries together into one dependency.
SWARM_JS_DIR=../../project/lib/swarm/src/swarm/client/js
CM_DIR="$SWARM_JS_DIR/codemirror"
HISTORY_DIR="$SWARM_JS_DIR/history"
MODERNIZR_DIR="$SWARM_JS_DIR/modernizr"
FASTCLICK_DIR="$SWARM_JS_DIR/fastclick"
UTILS_DIR="$SWARM_JS_DIR/utils"
IMAGES_LOADED_DIR="$SWARM_JS_DIR/images_loaded"
IS_MOBILE_DIR="$SWARM_JS_DIR/is_mobile"
JQUERY_DIR="$SWARM_JS_DIR/jquery"
WAITFORIMAGES_DIR="$SWARM_JS_DIR/wait_for_images"
TEMP_OUT_DIR="./temp"
cat $JQUERY_DIR/jquery.min.js $WAITFORIMAGES_DIR/min.js $IS_MOBILE_DIR/is_mobile.js $IMAGES_LOADED_DIR/images_loaded.min.js $UTILS_DIR/utils.min.js $HISTORY_DIR/native.history_min.js $MODERNIZR_DIR/modernizr.custom.90450.js $CM_DIR/cm_min.js $FASTCLICK_DIR/fastclick.min.js > $TEMP_OUT_DIR/dependencies.min.js

MODULE_NAME=dougkoellmer_com
# cat minified support and main app js bootstrapper into one file.
APP_JS=$(realpath ../../project/war/$MODULE_NAME/$MODULE_NAME.nocache.js)
JS_MIN_OUT=min.js
SUPPORT_JS="$TEMP_OUT_DIR/dependencies.min.js"
MODULE=$(realpath ../../project/war/$MODULE_NAME)
cat $SUPPORT_JS $APP_JS > "$MODULE/$JS_MIN_OUT"
#cat "$MODULE/$JS_MIN_OUT" > $APP_JS

cd ../../project/war/$MODULE_NAME
cp *.cache.js ../
cd -

# update the query string in the js link in the html in order to force-clear the cache.
JSP=$(realpath ../../project/war/index.jsp)
sh $SWARM_SCRIPTS/update_resource_version.sh $JSP $JS_MIN_OUT