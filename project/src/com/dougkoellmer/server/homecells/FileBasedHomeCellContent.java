package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class FileBasedHomeCellContent implements I_HomeCellContent
{
	public static enum Type
	{
		VIRTUALIZED,
		RAW
	}
	
	private String m_sourceCode;
	private final String m_fileName;
	private final Type m_type;
	
	public FileBasedHomeCellContent(String fileName, Type type)
	{
		m_fileName = fileName;
		m_type = type;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_sourceCode = U_Servlet.getResource(servletContext, HomeCellCreator.BASE_RESOURCE_PATH + "/" + m_fileName + ".html");
	}
	
	public String getCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SOURCE )
		{
			return m_type == Type.VIRTUALIZED ? m_sourceCode : null;
		}
		else if( eCodeType == E_CodeType.SPLASH )
		{
			return m_type == Type.RAW ? m_sourceCode : null;
		}
		else
		{
			return null;
		}
	}
	
	public CellSize getFocusedCellSize()
	{
		CellSize cellSize = new CellSize();
		cellSize.setToDefaults();
		
		return cellSize;
	}

	@Override
	public E_CodeSafetyLevel getSafetyLevel(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return m_type == Type.VIRTUALIZED ? E_CodeSafetyLevel.VIRTUAL_STATIC_SANDBOX : E_CodeSafetyLevel.NO_SANDBOX_STATIC;
		}
		else
		{
			return null;
		}
	}
}
