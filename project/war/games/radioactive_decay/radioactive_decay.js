
var activeAtoms = [];
var deadAtoms = [];

var lastSliderValue = 0;
var lastTime = 0;


var atomManager = null;
var fpsViewer = null;
var atomContainer = null;

$(document).ready(function()
{
	var $sliderBg = $("#slider_bg");
	$sliderBg.load(function()
	{
		placeUI();
	});
	$sliderBg.attr("src", calcRandomVersion("img/slider_bg.png"));
	
	var $sliderHandle = $("#slider_handle_image");
	$sliderHandle.load(function()
	{
	});
	$sliderHandle.attr("src", calcRandomVersion("img/slider_button.png"));
	
	var sliderOptions =
	{
		step:SLIDER_STEP,
		min:0,
		max:SLIDER_MAX,
		slide:onSliderChange
	};
	
	$slider = $( "#slider" );
	$slider.slider(sliderOptions);
	
	setAtomsLeftLabel(ATOM_COUNT);
	setTimeLabel(0);
	
	placeUI();
	
	var $atomContainer = $("#atom_container");
	atomManager = new AtomManager($atomContainer.get(0), $atomContainer.width(), $atomContainer.height());
	
	lastTime = getTime();
	
	fpsViewer = new FpsViewer($(".fps_viewer")[0]);
	
	//$atomContainer = $("#ato_container");
	
	setInterval(update, Math.round(1000 / TARGET_FPS));
});

function getTime()
{
	return (new Date()).getTime();
}

function update()
{
	var currentTime = getTime();
	var timeStep = (currentTime - lastTime) / 1000;
	lastTime = currentTime;
	
	//$atomContainer.css("display", "none");
	
	atomManager.update(timeStep);
	
	setAtomsLeftLabel(atomManager.getLiveCount());
	
	//$atomContainer.css("display", "");
	
	//fpsViewer.update(timeStep);
}

$(window).resize(function()
{
	placeUI();
	
	setTimeout(function(){atomManager.onScreenResize($atomContainer.width(), $atomContainer.height())}, 1);
});

function onSliderChange(event, ui)
{
	var value = ui.value;
	
	var time = (ui.value / SLIDER_MAX) * TOTAL_TIME;
	setTimeLabel(time);
	
	var timeInBillions = time * ONE_BILLION;
	
	if( value > lastSliderValue )
	{
		atomManager.onTimeChange(timeInBillions, 1);
	}
	else if( value < lastSliderValue )
	{
		atomManager.onTimeChange(timeInBillions, -1);
	}
	
	lastSliderValue = value;
}

function setTimeLabel(value_float)
{
	$atomTracker = $("#time_tracker");
	$atomTracker.html("Time: " + value_float.toFixed(2) + " Billion Years");
}

function setAtomsLeftLabel(value_int)
{
	$atomTracker = $("#atom_tracker");
	$atomTracker.html("Atoms: " + value_int);
}
	