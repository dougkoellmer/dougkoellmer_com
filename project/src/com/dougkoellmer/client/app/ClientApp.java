package com.dougkoellmer.client.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import swarm.client.app.A_ClientApp;
import swarm.client.app.ClientAppConfig;
import swarm.client.app.E_Platform;
import swarm.client.app.E_StartUpStage;
import swarm.client.input.ClickManager;
import swarm.client.js.JsConfig;
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
import swarm.client.transaction.E_ResponseErrorControl;
import swarm.client.transaction.E_ResponseSuccessControl;
import swarm.client.transaction.E_TransactionAction;
import swarm.client.transaction.I_AsyncRequestDispatcher;
import swarm.client.transaction.I_TransactionResponseHandler;
import swarm.client.view.E_ZIndex;
import swarm.client.view.S_UI;
import swarm.client.view.ViewConfig;
import swarm.client.view.ViewController;
import swarm.client.view.cell.GifSpinner;
import swarm.client.view.cell.I_CellSpinner;
import swarm.client.view.cell.I_CellSpinnerFactory;
import swarm.client.view.cell.SpritePlateSpinner;
import swarm.client.view.tabs.I_Tab;
import swarm.client.view.tabs.account.AccountTab;
import swarm.client.view.tabs.code.CodeEditorTab;
import swarm.client.view.tooltip.E_ToolTipType;
import swarm.client.view.tooltip.ToolTipManager;
import swarm.shared.entities.A_Grid;
import swarm.shared.statemachine.A_State;
import swarm.shared.statemachine.I_StateEventListener;
import swarm.shared.structs.CellAddress;
import swarm.shared.structs.CellAddressMapping;
import swarm.shared.structs.CellSize;
import swarm.shared.transaction.E_RequestPath;
import swarm.shared.transaction.E_ResponseError;
import swarm.shared.transaction.TransactionRequest;
import swarm.shared.transaction.TransactionResponse;

import com.dougkoellmer.client.managers.CellAddressManager;
import com.dougkoellmer.client.entities.ClientGrid;
import com.dougkoellmer.client.entities.ClientUser;
import com.dougkoellmer.client.view.DkViewController;
import com.dougkoellmer.shared.app.S_App;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
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
public class ClientApp extends A_ClientApp implements EntryPoint
{
	private static final double DEBUG_TIME = 7;
	private static final double DEFAULT_RETRACTION_TIME = .3;
	
	private static final Logger s_logger = Logger.getLogger(ClientApp.class.getName());	
	
	private static final JsConfig s_config = new JsConfig("dk_config");
	
	public ClientApp()
	{
		super(makeAppConfig(), makeViewConfig());
	}
	
	private static ClientAppConfig makeAppConfig()
	{
		ClientAppConfig appConfig = new ClientAppConfig();
		
		int cacheSize = 256;
		double cacheExpiration = Double.MAX_VALUE;
		
		appConfig.minSnapTime	 = s_config.getDouble("minSnapTime");
		appConfig.snapTimeRange = 1;
		appConfig.addressCacheSize = cacheSize;
		appConfig.addressCacheExpiration_seconds = cacheExpiration;
		appConfig.codeCacheSize = cacheSize;
		appConfig.codeCacheExpiration_seconds = cacheExpiration;
		appConfig.cellSizeCacheSize = cacheSize;
		appConfig.cellSizeCacheExpiration_seconds = cacheExpiration;
		appConfig.cellHudHeight = S_ClientApp.CELL_HUD_HEIGHT;
		appConfig.backOffDistance = s_config.getDouble("backOffDistance");	
		appConfig.publicRecaptchaKey = "";
		appConfig.useVirtualSandbox = false;
		appConfig.appServerVersion = S_App.APP_SERVER_VERSION;
		appConfig.maxSubCellDimension = 32;
		
		appConfig.appId = S_App.APP_ID;
		
		appConfig.user = new ClientUser();
		appConfig.grid = new ClientGrid();
		
		return appConfig;
	}
	
	private static ViewConfig makeViewConfig()
	{
		ViewConfig viewConfig = new ViewConfig();
		
		viewConfig.magFadeInTime_seconds = s_config.getDouble("magFadeInTime_seconds");
		viewConfig.hudFadeOutTime_seconds = s_config.getDouble("hudFadeOutTime_seconds");
		viewConfig.magnifierTickCount = 7;
		viewConfig.defaultPageTitle = "Doug Koellmer";
		viewConfig.cellHighlightColor = "rgb(145, 167, 223)";
		viewConfig.initialBumpDistance = 330;
		viewConfig.cellRetractionEasing = s_config.getDouble("cellRetractionEasing");
		viewConfig.cellSizeChangeTime_seconds = s_config.getDouble("cellSizeChangeTime_seconds");
		viewConfig.focuserFadeOutTime_seconds = s_config.getDouble("focuserFadeOutTime_seconds");
		
		return viewConfig;
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		super.startUp(E_StartUpStage.values()[0]);
	}
	
