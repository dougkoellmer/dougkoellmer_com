package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class FileBasedHomeCellContent implements I_HomeCellContent
{
	private String m_sourceCode;
	private final String m_fileName;
	
	public FileBasedHomeCellContent(String fileName)
	{
		m_fileName = fileName;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_sourceCode = U_Servlet.getResource(servletContext, HomeCellCreator.BASE_RESOURCE_PATH + "/" + m_fileName + ".html");
	}
	
	public String getSourceCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SOURCE )
		{
			return m_sourceCode;
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
			return E_CodeSafetyLevel.VIRTUAL_STATIC_SANDBOX;
		}
		else
		{
			return null;
		}
	}
}
