package com.dougkoellmer.server.homecells;

import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import com.dougkoellmer.server.entities.ServerGrid;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class ThumbnailHomeCellContent implements I_HomeCellContent
{
	private static final Logger s_logger = Logger.getLogger(ThumbnailHomeCellContent.class.getName());
	
	static final int MIN_COUNT = 12;
	
	private String m_sourcCode;
	private int m_height = 0;
	
	public ThumbnailHomeCellContent()
	{
	}
	
	private boolean isListCell(E_HomeCell cell)
	{
		return cell == E_HomeCell.LIFE_HACKS;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		String trClass = "class='dk_thumb_row'";
		StringBuilder builder = new StringBuilder();
		builder.append("<div style='width:100%; height:100%; font-size:0px; overflow:hidden;'>");
		builder.append("<table style='' class='dk_thumb_table'><tr "+trClass+">");
		
		int i = 0;
		Iterator<E_HomeCell> children = homeCell.getChildren();
		int paddedChildCount = homeCell.getPaddedChildCount();
		
		while( i < MIN_COUNT || (children.hasNext() || !children.hasNext() && i % 2 != 0) )
		{
			boolean lastRow = i >= paddedChildCount-2;
			boolean isLeft = i%2==0;
			String verClass, horClass;
			if( i < 2 )
			{
				verClass = "dk_thumb_cell_with_border_bottom";
			}
			else if( lastRow )
			{
				verClass = "dk_thumb_cell_with_border_top";
			}
			else
			{
				verClass = "dk_thumb_cell_with_border_bottom dk_thumb_cell_with_border_top";
			}
			
			horClass = isLeft ? "dk_thumb_cell_with_border_right" : "dk_thumb_cell_with_border_left";
			
			String tdClass = "class='dk_thumb_cell "+verClass + " " + horClass + "'";
			
			//--- DRK > WOAH! mega-hack!!!
			String tdStyle = (paddedChildCount == MIN_COUNT || paddedChildCount == MIN_COUNT+2 ) && lastRow ? "style='height:82px;'" : "";
			
			if( children.hasNext() )
			{
				E_HomeCell child = children.next();
				String description = U_HomeCellMeta.getDescription(child);
				String address = child.getPrimaryAddress().getRaw();
				
				String thumb = "/img/cell_content/thumbs/"+child.getCellName()+".thumb.jpg";
				
				if( isListCell(child) )
				{
					thumb = "/img/cell_content/thumbs/list_content.thumb.jpg";
				}
				else if( !U_Servlet.fileExists(servletContext, thumb) )
				{
					thumb = "/img/coming_soon.thumb.png";
				}
				
				thumb = U_HomeCell.getImgPath(thumb);
				
				builder.append("<td "+tdClass+" " + tdStyle + ">");
				builder.append("<a href='"+address+"' class='waypoint_cell_link'>");
				builder.append("<table style='width:100%; height:100%;' class='waypoint_no_table_fluff'><tr><td style='vertical-align:middle;'><img class='dk_thumb_cell_img' src='"+thumb+"'></td><td style='text-align:right;'><div class='dk_thumb_desc'>");
				builder.append(description);
				builder.append("</div></td></tr></table>");
				builder.append("</a>");
				builder.append("</td>");
			}
			else
			{
				builder.append("<td "+tdClass+" " + tdStyle + ">");
				builder.append("<div style='width:100%; height:100%;'></div>");
				builder.append("</td>");
			}
			
			boolean lastCell = !children.hasNext() && i >= MIN_COUNT-1;
			
			if( i % 2 != 0 && i != 0 && !lastCell)
			{
				builder.append("</tr><tr "+trClass+">");
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
		
		m_sourcCode = builder.toString();
	}
	
	public String getCode(E_CodeType eCodeType)
	{
		if( eCodeType == E_CodeType.SOURCE )
		{
			return m_sourcCode;
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
			return E_CodeSafetyLevel.VIRTUAL_STATIC_SANDBOX;
		}
		else
		{
			return null;
		}
	}
}
