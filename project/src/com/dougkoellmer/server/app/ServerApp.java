package com.dougkoellmer.server.app;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import swarm.client.app.PlatformInfo;
import swarm.client.transaction.ClientTransactionManager;
import swarm.server.account.DummyAccountDatabase;
import swarm.server.account.I_AccountDatabase;
import swarm.server.account.PropertyAccountDatabase;
import swarm.server.account.SqlAccountDatabase;
import swarm.server.account.ServerAccountManager;
import swarm.server.app.A_ServerApp;
import swarm.server.app.ServerAppConfig;
import swarm.server.code.ServerCodeCompiler;
import swarm.server.data.blob.BlobManagerFactory;
import swarm.server.entities.BaseServerGrid;
import swarm.server.handlers.*;
import swarm.server.handlers.admin.adminHandler;
import swarm.server.handlers.admin.I_HomeCellCreator;
import swarm.server.handlers.admin.clearCell;
import swarm.server.handlers.admin.createGrid;
import swarm.server.handlers.admin.deactivateUserCells;
import swarm.server.handlers.admin.recompileCells;
import swarm.server.handlers.admin.refreshHomeCells;
import swarm.server.handlers.normal.*;
import swarm.server.session.SessionManager;
import swarm.server.telemetry.TelemetryDatabase;
import swarm.server.thirdparty.json.ServerJsonFactory;
import swarm.server.transaction.E_DebugRequestPath;
import swarm.server.transaction.I_RequestHandler;
import swarm.server.transaction.I_TransactionScopeListener;
import swarm.server.transaction.InlineTransactionManager;
import swarm.server.transaction.ServerTransactionManager;
import swarm.shared.E_AppEnvironment;
import swarm.shared.account.SignInValidator;
import swarm.shared.account.SignUpValidator;
import swarm.shared.app.A_App;
import swarm.shared.app.S_CommonApp;
import swarm.shared.debugging.I_AssertionDelegate;
import swarm.shared.debugging.TelemetryAssert;
import swarm.shared.debugging.U_Debug;
import swarm.shared.json.JsonHelper;
import swarm.shared.transaction.E_RequestPath;
import swarm.shared.transaction.E_TelemetryRequestPath;
import swarm.shared.transaction.I_RequestPath;
import swarm.shared.transaction.RequestPathManager;

import com.dougkoellmer.server.entities.ServerGrid;
import com.dougkoellmer.server.homecells.HomeCellCreator;
import com.dougkoellmer.shared.app.S_App;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.api.utils.SystemProperty.Environment.Value;
import com.google.gwt.user.client.Window;

public final class ServerApp extends A_ServerApp
{
	private static final Logger s_logger = Logger.getLogger(ServerApp.class.getName());

	ServerApp(ServletContext servletContext)
	{
		super();
		
		ServerAppConfig config = new ServerAppConfig();
		
		config.databaseUrl = null;
		config.accountsDatabase = null;
		config.telemetryDatabase = null;
		config.T_homeCellCreator = HomeCellCreator.class;
		config.gridExpansionDelta = 8;
		config.startingZ = 430;
		config.startingCoord = E_HomeCell.HOME.getCoordinate();
		config.appServerVersion = S_App.APP_SERVER_VERSION;
		
		config.mainPage = "http://www.dougkoellmer.com";
		
		config.privateRecaptchaKey = null;
		config.publicRecaptchaKey = null;
		
		config.appId = S_App.APP_ID;
		
		SystemProperty.Environment.Value env = SystemProperty.environment.value();
		if (env  == Value.Production)
		{
			config.requestCacheExpiration_seconds = 60*60*24*365;// one year expiration for json requests
		}
		else if(env == Value.Development)
		{
		}
		
		/*boolean clientSide = false;
		smI_AccountDatabase accountDatabase = new smDummyAccountDatabase();
		smSignInValidator signInValidator = new smSignInValidator(clientSide);
		smSignUpValidator signUpValidator = new smSignUpValidator(clientSide);
		m_context.accountMngr = new smServerAccountManager(signInValidator, signUpValidator, accountDatabase);*/
		
		boolean clientSide = false;
		PropertyAccountDatabase localDatabase = new PropertyAccountDatabase(servletContext, "/WEB-INF/account.properties");
		SignInValidator signInValidator = new SignInValidator(clientSide);
		SignUpValidator signUpValidator = new SignUpValidator(clientSide);
		m_context.accountMngr = new ServerAccountManager(signInValidator, signUpValidator, localDatabase);
		
		super.entryPoint(config);
		
		ServerTransactionManager txnManager = this.m_context.txnMngr;
		
		setNormalHandler(new getUserData(false, config.gridExpansionDelta),	E_RequestPath.getUserData);		
		setAdminHandler(new createGrid(ServerGrid.class), swarm.server.transaction.E_AdminRequestPath.createGrid);
		
		//setAdminHandler(new refreshGrid(),	smE_AdminRequestPath.refreshGrid);
	}
}
