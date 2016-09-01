const centerVStartPoint = new Point();
var centerVLowerTrapezoidLength = 0;
const frontVDrawStartPoint = new Point();
const startOfTopCrossPipe = new Point();

function drawPipes(canvas) {
	drawVs(canvas);
	drawCrossPipes(canvas);
}

function drawVs(canvas)
{
	drawV(canvas, -1);
	drawV(canvas, 1);
	drawCenterV(canvas);
}

function drawV(canvas, direction)
{
	const context = canvas.getContext("2d");
	context.lineWidth = DEFAULT_LINE_WIDTH;
	context.fillStyle = PIPE_COLOR;
	context.beginPath();
	
	const startY = START_POINT.getY() + OFFSET_FROM_AXLE;
	const offsetH = BOTTOM_PIPE_LENGTH/2.0;
	const startX = START_POINT.getX();
	const offsetRadius = Math.abs(PIPE_RADIUS / Math.cos(FORK_ANGLE_RAD));
	
	const drawPoint = new Point(startX + direction * (offsetH + offsetRadius), startY);
	const drawVecX = new Point((-direction)*offsetRadius*2, 0);
	const drawVecY = new Point(0, -direction*PIPE_DIAM/2.0);
	drawVecY.rotateBy(direction*FORK_ANGLE_RAD);
	drawVecY.setLength(offsetRadius + PIPE_OVERHANG);
	if( direction == 1 ) drawVecY.negate();
	drawPoint.translateBy(drawVecY);
	drawPoint.moveTo(context);
	frontVDrawStartPoint.copy(drawPoint);
	frontVDrawStartPoint.incX(-offsetRadius);
	const measurePoint = new Point();
	measurePoint.copy(drawPoint);
	startMeasurement(measurePoint);
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	
	const countDelta = direction == 1 ? 2 : 1;
	const vLength = (CROSS_PIPE_COUNT-2)*(RACK_HEIGHT / (CROSS_PIPE_COUNT-countDelta));
	drawVecY.negate();
	drawVecY.setLength(vLength / Math.cos(FORK_ANGLE_RAD) + offsetRadius*2 + PIPE_OVERHANG*2);
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	drawVecX.negate();
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	measurePoint.set(measurePoint.getX(), drawPoint.getY());
	endMeasurement(drawPoint, 9*direction, MeasureType.LENGTH)
	
	
	context.closePath();
	context.stroke();
	context.fill();
}

function drawCenterV(canvas)
{
	const context = canvas.getContext("2d");
	context.lineWidth = DEFAULT_LINE_WIDTH*2;
	context.fillStyle = PIPE_COLOR;
	context.beginPath();
	
	const offsetH = BOTTOM_PIPE_LENGTH/2.0;
	const startX = START_POINT.getX();
	const offsetRadius = Math.abs(PIPE_RADIUS / Math.cos(FORK_ANGLE_RAD));
	const crossPipeGap = RACK_HEIGHT / (CROSS_PIPE_COUNT-1);
	const startXOffset = ((crossPipeGap - OFFSET_FROM_AXLE) * Math.tan(FORK_ANGLE_RAD));
	
	const drawPoint = new Point(startX+startXOffset, START_POINT.getY() + OFFSET_FROM_AXLE - crossPipeGap);
	centerVStartPoint.copy(drawPoint);
	centerVStartPoint.incX(offsetRadius);
	const startPointOfCenterV = new Point(drawPoint.getX() + offsetRadius, drawPoint.getY());
	const drawVecX = new Point(startXOffset, 0);
	const drawVecY = new Point(0, PIPE_DIAM/2.0);
	drawVecY.rotateBy(-FORK_ANGLE_RAD);
	drawVecY.setLength(offsetRadius + PIPE_OVERHANG);
	drawPoint.translateBy(drawVecY);
	drawPoint.moveTo(context);
	
	const measurePoint = new Point();
	measurePoint.copy(drawPoint);
	startMeasurement(measurePoint);
	
	drawVecX.setLength(offsetRadius*2);
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	
	const vLength = crossPipeGap*(CROSS_PIPE_COUNT-2) + CENTER_V_OVERHANG;
	drawVecY.negate();
	drawVecY.setLength(vLength / Math.cos(FORK_ANGLE_RAD) + offsetRadius*2 + PIPE_OVERHANG);
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	drawVecX.negate();
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	
	endMeasurement(drawPoint, -17, MeasureType.LENGTH);
	
	context.closePath();
	context.stroke();
	context.fill();
}

