package com.dougkoellmer.server.homecells;

import java.util.Iterator;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;

public class ThumbnailHomeCellContent implements I_HomeCellContent
{
	//private static final String;
	
	private String m_content;
	
	public ThumbnailHomeCellContent()
	{
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<div style='width:100%; height:100%; font-size:0px; overflow-y:scroll;'>");
		builder.append("<table style='' class='dk_thumb_table'><tr>");
		
		int i = 0, minCount = 12;
		Iterator<E_HomeCell> children = homeCell.getChildren();
		
		while( i < minCount || (children.hasNext() || !children.hasNext() && i % 2 != 0) )
		{				
			if( children.hasNext() )
			{
				E_HomeCell child = children.next();
				String description = U_HomeCellMeta.getDescription(child);
				String address = child.getPrimaryAddress();
				
				builder.append("<td class='dk_thumb_cell' style=''>");
				builder.append("<a href='"+address+"' class='waypoint_cell_link'>");
				builder.append("<table style='width:100%; height:100%;' class='waypoint_no_table_fluff'><tr><td style='vertical-align:middle;'><img class='dk_thumb_cell_img' src=/img/coming_soon.thumb.png/></td><td style='text-align:right;'><div class='dk_thumb_desc'>");
				builder.append(description);
				builder.append("</div></td></tr></table>");
				builder.append("</a>");
				builder.append("</td>");
			}
			else
			{
				builder.append("<td class='dk_thumb_cell_empty dk_cell_content_border_box'>");
				builder.append("</td>");
			}
			
			boolean lastCell = !children.hasNext() && i >= minCount-1;
			
			if( i % 2 != 0 && i != 0 && !lastCell)  builder.append("</tr><tr>");
			
			i++;
		}
		
		builder.append("</tr></table></div>");
		
		m_content = builder.toString();
	}
	
	public String getContent()
	{
		return m_content;
	}
}
