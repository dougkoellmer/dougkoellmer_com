#!/bin/bash

#Snapshots have to be take twice because thumb sprite plate has to be
#compiled first before snapshots of thumb cells for meta so thumbs are actually there.
sh build_thumbnails.sh take_snapshots
sh build_meta_cells.sh take_snapshots