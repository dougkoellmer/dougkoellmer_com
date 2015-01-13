package com.dougkoellmer.shared.app;

public class S_App
{
	public static final String APP_ID = "dk";
	
	public static final int APP_VERSION = 79;
	
	//--- DRK > Need this cause in Chrome (at least) with DIVs using background-url and set at width/height at 100%
	//----		when you zoom out a bit you can see the element behind it (in this case the white background of the cell)
	//---		flash at the edges real quick in some cases...probably some rounding error in the renderer or something.
	public static final String IMAGE_CORRECTION_OVERFLOW = "100.1%";
	
	
	public static final String BACKGROUND_COLOR = "rgb(100,100,100)";
	
	public static final long HTTP_CACHE_EXPIRATION_SECONDS = 60*60*24*365; // one year
}
