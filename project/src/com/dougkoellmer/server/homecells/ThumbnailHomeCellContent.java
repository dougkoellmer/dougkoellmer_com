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
		int spacing = 10;
		m_content = "<div style='width:100%; height:100%; font-size:0px; overflow-y:scroll;'>";
		m_content += "<table style='' class='dk_thumb_table'>";
		m_content += "<tr>";
		
		int i = 0, minCount = 12;
		Iterator<E_HomeCell> children = homeCell.getChildren();
		
		while( i < minCount || (children.hasNext() || !children.hasNext() && i % 2 != 0) )
		{				
			if( children.hasNext() )
			{
				E_HomeCell child = children.next();
				String description = U_HomeCellMeta.getDescription(child);
				String address = child.getPrimaryAddress();
				
				m_content += "<td class='dk_thumb_cell' style=''>";
				m_content += 	"<a href='"+address+"' class='waypoint_cell_link'>";
				m_content += 		"<table style='width:100%; height:100%;' class='waypoint_no_table_fluff'><tr><td style='vertical-align:middle;'><img class='dk_thumb_cell_img' /></td><td style='text-align:right;'><div class='dk_thumb_desc'>";
				m_content += 		description;
				m_content += 		"</div></td></tr></table>";
				m_content += 	"</a>";
				m_content += "</td>";
			}
			else
			{
				m_content += "<td class='dk_thumb_cell_empty dk_cell_content_border_box'>";
				m_content += "</td>";
			}
			
			boolean lastCell = !children.hasNext() && i >= minCount-1;
			
			if( i % 2 != 0 && i != 0 && !lastCell)  m_content += "</tr><tr>";
			
			i++;
		}
		
		m_content += "</tr></table></div>";
	}
	
	public String getContent()
	{
		return m_content;
	}
}
