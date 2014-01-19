package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

public interface I_HomeCellContent
{
	String getContent();
	
	void init(ServletContext servletContext);
}
