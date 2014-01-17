package com.dougkoellmer.client.view;

import swarm.client.app.smClientAppConfig;
import swarm.client.states.Action_Base_HideSupplementState;
import swarm.client.view.smViewConfig;
import swarm.client.view.smViewController;
import swarm.client.view.smViewContext;
import swarm.client.view.cell.smVisualCellContainer;
import swarm.client.view.cell.smVisualCellHud;
import swarm.shared.statemachine.smA_Action;

public class ViewController extends smViewController
{
	public ViewController(smViewContext viewContext, smViewConfig config, smClientAppConfig appConfig)
	{
		super(viewContext, config, appConfig);
	}

	@Override
	protected void startUpCoreUI()
	{
		m_viewContext.stateContext.performAction(Action_Base_HideSupplementState.class);
		
		super.startUpCoreUI();
		
		smVisualCellContainer cellContainer = m_viewContext.splitPanel.getCellContainer();
		smVisualCellHud cellHud = new smVisualCellHud(m_viewContext, m_appConfig);
		this.addStateListener(cellHud);
		
		cellContainer.add(cellHud);
		
		cellContainer.getCellContainerInner().getElement().getStyle().setBackgroundColor("#9DA1CA");
	}
}