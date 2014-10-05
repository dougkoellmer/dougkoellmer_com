package com.dougkoellmer.server.entities;

import java.util.logging.Logger;

import com.dougkoellmer.shared.homecells.S_HomeCell;

import swarm.server.entities.BaseServerGrid;
import swarm.shared.json.A_JsonFactory;
import swarm.shared.json.I_JsonObject;


public class ServerGrid extends BaseServerGrid
{
	private static final Logger s_logger = Logger.getLogger(ServerGrid.class.getName());
	
	public ServerGrid()
	{
		this.m_cellHeight = this.m_cellWidth = S_HomeCell.DEFAULT_CELL_SIZE;
		this.m_cellPadding = S_HomeCell.CELL_PADDING;
		
		this.expandToSize(S_HomeCell.GRID_SIZE, S_HomeCell.GRID_SIZE);
	}
	
	@Override public void writeJson(I_JsonObject json_out, A_JsonFactory factory)
	{
		// do nothing cause we already know grid properties on the client through shared constants.
	}
}