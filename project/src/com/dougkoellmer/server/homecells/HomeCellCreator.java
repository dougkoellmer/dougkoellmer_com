package com.dougkoellmer.server.homecells;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.w3c.tidy.Tidy;
import org.w3c.tidy.TidyMessage;
import org.w3c.tidy.TidyMessageListener;

import com.dougkoellmer.server.app.ServerApp;
import com.dougkoellmer.server.app.VersionTracker;
import com.dougkoellmer.shared.app.S_App;
import com.dougkoellmer.shared.homecells.E_HomeCell;
import com.dougkoellmer.shared.homecells.S_HomeCell;
import com.dougkoellmer.shared.homecells.U_HomeCellSize;

import swarm.shared.entities.E_CharacterQuota;
import swarm.shared.entities.E_CodeSafetyLevel;
import swarm.server.account.UserSession;
import swarm.server.app.ServerContext;
import swarm.server.blobxn.BlobTransaction_AddCellToUser;
import swarm.server.blobxn.BlobTransaction_SetCellAddress;
import swarm.server.blobxn.BlobTransaction_SetCellPrivileges;
import swarm.server.data.blob.BlobException;
import swarm.server.data.blob.BlobManagerFactory;
import swarm.server.data.blob.E_BlobCacheLevel;
import swarm.server.data.blob.E_BlobTransactionType;
import swarm.server.data.blob.I_BlobManager;
import swarm.server.entities.E_GridType;
import swarm.server.entities.ServerCell;
import swarm.server.entities.ServerUser;
import swarm.server.handlers.U_CellCode;
import swarm.server.handlers.admin.I_HomeCellCreator;
import swarm.server.structs.ServerCellAddress;
import swarm.server.structs.ServerCellAddressMapping;
import swarm.server.structs.ServerCode;
import swarm.server.structs.ServerCodePrivileges;
import swarm.server.thirdparty.servlet.U_Servlet;
import swarm.server.transaction.ServerTransactionManager;
import swarm.server.transaction.TransactionContext;
import swarm.shared.code.CompilerResult;
import swarm.shared.code.E_CompilationStatus;
import swarm.shared.code.U_Code;
import swarm.shared.entities.E_CodeType;
import swarm.shared.structs.CellSize;
import swarm.shared.structs.Code;
import swarm.shared.structs.CodePrivileges;
import swarm.shared.structs.E_NetworkPrivilege;
import swarm.shared.structs.GridCoordinate;
import swarm.shared.transaction.E_RequestPath;
import swarm.shared.transaction.E_ResponseError;
import swarm.shared.transaction.TransactionRequest;
import swarm.shared.transaction.TransactionResponse;

public class HomeCellCreator implements I_HomeCellCreator {
	private static final Logger s_logger = Logger
			.getLogger(HomeCellCreator.class.getName());

	private ServerContext m_serverContext;
	private ServletContext m_servletContext;

	public static final String BASE_RESOURCE_PATH = "/WEB-INF/home_cells";

	// <link type='text/css' rel='stylesheet' href='/r.css/default_cell.css'/>
	private static final String HTML_START = "<!doctype><html><head></head><body>";
	private static final String HTML_END = "</body></html>";

	public HomeCellCreator() {
	}

	public void initialize(ServerContext serverContext,
			ServletContext servletContext) {
		m_servletContext = servletContext;
		m_serverContext = serverContext;
	}

	private HomeCellMetaData getMetaData(E_HomeCell cell) {
		String description = U_HomeCellMeta.getDescription(cell);
		I_HomeCellContent content = U_HomeCellMeta.getContent(cell);

		if (description == null || content == null) {
			throw new Error("Null piece of cell meta data for "
					+ cell.getCellName());
		}

		return new HomeCellMetaData(description, content);
	}

