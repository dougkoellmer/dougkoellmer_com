package com.dougkoellmer.server.homecells;

public enum E_Gravity
{
	LEFT,
	HOR_CENTER,
	RIGHT,
	TOP,
	VER_CENTER,
	BOTTOM;
	
	public static int or(E_Gravity ... values)
	{
		int bits = 0x0;
		for( int i = 0; i < values.length; i++ )
		{
			bits |= values[i].getBit();
		}
		
		return bits;
	}
	
	public boolean and(int bits)
	{
		return (bits & this.getBit()) != 0;
	}
	
	public int getBit()
	{
		return 0x1 << this.ordinal();
	}
}
