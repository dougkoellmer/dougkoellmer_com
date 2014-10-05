package com.dougkoellmer.client.entities;

import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;

import swarm.client.entities.ClientGrid;
import swarm.shared.structs.BitArray;
import swarm.shared.structs.GridCoordinate;

public class DkClientGrid extends ClientGrid
{
	public DkClientGrid()
	{
		m_width = S_HomeCell.GRID_SIZE;
		m_height = S_HomeCell.GRID_SIZE;
		m_cellWidth = S_HomeCell.DEFAULT_CELL_SIZE;
		m_cellHeight = S_HomeCell.DEFAULT_CELL_SIZE;
		m_cellPadding = S_HomeCell.CELL_PADDING;
		m_ownership = new BitArray(m_width*m_height);
		
		for( int i = 0; i < E_HomeCell.values().length; i++ )
		{
			E_HomeCell homeCell = E_HomeCell.values()[i];
			GridCoordinate coord = homeCell.getCoordinate();
			int index = coord.getN() * m_width + coord.getM();
			
			m_ownership.set(index, true);
		}
		
		initMetaOwnership();
	}
}
