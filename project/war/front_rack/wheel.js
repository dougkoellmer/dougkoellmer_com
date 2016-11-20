function drawWheel(canvas) {
	
	var context = canvas.getContext("2d");
	
	var rimRadius = WHEEL_DIAM/2 - TIRE_THICKNESS - RIM_THICKNESS;
	var rotationPoint = new Point(START_POINT.getX(), START_POINT.getY() - rimRadius);
	var angle = (360 / SPOKE_COUNT) * (Math.PI / 180);
	
	
	// context.beginPath();
//
// 	context.lineWidth = 1;
// 	context.strokeStyle = SPOKE_COLOR;
//
// 	context.fillStyle = TIRE_COLOR;
// 	START_POINT.circle(context, rimRadius + RIM_THICKNESS + WHEEL_THICKNESS);
//
// 	context.fill();
// 	context.stroke();
	
	
	context.beginPath();
	context.fillStyle = TIRE_COLOR;
	START_POINT.circle(context, rimRadius + RIM_THICKNESS + TIRE_THICKNESS);
	START_POINT.circleBackwards(context, rimRadius);
	
	context.fill();
	context.stroke();
	
	
	context.beginPath();
	context.fillStyle = RIM_COLOR;
	START_POINT.circle(context, rimRadius + RIM_THICKNESS);
	START_POINT.circleBackwards(context, rimRadius);
	
	context.fill();
	context.stroke();
	
	
	
	
	
	
	context.beginPath();
	
	context.lineWidth = 1;
	context.fillStyle = "rgba(0, 0, 0, 0)"
	START_POINT.circle(context, rimRadius);

	context.fill();
	context.stroke();
	
	
	
	context.lineWidth = SPOKE_WIDTH;
	context.strokeStyle = SPOKE_COLOR;
	
	context.beginPath();
	
	for( var i = 0; i < SPOKE_COUNT; i++ )
	{
		START_POINT.moveTo(context);
		rotationPoint.lineTo(context);
		
		rotationPoint.rotateBy(angle, START_POINT);
	}
	
	context.stroke();
	
	
}
