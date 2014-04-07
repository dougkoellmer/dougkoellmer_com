package com.dougkoellmer.server.homecells;

import com.dougkoellmer.server.app.ServerApp;

public class U_HomeCell
{
	public static String stripAnalytics(String html)
	{
		return html.replaceAll("<script>.*GoogleAnalyticsObject.*</script>", "");
	}
	
	public static String getImgPath(String rawPath)
	{
		return rawPath + "?v="+ServerApp.getInstance().getConfig().appVersion;
	}
}
