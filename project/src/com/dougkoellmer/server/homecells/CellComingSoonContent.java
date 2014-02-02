package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public class CellComingSoonContent implements I_HomeCellContent
{
	private String m_content;
	
	public CellComingSoonContent()
	{
		m_content = "<table style='width:100%; height:100%;'><tr><td style='vertical-alignment:middle; text-align:center;'>";
		m_content += "<img src='/img/coming_soon.png' />";
		m_content += "</td></tr></table>";
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		
	}
	
	public String getContent()
	{
		return m_content;
	}
}