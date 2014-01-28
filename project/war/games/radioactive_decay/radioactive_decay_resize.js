function placeUI()
{
	var screenWidth = $(document.body).width();
	var screenHeight = $(document.body).height();

	var $instructionsWrapper = $("#instructions_wrapper");
	var $sliderWrapper = $("#slider_wrapper");
	var $toolbarWrapper = $("#toolbar_wrapper");
	var $atomContainerCell = $("#atom_container_cell");
	
	var sliderWrapperHeight = $sliderWrapper.height();
	var sliderWrapperWidth = $sliderWrapper.width();
	
	var totalOtherHeight = $instructionsWrapper.height() + sliderWrapperHeight + $toolbarWrapper.height();
	
	var buffer = ATOM_RADIUS + VIBRATION_RADIUS;
	var atomContainerCellHeight = screenHeight - totalOtherHeight;
	$atomContainerCell.height(atomContainerCellHeight);
	
	var atomContainerCellWidth = $atomContainerCell.width();
	
	var screenWrapperPadding = 20;
	$atomContainer = $("#atom_container");
	$atomContainer.width(screenWidth - screenWrapperPadding - buffer*2);
	$atomContainer.height(screenHeight - totalOtherHeight - buffer*2);
	$atomContainer.css("left", buffer);
	$atomContainer.css("top", buffer);
	
	$sliderBg = $("#slider_bg");
	$slider = $("#slider");
	
	$sliderBg.css("left", sliderWrapperWidth/2 - $sliderBg.width()/2);
	$sliderBg.css("top", sliderWrapperHeight/2 - $sliderBg.height()/2);
	
	$slider.css("left", sliderWrapperWidth/2 - $slider.width()/2);
	$slider.css("top", sliderWrapperHeight/2 - $slider.height()/2);
}