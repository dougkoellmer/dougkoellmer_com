package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;

import swarm.shared.entities.smE_CharacterQuota;

import swarm.server.account.smUserSession;
import swarm.server.app.smServerContext;
import swarm.server.blobxn.smBlobTransaction_AddCellToUser;
import swarm.server.blobxn.smBlobTransaction_SetCellAddress;
import swarm.server.blobxn.smBlobTransaction_SetCellPrivileges;
import swarm.server.data.blob.smBlobException;
import swarm.server.data.blob.smBlobManagerFactory;
import swarm.server.data.blob.smE_BlobCacheLevel;
import swarm.server.data.blob.smE_BlobTransactionType;
import swarm.server.data.blob.smI_BlobManager;
import swarm.server.entities.smE_GridType;
import swarm.server.entities.smServerCell;
import swarm.server.entities.smServerUser;
import swarm.server.handlers.smU_CellCode;
import swarm.server.handlers.admin.smI_HomeCellCreator;
import swarm.server.structs.smServerCellAddress;
import swarm.server.structs.smServerCellAddressMapping;
import swarm.server.structs.smServerCode;
import swarm.server.structs.smServerCodePrivileges;
import swarm.server.thirdparty.servlet.smU_Servlet;
import swarm.server.transaction.smServerTransactionManager;
import swarm.server.transaction.smTransactionContext;

import swarm.shared.code.smCompilerResult;
import swarm.shared.code.smE_CompilationStatus;
import swarm.shared.code.smU_Code;
import swarm.shared.entities.smE_CodeType;
import swarm.shared.structs.smCode;
import swarm.shared.structs.smCodePrivileges;
import swarm.shared.structs.smE_NetworkPrivilege;
import swarm.shared.structs.smGridCoordinate;
import swarm.shared.transaction.smE_RequestPath;
import swarm.shared.transaction.smE_ResponseError;
import swarm.shared.transaction.smTransactionRequest;
import swarm.shared.transaction.smTransactionResponse;

public class HomeCellCreator implements smI_HomeCellCreator
{
	private static final Logger s_logger = Logger.getLogger(HomeCellCreator.class.getName());
	
	private ServletContext m_context;
	
	public static final String BASE_RESOURCE_PATH = "/WEB-INF/home_cells/";
	
	private smServerContext m_serverContext;
	
	public HomeCellCreator()
	{
	}
	
	public void initialize(smServerContext serverContext, ServletContext servletContext)
	{
		m_serverContext = serverContext;
		m_context = servletContext;
	}
	
	public ServletContext getServletContext()
	{
		return m_context;
	}
	
	public void run(smTransactionRequest request, smTransactionResponse response, smTransactionContext context, smUserSession session, smServerUser user)
	{
	}
}
