package com.dougkoellmer.shared.homecells;

public class S_HomeCell
{
	public static final int DEFAULT_CELL_SIZE = 512;
	public static final int CELL_PADDING = 16;
	public static final int GRID_SIZE = 32;
	public static final int THUMBNAIL_SPRITE_PLATE_WIDTH = 10;
	public static final int THUMB_ACTUAL_SIZE=96;
	public static final int THUMB_SIZE = 64;
	
	
	public static int MIN_THUMBNAIL_COUNT = 12;
	public static int MIN_THUMB_ROW_COUNT = MIN_THUMBNAIL_COUNT/2;
	public static final double THUMB_ROW_HEIGHT = (((double)S_HomeCell.DEFAULT_CELL_SIZE)/((double)S_HomeCell.MIN_THUMB_ROW_COUNT));
	
	public static final int IMG_STRIP_SPACING = 16;
	public static final String IMG_STRIP_SPACING_COLOR = "#000000";
}
