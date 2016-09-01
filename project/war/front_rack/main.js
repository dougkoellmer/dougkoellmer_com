

function main() {
	
	const gridCanvas = document.getElementById("grid_canvas");
	gridCanvas.width = gridCanvas.clientWidth;
	gridCanvas.height = gridCanvas.clientHeight;
	
	const defaultCanvas = document.getElementById("default_canvas");
	defaultCanvas.width = defaultCanvas.clientWidth;
	defaultCanvas.height = defaultCanvas.clientHeight;
	
	
	const bodyElem = document.body;
	
	if( SHOWING_RIGHT_SIDE == true )
	{
		setTimeout(function() {
			document.body.style.transform = "scale(-1,1);";
		}, 1000);
		
	}
	
	
	

	drawGrid(gridCanvas);
	drawWheel(defaultCanvas);
	drawFrame(defaultCanvas);
	drawPipes(defaultCanvas);
}



main();
