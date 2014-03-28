package com.dougkoellmer.shared.homecells;

import swarm.shared.structs.CellSize;

class U_HomeCell
{
	static CellSize getCellSize(E_HomeCell cell)
	{
		switch(cell)
		{
			case ROSE: return new CellSize(709, 595);
		}
		
		return new CellSize(CellSize.DEFAULT_DIMENSION, CellSize.DEFAULT_DIMENSION);
	}
}
