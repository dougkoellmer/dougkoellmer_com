package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import com.dougkoellmer.server.entities.ServerGrid;
import com.dougkoellmer.server.homecells.U_HomeCell.E_PlayIcon;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class ThumbnailHomeCellContent implements I_HomeCellContent
{
	private static final Logger s_logger = Logger.getLogger(ThumbnailHomeCellContent.class.getName());
	
	static final int MIN_COUNT = 12;
	
	private String m_sourceCode;
	private int m_height = 0;
	
	public ThumbnailHomeCellContent()
	{
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		final String thumbPlateUrl = "/img/cell_content/thumbs/auto/thumb_plate.jpg";
		
		InputStream imageStream = servletContext.getResourceAsStream(thumbPlateUrl);
		byte[] imageData = null;
		try {
			imageData = IOUtils.toByteArray(imageStream);
		} catch (IOException e) {}			
		Image image = ImagesServiceFactory.makeImage(imageData);
		
		int padding = 2;
		double percentage = ((double)S_HomeCell.THUMB_SIZE+padding)/((double)S_HomeCell.THUMB_ACTUAL_SIZE);
		int plateWidth = image.getWidth();
		int plateHeight = image.getHeight();
		double scaledPlateWidth = (plateWidth*percentage);
		double scaledPlateHeight = (plateHeight*percentage);
		
		
		String backgroundSize = scaledPlateWidth+"px "+scaledPlateHeight+"px";
		
		StringBuilder builder = new StringBuilder();
		builder.append("<div style='width:100%; height:100%; font-size:0px; line-height:0px; overflow:hidden;'>");
		
		int i = 0;
		int borderWidth = 5;
		Iterator<E_HomeCell> children = homeCell.getChildren();
		int paddedChildCount = homeCell.getPaddedChildCount();
		String border = borderWidth+"px solid rgb(100,100,100)";
		double cellWidth = ((double)(S_HomeCell.DEFAULT_CELL_SIZE - borderWidth))/2.0;
		double cellHeight = ((double)(S_HomeCell.DEFAULT_CELL_SIZE - borderWidth*5))/6.0;
		
		while( i < MIN_COUNT || (children.hasNext() || !children.hasNext() && i % 2 != 0) )
		{
			boolean lastRow = i >= paddedChildCount-2;
			boolean isLeft = i%2==0;
			
			//--- DRK > WOAH! mega-hack!!!
			String wrapperStyle = isLeft ? "display:inline-block;" : "display:inline-block;";
			wrapperStyle += isLeft ? "border-right:"+border+";" : "";
			wrapperStyle += "border-bottom:"+border+";";
			wrapperStyle += "height:"+cellHeight+"px;width:"+cellWidth+"px;";
			
			
			if( children.hasNext() )
			{
				E_HomeCell child = children.next();
				String description = U_HomeCellMeta.getDescription(child);
				String address = "javascript:dk.snap('"+child.getPrimaryAddress().getRaw()+"');";
				
				String thumbUrl = "/img/cell_content/thumbs/auto/"+child.getCoordinate().writeString()+".jpg";

				if( !U_Servlet.fileExists(servletContext, thumbUrl) )
				{
					thumbUrl = "/img/coming_soon.thumb.png";
				}
				
				thumbUrl = U_HomeCell.getImgPath(thumbUrl);
				
				int index= child.getIndex();
				int plateM = index % S_HomeCell.THUMBNAIL_SPRITE_PLATE_WIDTH;
				int plateN = (index-plateM) / S_HomeCell.THUMBNAIL_SPRITE_PLATE_WIDTH;
				int offsetM = (int) (-((S_HomeCell.THUMB_SIZE+padding)*plateM));
				int offsetN = (int) (-((S_HomeCell.THUMB_SIZE+padding)*plateN));
				
				offsetM-=1;
				offsetN-=1;
				
//				String thumbHtml = "<img class='dk_thumb_cell_img' src='"+thumbUrl+"'>";
				
				String thumbHtml = "<div class='dk_thumb_cell_img' style=\"background-position:"+offsetM+"px "+offsetN+"px; ;background-size:"+backgroundSize+"; background-repeat:no-repeat; background-image:url('"+thumbPlateUrl+"')\"></div>";
				
				builder.append("<div style='"+wrapperStyle+"'>");
				builder.append("<a target=\"_self\" href=\""+address+"\" class='waypoint_cell_link visual_cell_content'>");
				builder.append("<table style='width:100%;' class='dk_thumb_cell waypoint_no_table_fluff'><tr><td style='vertical-align:middle;'>"+thumbHtml+"</td><td style='text-align:right;'><div class='dk_thumb_desc'>");
				builder.append(description);
				builder.append("</div></td></tr></table>");
				builder.append("</a>");
				builder.append("</div>");
			}
			else
			{
				builder.append("<div style='"+wrapperStyle+"'>");
//				builder.append("<div style='width:100%; height:100%;'></div>");
				builder.append("</div>");
			}
			
			boolean lastCell = !children.hasNext() && i >= MIN_COUNT-1;
			
			if( i % 2 != 0 && i != 0 && !lastCell)
			{
//				builder.append("<br>");
			}
			
			i++;
		}
		
		int rowCount = paddedChildCount/2;
		m_height = (int) Math.ceil(((double)rowCount)*S_HomeCell.THUMB_ROW_HEIGHT);
		m_height = Math.max(m_height, S_HomeCell.DEFAULT_CELL_SIZE);
		
		if( rowCount == 10 )
		{
			m_height += 1; // wow, ghetto
		}
		
		builder.append("</tr></table></div>");
		
		m_sourceCode = builder.toString();
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
