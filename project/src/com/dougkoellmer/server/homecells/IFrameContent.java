package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public class IFrameContent implements I_HomeCellContent
{
	private String m_compiled = null;
	private String m_splash = null;
	
	private final E_HomeCell m_cell;
	private final String m_htmlPath;
	private final String m_gravity;
	private final boolean m_remote;
	
	public IFrameContent(E_HomeCell cell, boolean remote, String htmlPath)
	{
		this(cell, htmlPath, remote, "center");
	}
	
	public IFrameContent(E_HomeCell cell, String htmlPath, boolean remote, String gravity)
	{
		m_cell = cell;
		m_htmlPath = htmlPath;
		m_gravity = gravity;
		m_remote = remote;
	}
	
	@Override
	public String getCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return m_splash;
		}
		else if( eCodeType == E_CodeType.COMPILED )
		{
			return m_remote ? m_htmlPath+"/index.html" : m_compiled;
		}
		else if( eCodeType == E_CodeType.SOURCE )
		{
			return m_remote ? m_compiled : null;
		}
		
		return null;
	}

	@Override
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		m_splash = U_HomeCell.getSplashImage(homeCell, "splash", m_gravity, servletContext);
		
		m_compiled = U_Servlet.getResource(servletContext, m_htmlPath+"/index.html");
		
		m_compiled = U_HomeCell.stripAnalytics(m_compiled);
	}

	@Override
	public E_CodeSafetyLevel getSafetyLevel(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return E_CodeSafetyLevel.NO_SANDBOX_STATIC;
		}
		else if( eCodeType == E_CodeType.COMPILED )
		{
			return m_remote ? E_CodeSafetyLevel.REMOTE_SANDBOX : E_CodeSafetyLevel.LOCAL_SANDBOX;
		}
		
		return null;
	}
}
