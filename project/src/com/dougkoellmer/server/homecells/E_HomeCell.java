package com.dougkoellmer.server.homecells;

import java.util.ArrayList;

import swarm.shared.structs.smGridCoordinate;

public enum E_HomeCell
{
	HOME			(18, 14, new FileBasedHomeCellContent("home")),
	
	PRECIOUSES			(HOME, -1,	-1,	new ThumbnailHomeCellContent()),
		SWORD			(-1,	-1, 	new ImageHomeCellContent()),
		WOODEN_CUP		(-1,	-1, 	new ImageHomeCellContent()),
		HATCHET			(0,		-1, 	new ImageHomeCellContent()),
		MITER_SAW		(1,		-1, 	new ImageHomeCellContent()),
		CARVING_TOOLS	(1,		-1,	 	new ImageHomeCellContent()),
		VACUUM_SEALER	(1,		0, 		new ImageHomeCellContent()),
		LIGHTER			(1,		0, 		new ImageHomeCellContent()),
		
	ABILITIES				(HOME, 	1,	-1,			new ThumbnailHomeCellContent()),
		FOOT_JUGGLING			(0,		-1, 	new ImageHomeCellContent()),
		JUGGLING				(0,		-1, 	new ImageHomeCellContent()),
		FAST_SHUFFLE			(1,		0, 		new ImageHomeCellContent()),
		CARD_DECK_SPLIT			(0,		-1, 	new ImageHomeCellContent()),
		CARD_THROW				(1,		0, 		new ImageHomeCellContent()),
		PEN_TWIRL				(1,		-1, 	new ImageHomeCellContent()),
		BOTTLE_OPENING			(1,		0, 		new ImageHomeCellContent()),
		FAST_SHOE_TIE			(1,		0, 		new ImageHomeCellContent()),
		ONE_HAND_MATCH_LIGHT	(1,		0, 		new ImageHomeCellContent()),
		FAST_SHIRT_SWAP			(1,		1, 		new ImageHomeCellContent()),
		
	SOFTWARE				(HOME,	1,	1,				new FileBasedHomeCellContent("code")),
		FOR_COMPUTERS		(SOFTWARE,	1,	0,				new ThumbnailHomeCellContent()),
			PRESSURE				(1,		0, 		new ImageHomeCellContent()),
			PRESSURE_AND_HEAT		(1,		-1, 	new ImageHomeCellContent()),
			RADIOACTIVE_DECAY		(1,		0, 		new ImageHomeCellContent()),
			CORROSION_CELL			(1,		0, 		new ImageHomeCellContent()),
			FEED_THE_BEAR			(1,		-1, 	new ImageHomeCellContent()),
			SWARM					(1,		0, 		new ImageHomeCellContent()),
			B33HIVE					(1,		1, 		new ImageHomeCellContent()),
			QUICKPHYX				(1,		0, 		new ImageHomeCellContent()),
			CORROSION_GAME			(0,		1, 		new ImageHomeCellContent()),
			GLUE					(1,		0, 		new ImageHomeCellContent()),
			WHIP_THE_RAGDOLL		(0,		1, 		new ImageHomeCellContent()),
			TOUCH_TONE_HERO			(1,		0, 		new ImageHomeCellContent()),
			BALANCE_THING_GAME		(0,		1, 		new ImageHomeCellContent()),
			FIREWORKS				(0,		1, 		new ImageHomeCellContent()),
			GRAVITATION				(0,		1, 		new ImageHomeCellContent()),
			
		FOR_BIOTICS			(SOFTWARE, 1, 1,		new ThumbnailHomeCellContent()),
			POLISH_FOREST_ADVENTURE		(1,		0, 	new ImageHomeCellContent()),
			SPANISH_OPERA_ADVENTURE		(1,		0, 	new ImageHomeCellContent()),
			OLD_FRIEND					(1,		1, 	new ImageHomeCellContent()),
			MUSINGS						(1,		1, 	new ImageHomeCellContent()),
			WHAT_IS_CORROSION			(0,		1, 	new ImageHomeCellContent()),
	
