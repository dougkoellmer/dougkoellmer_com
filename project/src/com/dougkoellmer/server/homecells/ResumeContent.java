package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public class ResumeContent implements I_HomeCellContent
{
	private String m_compiled = null;
	
	@Override
	public String getCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			String imgPath = U_HomeCell.getImgPath("/img/cell_content/resume.splash.jpg");
			return "<img src='"+imgPath+"' style='width:100%; height:100%; min-height:658px;' />";
		}
		else if( eCodeType == E_CodeType.COMPILED )
		{
			return m_compiled;
		}
		
		return null;
	}

	@Override
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_compiled = U_Servlet.getResource(servletContext, "/resume/index.html");
		
		m_compiled = U_HomeCell.stripAnalytics(m_compiled);
	}

	@Override
	public E_CodeSafetyLevel getSafetyLevel(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return E_CodeSafetyLevel.NO_SANDBOX_DYNAMIC;
		}
		else if( eCodeType == E_CodeType.COMPILED )
		{
			return E_CodeSafetyLevel.LOCAL_SANDBOX;
		}
		
		return null;
	}
}
