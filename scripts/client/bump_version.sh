#!/bin/bash

FILE="../../project/src/com/dougkoellmer/shared/app/S_App.java"
COMPILED_FILE="../../project/war/WEB-INF/classes/com/dougkoellmer/shared/app/S_App.class"

VAR_NAME="APP_VERSION"
CURRENT_VERSION=$(grep -F -m 1 'APP_VERSION =' $FILE); # can't use variable in here for some reason for var name
CURRENT_VERSION=$(echo $CURRENT_VERSION | sed -re 's/.*= *([0-9]*);/\1/g')
let "CURRENT_VERSION = CURRENT_VERSION + 1"


# Normally should be able to use sed with -i to replace inline, but sed messes up
# file permissions so we have to jump through some hoops.
sed -E "s/$VAR_NAME *= *[0-9]+;/$VAR_NAME = $CURRENT_VERSION;/g" $FILE > "$FILE.tmp"
rm $FILE
cat "$FILE.tmp" > $FILE
chmod 777 "$FILE.tmp"
rm "$FILE.tmp"
rm $COMPILED_FILE