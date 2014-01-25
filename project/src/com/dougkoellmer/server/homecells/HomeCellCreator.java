package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
	
	private smServerContext m_serverContext;
	private ServletContext m_servletContext;
	
	public static final String BASE_RESOURCE_PATH = "/WEB-INF/home_cells";
	
	//<link type='text/css' rel='stylesheet' href='/r.css/default_cell.css'/>
	private static final String HTML_START = "<!doctype><html><head></head><body>";
	private static final String HTML_END = "</body></html>";
	
	//--- DRK > For some reason it's a compiler error to have these as static members of E_HomeCell itself.
	static E_HomeCell s_previousCell;
	static ArrayList<E_HomeCell> s_cellStack = new ArrayList<>();
	
	
	public HomeCellCreator()
	{
	}
	
	public void initialize(smServerContext serverContext, ServletContext servletContext)
	{
		m_servletContext = servletContext;
		m_serverContext = serverContext;
	}
	
	private String makeCellCode(E_HomeCell cell)
	{
		HomeCellMetaData metaData = cell.getMetaData();
		I_HomeCellContent content = metaData.getContent();
		content.init(m_servletContext, cell);
	
		return HTML_START + content.getContent() + HTML_END;
	}
	
	public void run(smTransactionRequest request, smTransactionResponse response, smTransactionContext context, smUserSession session, smServerUser user)
	{
		smServerCodePrivileges privileges = new smServerCodePrivileges();
		privileges.setNetworkPrivilege(smE_NetworkPrivilege.ALL);
		privileges.setCharacterQuota(smE_CharacterQuota.TIER_1);
		
		for( int i = 0; i < E_HomeCell.values().length; i++ )
		{
			E_HomeCell eCell = E_HomeCell.values()[i];
			smGridCoordinate coordinate = eCell.getCoordinate();
			smServerCellAddressMapping mapping = new smServerCellAddressMapping(smE_GridType.ACTIVE, coordinate);
			
			smServerCellAddress primaryAddress = new smServerCellAddress(eCell.getPrimaryAddress());
			smServerCellAddress secondaryAddress = eCell.getSecondaryAddress() != null ? new smServerCellAddress(eCell.getSecondaryAddress()) : null;
			
			smServerCellAddress[] addressArray;
			
			if( secondaryAddress != null)
			{
				addressArray = new smServerCellAddress[2];
				addressArray[0] = primaryAddress;
				addressArray[1] = secondaryAddress;
			}
			else
			{
				addressArray = new smServerCellAddress[1];
				addressArray[0] = primaryAddress;
			}

			//--- DRK > First take ownership of the cell if necessary.
			if( !user.isCellOwner(coordinate) )
			{
				int gridExpansionDelta = m_serverContext.config.gridExpansionDelta;
				smBlobTransaction_AddCellToUser blobTransaction = new smBlobTransaction_AddCellToUser(session, addressArray, coordinate, privileges, gridExpansionDelta);
				blobTransaction.checkUsernameMatch(false);
				
				try
				{
					blobTransaction.perform(m_serverContext.blobMngrFactory, smE_BlobTransactionType.MULTI_BLOB_TYPE, 1);
				}
				catch (smBlobException e)
				{
					s_logger.log(Level.SEVERE, "Could not take ownership of home cell.", e);
					
					response.setError(smE_ResponseError.SERVICE_EXCEPTION);
					
					return;
				}
			}
			else
			{
				//--- DRK > Set or reset cell name. NOTE: This doesn't delete old address, at least not yet.
				smBlobTransaction_SetCellAddress setAddressTransaction = new smBlobTransaction_SetCellAddress(mapping, addressArray);
				try
				{
					setAddressTransaction.perform(m_serverContext.blobMngrFactory, smE_BlobTransactionType.MULTI_BLOB_TYPE, 1);
				}
				catch (smBlobException e)
				{
					s_logger.log(Level.SEVERE, "Could not rename cell address.", e);
					
					response.setError(smE_ResponseError.SERVICE_EXCEPTION);
					
					return;
				}
			}
		
			//--- DRK > Get the source code for the cell.
			String code = this.makeCellCode(eCell);
			smServerCode sourceCode = new smServerCode(code, smE_CodeType.SOURCE);
			
			//--- DRK > Get the cell itself.
			smI_BlobManager blobManager = m_serverContext.blobMngrFactory.create(smE_BlobCacheLevel.PERSISTENT);
			smI_BlobManager cachedBlobMngr = m_serverContext.blobMngrFactory.create(smE_BlobCacheLevel.MEMCACHE);
			smServerCell persistedCell = smU_CellCode.getCellForCompile(blobManager, mapping, response);
			
			if( persistedCell == null )  return;
			
			persistedCell.getCodePrivileges().setCharacterQuota(smE_CharacterQuota.UNLIMITED);
			
			smCompilerResult result = smU_CellCode.compileCell(m_serverContext.codeCompiler, persistedCell, sourceCode, mapping, m_serverContext.config.appId);
			
			if( result.getStatus() != smE_CompilationStatus.NO_ERROR )
			{
				s_logger.severe("Couldn't compile source code: ");
				
				response.setError(smE_ResponseError.SERVICE_EXCEPTION);
				
				return;
			}

			if( !smU_CellCode.saveBackCompiledCell(blobManager, cachedBlobMngr, mapping, persistedCell, response) )
			{
				response.setError(smE_ResponseError.SERVICE_EXCEPTION);
				
				return;
			}
		}
	}
}
