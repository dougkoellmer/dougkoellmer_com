#!/bin/bash

# "Builds" all the JS, taking minified GWT output, and cat-ing that with minified native support JS
# like History.JS, CodeMirror, Modernizr, etc. Then updates resource version in HTML (or JSP) file.

source ./config.sh

echo "\n\nSTARTING THE SERVER, yay! Press control+c to stop the server.\n\n"

sh ../../project/lib/swarm/tools/appengine-java-sdk/bin/dev_appserver.sh --sdk_root=$GAE_HOME ../../project/war
