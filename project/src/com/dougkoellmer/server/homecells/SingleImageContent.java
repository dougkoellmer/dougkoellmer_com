package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class SingleImageContent implements I_HomeCellContent
{
	private static final String IMG_PATH = "/img/cell_content/";
	
	private String m_sourceCode;
	private final String m_cellName;
	
	public SingleImageContent(E_HomeCell cell)
	{
		m_cellName = cell.getCellName();
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_sourceCode = "<img style='height:100%; width:auto;' src='"+IMG_PATH+m_cellName+".solo.jpg'/>";
	}
	
	public String getSourceCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
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
