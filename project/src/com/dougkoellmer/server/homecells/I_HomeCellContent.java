package com.dougkoellmer.server.homecells;

import javax.servlet.ServletContext;

import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;

import com.dougkoellmer.shared.homecells.E_HomeCell;

public interface I_HomeCellContent
{
	String getSourceCode(E_CodeType eCodeType);
	
	CellSize getFocusedCellSize();
	
	E_CodeSafetyLevel getSafetyLevel(E_CodeType eCodeType);
	
	void init(ServletContext servletContext, E_HomeCell homeCell);
}
