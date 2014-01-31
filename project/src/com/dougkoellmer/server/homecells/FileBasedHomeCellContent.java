package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.server.thirdparty.servlet.U_Servlet;

public class FileBasedHomeCellContent implements I_HomeCellContent
{
	private String m_content;
	private final String m_fileName;
	
	public FileBasedHomeCellContent(String fileName)
	{
		m_fileName = fileName;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_content = U_Servlet.getResource(servletContext, HomeCellCreator.BASE_RESOURCE_PATH + "/" + m_fileName + ".html");
	}
	
	public String getContent()
	{
		return m_content;
	}
}
