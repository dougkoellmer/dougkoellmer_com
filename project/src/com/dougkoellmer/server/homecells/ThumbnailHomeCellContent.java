package com.dougkoellmer.server.homecells;

import java.util.Iterator;

import javax.servlet.ServletContext;

import swarm.server.thirdparty.servlet.smU_Servlet;

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
		m_content = "<div style='width:100%; height:100%; font-size:0px; padding-top:"+spacing+"px; overflow-y:scroll;'>";
		
		int i = 0, minCount = 16;
		Iterator<E_HomeCell> children = homeCell.getChildren();
		
		while( i < minCount && children.hasNext() )
		{
			if( children.hasNext() )
			{
				E_HomeCell child = children.next();
				HomeCellMetaData metaData = child.getMetaData();
				String description = metaData.getDescription();
				String address = child.getPrimaryAddress();
				String margin = ":"+spacing+"px;";
				String marginStyle = (i % 2 == 0 ? "margin-right" : "margin-left") + margin;
				String floatStyle = i % 2 == 0 ? "float:right;" : "";
				
				m_content += "<div class='dk_thumb_cell' style='"+floatStyle+marginStyle+" margin-bottom"+margin+"'>";
				m_content += 	"<a href='"+address+"' class='waypoint_cell_link'>";
				m_content += 		"<table><tr><td><img class='dk_thumb_cell_img' /></td><td style='text-align:right;'><div class='dk_thumb_desc'>";
				m_content += 		description;
				m_content += 		"</div></td></tr></table>";
				m_content += 	"</a>";
				m_content += "</div>";
			}
			else
			{
				m_content += "<div class='dk_thumb_cell_empty dk_cell_content_border_box'>";
				m_content += "</div>";
			}
			
			i++;
		}
		
		m_content += "</div>";
	}
	
	public String getContent()
	{
		return m_content;
	}
}
