package com.dougkoellmer.client.managers;


import java.util.HashMap;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.client.app.AppContext;
import swarm.shared.structs.CellAddress;
import swarm.shared.structs.CellAddressMapping;
import swarm.shared.time.I_TimeSource;

public class CellAddressManager extends swarm.client.managers.CellAddressManager
{
	private final HashMap<String, Integer> m_mappings = new HashMap<String, Integer>();
	
	public CellAddressManager(AppContext context)
	{
		super(context, 0, 0, null);
		
		initMappings();
	}
	
	private void initMappings()
	{
		for( int i = 0; i < E_HomeCell.values().length; i++ )
		{
			E_HomeCell ithCell = E_HomeCell.values()[i];
			
			m_mappings.put(ithCell.getPrimaryAddress(), i);
		}
	}
	
	@Override
	protected CellAddressMapping getMappingFromLocalSource(CellAddress address)
	{
		Integer result = m_mappings.get(address.getRawAddress());
		if( result != null )
		{
			return E_HomeCell.values()[result].getMapping();
		}
		
		return super.getMappingFromLocalSource(address);
	}
}
