function loadJs(path)
{
	document.write('<scr'+'ipt type="text/javascript" s'+'rc="'+path+'?v='+(Math.random()*9007199254740992)+'"></scr'+'ipt>');
	
    /*var scriptElement = document.createElement('script');
    scriptElement.type = 'text/javascript';
    scriptElement.src = path + '?v=' + (Math.random()*9007199254740992);
    var scriptTags = document.getElementsByTagName('script');
    var scriptTag = scriptTags[scriptTags.length-1];
    scriptTag.parentNode.insertBefore(scriptElement, null);*/
}