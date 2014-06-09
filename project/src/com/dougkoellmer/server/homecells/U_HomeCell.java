package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.server.app.ServerApp;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

public class U_HomeCell
{
	public static final String IMG_PATH = "/img/cell_content/";
	private static final Logger s_logger = Logger.getLogger(U_HomeCell.class.getName());
	
	public static String stripAnalytics(String html)
	{
		return html.replaceAll("<script>.*GoogleAnalyticsObject.*</script>", "");
	}
	
	public static String getImgPath(String rawPath)
	{
		return rawPath + "?v="+ServerApp.getInstance().getConfig().appVersion;
	}
	
	public static boolean isNaturalHeightCell(E_HomeCell cell)
	{
		if( isListCell(cell) )  return true;
		
		return cell == E_HomeCell.POLISH_FOREST_ADVENTURE;
	}
	
	public static boolean isListCell(E_HomeCell cell)
	{
		return cell == E_HomeCell.LIFE_HACKS || cell == E_HomeCell.MUSINGS;
	}
	
	public static String getSplashImage(E_HomeCell cell, String contentType, String gravity, ServletContext servletContext)
	{
		String cellName = cell.getCellName();
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
		if( gravity != null )
		{
			position = "background-position:"+gravity+";";
		}
		
		String imgPath = IMG_PATH+cellName+"."+contentType+".jpg";
		InputStream imageStream = servletContext.getResourceAsStream(imgPath);
		byte[] imageData = null;
		try {
			imageData = IOUtils.toByteArray(imageStream);
		} catch (IOException e)
		{
			s_logger.severe(e+"");
		}
		Image image = ImagesServiceFactory.makeImage(imageData);
		
		int imageHeight = image.getHeight();
		int maxHeight = imageHeight < S_HomeCell.DEFAULT_CELL_SIZE ? S_HomeCell.DEFAULT_CELL_SIZE : imageHeight;
		String maxHeightString = "max-height:"+maxHeight+"px;";
		
		imgPath = U_HomeCell.getImgPath(imgPath);
		return "<div style=\"background-repeat:no-repeat; width:100%;  height:100%; "+heightWidth+" "+position+" background-image:url('"+imgPath+"');\"></div>";
	}
}
