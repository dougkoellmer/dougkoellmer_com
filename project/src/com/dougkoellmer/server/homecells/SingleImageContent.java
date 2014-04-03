package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

public class SingleImageContent implements I_HomeCellContent
{
	private static final String IMG_PATH = "/img/cell_content/";
	
	private String m_sourceCode;
	private final String m_cellName;
	private final E_Direction m_direction;
	private final String m_gravity;
	
	public SingleImageContent(E_HomeCell cell)
	{
		this(cell, E_Direction.HORIZONTAL, null);
	}
	
	public SingleImageContent(E_HomeCell cell, String gravity)
	{
		this(cell, E_Direction.HORIZONTAL, gravity);
	}
	
	public SingleImageContent(E_HomeCell cell, E_Direction direction)
	{
		this(cell, direction, null);
	}
	
	public SingleImageContent(E_HomeCell cell, E_Direction direction, String gravity)
	{
		m_cellName = cell.getCellName();
		m_direction = direction;
		m_gravity = gravity;
	}
	
	public void init(ServletContext servletContext, E_HomeCell homeCell)
	{
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
		if( m_gravity != null )
		{
			position = "background-position:"+m_gravity+";";
		}
		
		m_sourceCode = "<div style=\"background-repeat:no-repeat; width:100%; height:100%; "+heightWidth+" "+position+" background-image:url('"+IMG_PATH+m_cellName+".solo.jpg');\"></div>";
	}
	
	public String getSourceCode(E_CodeType eCodeType)
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
