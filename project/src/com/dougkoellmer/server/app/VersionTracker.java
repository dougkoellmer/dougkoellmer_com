package com.dougkoellmer.server.app;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ConcurrentModificationException;
import java.util.Map;

import com.dougkoellmer.shared.app.S_App;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.api.utils.SystemProperty.Environment.Value;

import swarm.server.app.ServerAppConfig;
import swarm.server.data.blob.BlobException;
import swarm.server.data.blob.BlobManagerFactory;
import swarm.server.data.blob.E_BlobCacheLevel;
import swarm.server.data.blob.I_Blob;
import swarm.server.data.blob.I_BlobKey;
import swarm.server.data.blob.I_BlobManager;
import swarm.server.data.blob.U_Blob;

public class VersionTracker
{	
	private static class BlobKey implements I_BlobKey
	{
		@Override
		public String createBlobKey(I_Blob blob)
		{
			return "app_version_afa_db_knows";
		}
	}
	
	public static class VersionBlob implements I_Blob
	{
		private int m_version;
		
		public VersionBlob()
		{
			
		}
		
		VersionBlob(int version)
		{
			m_version = version;
		}
		
		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
		{
			m_version = in.readInt();
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException
		{
			out.writeInt(m_version);
		}

		@Override
		public E_BlobCacheLevel getMaximumCacheLevel()
		{
			return E_BlobCacheLevel.PERSISTENT;
		}

		@Override
		public String getKind() 
		{
			return "version";
		}

		@Override
		public Map<String, Object> getQueryableProperties()
		{
			return null;
		}
	}
	
	private BlobKey m_key = new BlobKey();
	
	public void checkVersion()
	{
		I_BlobManager blobMngr = ServerApp.getInstance().getContext().blobMngrFactory.create(E_BlobCacheLevel.PERSISTENT);
		VersionBlob version = null;
		try {
			version = blobMngr.getBlob(m_key, VersionBlob.class);
			
			if( version != null && version.m_version == ServerApp.getInstance().getConfig().appVersion )
			{
				ServerAppConfig config = ServerApp.getInstance().getConfig();
				config.requestCacheExpiration_seconds = S_App.HTTP_CACHE_EXPIRATION_SECONDS;
			}
		}
		catch (BlobException e)
		{
			e.printStackTrace();
		}
	}
	
	public void pushVersion()
	{
		I_BlobManager blobMngr = ServerApp.getInstance().getContext().blobMngrFactory.create(E_BlobCacheLevel.PERSISTENT);
		VersionBlob version = new VersionBlob(ServerApp.getInstance().getConfig().appVersion);
		ServerAppConfig config = ServerApp.getInstance().getConfig();
		
		try
		{
			blobMngr.putBlob(m_key, version);
			
			SystemProperty.Environment.Value env = SystemProperty.environment.value();
			if (env  == Value.Production)
			{
				config.requestCacheExpiration_seconds = S_App.HTTP_CACHE_EXPIRATION_SECONDS;
			}
			else if(env == Value.Development)
			{
			}
		}
		catch (ConcurrentModificationException e)
		{
			e.printStackTrace();
		} catch (BlobException e)
		{
			e.printStackTrace();
		}
	}
}
