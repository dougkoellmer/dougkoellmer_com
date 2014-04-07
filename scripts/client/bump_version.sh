#!/bin/bash

FILE="../../project/src/com/dougkoellmer/shared/app/S_App.java"

VAR_NAME="APP_VERSION"
CURRENT_VERSION=$(grep -F -m 1 'APP_VERSION =' $FILE); # can't use variable in here for some reason for var name
CURRENT_VERSION=$(echo $CURRENT_VERSION | sed -re 's/.*= *([0-9]*);/\1/g')
let "CURRENT_VERSION = CURRENT_VERSION + 1"
sed -i -E "s/$VAR_NAME *= *[0-9]+;/$VAR_NAME = $CURRENT_VERSION;/g" $FILE

#! Necessary because sed messes up file permissions under cygwin.
chmod 777 "$FILE"