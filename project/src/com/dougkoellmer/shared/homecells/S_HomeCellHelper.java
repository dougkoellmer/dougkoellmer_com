package com.dougkoellmer.shared.homecells;

import java.util.ArrayList;

class S_HomeCellHelper
{
	//--- DRK > For some reason it's a compiler error to have these as static members of E_HomeCell itself.
	static E_HomeCell s_previousCell;
	static ArrayList<E_HomeCell> s_cellStack = new ArrayList<E_HomeCell>();
}
