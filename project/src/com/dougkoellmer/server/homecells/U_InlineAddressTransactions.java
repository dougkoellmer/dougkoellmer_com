package com.dougkoellmer.server.homecells;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swarm.server.account.UserSession;
import swarm.server.app.A_ServerApp;
import swarm.server.app.ServerAppConfig;
import swarm.server.app.ServerContext;
import swarm.server.data.blob.BlobException;
import swarm.server.data.blob.E_BlobCacheLevel;
import swarm.server.data.blob.I_Blob;
import swarm.server.data.blob.I_BlobKey;
import swarm.server.data.blob.I_BlobManager;
import swarm.server.entities.BaseServerGrid;
import swarm.server.entities.E_GridType;
import swarm.server.entities.ServerUser;
import swarm.server.structs.ServerCellAddress;
import swarm.server.structs.ServerCellAddressMapping;
import swarm.server.transaction.InlineTransactionManager;
import swarm.shared.entities.A_Grid;
import swarm.shared.json.I_WritesJson;
import swarm.shared.structs.CellAddress;
import swarm.shared.structs.CellAddressMapping;
import swarm.shared.structs.E_CellAddressParseError;
import swarm.shared.structs.E_GetCellAddressMappingError;
import swarm.shared.structs.GetCellAddressMappingResult;
import swarm.shared.structs.GridCoordinate;
import swarm.shared.structs.Point;
import swarm.shared.transaction.E_RequestPath;
import swarm.shared.transaction.TransactionRequest;
import swarm.shared.transaction.TransactionResponse;

public class U_InlineAddressTransactions
{
	private static String s_addressTxns = null;
	
	public static void addInlineTransactions(HttpServletRequest nativeRequest, HttpServletResponse nativeResponse, Writer out) throws IOException
	{
		if( s_addressTxns == null )
		{
			buildTransactionString();
		}
		
		out.write(s_addressTxns);
	}
	
	private synchronized static void buildTransactionString() throws IOException
	{
		if( s_addressTxns != null )  return;
		
		ServerContext context = A_ServerApp.getInstance().getContext();
		ServerAppConfig config = context.config;
		InlineTransactionManager txnMngr = context.inlineTxnMngr;
		
		StringWriter buffer = new StringWriter();
		
		try
		{
			txnMngr.beginBatch(buffer, null, null);
			
			for( int i = 0; i < E_HomeCell.values().length; i++ )
			{
				E_HomeCell cell = E_HomeCell.values()[i];
				CellAddress address = new CellAddress(cell.getPrimaryAddress());
				CellAddressMapping mapping = new CellAddressMapping(cell.getCoordinate());
				GetCellAddressMappingResult result = new GetCellAddressMappingResult(mapping, E_GetCellAddressMappingError.NO_ERROR);
				
				txnMngr.makeInlineRequestWithResponse(E_RequestPath.getCellAddressMapping, address, result);
			}
		}
		catch(Exception e)
		{
			buffer = null;
			throw e;
		}
		finally
		{
			txnMngr.endBatch();
		}
		
		s_addressTxns = buffer != null ? buffer.getBuffer().toString() : "";
	}
}
