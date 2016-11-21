#!/bin/bash

source ./config.sh

#Snapshots have to be take twice because thumb sprite plate has to be
#compiled first before snapshots of thumb cells for meta so thumbs are actually there.
# YOU'LL notice errors referencing thumb plate not existing, cause on the first pass it doesn't
sh build_thumbnails.sh take_snapshots
sh build_meta_cells.sh take_snapshots

#rm -rf $SNAPSHOT_DIR