	CREATIONS		(HOME,	-1,	1,			new FileBasedHomeCellContent("hardware")),
		USEFUL			(CREATIONS, -1,		0, 		new FileBasedHomeCellContent("useful")),
			INVENTIONS	(USEFUL, -1, -1,		new ThumbnailHomeCellContent()),
				BIKE_CARD_THING			(-1,	-1, 	new ImageHomeCellContent()),
				DOWEL_HOLDER			(-1,	0, 		new ImageHomeCellContent()),
				OUTDOOR_TOOL_HOLDER		(0,		-1, 	new ImageHomeCellContent()),
				GUITAR_CASE				(-1,	0, 		new ImageHomeCellContent()),
				FLASHLIGHT_LAMP			(0,		-1, 	new ImageHomeCellContent()),
				SLING_SHOT				(0,		-1, 	new ImageHomeCellContent()),
				BREAKAWAY_KNOT			(0,		-1, 	new ImageHomeCellContent()),
				SANDER_HOLDER			(1,		0, 		new ImageHomeCellContent()),
				RASP_HANDLE				(0,		-1, 	new ImageHomeCellContent()),
				HANGING_CRATES			(1,		-1, 	new ImageHomeCellContent()),
				HEATING_BAG				(1,		-1, 	new ImageHomeCellContent()),
				SAND_WEIGHT				(1,		0, 		new ImageHomeCellContent()),
				SHIRT_PILLOW_CASE		(1,		-1, 	new ImageHomeCellContent()),
				TORSION_KNOT			(1,		0,		new ImageHomeCellContent()),
			
			MISC	(USEFUL, -1, 0, new ThumbnailHomeCellContent()),
				TERMINATOR_GLASSES	(-1,	0,		new ImageHomeCellContent()),
				CANVAS_BAG			(-1,	1, 		new ImageHomeCellContent()),
				CANVAS_WRAP			(-1,	0, 		new ImageHomeCellContent()),
				SANTOKU_SHEATH		(-1,	0, 		new ImageHomeCellContent()),
				F1_SHEATH_MOD		(-1,	0, 		new ImageHomeCellContent()),
				SAW_SHEATH			(-1,	-1, 	new ImageHomeCellContent()),
				BROOM				(-1,	0, 		new ImageHomeCellContent()),
				POI					(-1,	-1, 	new ImageHomeCellContent()),
				JUGGLING_TORCHES	(0,		-1, 	new ImageHomeCellContent()),
				THROWING_KNIVES		(0,		-1, 	new ImageHomeCellContent()),
				STONE_CUP			(0,		-1, 	new ImageHomeCellContent()),
				FIRE_POKER			(1,		-1, 	new ImageHomeCellContent()),
				
			WOOD	(USEFUL, -1, 1, new ThumbnailHomeCellContent()),
				TILLER_TREE				(0,		1, 		new ImageHomeCellContent()),
				THROWING_KNIFE_TARGET	(-1,	1, 		new ImageHomeCellContent()),
				WOOD_HAMMER				(0,		1, 		new ImageHomeCellContent()),
				COASTERS				(-1,	0, 		new ImageHomeCellContent()),
				KITCHEN_UTENSILS		(0,		1, 		new ImageHomeCellContent()),
				SHAKERS					(-1,	0, 		new ImageHomeCellContent()),
				BIKE_RACK				(0,		1, 		new ImageHomeCellContent()),
				BACKSCRATCHER_1			(-1,	0, 		new ImageHomeCellContent()),
				BACKSCRATCHER_2			(-1,	0, 		new ImageHomeCellContent()),
				CUTTING_BOARD_STAND		(0,		1, 		new ImageHomeCellContent()),
				CUTTING_BOARD			(-1,	0, 		new ImageHomeCellContent()),
				BOW_1					(-1,	0, 		new ImageHomeCellContent()),
				BOW_2					(-1,	0, 		new ImageHomeCellContent()),
				DOORSTOP				(0,		-1, 	new ImageHomeCellContent()),
				DOGGIE_RAMP				(-1,	0, 		new ImageHomeCellContent()),
				OAR						(-1,	0, 		new ImageHomeCellContent()),
				BOOMERANG				(0,		-1, 	new ImageHomeCellContent()),
				COASTER_HOLDER			(0,		-1, 	new ImageHomeCellContent()),
				RED_OAK_SPOON			(-1,	-1, 	new ImageHomeCellContent()),
				PLYWOOD_PUNISHER		(0,		-1, 	new ImageHomeCellContent()),
				
