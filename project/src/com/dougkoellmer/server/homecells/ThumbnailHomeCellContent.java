package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.server.thirdparty.servlet.smU_Servlet;

public class ThumbnailHomeCellContent implements I_HomeCellContent
{
	private final String m_content;
	
	public ThumbnailHomeCellContent()
	{
		m_content = "thumbnails";
	}
	
	public void init(ServletContext servletContext)
	{
		
	}
	
	public String getContent()
	{
		return m_content;
	}
}
