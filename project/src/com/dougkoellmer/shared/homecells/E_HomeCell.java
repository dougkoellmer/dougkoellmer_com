package com.dougkoellmer.shared.homecells;

import java.util.ArrayList;
import java.util.Iterator;









import com.dougkoellmer.server.entities.ServerGrid;

import swarm.shared.structs.CellAddress;
import swarm.shared.structs.CellAddressMapping;
import swarm.shared.structs.CellSize;
import swarm.shared.structs.GridCoordinate;

public enum E_HomeCell
{
	HOME				(16, 14),
	
	PRECIOUSES			(HOME, -1,	-1),
		SWORD			(-1,	-1),
		WOODEN_CUP		(0,		-1),
		HATCHET			(0,		-1),
		MITER_SAW		(1,		-1),
		CARVING_TOOLS	(1,		-1),
		VACUUM_SEALER	(1,		-1),
		LIGHTER			(1,		0),
		
	ABILITIES				(HOME, 	1,	-1),
		FOOT_JUGGLING			(0,		-1),
		JUGGLING				(0,		-1),
		FAST_SHUFFLE			(1,		0),
		CARD_DECK_SPLIT			(0,		-1),
		CARD_THROW				(1,		0),
		PEN_TWIRL				(1,		-1),
		BOTTLE_OPENING			(1,		0),
		FAST_SHOE_TIE			(1,		0),
		ONE_HAND_MATCH_LIGHT	(1,		0),
		FAST_SHIRT_SWAP			(1,		1),
		
	SOFTWARE				(HOME,	1,	1),
		FOR_COMPUTERS		(SOFTWARE,	1,	0),
			PRESSURE				(1,		-1),
			PRESSURE_AND_HEAT		(1,		-1),
			RADIOACTIVE_DECAY		(1,		0),
			CORROSION_CELL			(1,		0),
			FEED_THE_BEAR			(1,		0),
			SWARM					(1,		0),
			B33HIVE					(1,		1),
			QUICKPHYX				(1,		0),
			CORROSION_GAME			(0,		1),
			GLUE					(1,		0),
			WHIP_THE_RAGDOLL		(0,		1),
			TOUCH_TONE_HERO			(1,		0),
			BALANCE_THING_GAME		(0,		1),
			FIREWORKS				(0,		1),
			GRAVITATION				(0,		1),
			
		FOR_BIOTICS			(SOFTWARE, 1, 1),
			RESUME						(1,		0),
			PORTFOLIO					(1,		0),
			POLISH_FOREST_ADVENTURE		(1,		0),
			SPANISH_OPERA_ADVENTURE		(1,		1),
			OLD_FRIEND					(1,		1),
			GRAND_IDEAS					(1,		1),
			MUSINGS						(0,		1),
			WHAT_IS_CORROSION			(0,		1),
			PERFECT_COFFEE				(0,		1),
	
	CREATIONS		(HOME,	-1,	1),
		USEFUL			(CREATIONS, -1,		0),
			INVENTIONS	(USEFUL, -1, -1),
				GUITAR_CASE				(-1,	-1),
				SLING_SHOT				(-1,	0),
				OUTDOOR_TOOL_HOLDER		(-1,	-1),
				DOWEL_HOLDER			(-1,	-1),
				HANGING_CRATES			(0,		-1),
				SANDER_HOLDER			(0,		-1),
				BREAKAWAY_KNOT			(1,		-1),
				TORSION_KNOT			(0,		-1),
				RASP_HANDLE				(1,		-1),
				FLASHLIGHT_LAMP			(1,		0),
				LIFE_HACKS				(1,		-1),
				BIKE_CARD_THING			(1,		0),
			
			SUNDRY	(USEFUL, -1, 0),
				TERMINATOR_GLASSES	(-1,	0),
				CANVAS_BAG			(-1,	1),
				CANVAS_WRAP			(-1,	0),
				SANTOKU_SHEATH		(-1,	0),
				F1_SHEATH_MOD		(-1,	0),
				SAW_SHEATH			(-1,	-1),
				BROOM				(-1,	0),
				FIRE_POI			(-1,	-1),
				JUGGLING_TORCHES	(0,		-1),
				THROWING_KNIVES		(0,		-1),
				STONE_CUP			(0,		-1),
				FIRE_POKER			(1,		-1),
				