	public void run(TransactionRequest request, TransactionResponse response, TransactionContext context, UserSession session, ServerUser user)
	{
		int homeCellIndex = 0;
		for( int i = 0; i < S_HomeCell.GRID_SIZE; i++ )
		{
			for( int j = 0; j < S_HomeCell.GRID_SIZE; j++ )
			{
				E_HomeCell cell = E_HomeCell.get(j, i);
				
				if( cell != null )
				{
					cell.setIndex(homeCellIndex);
					homeCellIndex++;
				}
			}
		}
		
		ServerCodePrivileges privileges = new ServerCodePrivileges();
		privileges.setNetworkPrivilege(E_NetworkPrivilege.ALL);
		privileges.setCharacterQuota(E_CharacterQuota.TIER_1);

		for (int i = 0; i < E_HomeCell.values().length; i++)
		{
			E_HomeCell eCell = E_HomeCell.values()[i];
			GridCoordinate coordinate = eCell.getCoordinate();
			ServerCellAddressMapping mapping = new ServerCellAddressMapping(
					E_GridType.ACTIVE, coordinate);

			ServerCellAddress primaryAddress = new ServerCellAddress(
					eCell.getPrimaryAddress());
			ServerCellAddress secondaryAddress = eCell.getSecondaryAddress() != null ? new ServerCellAddress(
					eCell.getSecondaryAddress()) : null;

			ServerCellAddress[] addressArray;

			if (secondaryAddress != null) {
				addressArray = new ServerCellAddress[2];
				addressArray[0] = primaryAddress;
				addressArray[1] = secondaryAddress;
			} else {
				addressArray = new ServerCellAddress[1];
				addressArray[0] = primaryAddress;
			}

			// --- DRK > First take ownership of the cell if necessary.
			if (!user.isCellOwner(coordinate)) {
				int gridExpansionDelta = m_serverContext.config.gridExpansionDelta;
				BlobTransaction_AddCellToUser blobTransaction = new BlobTransaction_AddCellToUser(
						session, addressArray, coordinate, privileges,
						gridExpansionDelta);
				blobTransaction.checkUsernameMatch(false);

				try {
					blobTransaction.perform(m_serverContext.blobMngrFactory,
							E_BlobTransactionType.MULTI_BLOB_TYPE, 1);
				} catch (BlobException e) {
					s_logger.log(Level.SEVERE,
							"Could not take ownership of home cell.", e);

					response.setError(E_ResponseError.SERVICE_EXCEPTION);

					return;
				}
			} else {
				// --- DRK > Set or reset cell name. NOTE: This doesn't delete
				// old address, at least not yet.
				BlobTransaction_SetCellAddress setAddressTransaction = new BlobTransaction_SetCellAddress(
						mapping, addressArray);
				try {
					setAddressTransaction.perform(
							m_serverContext.blobMngrFactory,
							E_BlobTransactionType.MULTI_BLOB_TYPE, 1);
				} catch (BlobException e) {
					s_logger.log(Level.SEVERE,
							"Could not rename cell address.", e);

					response.setError(E_ResponseError.SERVICE_EXCEPTION);

					return;
				}
			}

			// --- DRK > Get the cell itself.
			I_BlobManager blobManager = m_serverContext.blobMngrFactory
					.create(E_BlobCacheLevel.PERSISTENT);
			I_BlobManager cachedBlobMngr = m_serverContext.blobMngrFactory
					.create(E_BlobCacheLevel.MEMCACHE);
			ServerCell persistedCell = U_CellCode.getCellForCompile(
					blobManager, mapping, response);

			if (persistedCell == null)
				return;

			// --- DRK > Get and copy over meta data.
			HomeCellMetaData metaData = getMetaData(eCell);
			I_HomeCellContent content = metaData.getContent();
			content.init(m_servletContext, eCell);
			CellSize focusedCellSize = U_HomeCellSize.getFocusedCellSize(eCell);
			persistedCell.getFocusedCellSize().copy(focusedCellSize);
			persistedCell.getCodePrivileges().setCharacterQuota(
					E_CharacterQuota.UNLIMITED);
			E_CodeSafetyLevel splashSafety = content
					.getSafetyLevel(E_CodeType.SPLASH);
			E_CodeSafetyLevel compiledSafety = content
					.getSafetyLevel(E_CodeType.COMPILED);

			String sourceCodeRaw = "";

			// s_logger.severe(splashSafety + " " + compiledSafety + " " +
			// eCell);

			if (eCell == E_HomeCell.RED_OAK_SPOON)
			{
				s_logger.severe("");
			}

			s_logger.severe("Generating " + eCell);

			boolean containsSplash = false;

			if (splashSafety.isVirtual() && (compiledSafety != null && compiledSafety.isVirtual() || compiledSafety == null))
			{
				// --- DRK > Get the source code for the cell.
				sourceCodeRaw = HTML_START + content.getCode(E_CodeType.SOURCE) + HTML_END;
				ServerCode sourceCode = new ServerCode(sourceCodeRaw, E_CodeType.SOURCE);

				CompilerResult result = U_CellCode.compileCell(m_serverContext.codeCompiler, persistedCell, sourceCode, mapping, m_serverContext.config.appId);

				if (result.getStatus() != E_CompilationStatus.NO_ERROR)
				{
					s_logger.severe("Couldn't compile source code: ");

					response.setError(E_ResponseError.SERVICE_EXCEPTION);

					return;
				}
			}
			else if( compiledSafety == E_CodeSafetyLevel.REMOTE_SANDBOX )
			{
				sourceCodeRaw = content.getCode(E_CodeType.SOURCE);
				ServerCode sourceCode = new ServerCode(sourceCodeRaw, E_CodeType.SOURCE);
				persistedCell.setCode(E_CodeType.SOURCE, sourceCode);
				
				ServerCode splashCode = new ServerCode(content.getCode(E_CodeType.SPLASH), E_CodeType.SPLASH);
				splashCode.setSafetyLevel(splashSafety);
				persistedCell.setCode(E_CodeType.SPLASH, splashCode);
				
				ServerCode compiledCode = new ServerCode(content.getCode(E_CodeType.COMPILED), E_CodeType.COMPILED);
				compiledCode.setSafetyLevel(compiledSafety);
				persistedCell.setCode(E_CodeType.COMPILED, compiledCode);
			}
			/*
			 * else if( splashSafety == E_CodeSafetyLevel.NO_SANDBOX_STATIC &&
			 * compiledSafety == null ) { String sourceCode =
			 * content.getSourceCode(E_CodeType.SOURCE);
			 * 
			 * ServerCode splashCode = new ServerCode(sourceCode,
			 * E_CodeType.SPLASH, E_CodeType.COMPILED);
			 * splashCode.setSafetyLevel(splashSafety);
			 * persistedCell.setCode(E_CodeType.SPLASH, splashCode);
			 * 
			 * sourceCodeRaw = sourceCode; }
			 */
			else
			{
				String sourceCodeForSplash = content.getCode(E_CodeType.SPLASH);
				String sourceCodeForCompiled = content.getCode(E_CodeType.COMPILED);

				if (sourceCodeForSplash != null && sourceCodeForCompiled == null)
				{
					sourceCodeRaw = sourceCodeForSplash;

					ServerCode splashCode = new ServerCode(sourceCodeForSplash, E_CodeType.SPLASH, E_CodeType.COMPILED);
					splashCode.setSafetyLevel(splashSafety);
					persistedCell.setCode(E_CodeType.SPLASH, splashCode);
				}
				else // -DRK > assuming splash and compiled are both not null.
				{
					// TODO: Add splash tag somehow so tidy doesn't freak out.

					if (compiledSafety == E_CodeSafetyLevel.LOCAL_SANDBOX) {
						// sourceCodeForCompiled.replace("<body>",
						// "<body><div name='splash'>"+sourceCodeForSplash+"splash</div>");
						containsSplash = true;
						sourceCodeRaw = sourceCodeForCompiled;
					}
					else
					{
						containsSplash = true;
						// sourceCodeRaw =
						// "<splash>"+sourceCodeForSplash+"</splash>"+sourceCodeForCompiled;
						sourceCodeRaw = sourceCodeForCompiled;
					}

					ServerCode splashCode = new ServerCode(sourceCodeForSplash,
							E_CodeType.SPLASH);
					splashCode.setSafetyLevel(splashSafety);
					persistedCell.setCode(E_CodeType.SPLASH, splashCode);

					ServerCode compileCode = new ServerCode(
							sourceCodeForCompiled, E_CodeType.COMPILED);
					compileCode.setSafetyLevel(compiledSafety);
					persistedCell.setCode(E_CodeType.COMPILED, compileCode);
				}

				// TODO: Might need to append HTML_START/END for tidy
			}

			String tidied = tidy(sourceCodeRaw);

			if (containsSplash) {
				// tidied.replace("<div name='splash'>", "<splash>");
				// tidied.replace("splash</div>", "</splash>");
			}
			persistedCell.setCode(E_CodeType.SOURCE, new ServerCode(tidied,
					E_CodeType.SOURCE));

			if (!U_CellCode.saveBackCompiledCell(blobManager, cachedBlobMngr,
					mapping, persistedCell, response)) {
				response.setError(E_ResponseError.SERVICE_EXCEPTION);

				return;
			}
		}
		
		VersionTracker versionTracker = ((com.dougkoellmer.server.app.ServerContext)m_serverContext).versionTracker;
		versionTracker.pushVersion();
	}

	private String tidy(String sourceCode) {
		Tidy tidy = new Tidy();
		tidy.setTabsize(4);
		tidy.setSpaces(3);
		tidy.setIndentContent(true);
		tidy.setTidyMark(false);
		tidy.setWraplen(Integer.MAX_VALUE);
		tidy.setTrimEmptyElements(false);
		InputStream inputStream = new ByteArrayInputStream(
				sourceCode.getBytes());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parse(inputStream, outputStream);
		String tidiedCode = new String(outputStream.toByteArray());
		return tidiedCode;
	}
}
