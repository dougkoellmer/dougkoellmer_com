package com.dougkoellmer.client.managers;

import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.U_HomeCellSize;

import swarm.client.app.AppContext;
import swarm.shared.structs.CellAddressMapping;
import swarm.shared.structs.CellSize;

public class CellSizeManager extends swarm.client.managers.CellSizeManager
{
	public CellSizeManager(AppContext appContext)
	{
		super(appContext);
				
		for( int i = 0; i < E_HomeCell.values().length; i++ )
		{
			E_HomeCell homeCell = E_HomeCell.values()[i];
			CellSize cellSize = U_HomeCellSize.getFocusedCellSize(homeCell);
			forceCache(homeCell.getMapping(), cellSize);
		}
	}
}
