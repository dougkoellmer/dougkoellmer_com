#!/bin/bash

source ./config_app_version.sh


let "CURRENT_VERSION = CURRENT_VERSION + 1"


# Normally should be able to use sed with -i to replace inline, but sed messes up
# file permissions so we have to jump through some hoops.
sed -E "s/$VAR_NAME *= *[0-9]+;/$VAR_NAME = $CURRENT_VERSION;/g" $FILE > "$FILE.tmp"
rm $FILE
cat "$FILE.tmp" > $FILE
chmod 777 "$FILE.tmp"
rm "$FILE.tmp"
rm $COMPILED_FILE