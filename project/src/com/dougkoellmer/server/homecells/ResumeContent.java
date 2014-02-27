package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public class ResumeContent implements I_HomeCellContent
{
	@Override
	public String getContent()
	{
		return "<img src='/img/cell_content/resume.jpg' style='width:100%; height:100%; min-height:658px;' />";
	}

	@Override
	public CellSize getCellSize()
	{
		return new CellSize(880, 1131);
	}

	@Override
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
	}
}
