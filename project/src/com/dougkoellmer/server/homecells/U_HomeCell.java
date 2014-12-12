package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import sun.java2d.pipe.SpanIterator;
import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.app.S_CommonApp;
import swarm.shared.structs.CellSize;
import swarm.shared.structs.Tuple;

import com.dougkoellmer.server.app.ServerApp;
import com.dougkoellmer.shared.app.S_App;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.dougkoellmer.shared.homecells.U_HomeCellSize;
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
	
	public static String createImgDiv(String source, int maxHeight, String backgroundSize, String position, boolean doOverflow)
	{
		String percentage = doOverflow ? S_App.IMAGE_CORRECTION_OVERFLOW : "100%";
		return "<div style=\"background-repeat:no-repeat; width:"+percentage+"; max-height:"+maxHeight+"px; height:"+percentage+"; "+ backgroundSize+" "+position+" background-image:url('"+source+"');\"></div>";
	}
	
	private static final double PLAY_ICON_ALPHA = .85;
	
	static enum E_PlayIcon
	{
		SMALL, LARGE;
	}
	
	public static String createPlayIcon(E_PlayIcon icon)
	{
		String iconPath;
		String position;
		if( icon == E_PlayIcon.SMALL )
		{
			position = "";
			iconPath = "/r.img/video_play_icon.thumb.png";
		}
		else
		{
			position ="position:absolute;";
			iconPath = "/r.img/video_play_icon.png";
		}
		
		String size = S_HomeCell.DEFAULT_CELL_SIZE + "px";
		
		String html = "<table style='"+position+" top:0px; left:0px; width:100%; height:"+size+";'><tr><td style='text-align:center'>";
		html += "<img style='opacity:"+PLAY_ICON_ALPHA+";' src='"+getImgPath(iconPath)+"' />";
		html += "</td></tr></table>";
		
		return html;
	}
	
	public static String getImgPath(String rawPath)
	{
		return rawPath + "?v="+ServerApp.getInstance().getConfig().appVersion;
	}
	
	
	public static boolean usesListIcon(E_HomeCell cell)
	{
		return U_HomeCellSize.isListCell(cell) || U_HomeCellSize.isLongForm(cell) || cell == E_HomeCell.OLD_FRIEND;
	}
	
	public static Tuple<String, String> makeStripHtml(ServletContext servletContext, E_HomeCell cell, String cssBackgroundSize, String cssPosition)
	{
		return makeStripHtml(servletContext, cell, cssBackgroundSize, cssPosition, true);
	}
	
	public static Tuple<String, String> makeStripHtml(ServletContext servletContext, E_HomeCell cell, String cssBackgroundSize, String cssPosition, boolean includeFirstStaticImage)
	{
		String cellName = cell.getCellName();
				
		int imgCount = 0;
		int count = 0;
		while( true )
		{
			String imgPath = IMG_PATH+cellName+".strip_"+count+".jpg";
			
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
		
		
		String splash = "";
		String compiled = "";
		int totalHeight = 0;
		count = 0;
		
		String spacer = makeStripSpacerHtml();
		String emptyImg = "";
		
		while(count < imgCount)
		{
			String imgPath = IMG_PATH+cellName+".strip_"+count+".jpg";
			
//			if( count == 0 )
			{
				InputStream imageStream = servletContext.getResourceAsStream(imgPath);
				byte[] imageData = null;
				try {
					imageData = IOUtils.toByteArray(imageStream);
				} catch (IOException e) {}			
				Image image = ImagesServiceFactory.makeImage(imageData);
				
				imageHeight = image.getHeight();
				if( imageHeight < S_HomeCell.DEFAULT_CELL_SIZE )
				{
					imageHeight = S_HomeCell.DEFAULT_CELL_SIZE;
				}
				
				imgPath = U_HomeCell.getImgPath(imgPath);
				String img = U_HomeCell.createImgDiv(imgPath, imageHeight, cssBackgroundSize, cssPosition, /*doOverflow=*/true);
				emptyImg = makeEmptyImg(imageHeight);
				
				splash += count == 0 && includeFirstStaticImage ? img : emptyImg;
				compiled += img;
			}
			
			if( count < imgCount-1 )
			{
				splash += spacer;
				compiled += spacer;
				totalHeight += S_HomeCell.IMG_STRIP_SPACING;
			}
			
			count++;
			totalHeight += imageHeight;
		}
		
		CellSize cellSize = U_HomeCellSize.getFocusedCellSize(cell);
		
		if( cellSize.getHeight() != totalHeight + U_HomeCellSize.getExtraHeight(cell))
		{
			throw new Error();
		}
		
		return new Tuple<String, String>(splash, compiled);
	}
	
	public static String makeStripSpacerHtml()
	{
		return "<div style='background-color:"+S_HomeCell.IMG_STRIP_SPACING_COLOR+"; width:100%; height:"+S_HomeCell.IMG_STRIP_SPACING+"px;'></div>";
	}
	
	private static String makeEmptyImg(int height)
	{
		return "<div style='width:100%; height:"+height+"px;'></div>";
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
