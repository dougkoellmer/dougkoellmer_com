package com.dougkoellmer.shared.homecells;

import swarm.shared.structs.CellSize;


public class U_HomeCellSize
{
	private static CellSize calcThumbCellSize(E_HomeCell cell)
	{
		int paddedChildCount = cell.getPaddedChildCount();
		int rowCount = paddedChildCount/2;
		int height = (int) Math.ceil(((double)rowCount)*S_HomeCell.THUMB_ROW_HEIGHT);
		height = Math.max(height, S_HomeCell.DEFAULT_CELL_SIZE);
		
		if( rowCount == 10 )
		{
			height += 1; // wow, ghetto
		}

		return new CellSize(CellSize.DEFAULT_DIMENSION, height);
	}
	
	public static int getExtraHeight(E_HomeCell cell)
	{
		switch(cell)
		{
			case GUITAR_CASE:		return S_HomeCell.DEFAULT_CELL_SIZE+S_HomeCell.IMG_STRIP_SPACING;
		}
		
		return 0;
	}
	
	public static CellSize getFocusedCellSize(E_HomeCell cell)
	{
		CellSize cellSize = null;
		
		//--- DRK > Some manual overrides for autogenerated cell sizes in case
		//---		they don't end up working perfectly.
		switch(cell)
		{
			case BACKSCRATCHER_1:			cellSize = new CellSize(674, 1040);  break;
		}
		
		if( cellSize != null )
		{
			return cellSize;
		}
		
		cellSize = U_AutoGenHomeCellSize.getFocusedCellSize(cell);
		
		if( cellSize == null )
		{
			switch(cell)
			{
				case RESUME:					cellSize = new CellSize(882, 1358);  break;
				
				case PORTFOLIO:					cellSize = new CellSize(992, 2182);  break;
				
				case FEED_THE_BEAR:				cellSize = new CellSize(950, 650);  break;
				case PRESSURE:					cellSize = new CellSize(821, 612);  break;
				case PRESSURE_AND_HEAT:			cellSize = new CellSize(928, 755);  break;
				case RADIOACTIVE_DECAY:			cellSize = new CellSize(978, 615);  break;
				
				case PRECIOUSES: case ABILITIES: case FOR_COMPUTERS: case FOR_BIOTICS:
				case INVENTIONS: case SUNDRY: case WOOD: case ART:
												cellSize = calcThumbCellSize(cell);  break;
												
				case PLYWOOD_PUNISHER:			cellSize = new CellSize(910, 512);	break;
			}
		}
		else if( cellSize != null )
		{
			cellSize = new CellSize(cellSize.getWidth(), cellSize.getHeight() + getExtraHeight(cell));
		}
		
		if( cellSize == null )
		{
			if( isNaturalHeightCell(cell) )
			{
				cellSize = new CellSize(CellSize.DEFAULT_DIMENSION, CellSize.NATURAL_DIMENSION);
			}
			else
			{
				cellSize = new CellSize(CellSize.DEFAULT_DIMENSION, CellSize.DEFAULT_DIMENSION);
			}
		}
		
		if( cellSize.isPartiallyExplicit() )
		{
			cellSize.setToDefaultsIfMatches(S_HomeCell.DEFAULT_CELL_SIZE, S_HomeCell.DEFAULT_CELL_SIZE);
		}
		
		return cellSize;
	}
	
	public static boolean isLongForm(E_HomeCell cell)
	{
		return cell == E_HomeCell.POLISH_FOREST_ADVENTURE ||
				cell == E_HomeCell.SPANISH_OPERA_ADVENTURE ||
				cell == E_HomeCell.WHAT_IS_CORROSION ||
				cell == E_HomeCell.PERFECT_COFFEE;
	}
	
	public static boolean isNaturalHeightCell(E_HomeCell cell)
	{
		if( isListCell(cell) )  return true;
		
		if( isLongForm(cell) )  return true;
		
		return false;
	}
	
	public static boolean isListCell(E_HomeCell cell)
	{
		return
			cell == E_HomeCell.LIFE_HACKS ||
			cell == E_HomeCell.MUSINGS ||
			cell == E_HomeCell.GRAND_IDEAS;
	}
}
