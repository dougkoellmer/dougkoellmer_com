package com.dougkoellmer.server.homecells;

public class HomeCellMetaData
{
	private final String m_description;
	private final I_HomeCellContent m_content;
	
	public HomeCellMetaData(String description, I_HomeCellContent content)
	{
		m_content = content;
		m_description = description;
	}
	
	public String getDescription()
	{
		return m_description;
	}
	
	public I_HomeCellContent getContent()
	{
		return m_content;
	}
}
