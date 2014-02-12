package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.structs.CellSize;

public class ImageHomeCellContent implements I_HomeCellContent
{
	private final String m_content;
	
	public ImageHomeCellContent()
	{
		m_content = "IMAGE";
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		
	}
	
	public String getContent()
	{
		return m_content;
	}
	
	public CellSize getCellSize()
	{
		CellSize cellSize = new CellSize();
		cellSize.setToDefaults();
		
		return cellSize;
	}
}
