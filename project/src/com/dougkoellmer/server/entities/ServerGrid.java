package com.dougkoellmer.server.entities;

import java.util.logging.Logger;

import swarm.server.entities.BaseServerGrid;


public class ServerGrid extends BaseServerGrid
{
	private static final Logger s_logger = Logger.getLogger(ServerGrid.class.getName());
	
	public static final int CELL_SIZE = 512;
	
	public ServerGrid()
	{
		this.m_cellHeight = this.m_cellWidth = CELL_SIZE;
		this.m_cellPadding = 16;
		
		this.expandToSize(35, 35);
	}
}