file_absPath() {
  echo "$(cd "$(dirname "$1")" && pwd)/$(basename "$1")"
}

SWARM_DIR="../../project/lib/swarm"
GRID_SIZE=32
CELL_SIZE=512
CELL_PADDING=16
SNAPSHOT_TOOL_DIR="${SWARM_DIR}/tools/nwjs/nwjs.app/Contents/MacOS/nwjs"
SNAPSHOT_TOOL="$SNAPSHOT_TOOL_DIR ./meta_temp/" # node-webkit
IMG_QUALITY=90
SNAPSHOT_DIR="./meta_temp"
THUMB_DIR="../../project/war/img/cell_content/thumbs/auto"
THUMB_SIZE=96x96
export GAE_HOME=$(file_absPath "${SWARM_DIR}/tools/appengine-java-sdk")
export GWT_HOME=$(file_absPath "${SWARM_DIR}/tools/gwt")


FINAL_IMG_SIZE=${CELL_SIZE}x${CELL_SIZE}


# NOTE: Couldn't get imagemagick working from straight up binary download on mac.
# So for now it's a dependency that has to be on-system.
# Had to resort to `sudo port install imagemagick`. The brew equivalent may come
# up in googling but didn't work on OSX 10.11.3 at the time.

# export MAGICK_HOME="../../project/lib/swarm/tools/image_magick"
# export DYLD_LIBRARY_PATH="$MAGICK_HOME/lib"
# IMAGE_MAGICK_BIN="$MAGICK_HOME/bin"
# IMAGE_MAGICK_CONVERT="$IMAGE_MAGICK_BIN/convert"
# IMAGE_MAGICK_MONTAGE="$IMAGE_MAGICK_BIN/montage"

IMAGE_MAGICK_CONVERT=convert
IMAGE_MAGICK_MONTAGE=montage