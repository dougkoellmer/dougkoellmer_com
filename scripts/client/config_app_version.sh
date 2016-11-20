#!/bin/bash

FILE="../../project/src/com/dougkoellmer/shared/app/S_App.java"
COMPILED_FILE="../../project/war/WEB-INF/classes/com/dougkoellmer/shared/app/S_App.class"

VAR_NAME="APP_VERSION"
CURRENT_VERSION=$(grep -F -m 1 'APP_VERSION =' $FILE); # can't use variable in here for some reason for var name
CURRENT_VERSION=$(echo $CURRENT_VERSION | sed -E 's/.*= *([0-9]*);/\1/g')
echo CURRENT_VERSION=$CURRENT_VERSION

