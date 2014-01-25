package com.dougkoellmer.client.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import swarm.client.app.smA_ClientApp;
import swarm.client.app.smClientAppConfig;
import swarm.client.app.smE_Platform;
import swarm.client.app.smE_StartUpStage;
import swarm.client.input.smClickManager;
import swarm.client.states.*;
import swarm.client.states.account.StateMachine_Account;
import swarm.client.states.account.State_AccountStatusPending;
import swarm.client.states.account.State_SignInOrUp;
import swarm.client.states.account.State_ManageAccount;
import swarm.client.states.camera.StateMachine_Camera;
import swarm.client.states.camera.State_CameraFloating;
import swarm.client.states.camera.State_CameraSnapping;
import swarm.client.states.camera.State_GettingMapping;
import swarm.client.states.camera.State_ViewingCell;
import swarm.client.states.code.StateMachine_EditingCode;
import swarm.client.states.code.State_EditingCode;
import swarm.client.states.code.State_EditingCodeBlocker;
import swarm.client.view.smE_ZIndex;
import swarm.client.view.smS_UI;
import swarm.client.view.smViewConfig;
import swarm.client.view.smViewController;
import swarm.client.view.tabs.smI_Tab;
import swarm.client.view.tabs.account.smAccountTab;
import swarm.client.view.tabs.code.smCodeEditorTab;
import swarm.client.view.tooltip.smE_ToolTipType;
import swarm.client.view.tooltip.smToolTipManager;
import swarm.shared.statemachine.smA_State;
import swarm.shared.statemachine.smI_StateEventListener;

import com.dougkoellmer.client.entities.ClientGrid;
import com.dougkoellmer.client.entities.ClientUser;
import com.dougkoellmer.client.view.ViewController;
import com.dougkoellmer.shared.app.S_App;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.logging.client.TextLogFormatter;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ClientApp extends smA_ClientApp implements EntryPoint
{
	private static final Logger s_logger = Logger.getLogger(ClientApp.class.getName());	
	
	public ClientApp()
	{
		super(makeAppConfig(), makeViewConfig());
	}
	
	private static smClientAppConfig makeAppConfig()
	{
		smClientAppConfig appConfig = new smClientAppConfig();
		
		int cacheSize = 256;
		double cacheExpiration = Double.MAX_VALUE;
		
		appConfig.minSnapTime	 = .5;
		appConfig.snapTimeRange = 1;
		appConfig.addressCacheSize = cacheSize;
		appConfig.addressCacheExpiration_seconds = cacheExpiration;
		appConfig.codeCacheSize = cacheSize;
		appConfig.codeCacheExpiration_seconds = cacheExpiration;
		appConfig.cellHudHeight = S_ClientApp.CELL_HUD_HEIGHT;
		appConfig.backOffDistance = S_ClientApp.VIEWING_CELL_CLOSE_BUTTON_DISTANCE_OFFSET;	
		appConfig.publicRecaptchaKey = "";
		appConfig.useVirtualSandbox = false;
		
		appConfig.appId = S_App.APP_ID;
		
		appConfig.user = new ClientUser();
		appConfig.grid = new ClientGrid();
		
		return appConfig;
	}
	
	private static smViewConfig makeViewConfig()
	{
		smViewConfig viewConfig = new smViewConfig();
		
		viewConfig.magFadeInTime_seconds = .5;
		viewConfig.magnifierTickCount = 7;
		viewConfig.defaultPageTitle = "Doug";
		viewConfig.cellHighlightColor = "rgb(145, 167, 223)";
		
		return viewConfig;
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		super.startUp(smE_StartUpStage.values()[0]);
	}
	
	@Override
	protected void stage_startViewManagers()
	{
		m_viewContext.clickMngr = new smClickManager();
		
		m_viewContext.toolTipMngr = new smToolTipManager(m_appContext.platformInfo.getPlatform() != smE_Platform.IOS, smS_UI.TOOL_TIP_DELAY);
		
		//--- DRK > Set defaults for tool tips.
		for( int i = smE_ZIndex.TOOL_TIP_1.ordinal(), j = 0; i <= smE_ZIndex.TOOL_TIP_5.ordinal(); i++, j++ )
		{
			m_viewContext.toolTipMngr.setDefaultZIndex(smE_ToolTipType.values()[j], i);
		}
		m_viewContext.toolTipMngr.setDefaultPadding(smS_UI.TOOl_TIP_PADDING);
		
		//TODO(DRK) Ugh, real hacky here.
		smI_Tab[] tabs = {new smCodeEditorTab(m_viewContext)};
		m_viewConfig.tabs = tabs;
	}
	
	@Override
	protected void stage_registerStateMachine(smI_StateEventListener stateEventListener_null, Class<? extends smA_State> consoleState_T_null)
	{
		ViewController viewController = new ViewController(m_viewContext, m_viewConfig, m_appConfig);
		
		super.stage_registerStateMachine(viewController, StateMachine_Tabs.class);
	
		registerAccountStates();
		registerCodeEditingStates();
		List<Class<? extends smA_State>> tabStates = new ArrayList<Class<? extends smA_State>>();
		//tabStates.add(StateMachine_Account.class);
		tabStates.add(StateMachine_EditingCode.class);
		m_stateContext.registerState(new StateMachine_Tabs(tabStates));
	}
	
	@Override
	protected void stage_gunshotSound()
	{
		super.stage_gunshotSound();
	}
}
