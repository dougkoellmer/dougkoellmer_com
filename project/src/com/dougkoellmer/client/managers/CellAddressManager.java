package com.dougkoellmer.client.managers;


import java.util.HashMap;

import com.dougkoellmer.shared.homecells.E_HomeCell;

import swarm.client.app.AppContext;
import swarm.shared.lang.Boolean;
import swarm.shared.structs.CellAddress;
import swarm.shared.structs.CellAddressMapping;
import swarm.shared.time.I_TimeSource;

public class CellAddressManager extends swarm.client.managers.CellAddressManager
{
	private final HashMap<String, Integer> m_addyToCell = new HashMap<String, Integer>();
	private final HashMap<CellAddressMapping, Integer> m_mappingToCell = new HashMap<CellAddressMapping, Integer>();
	
	
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
			
			m_addyToCell.put(ithCell.getPrimaryAddress().getRaw(), i);
			m_mappingToCell.put(ithCell.getMapping(), i);
		}
	}
	
	@Override
	protected CellAddress getAddressFromLocalSource(CellAddressMapping mapping, Boolean updateBuffer_out)
	{
		updateBuffer_out.value = true;
		
		Integer result = m_mappingToCell.get(mapping);
		if( result != null )
		{
			return E_HomeCell.values()[result].getPrimaryAddress();
		}
		
		return super.getAddressFromLocalSource(mapping, updateBuffer_out);
	}
	
	@Override
	protected CellAddressMapping getMappingFromLocalSource(CellAddress address)
	{
		Integer result = m_addyToCell.get(address.getRaw());
		if( result != null )
		{
			return E_HomeCell.values()[result].getMapping();
		}
		
		return super.getMappingFromLocalSource(address);
	}
}