function drawCrossPipes(canvas)
{
	const offsetY = RACK_HEIGHT / (CROSS_PIPE_COUNT-1);
	const startY = START_POINT.getY() + OFFSET_FROM_AXLE;
	const offsetRadius = (PIPE_RADIUS / Math.cos(FORK_ANGLE_RAD));
	const overhang = PIPE_OVERHANG*2 + 2*offsetRadius;
	var currY = startY;
	
	for( var i = 0; i < CROSS_PIPE_COUNT; i++ )
	{
		const adjacent = currY - startY;
		const pipeLength = overhang + BOTTOM_PIPE_LENGTH + 2*Math.abs(Math.tan(FORK_ANGLE_RAD) * adjacent);
		const drawPoint = new Point(START_POINT.getX(), currY);
		const specialPoint = new Point();
		var specialPipeLength = 0;
		
		if( i == CROSS_PIPE_COUNT-1 )
		{
			specialPipeLength = centerVLowerTrapezoidLength + overhang + 2*Math.abs(Math.tan(FORK_ANGLE_RAD) * (offsetY*(CROSS_PIPE_COUNT-2)))
			specialPoint.copy(drawPoint);
			specialPoint.setX(centerVStartPoint.getX() + centerVLowerTrapezoidLength/2.0);
			drawCrossPipe(canvas, specialPoint, specialPipeLength, i, pipeLength);
		}
		else
		{
			if( i == 1 )
			{
				centerVLowerTrapezoidLength = (drawPoint.getX() + pipeLength/2 - overhang/2) - centerVStartPoint.getX();
				
				const newOffsetPoint = new Point();
				newOffsetPoint.copy(drawPoint);
				newOffsetPoint.incX(-pipeLength/2.0);
				startMeasurement(newOffsetPoint);
				newOffsetPoint.copy(centerVStartPoint);
				newOffsetPoint.incX(offsetRadius);
				endMeasurement(newOffsetPoint, 20, MeasureType.OFFSET);
			}
			
			drawCrossPipe(canvas, drawPoint, pipeLength, i, pipeLength);
		}
		
		if( i < CROSS_PIPE_COUNT-1 )
		{
			const offset = 15 - (currY - frontVDrawStartPoint.getY())/2;
			//startMeasurement(frontVDrawStartPoint);
			const endMeasurePoint = new Point();
			endMeasurePoint.copy(drawPoint);
			endMeasurePoint.incX(pipeLength/2 - overhang/2);
// 			endMeasurement(endMeasurePoint, offset);
			
			//if( i == 0 )
			{
				const adjacent_imaginary = (currY-PIPE_RADIUS) - startY;
				const pipeLength_imaginary = overhang + BOTTOM_PIPE_LENGTH + 2*Math.abs(Math.tan(FORK_ANGLE_RAD) * adjacent_imaginary);
				
				startMeasurement(frontVDrawStartPoint);
				endMeasurePoint.copy(drawPoint);
				endMeasurePoint.incY(-PIPE_RADIUS);
				endMeasurePoint.incX(pipeLength_imaginary/2 - overhang/2);
				endMeasurement(endMeasurePoint, offset, MeasureType.OFFSET);
			}
		}
		
		currY -= offsetY;
		
		if( i == CROSS_PIPE_COUNT-1 ) {
			
			console.log(offsetRadius);
			
			const effectiveTopLength = specialPipeLength - overhang - offsetRadius*2 - PIPE_DIAM - PIPE_OVERHANG*2.0;
			specialPoint.incX(-effectiveTopLength/2);
			const stepSize = effectiveTopLength/(TOP_PIPE_COUNT-1);
			for( var j = 0; j < TOP_PIPE_COUNT; j++ ) {
				
				drawSideOfPipe(canvas, specialPoint);
				
				//if( j > 0 )
				{
					startMeasurement(startOfTopCrossPipe);
					const endMeasurePoint = new Point();
					endMeasurePoint.copy(specialPoint);
					endMeasurePoint.incX(-PIPE_RADIUS);
					endMeasurement(endMeasurePoint, (1 + (TOP_PIPE_COUNT-j) *2), MeasureType.OFFSET);
				}
				
				specialPoint.incX(stepSize);
			}
		}
	}
}

function drawCrossPipe(canvas, position, pipeLength, index, defaultPipeLength)
{
	const context = canvas.getContext("2d");
	
	context.beginPath();
	
	context.fillStyle = PIPE_COLOR;
	context.lineWidth = DEFAULT_LINE_WIDTH;
	
	const angledDiam = Math.abs(PIPE_DIAM / Math.cos(FORK_ANGLE_RAD));
	
	const squeeze = Math.abs((PIPE_DIAM * Math.tan(FORK_ANGLE_RAD)))/2;
	
	const drawVecX = new Point(pipeLength/2.0, 0);
	const drawVecY = new Point(0, -angledDiam/2.0);
	const drawPoint = new Point();
	const measurePoint_local = new Point();
	
	drawPoint.copy(position);
	drawVecY.rotateBy(FORK_ANGLE_RAD);
	drawPoint.translateBy(drawVecX);
	drawPoint.translateBy(drawVecY);
	drawPoint.moveTo(context);
	
	//if( index < CROSS_PIPE_COUNT-1 )
	{
		measurePoint_local.copy(drawPoint);
		measurePoint_local.incX(-squeeze);
		startMeasurement(measurePoint_local);
	}
	
	drawVecY.setLength(angledDiam);
	drawVecY.negate();
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	
	drawVecY.setLength(angledDiam/2.0);
	drawVecX.negate();
	drawPoint.copy(position);
	drawVecY.rotateBy(-FORK_ANGLE_RAD*2);
	drawPoint.translateBy(drawVecX);
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	drawVecY.setLength(angledDiam);
	drawVecY.negate();
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	//if( index < CROSS_PIPE_COUNT-1 )
	{
		measurePoint_local.copy(drawPoint);
		measurePoint_local.incX(squeeze);
		endMeasurement(measurePoint_local, -(defaultPipeLength/2+index*6), MeasureType.LENGTH);
		
		if( index == CROSS_PIPE_COUNT-1) {
			startOfTopCrossPipe.copy(measurePoint_local);
			startOfTopCrossPipe.incX(pipeLength);
			startOfTopCrossPipe.incY(PIPE_RADIUS);
		}
	}
	
	
	context.closePath();
	context.fill();
	context.stroke();
}

function drawSideOfPipe(canvas, point) {
	
	const context = canvas.getContext("2d");
	context.beginPath();
	context.lineWidth = DEFAULT_LINE_WIDTH;
	
	context.arc(point.getX()*PIXELS_PER_INCH,point.getY()*PIXELS_PER_INCH,PIPE_RADIUS*PIXELS_PER_INCH,0,2*Math.PI);
	
	context.stroke();
}