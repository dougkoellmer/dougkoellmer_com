package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class SingleImageContent implements I_HomeCellContent
{	
	private String m_sourceCode;
	private final String m_cellName;
	private final E_Direction m_direction;
	private final String m_gravity;
	
	public SingleImageContent(E_HomeCell cell)
	{
		this(cell, E_Direction.HORIZONTAL, "center");
	}
	
	public SingleImageContent(E_HomeCell cell, String gravity)
	{
		this(cell, E_Direction.HORIZONTAL, gravity);
	}
	
	public SingleImageContent(E_HomeCell cell, E_Direction direction)
	{
		this(cell, direction, "center");
	}
	
	public SingleImageContent(E_HomeCell cell, E_Direction direction, String gravity)
	{
		m_cellName = cell.getCellName();
		m_direction = direction;
		m_gravity = gravity;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		String heightWidth = "background-size:cover;";
		/*if( m_direction == E_Direction.HORIZONTAL )
		{
			heightWidth = "background-size:auto 100%;";
			
		}
		else
		{
			heightWidth = "background-size:100% auto;";
		}*/
		
		String position = "";
		if( m_gravity != null )
		{
			position = "background-position:"+m_gravity+";";
		}
		
		String imgPath = U_HomeCell.IMG_PATH+m_cellName+".solo.jpg";
		InputStream imageStream = servletContext.getResourceAsStream(imgPath);
		byte[] imageData = null;
		try {
			imageData = IOUtils.toByteArray(imageStream);
		} catch (IOException e) {}			
		Image image = ImagesServiceFactory.makeImage(imageData);
		
		int imageHeight = image.getHeight();
		int maxHeight = imageHeight < S_HomeCell.DEFAULT_CELL_SIZE ? S_HomeCell.DEFAULT_CELL_SIZE : imageHeight;
		
		imgPath = U_HomeCell.getImgPath(imgPath);
		m_sourceCode = "<div style=\"background-repeat:no-repeat; width:100%; max-height:"+maxHeight+"px; height:100%; "+heightWidth+" "+position+" background-image:url('"+imgPath+"');\"></div>";
	}
	
	public String getCode(E_CodeType eCodeType)
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
