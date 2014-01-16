package com.dougkoellmer.server.app;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import swarm.client.app.smPlatformInfo;
import swarm.client.transaction.smClientTransactionManager;
import swarm.server.account.smDummyAccountDatabase;
import swarm.server.account.smI_AccountDatabase;
import swarm.server.account.smPropertyAccountDatabase;
import swarm.server.account.smSqlAccountDatabase;
import swarm.server.account.smServerAccountManager;
import swarm.server.app.smA_ServerApp;
import swarm.server.app.smServerAppConfig;
import swarm.server.code.smServerCodeCompiler;
import swarm.server.data.blob.smBlobManagerFactory;
import swarm.server.entities.smServerGrid;
import swarm.server.handlers.*;
import swarm.server.handlers.admin.adminHandler;
import swarm.server.handlers.admin.smI_HomeCellCreator;
import swarm.server.handlers.admin.clearCell;
import swarm.server.handlers.admin.createGrid;
import swarm.server.handlers.admin.deactivateUserCells;
import swarm.server.handlers.admin.recompileCells;
import swarm.server.handlers.admin.refreshHomeCells;
import swarm.server.handlers.normal.*;
import swarm.server.session.smSessionManager;
import swarm.server.telemetry.smTelemetryDatabase;
import swarm.server.thirdparty.json.smServerJsonFactory;
import swarm.server.transaction.smE_DebugRequestPath;
import swarm.server.transaction.smI_RequestHandler;
import swarm.server.transaction.smI_TransactionScopeListener;
import swarm.server.transaction.smInlineTransactionManager;
import swarm.server.transaction.smServerTransactionManager;
import swarm.shared.smE_AppEnvironment;
import swarm.shared.account.smSignInValidator;
import swarm.shared.account.smSignUpValidator;
import swarm.shared.app.smA_App;
import swarm.shared.app.smS_App;
import swarm.shared.debugging.smI_AssertionDelegate;
import swarm.shared.debugging.smTelemetryAssert;
import swarm.shared.debugging.smU_Debug;
import swarm.shared.json.smJsonHelper;
import swarm.shared.transaction.smE_RequestPath;
import swarm.shared.transaction.smE_TelemetryRequestPath;
import swarm.shared.transaction.smI_RequestPath;
import swarm.shared.transaction.smRequestPathManager;

import com.dougkoellmer.server.homecells.HomeCellCreator;
import com.dougkoellmer.shared.app.S_App;
import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.gwt.user.client.Window;

public final class ServerApp extends smA_ServerApp
{
	private static final Logger s_logger = Logger.getLogger(ServerApp.class.getName());

	ServerApp(ServletContext servletContext)
	{
		super();
		
		smServerAppConfig config = new smServerAppConfig();
		
		config.databaseUrl = null;
		config.accountsDatabase = null;
		config.telemetryDatabase = null;
		config.T_homeCellCreator = HomeCellCreator.class;
		config.gridExpansionDelta = 8;
		config.startingZ = 128;
		
		config.mainPage = "http://www.dougkoellmer.com";
		
		config.privateRecaptchaKey = null;
		config.publicRecaptchaKey = null;
		
		config.appId = S_App.APP_ID;
		
		/*boolean clientSide = false;
		smI_AccountDatabase accountDatabase = new smDummyAccountDatabase();
		smSignInValidator signInValidator = new smSignInValidator(clientSide);
		smSignUpValidator signUpValidator = new smSignUpValidator(clientSide);
		m_context.accountMngr = new smServerAccountManager(signInValidator, signUpValidator, accountDatabase);*/
		
		boolean clientSide = false;
		smPropertyAccountDatabase localDatabase = new smPropertyAccountDatabase(servletContext, "/WEB-INF/account.properties");
		smSignInValidator signInValidator = new smSignInValidator(clientSide);
		smSignUpValidator signUpValidator = new smSignUpValidator(clientSide);
		m_context.accountMngr = new smServerAccountManager(signInValidator, signUpValidator, localDatabase);
		
		super.entryPoint(config);
		
		smServerTransactionManager txnManager = this.m_context.txnMngr;
		
		setNormalHandler(new getUserData(true, config.gridExpansionDelta),	smE_RequestPath.getUserData);		
		
		
		//setAdminHandler(new refreshGrid(),	smE_AdminRequestPath.refreshGrid);
	}
}
