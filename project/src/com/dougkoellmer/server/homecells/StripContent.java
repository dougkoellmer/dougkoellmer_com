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

public class StripContent implements I_HomeCellContent
{
	private static final String IMG_PATH = "/img/cell_content/";
	
	private String m_splash;
	private String m_compiled;
	private final String m_cellName;
	private final String m_position;
	private final String m_heightWidth;
	private final E_HomeCell m_cell;
	
	
	public StripContent(E_HomeCell cell)
	{
		this(cell, "center");
	}
	
	public StripContent(E_HomeCell cell, String gravity)
	{
		m_cell = cell;
		m_cellName = cell.getCellName();
		
		m_heightWidth = "background-size:cover;";
		
		if( gravity != null )
		{
			m_position = "background-position:"+gravity+";";
		}
		else
		{
			m_position = "";
		}
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{		
		int imgCount = 0;
		int count = 0;
		while( true )
		{
			String imgPath = IMG_PATH+m_cellName+".strip_"+count+".jpg";
			
			if( U_Servlet.fileExists(servletContext, imgPath) )
			{
				imgCount++;
			}
			else
			{
				break;
			}
			
			count++;
		}
		
		int imageHeight = 0;
		
		m_splash = "";
		m_compiled = "";
		int totalHeight = 0;
		count = 0;
		
		String spacer = this.getSpacer();
		String emptyImg = "";
		
		while(count < imgCount)
		{
			String imgPath = IMG_PATH+m_cellName+".strip_"+count+".jpg";
			
			if( count == 0 )
			{
				InputStream imageStream = servletContext.getResourceAsStream(imgPath);
				byte[] imageData = null;
				try {
					imageData = IOUtils.toByteArray(imageStream);
				} catch (IOException e) {}			
				Image image = ImagesServiceFactory.makeImage(imageData);
				
				imageHeight = image.getHeight();
				
				
				imgPath = U_HomeCell.getImgPath(imgPath);
				String img = this.getImg(imgPath, imageHeight);
				emptyImg = this.getEmptyImg(imageHeight);
				
				m_splash += img;
				m_compiled += img;
			}
			else
			{
				m_splash += emptyImg;
				imgPath = U_HomeCell.getImgPath(imgPath);
				m_compiled += this.getImg(imgPath, imageHeight);
			}
			
			if( count < imgCount-1 )
			{
				m_splash += spacer;
				m_compiled += spacer;
				totalHeight += S_HomeCell.IMG_STRIP_SPACING;
			}
			
			count++;
			totalHeight += imageHeight;
		}
		
		CellSize cellSize = U_HomeCellMeta.getFocusedCellSize(m_cell);
		
		if( cellSize.getHeight() != totalHeight )
		{
			throw new Error();
		}
	}
	
	private String getImg(String source, int height)
	{
		return "<div style=\"background-repeat:no-repeat; width:100%; max-height:"+height+"px; height:100%; "+ m_heightWidth+" "+m_position+" background-image:url('"+source+"');\"></div>";
	}
	
	private String getEmptyImg(int height)
	{
		return "<div style='width:100%; height:"+height+"px;'></div>";
	}
	
	private String getSpacer()
	{
		return "<div style='background-color:"+S_HomeCell.IMG_STRIP_SPACING_COLOR+"; width:100%; height:"+S_HomeCell.IMG_STRIP_SPACING+"px;'></div>";
	}
	
	public String getSourceCode(E_CodeType eCodeType)
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
