var centerVStartPoint = new Point();
var centerVLowerTrapezoidLength = 0;
var frontVDrawStartPoint = new Point();
var startOfTopCrossPipe = new Point();

var HORIZONTAL_MEASURE_SCALE = 2;

function getCrossGapMultiplier() {
	var crossPipeGapMultiplier = 1;
	if( CROSS_PIPE_COUNT > 6 ) {
		crossPipeGapMultiplier = 2;
	}
	if( CROSS_PIPE_COUNT > 9 ) {
		crossPipeGapMultiplier = 3;
	}
	
	return crossPipeGapMultiplier;
}

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
	var context = canvas.getContext("2d");
	context.lineWidth = DEFAULT_LINE_WIDTH;
	context.fillStyle = PIPE_COLOR;
	context.beginPath();
	
	var startY = START_POINT.getY() + OFFSET_FROM_AXLE;
	var offsetH = BOTTOM_PIPE_LENGTH/2.0;
	var startX = START_POINT.getX();
	var offsetRadius = Math.abs(PIPE_RADIUS() / Math.cos(FORK_ANGLE_RAD()));
	
	var drawPoint = new Point(startX + direction * (offsetH + offsetRadius), startY);
	var drawVecX = new Point((-direction)*offsetRadius*2, 0);
	var drawVecY = new Point(0, -direction*PIPE_DIAM/2.0);
	drawVecY.rotateBy(direction*FORK_ANGLE_RAD());
	drawVecY.setLength(offsetRadius + PIPE_OVERHANG);
	if( direction == 1 ) drawVecY.negate();
	drawPoint.translateBy(drawVecY);
	drawPoint.moveTo(context);
	frontVDrawStartPoint.copy(drawPoint);
	frontVDrawStartPoint.incX(-offsetRadius);
	var measurePoint = new Point();
	measurePoint.copy(drawPoint);
	startMeasurement(measurePoint);
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	
	var countDelta = direction == 1 ? 2 : 1;
	var vLength = (CROSS_PIPE_COUNT-2)*(RACK_HEIGHT / (CROSS_PIPE_COUNT-countDelta));
	drawVecY.negate();
	drawVecY.setLength(vLength / Math.cos(FORK_ANGLE_RAD()) + offsetRadius*2 + PIPE_OVERHANG*2);
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	drawVecX.negate();
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	measurePoint.set(measurePoint.getX(), drawPoint.getY());
	var measureOffset = direction*getMeasurementOffset(3, MeasureType.LENGTH);
	endMeasurement(drawPoint, measureOffset, MeasureType.LENGTH)
	
	
	context.closePath();
	context.stroke();
	context.fill();
}

function drawCenterV(canvas)
{
	var context = canvas.getContext("2d");
	context.lineWidth = DEFAULT_LINE_WIDTH*2;
	context.fillStyle = PIPE_COLOR;
	context.beginPath();
	
	var crossPipeGapMultiplier = getCrossGapMultiplier();
	var offsetH = BOTTOM_PIPE_LENGTH/2.0;
	var startX = START_POINT.getX();
	var offsetRadius = Math.abs(PIPE_RADIUS() / Math.cos(FORK_ANGLE_RAD()));
	var crossPipeGap = RACK_HEIGHT / (CROSS_PIPE_COUNT-1);
	var startXOffset = ((crossPipeGap*crossPipeGapMultiplier - OFFSET_FROM_AXLE) * Math.tan(FORK_ANGLE_RAD()));
	
	
	var drawPoint = new Point(startX+startXOffset, START_POINT.getY() + OFFSET_FROM_AXLE - crossPipeGap*crossPipeGapMultiplier);
	centerVStartPoint.copy(drawPoint);
	centerVStartPoint.incX(offsetRadius);
	var startPointOfCenterV = new Point(drawPoint.getX() + offsetRadius, drawPoint.getY());
	var drawVecX = new Point(startXOffset, 0);
	var drawVecY = new Point(0, PIPE_DIAM/2.0);
	drawVecY.rotateBy(-FORK_ANGLE_RAD());
	drawVecY.setLength(offsetRadius + PIPE_OVERHANG);
	drawPoint.translateBy(drawVecY);
	drawPoint.moveTo(context);
	
	var measurePoint = new Point();
	measurePoint.copy(drawPoint);
	startMeasurement(measurePoint);
	
	drawVecX.setLength(offsetRadius*2);
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	
	var lengthSubtraction = 2 + (crossPipeGapMultiplier-1);
	var vLength = crossPipeGap*(CROSS_PIPE_COUNT-lengthSubtraction) + CENTER_V_OVERHANG;
	drawVecY.negate();
	drawVecY.setLength(vLength / Math.cos(FORK_ANGLE_RAD()) + offsetRadius*2 + PIPE_OVERHANG);
	drawPoint.translateBy(drawVecY);
	drawPoint.lineTo(context);
	
	var topCrossBarVec = new Point();
	topCrossBarVec.copy(drawVecY);
	topCrossBarVec.setLength(PIPE_OVERHANG+PIPE_RADIUS());
	topCrossBarVec.negate();
	var topCrossBarPoint = new Point();
	topCrossBarPoint.copy(drawPoint);
	topCrossBarPoint.translateBy(topCrossBarVec);
	topCrossBarVec.setToPerpVector(-1);
	topCrossBarVec.setLength(PIPE_RADIUS());
	topCrossBarPoint.translateBy(topCrossBarVec);
	
	drawVecX.negate();
	drawPoint.translateBy(drawVecX);
	drawPoint.lineTo(context);
	
	var measureOffset = -getMeasurementOffset(7, MeasureType.LENGTH);
	endMeasurement(drawPoint, measureOffset, MeasureType.LENGTH);
	
	context.closePath();
	context.stroke();
	context.fill();
	
	drawSideOfPipe(canvas, topCrossBarPoint);
}

