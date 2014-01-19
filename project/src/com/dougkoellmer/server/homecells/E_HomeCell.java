package com.dougkoellmer.server.homecells;

import swarm.shared.structs.smGridCoordinate;

public enum E_HomeCell
{
	HOME			(18, 14, new FileBasedHomeCellContent("home")),
	
	TREASURES		(HOME, -1,	-1,	new ThumbnailHomeCellContent()),
		SWORD			(-1,	-1, 	new ImageHomeCellContent()),
		WOODEN_CUP		(0,		-1, 	new ImageHomeCellContent()),
		HATCHET			(0,		-1, 	new ImageHomeCellContent()),
		MITER_SAW		(1,		-1, 	new ImageHomeCellContent()),
		CARVING_TOOLS	(1,		0,	 	new ImageHomeCellContent()),
		VACUUM_SEALER	(1,		0, 		new ImageHomeCellContent()),
		LIGHTER			(1,		0, 		new ImageHomeCellContent());
	
	private final smGridCoordinate m_coordinate;
	private final String m_address;
	private final I_HomeCellContent m_content;
	
	private E_HomeCell(int offsetM, int offsetN, I_HomeCellContent content)
	{
		this(null, offsetM, offsetN, null, content);
	}
	
	private E_HomeCell(int offsetM, int offsetN, String address_nullable, I_HomeCellContent content)
	{
		this(null, offsetM, offsetN, address_nullable, content);
	}
	
	private E_HomeCell(E_HomeCell relativeCell_nullable, int offsetM, int offsetN, I_HomeCellContent content)
	{
		this(relativeCell_nullable, offsetM, offsetN, null, content);
	}
	
	private E_HomeCell(E_HomeCell relativeCell_nullable, int offsetM, int offsetN, String address_nullable, I_HomeCellContent content)
	{
		boolean isRootCell = false;
		
		if( this.ordinal() == 0 )
		{
			m_coordinate = new smGridCoordinate(offsetM, offsetN);
			m_address = this.name().toLowerCase();
		}
		else
		{
			isRootCell = relativeCell_nullable != null;
			
			if( relativeCell_nullable == null )
			{
				relativeCell_nullable = HomeCellCreator.s_previousCell;
			}
			
			if( address_nullable == null )
			{
				if( HomeCellCreator.s_currentRootCell == null )
				{
					m_address = this.name().toLowerCase();
				}
				else
				{
					m_address = HomeCellCreator.s_currentRootCell.getAddress() + "/" + this.name().toLowerCase();
				}
			}
			else
			{
				m_address = address_nullable;
			}
			
			m_coordinate = new smGridCoordinate(relativeCell_nullable.m() + offsetM, relativeCell_nullable.n() + offsetN);
		}
		
		HomeCellCreator.s_previousCell = this;
		m_content = content;
		
		if( isRootCell ) HomeCellCreator.s_currentRootCell = this;
	}
	
	public smGridCoordinate getCoordinate()
	{
		return m_coordinate;
	}
	
	public I_HomeCellContent getContent()
	{
		return m_content;
	}
	
	public String getAddress()
	{
		return m_address;
	}
	
	private int m()
	{
		return m_coordinate.getM();
	}
	
	private int n()
	{
		return m_coordinate.getN();
	}
}
