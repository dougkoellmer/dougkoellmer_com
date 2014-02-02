package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public interface I_HomeCellContent
{
	String getContent();
	
	void init(ServletContext servletContext, E_HomeCell homeCell);
}