function drawCrossPipes(canvas)
{
	var offsetY = RACK_HEIGHT / (CROSS_PIPE_COUNT-1);
	var startY = START_POINT.getY() + OFFSET_FROM_AXLE;
	var offsetRadius = (PIPE_RADIUS() / Math.cos(FORK_ANGLE_RAD()));
	var overhang = PIPE_OVERHANG*2 + 2*offsetRadius;
	var currY = startY;
	
	for( var i = 0; i < CROSS_PIPE_COUNT; i++ )
	{
		var adjacent = currY - startY;
		var pipeLength = overhang + BOTTOM_PIPE_LENGTH + 2*Math.abs(Math.tan(FORK_ANGLE_RAD()) * adjacent);
		var drawPoint = new Point(START_POINT.getX(), currY);
		var specialPoint = new Point();
		var specialPipeLength = 0;
		
		if( i == CROSS_PIPE_COUNT-1 )
		{
			var lengthSubtraction = 2 + (getCrossGapMultiplier()-1);
			specialPipeLength = centerVLowerTrapezoidLength + overhang + 2*Math.abs(Math.tan(FORK_ANGLE_RAD()) * (offsetY*(CROSS_PIPE_COUNT-lengthSubtraction)))
			specialPoint.copy(drawPoint);
			specialPoint.setX(centerVStartPoint.getX() + centerVLowerTrapezoidLength/2.0);
			drawCrossPipe(canvas, specialPoint, specialPipeLength, i, pipeLength);
		}
		else
		{
			if( i == getCrossGapMultiplier() )
			{
				centerVLowerTrapezoidLength = (drawPoint.getX() + pipeLength/2 - overhang/2) - centerVStartPoint.getX();
				
				var newOffsetPoint = new Point();
				newOffsetPoint.copy(drawPoint);
				newOffsetPoint.incX(-pipeLength/2.0);
				startMeasurement(newOffsetPoint);
				newOffsetPoint.copy(centerVStartPoint);
				newOffsetPoint.incX(offsetRadius);
				
				var measureOffset = getMeasurementOffset(CROSS_PIPE_COUNT+1, MeasureType.LENGTH, HORIZONTAL_MEASURE_SCALE);
				var pipeOffset = startY - currY;
				endMeasurement(newOffsetPoint, (measureOffset + pipeOffset) - PIPE_RADIUS(), MeasureType.OFFSET);
			}
			
			drawCrossPipe(canvas, drawPoint, pipeLength, i, pipeLength);
		}
		
		if( i < CROSS_PIPE_COUNT-1 )
		{
			var offset = 15 - (currY - frontVDrawStartPoint.getY())/2;
			//startMeasurement(frontVDrawStartPoint);
			var endMeasurePoint = new Point();
			endMeasurePoint.copy(drawPoint);
			endMeasurePoint.incX(pipeLength/2 - overhang/2);
// 			endMeasurement(endMeasurePoint, offset);
			
			//if( i == 0 )
			{
				var adjacent_imaginary = (currY-PIPE_RADIUS()) - startY;
				var pipeLength_imaginary = overhang + BOTTOM_PIPE_LENGTH + 2*Math.abs(Math.tan(FORK_ANGLE_RAD()) * adjacent_imaginary);
				
				startMeasurement(frontVDrawStartPoint);
				endMeasurePoint.copy(drawPoint);
				endMeasurePoint.incY(-PIPE_RADIUS());
				endMeasurePoint.incX(pipeLength_imaginary/2 - overhang/2);
				var measureOffset = getMeasurementOffset((CROSS_PIPE_COUNT - (i+1)) + 3, MeasureType.OFFSET);
				endMeasurement(endMeasurePoint, measureOffset, MeasureType.OFFSET);
			}
		}
		
		currY -= offsetY;
		
		if( i == CROSS_PIPE_COUNT-1 ) {
			
			//console.log(offsetRadius);
			
			var effectiveTopLength = specialPipeLength - overhang - offsetRadius*2 - PIPE_DIAM - PIPE_OVERHANG*2.0;
			specialPoint.incX(-effectiveTopLength/2);
			var stepSize = effectiveTopLength/(TOP_PIPE_COUNT-1);
			for( var j = 0; j < TOP_PIPE_COUNT; j++ ) {
				
				drawSideOfPipe(canvas, specialPoint);
				
				//if( j > 0 )
				{
					startMeasurement(startOfTopCrossPipe);
					var endMeasurePoint = new Point();
					endMeasurePoint.copy(specialPoint);
					endMeasurePoint.incX(-PIPE_RADIUS());
					
					var measureOffset = getMeasurementOffset(j, MeasureType.OFFSET, HORIZONTAL_MEASURE_SCALE);
					endMeasurement(endMeasurePoint, measureOffset, MeasureType.OFFSET);
				}
				
				specialPoint.incX(stepSize);
			}
		}
	}
}

