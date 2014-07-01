package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.server.homecells.U_HomeCell.E_PlayIcon;
import com.dougkoellmer.shared.app.S_App;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class YouTubeContent implements I_HomeCellContent
{
	private String m_splash;
	private String m_compiled;
	private final String m_cellName;
	private final String m_css_position;
	private final String m_css_backgroundSize;
	private final E_HomeCell m_cell;
	private final String m_videoId;
	
	
	public YouTubeContent(E_HomeCell cell)
	{
		this(cell, "center");
	}
	
	public YouTubeContent(E_HomeCell cell, String gravity)
	{
		m_videoId = U_HomeCellMeta.getVideoId(cell);
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
		String embedOptions = "";
		embedOptions += "autohide=1&";
		embedOptions += "rel=0&";
		embedOptions += "showinfo=0&";
		embedOptions += "controls=1&";
		embedOptions += "modestbranding=1&";
		embedOptions += "theme=light&";
		embedOptions += "autoplay=1";
		
		String imgPath = U_HomeCell.getImgPath("http://img.youtube.com/vi/"+m_videoId+"/maxresdefault.jpg");
		m_splash = U_HomeCell.createImgDiv(imgPath, S_HomeCell.DEFAULT_CELL_SIZE, m_css_backgroundSize, m_css_position);
		m_splash += U_HomeCell.createPlayIcon(E_PlayIcon.LARGE);
		m_compiled = "<iframe width='100%' height='100%' src='http://www.youtube.com/embed/"+m_videoId+"?"+embedOptions+"' frameborder='0' allowfullscreen></iframe>";
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
