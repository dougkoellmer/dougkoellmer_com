function addTouchActivePsuedoClass($object)
{
	$object.on("touchstart", function()
	{
		$(this).addClass("touch_active");
    });
	
	$object.on("touchend", function()
	{
		$(this).removeClass("touch_active");
    });
}