function drawCrossPipe(canvas, position, pipeLength, index, defaultPipeLength)
{
	var startY = START_POINT.getY() + OFFSET_FROM_AXLE;
	var context = canvas.getContext("2d");
	
	context.beginPath();
	
	context.fillStyle = PIPE_COLOR;
	context.lineWidth = DEFAULT_LINE_WIDTH;
	
	var angledDiam = Math.abs(PIPE_DIAM / Math.cos(FORK_ANGLE_RAD()));
	
	var squeeze = Math.abs((PIPE_DIAM * Math.tan(FORK_ANGLE_RAD())))/2;
	
	var drawVecX = new Point(pipeLength/2.0, 0);
	var drawVecY = new Point(0, -angledDiam/2.0);
	var drawPoint = new Point();
	var measurePoint_local = new Point();
	
	drawPoint.copy(position);
	drawVecY.rotateBy(FORK_ANGLE_RAD());
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
	drawVecY.rotateBy(-FORK_ANGLE_RAD()*2);
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
		var measureOffset = getMeasurementOffset(index+1, MeasureType.LENGTH, HORIZONTAL_MEASURE_SCALE);
		var pipeOffset = startY - position.getY();
		endMeasurement(measurePoint_local, -(measureOffset + pipeOffset), MeasureType.LENGTH);
		
		if( index == CROSS_PIPE_COUNT-1) {
			startOfTopCrossPipe.copy(measurePoint_local);
			startOfTopCrossPipe.incX(pipeLength);
			startOfTopCrossPipe.incY(PIPE_RADIUS());
		}
	}
	
	
	context.closePath();
	context.fill();
	context.stroke();
}

function drawSideOfPipe(canvas, point) {
	
	var context = canvas.getContext("2d");
	context.beginPath();
	context.lineWidth = DEFAULT_LINE_WIDTH;
	
	context.arc(point.getX()*PIXELS_PER_INCH,point.getY()*PIXELS_PER_INCH,PIPE_RADIUS()*PIXELS_PER_INCH,0,2*Math.PI);
	
	context.stroke();
}