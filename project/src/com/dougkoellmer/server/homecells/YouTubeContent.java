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
	public static enum E_Thumb
	{
		MAXRESDEFAULT,
		SDDEFAULT;
		
		String getImgUrl(String videoId)
		{
			return "http://img.youtube.com/vi/"+videoId+"/"+this.name().toLowerCase()+".jpg";
		}
	}
	
	protected String m_splash;
	protected String m_compiled;
	private final String m_cellName;
	protected final String m_css_position;
	protected final String m_css_backgroundSize;
	private final E_HomeCell m_cell;
	private final String m_videoId;
	private final E_Thumb m_thumbType;
	
	
	public YouTubeContent(E_HomeCell cell, E_Thumb thumbType)
	{
		this(cell, thumbType, "center");
	}
	
	public YouTubeContent(E_HomeCell cell, E_Thumb thumbType, String gravity)
	{
		m_videoId = U_HomeCellMeta.getVideoId(cell);
		m_cell = cell;
		m_cellName = cell.getCellName();
		m_thumbType = thumbType;
		
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
		
		String imgPath = U_HomeCell.getImgPath(m_thumbType.getImgUrl(m_videoId));
		m_splash = U_HomeCell.createImgDiv(imgPath, S_HomeCell.DEFAULT_CELL_SIZE, m_css_backgroundSize, m_css_position);
		m_splash += U_HomeCell.createPlayIcon(E_PlayIcon.LARGE);
		m_compiled = "<iframe width='100%' height='"+S_HomeCell.DEFAULT_CELL_SIZE+"px;' src='http://www.youtube.com/embed/"+m_videoId+"?"+embedOptions+"' frameborder='0' allowfullscreen></iframe>";
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
