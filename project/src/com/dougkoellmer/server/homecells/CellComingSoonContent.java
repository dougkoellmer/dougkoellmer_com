package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public class CellComingSoonContent implements I_HomeCellContent
{
	private String m_sourceCode = "";
	
	public CellComingSoonContent()
	{
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_sourceCode += "<table style='width:100%; height:100%;'><tr><td style='vertical-alignment:middle; text-align:center;'>";
		m_sourceCode += "<img src='/img/coming_soon.png' />";
		m_sourceCode += "</td></tr></table>";
	}
	
	public String getSourceCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return m_sourceCode;
		}
		
		return null;
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
			return E_CodeSafetyLevel.NO_SANDBOX_STATIC;
		}
		
		return null;
	}
}