package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class ListContent implements I_HomeCellContent
{	
	private String m_sourceCode;
	private final E_HomeCell m_cell;
	private final String[] m_list;
	
	public ListContent(E_HomeCell cell, String[] list)
	{
		m_cell = cell;
		m_list = list;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_sourceCode = "<dl class='dk_list_wrapper'>";
		for( int i = 0; i < m_list.length; i+=2 )
		{
			String title = m_list[i];
			String body = m_list[i+1];
			m_sourceCode += "<dt class='dk_list_title'>" + title + "</dt>";
			m_sourceCode += "<dd class='dk_list_body'>" + body + "</dd>";
		}
		m_sourceCode += "</dl>";
	}
	
	public String getCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH || eCodeType == E_CodeType.SOURCE )
		{
			return m_sourceCode;
		}
		else
		{
			return null;
		}
	}

	@Override
	public E_CodeSafetyLevel getSafetyLevel(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return E_CodeSafetyLevel.NO_SANDBOX_STATIC;
		}
		else
		{
			return null;
		}
	}
}
