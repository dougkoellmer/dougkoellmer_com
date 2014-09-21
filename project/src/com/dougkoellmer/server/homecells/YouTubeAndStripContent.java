package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.server.homecells.U_HomeCell.E_PlayIcon;
import com.dougkoellmer.server.homecells.YouTubeContent.E_Thumb;
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

public class YouTubeAndStripContent extends YouTubeContent
{	
	public YouTubeAndStripContent(E_HomeCell cell, E_Thumb thumbType)
	{
		this(cell, thumbType, "center");
	}
	
	public YouTubeAndStripContent(E_HomeCell cell, E_Thumb thumbType, String gravity)
	{
		super(cell, thumbType, gravity);
	}
	
	@Override public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		super.init(servletContext, homeCell);
		
		m_splash += U_HomeCell.makeStripSpacerHtml();
		m_compiled += U_HomeCell.makeStripSpacerHtml();

		Tuple<String, String> tuple = U_HomeCell.makeStripHtml(servletContext, homeCell, m_css_backgroundSize, m_css_position);
		m_splash += tuple.getFirst();
		m_compiled += tuple.getSecond();
		
		m_compiled = "<div style='font-size:0px; line-height:0px; height:100%;'>" + m_compiled + "</div>";
	}
}
