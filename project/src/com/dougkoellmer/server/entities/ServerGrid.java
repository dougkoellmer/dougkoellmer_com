package com.dougkoellmer.server.entities;

import java.util.logging.Logger;

import swarm.server.entities.smServerGrid;


public class ServerGrid extends smServerGrid
{
	private static final Logger s_logger = Logger.getLogger(ServerGrid.class.getName());
	
	public ServerGrid()
	{
		this.m_cellHeight = this.m_cellWidth = 512;
		this.m_cellPadding = 16;
		
		this.expandToSize(35, 35);
	}
}