

function main() {
	
	var gridCanvas = document.getElementById("grid_canvas");
	gridCanvas.width = gridCanvas.clientWidth;
	gridCanvas.height = gridCanvas.clientHeight;
	
	var defaultCanvas = document.getElementById("default_canvas");
	defaultCanvas.width = defaultCanvas.clientWidth;
	defaultCanvas.height = defaultCanvas.clientHeight;
	
	var measureCanvas = document.getElementById("measure_canvas");
	
	
	var bodyElem = document.body;
	
	if( SHOWING_RIGHT_SIDE == true )
	{
		setTimeout(function() {
			document.body.style.transform = "scale(-1,1);";
		}, 1000);
		
	}
	
	function nearestHalf(value) {
		return (Math.round(value * 2) / 2).toFixed(1)
	}
	
	function nearestTenth(value) {
		return (Math.round(value * 10) / 10).toFixed(1)
	}
	
	function toInteger(value) {
		return Math.round(value);
	}
	
	var Unit_Inches = "in";
	var Unit_Degrees = "deg";
	
	var sliderIndex = 0;
	
	newSlider("FORK_ANGLE", 10, 30, nearestHalf, .5, Unit_Degrees);
	newSlider("WHEEL_DIAM", 26, 31, nearestHalf, .5, Unit_Inches);
	newSlider("PIXELS_PER_INCH", 10, 15, toInteger, 1, "");
	newSlider("CROSS_PIPE_COUNT", 3, 10, toInteger, 1, "");
	newSlider("TOP_PIPE_COUNT", 2, 6, toInteger, 1, "");
	newSlider("TIRE_THICKNESS", 1, 4, nearestTenth, .1, Unit_Inches);
	newSlider("FORK_LENGTH", 15, 20, nearestTenth, .1, Unit_Inches);
	newSlider("PIPE_OVERHANG", .1, 2, nearestTenth, .1, Unit_Inches);
	newSlider("PIPE_DIAM", .5, 2, nearestTenth, .1, Unit_Inches);
	
	
	
	function draw() {
		//console.log("tire_thickness " + TIRE_THICKNESS);
		//console.log("typeof " + (typeof TIRE_THICKNESS));
		
		gridCanvas.getContext("2d").clearRect(0, 0, gridCanvas.width, gridCanvas.height);
		defaultCanvas.getContext("2d").clearRect(0, 0, defaultCanvas.width, defaultCanvas.height);
		measureCanvas.getContext("2d").clearRect(0, 0, measureCanvas.width, measureCanvas.height);
		
		drawGrid(gridCanvas);
		drawWheel(gridCanvas);
		drawFrame(defaultCanvas);
		drawPipes(defaultCanvas);
	}
	
	function newSlider(configVar, min, max, rounder, step, unit) {
		
		unit = unit ? unit : "";
		var sliderElem = document.createElement('div');
		sliderElem.className = "slider";
		var sliderParent = document.getElementById("slider_parent");
		
		var sliderElemWrapper = document.createElement('div');
		sliderElemWrapper.className = "slider_wrapper";
		var sliderParent = document.getElementById("slider_parent");
		sliderParent.appendChild(sliderElemWrapper);
		sliderElemWrapper.appendChild(sliderElem);
		
		var sliderElemValue = document.createElement('div');
		sliderElemValue.className = "slider_value";
		sliderElemWrapper.appendChild(sliderElemValue);
		
		if( sliderIndex % 2 == 0 ) {
			sliderElemWrapper.style.backgroundColor = "#eeeeee";
		} else {
			sliderElemWrapper.style.backgroundColor = "#dddddd";
		}
		sliderIndex++;
		
		function updateSliderElemValue() {
			var displayValue = rounder(window[configVar]);
			sliderElemValue.innerHTML = configVar + " = " + displayValue + " " + unit;
		}
		
		updateSliderElemValue();
		
		function onSlide() {
			window[configVar] = parseFloat(sliderElem.noUiSlider.get());
			updateSliderElemValue();
			draw();
		}
		
		var startValue = window[configVar];
		var step = step ? step : .5;
		
		noUiSlider.create(sliderElem, {
			start: [startValue],
			connect: false,
			range: {
				'min': min,
				'max': max
			},
			step:step
		});
	
		sliderElem.noUiSlider.on('slide', onSlide);
		
		
		return sliderElem;
	}

	
	
	draw();
}



main();
