var page = require('webpage').create();
page.open('http://127.0.0.1:8888/r.preview/splash/22x17', function()
{
	window.setTimeout(function () {
                page.render('github.png');
				phantom.exit();
            }, 2000);
});