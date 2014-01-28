function calcRandomVersion(path)
{
	return path + "?v=" + Math.random()*9007199254740992;
}

function loadJs(path)
{
	document.write('<scr'+'ipt type="text/javascript" s'+'rc="'+(calcRandomVersion(path))+'"></scr'+'ipt>');
}

function loadCss(path)
{
	document.write('<link rel="stylesheet" type="text/css" href="'+(calcRandomVersion(path))+'"></link>');
}

function shuffle(array)
{
    for(var j, x, i = array.length; i; j = parseInt(Math.random() * i), x = array[--i], array[i] = array[j], array[j] = x);
};