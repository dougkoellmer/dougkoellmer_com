function drawFrame(canvas) {
	
	var context = canvas.getContext("2d");
	
	context.beginPath();
	
	context.fillStyle = FRAME_COLOR;
	context.lineWidth = DEFAULT_LINE_WIDTH;
	
	
	var drawVec = new Point(FORK_BOTTOM_DIAM/2.0, 0);
	drawVec.rotateBy(-FORK_ANGLE_RAD());
	
	var drawPoint = new Point();
	
	
	
	
	drawPoint.copy(START_POINT);
	drawPoint.translateBy(drawVec);
	drawVec.setToPerpVector(-1);
	drawPoint.translateBy(drawVec);
	drawPoint.moveTo(context);
	
	
	
	drawPoint.copy(START_POINT);
	drawPoint.translateBy(drawVec);
	drawVec.setToPerpVector(-1);
	drawPoint.translateBy(drawVec);
	drawPoint.lineTo(context);
	
	
	drawVec.set(FORK_TOP_DIAM/2.0, 0);
	drawVec.rotateBy(-FORK_ANGLE_RAD());
	drawVec.negate();
	
	drawPoint.copy(START_POINT);
	drawPoint.translateBy(drawVec);
	drawVec.setToPerpVector(-1);
	drawVec.setLength(FORK_LENGTH - FORK_BOTTOM_DIAM/2.0);
	drawPoint.translateBy(drawVec);
	drawPoint.lineTo(context);
	
	
	
	drawVec.set(FORK_TOP_DIAM/2.0, 0);
	drawVec.rotateBy(-FORK_ANGLE_RAD());
	
	drawPoint.copy(START_POINT);
	drawPoint.translateBy(drawVec);
	drawVec.setToPerpVector(1);
	drawVec.setLength(FORK_LENGTH - FORK_BOTTOM_DIAM/2.0);
	drawPoint.translateBy(drawVec);
	drawPoint.lineTo(context);
	
	
	context.closePath();
	
	
	context.fill();
	context.stroke();
	
}