	@Override
	protected void stage_startAppManagers()
	{
		m_appContext.addressMngr = new CellAddressManager(m_appContext);
		
		super.stage_startAppManagers();
		
		A_Grid grid = this.m_appContext.gridMngr.getGrid();
		
		final CellAddressMapping utilMapping = new CellAddressMapping();
		final CellSize utilCellSize = new CellSize();
		
		I_TransactionResponseHandler cellSizeHandler = new I_TransactionResponseHandler()
		{
			public E_ResponseSuccessControl onResponseSuccess(TransactionRequest request, TransactionResponse response)
			{
				return E_ResponseSuccessControl.CONTINUE; // Pass handling up to cell size manager.
			}
			
			public E_ResponseErrorControl onResponseError(TransactionRequest request, TransactionResponse response)
			{
				if( request.getPath() == E_RequestPath.getFocusedCellSize )
				{
					if( response.getError() == E_ResponseError.NO_DISPATCHER )
					{
						utilMapping.readJson(request.getJsonArgs(), m_appContext.jsonFactory);
						m_appContext.cellSizeMngr.forceCache(utilMapping, utilCellSize);
						
						return E_ResponseErrorControl.BREAK;
					}
				}
				
				return E_ResponseErrorControl.CONTINUE;
			}
		};
		
		m_appContext.txnMngr.addHandler(m_appContext.cellSizeMngr);
		m_appContext.txnMngr.addHandler(cellSizeHandler);
		
		//--- DRK > Temporarily null out async dispatcher to force everything local.
		I_AsyncRequestDispatcher dispatcher_saved = m_appContext.txnMngr.getAsyncDispatcher();
		m_appContext.txnMngr.setAsyncDispatcher(null);
		
		//--- DRK > address->mapping relationships are embedded into the page as inline transactions,
		//---		so here we're invoking those transactions as an implicit prefilling side effect
		//---		to cache mapping->address relationships as well without having to hit server.
		for( int i = 0; i < E_HomeCell.values().length; i++ )
		{
			E_HomeCell ithCell = E_HomeCell.values()[i];
			
			//--- DRK > Prepopulate cell size cache at the same time.
			CellAddressMapping mapping = new CellAddressMapping(ithCell.getCoordinate());
			m_appContext.txnMngr.makeRequest(E_RequestPath.getFocusedCellSize, mapping);
			
			m_appContext.txnMngr.flushSyncResponses();
		}
		
		m_appContext.txnMngr.removeHandler(cellSizeHandler);
		m_appContext.txnMngr.removeHandler(m_appContext.cellSizeMngr);
		
		m_appContext.txnMngr.setAsyncDispatcher(dispatcher_saved);		
	}
	
	@Override
	protected void stage_startViewManagers()
	{
		m_viewContext.clickMngr = new ClickManager();
		
		m_viewContext.toolTipMngr = new ToolTipManager(m_appContext.platformInfo.getPlatform() != E_Platform.IOS, S_UI.TOOL_TIP_DELAY);
		
		//--- DRK > Set defaults for tool tips.
		for( int i = E_ZIndex.TOOL_TIP_1.ordinal(), j = 0; i <= E_ZIndex.TOOL_TIP_5.ordinal(); i++, j++ )
		{
			m_viewContext.toolTipMngr.setDefaultZIndex(E_ToolTipType.values()[j], i);
		}
		m_viewContext.toolTipMngr.setDefaultPadding(S_UI.TOOl_TIP_PADDING);
		
		//TODO(DRK) Ugh, real hacky here.
		I_Tab[] tabs = {new CodeEditorTab(m_viewContext)};
		m_viewConfig.tabs = tabs;
		
		m_viewContext.spinnerFactory = new I_CellSpinnerFactory()
		{
			@Override
			public I_CellSpinner newSpinner()
			{
				return new SpritePlateSpinner("dk_spinner", 15, 1/30.0);
			}
		};
	}
	
	@Override
	protected void stage_registerStateMachine(I_StateEventListener stateEventListener_null, Class<? extends A_State> consoleState_T_null)
	{
		DkViewController viewController = new DkViewController(m_viewContext, m_viewConfig, m_appConfig);
		
		super.stage_registerStateMachine(viewController, StateMachine_Tabs.class);
	
		registerAccountStates();
		registerCodeEditingStates();
		List<Class<? extends A_State>> tabStates = new ArrayList<Class<? extends A_State>>();
		//tabStates.add(StateMachine_Account.class);
		tabStates.add(StateMachine_EditingCode.class);
		m_stateContext.register(new StateMachine_Tabs(tabStates));
	}
	
	@Override
	protected void stage_gunshotSound()
	{
		super.stage_gunshotSound();
	}
}