		ART		(CREATIONS, 0, 1,		new ThumbnailHomeCellContent()),
			DRAGON						(0,		1, 		new ImageHomeCellContent()),
			ROSE						(1,		1, 		new ImageHomeCellContent()),
			SOLAR_SYSTEM				(1,		0, 		new ImageHomeCellContent()),
			BANANA						(0,		1, 		new ImageHomeCellContent()),
			PEACEFUL_MAN				(1,		0, 		new ImageHomeCellContent()),
			LADY						(0,		1, 		new ImageHomeCellContent()),
			BLACKHOLE					(0,		1, 		new ImageHomeCellContent()),
			INFINITE_TOLERANCE			(0,		1, 		new ImageHomeCellContent()),
			SAVE_ANIMALS				(-1,	0, 		new ImageHomeCellContent()),
			DINOSAURS					(0,		1, 		new ImageHomeCellContent()),
			QUICKB2_LOGO				(-1,	1, 		new ImageHomeCellContent()),
			B33HIVE_LOGO				(-1,	1, 		new ImageHomeCellContent()),
			MILKMAN						(-1,	0, 		new ImageHomeCellContent()),
			MOTHER_AND_CHILD			(-1,	1, 		new ImageHomeCellContent()),
			WOOD_CHAIN					(-1,	0, 		new ImageHomeCellContent()),
			GLASSES_HOLDER				(-1,	0, 		new ImageHomeCellContent());
	
	
	private final smGridCoordinate m_coordinate;
	private final String m_primaryAddress;
	private final String m_secondaryAddress;
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
		String cellName = this.getCellName();
		
		if( this.ordinal() == 0 )
		{
			m_coordinate = new smGridCoordinate(offsetM, offsetN);
			m_primaryAddress = cellName;
			m_secondaryAddress = null;
		}
		else
		{
			if( relativeCell_nullable == null )
			{
				relativeCell_nullable = HomeCellCreator.s_previousCell;
				m_secondaryAddress = cellName;
			}
			else
			{
				isRootCell = true;
				m_secondaryAddress = null;
				
				ArrayList<E_HomeCell> stack = HomeCellCreator.s_cellStack;
				if( stack.size() > 0 )
				{
					E_HomeCell top = stack.get(stack.size()-1);
					
					while( relativeCell_nullable != top && top != null  )
					{
						stack.remove(stack.size()-1);
						top = stack.size() > 0 ? stack.get(stack.size()-1) : null;
					}
				}
			}
			
			if( address_nullable == null )
			{
				m_primaryAddress = getBaseAddress() + cellName;
			}
			else
			{
				m_primaryAddress = address_nullable;
			}
			
			m_coordinate = new smGridCoordinate(relativeCell_nullable.m() + offsetM, relativeCell_nullable.n() + offsetN);
		}
		
		HomeCellCreator.s_previousCell = this;
		m_content = content;
		
		if( isRootCell )
		{
			HomeCellCreator.s_cellStack.add(this);
		}
	}
	
	private String getCellName()
	{
		return this.name().toLowerCase();
	}
	
	private static String getBaseAddress()
	{
		String address = "";
		
		for( int i = 0; i < HomeCellCreator.s_cellStack.size(); i++ )
		{
			address += HomeCellCreator.s_cellStack.get(i).getCellName() + "/";
		}
		
		return address;
	}
	
	public smGridCoordinate getCoordinate()
	{
		return m_coordinate;
	}
	
	public I_HomeCellContent getContent()
	{
		return m_content;
	}
	
	public String getPrimaryAddress()
	{
		return m_primaryAddress;
	}
	
	public String getSecondaryAddress()
	{
		return m_secondaryAddress;
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
