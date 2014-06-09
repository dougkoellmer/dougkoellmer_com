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
	public static enum Type
	{
		TITLED,
		RAW,
		SUBTITLED
	}
	private String m_sourceCode;
	private final E_HomeCell m_cell;
	private final String[] m_list;
	private final Type m_type;
	
	public ListContent(E_HomeCell cell, String[] list, Type type)
	{
		m_cell = cell;
		m_list = list;
		m_type = type;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		
		
		if( m_type == Type.TITLED )
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
		else
		{
			m_sourceCode = "<ul style='padding-left:0px;' class='dk_list_wrapper'>";
			
			for( int i = 0; i < m_list.length; i+=1 )
			{
				String body = m_list[i];
				m_sourceCode += "<li class='dk_list_body'>" + body + "</li>";
			}
			
			m_sourceCode += "</ul>";
		}
		
		
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
