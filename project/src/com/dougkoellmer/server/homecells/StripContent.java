package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.shared.app.S_App;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;
import swarm.shared.structs.Tuple;

public class StripContent implements I_HomeCellContent
{
	private static final String IMG_PATH = "/img/cell_content/";
	
	private String m_splash;
	private String m_compiled;
	private final String m_cellName;
	private final String m_css_position;
	private final String m_css_backgroundSize;
	private final E_HomeCell m_cell;
	
	
	public StripContent(E_HomeCell cell)
	{
		this(cell, "center");
	}
	
	public StripContent(E_HomeCell cell, String gravity)
	{
		m_cell = cell;
		m_cellName = cell.getCellName();
		
		m_css_backgroundSize = "background-size:cover;";
		
		if( gravity != null )
		{
			m_css_position = "background-position:"+gravity+";";
		}
		else
		{
			m_css_position = "";
		}
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		Tuple<String, String> tuple = U_HomeCell.makeStripHtml(servletContext, homeCell, m_css_backgroundSize, m_css_position);
		m_splash = tuple.getFirst();
		m_compiled = tuple.getSecond();
	}
	
	public String getCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SPLASH )
		{
			return m_splash;
		}
		else if( eCodeType == E_CodeType.COMPILED )
		{
			return m_compiled;
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
		else if( eCodeType == E_CodeType.COMPILED )
		{
			return E_CodeSafetyLevel.NO_SANDBOX_STATIC;
		}
		
		return null;
	}
}
