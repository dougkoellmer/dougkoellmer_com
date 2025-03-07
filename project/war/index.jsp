<!doctype html>

<%@page import="swarm.server.transaction.*"%>
<%@page import="com.dougkoellmer.server.homecells.*"%>
<%@ page session="false"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="description" content="Doug Koellmer's personal website.">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes">

<base href="/" target="_blank">

<title>Doug Koellmer</title>

<link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon.png">
<link rel="icon" type="image/png" href="/favicon-32x32.png" sizes="32x32">
<link rel="icon" type="image/png" href="/favicon-16x16.png" sizes="16x16">
<link rel="manifest" href="/manifest.json">
<link rel="mask-icon" href="/safari-pinned-tab.svg" color="#5bbad5">
<meta name="theme-color" content="#ffffff">

<link type="text/css" rel="stylesheet" href="/dougkoellmer_com/min.css?v=1437022872" />

<script type="text/javascript">
var RecaptchaOptions={theme : 'custom'};
<%
U_InlineTransactions.addInlineTransactions(request, response, out);
%>
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-8687362-1', 'dougkoellmer.com');
  ga('send', 'pageview');

</script>

<script>

	var dk_backOffTime = .4;
	//var dk_backOffTime = 5.0;
	var dk_config = 
	{
		minSnapTime:.3,
		//minSnapTime:5,
		magFadeInTime_seconds:dk_backOffTime,
		hudFadeOutTime_seconds:dk_backOffTime,
		cellSizeChangeTime_seconds:dk_backOffTime,
		focuserFadeOutTime_seconds:dk_backOffTime,
		cellRetractionEasing:2,
		backOffDistance:505
		//backOffDistance:1000
	};
	
	function hideAddressBar()
	{
	  if(!window.location.hash)
	  {
	      if(document.height < window.outerHeight)
	      {
	          document.body.style.height = (window.outerHeight + 50) + 'px';
	      }
	 
	      setTimeout( function(){ window.scrollTo(0, 1); }, 50 );
	  }
	}
	 
	window.addEventListener("load", function(){ if(!window.pageYOffset){ hideAddressBar(); } } );
	window.addEventListener("orientationchange", hideAddressBar );


</script>
</head>

<body>

	<noscript>
		<table id='error_splash' class='sm_unsupported_platform_font' style='width:100%; height:100%;'><tr><td style='vertical-align:middle; text-align:center;'>
		<!--<img style='width:280px; height:255px;' src='/img/dk.png'/><br>--><br><br>
		<div id='error_splash_message'>Sorry, but your web browser must have JavaScript enabled in order for this page to display correctly.</div>
		</td></tr></table>
	</noscript>

	<div id="sm_initial_sync_screen">
		<table style="width: 100%; height: 100%;">
			<tr>
				<td align="center" style="vertical-align: middle;">
					<div id="sm_initial_sync_screen_label"></div>
				</td>
			</tr>
		</table>
	</div>
	
	<script type="text/javascript" src="/dougkoellmer_com/min.js?v=1437022871"></script>
</body>
</html>
