package com.dougkoellmer.server.homecells;

public class U_HomeCell
{
	public static String stripAnalytics(String html)
	{
		return html.replaceAll("<script>.*GoogleAnalyticsObject.*</script>", "");
	}
}
