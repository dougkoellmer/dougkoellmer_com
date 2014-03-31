package com.dougkoellmer.server.entities;

import java.util.logging.Logger;

import com.dougkoellmer.shared.homecells.S_HomeCell;

import swarm.server.entities.BaseServerGrid;


public class ServerGrid extends BaseServerGrid
{
	private static final Logger s_logger = Logger.getLogger(ServerGrid.class.getName());
	
	public ServerGrid()
	{
		this.m_cellHeight = this.m_cellWidth = S_HomeCell.DEFAULT_CELL_SIZE;
		this.m_cellPadding = 16;
		
		this.expandToSize(32, 32);
	}
}