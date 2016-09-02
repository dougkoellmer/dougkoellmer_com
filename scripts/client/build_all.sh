#!/bin/bash

# "Builds" all the JS, taking minified GWT output, and cat-ing that with minified native support JS
# like History.JS, CodeMirror, Modernizr, etc. Then updates resource version in HTML (or JSP) file.

source ./config.sh

cd ../../project/
ant build_app
cd - > /dev/null