			WOOD	(USEFUL, -1, 1),
				BOOMERANG				(0,		1),
				BIKE_RACK				(-1,	1),
				CUTTING_BOARD_STAND		(0,		1),
				OAR						(-1,	0),
				KITCHEN_UTENSILS		(0,		1),
				SHAKERS					(-1,	0),
				THROWING_KNIFE_TARGET	(0,		1),
				BACKSCRATCHER_1			(-1,	0),
				BACKSCRATCHER_2			(-1,	0),
				WOOD_MALLET				(0,		1),
				CUTTING_BOARD			(-1,	0),
				BOW_1					(-1,	0),
				BOW_2					(-1,	0),
				DOORSTOP				(0,		-1),
				DOGGIE_RAMP				(-1,	0),
				COASTERS				(-1,	0),
				TILLER_TREE				(0,		-1),
				COASTER_HOLDER			(0,		-1),
				RED_OAK_SPOON			(-1,	-1),
				PLYWOOD_PUNISHER		(0,		-1),
				
		ART		(CREATIONS, 0, 1),
			ROSE						(0,		1),
			LADY						(1,		1),
			SOLAR_SYSTEM				(1,		0),
			BANANA						(0,		1),
			WOOD_CHAIN					(1,		0),
			DRAGON						(0,		1),
			BLACKHOLE					(0,		1),
			INFINITE_TOLERANCE			(0,		1),
			SAVE_ANIMALS				(-1,	0),
			DINOSAURS					(0,		1),
			QUICKB2_LOGO				(-1,	1),
			B33HIVE_LOGO				(-1,	1),
			MILKMAN						(-1,	0),
			MOTHER_AND_CHILD			(-1,	1),
			PEACEFUL_MAN				(-1,	0),
			GLASSES_HOLDER				(-1,	0);
	
	
	private final CellAddressMapping m_mapping;
	private final CellAddress m_primaryAddress;
	private final CellAddress m_secondaryAddress;
	private final ArrayList<E_HomeCell> m_children = new ArrayList<E_HomeCell>();
	private final E_HomeCell m_parent;
	
	private E_HomeCell(int offsetM, int offsetN)
	{
		this(null, offsetM, offsetN);
	}
	
	private E_HomeCell(E_HomeCell relativeCell_nullable, int offsetM, int offsetN)
	{
		boolean isRootCell = false;
		String cellName = this.getCellName();
		
		if( this.ordinal() == 0 )
		{
			m_mapping = new CellAddressMapping(offsetM, offsetN);
			m_primaryAddress = new CellAddress(cellName);
			m_secondaryAddress = null;
			m_parent = null;
		}
		else
		{
			ArrayList<E_HomeCell> stack = S_HomeCellHelper.s_cellStack;
			
			if( relativeCell_nullable == null )
			{
				relativeCell_nullable = S_HomeCellHelper.s_previousCell;
				m_secondaryAddress = new CellAddress(cellName);
			}
			else
			{
				isRootCell = true;
				m_secondaryAddress = null;
				
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
			
			m_parent = stack.size() > 0 ? stack.get(stack.size()-1) : null;
			
			if( m_parent != null )
			{
				m_parent.m_children.add(this);
			}

			m_primaryAddress = new CellAddress(getBaseAddress() + cellName);			
			m_mapping = new CellAddressMapping(relativeCell_nullable.m() + offsetM, relativeCell_nullable.n() + offsetN);
		}
		
		S_HomeCellHelper.s_previousCell = this;
		
		if( isRootCell )
		{
			S_HomeCellHelper.s_cellStack.add(this);
		}
	}
	
	public E_HomeCell getParent()
	{
		return m_parent;
	}
	
	public int getPaddedChildCount()
	{
		return Math.max(S_HomeCell.MIN_THUMBNAIL_COUNT, this.getChildCount());
	}
	
	public Iterator<E_HomeCell> getChildren()
	{
		return m_children.iterator();
	}
	
	public int getChildCount()
	{
		return m_children.size();
	}
	
	public String getCellName()
	{
		return this.name().toLowerCase();
	}
	
	private static String getBaseAddress()
	{
		String address = "";
		
		for( int i = 0; i < S_HomeCellHelper.s_cellStack.size(); i++ )
		{
			address += S_HomeCellHelper.s_cellStack.get(i).getCellName() + "/";
		}
		
		return address;
	}
	
	public GridCoordinate getCoordinate()
	{
		return m_mapping.getCoordinate();
	}
	
	public CellAddressMapping getMapping()
	{
		return m_mapping;
	}
	
	public CellAddress getPrimaryAddress()
	{
		return m_primaryAddress;
	}
	
	public CellAddress getSecondaryAddress()
	{
		return m_secondaryAddress;
	}
	
	private int m()
	{
		return getCoordinate().getM();
	}
	
	private int n()
	{
		return getCoordinate().getN();
	}
}
