function drawGrid(canvas) {
	
	const context = canvas.getContext("2d");
	console.log(context);
	
	context.lineWidth = GRID_LINE_WIDTH;
	context.strokeStyle = GRID_COLOR;
	
	context.beginPath();
	
	var x = 0, y = 0;
	const delta = GRID_SIZE * PIXELS_PER_INCH;
	
	for( x = 0; x < canvas.width; x += delta )
	{
		context.moveTo(x, 0);
		context.lineTo(x, canvas.height);
	}
	
	for( y = 0; y < canvas.height; y += delta )
	{
		context.moveTo(0, y);
		context.lineTo(canvas.width, y);
	}
	
	context.stroke();
}
