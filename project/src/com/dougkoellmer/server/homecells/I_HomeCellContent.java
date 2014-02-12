package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public interface I_HomeCellContent
{
	String getContent();
	
	CellSize getCellSize();
	
	void init(ServletContext servletContext, E_HomeCell homeCell);
}
