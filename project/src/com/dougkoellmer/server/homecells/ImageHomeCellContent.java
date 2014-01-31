package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.server.thirdparty.servlet.U_Servlet;

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
}
