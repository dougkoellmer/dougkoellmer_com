const measureCanvas = document.getElementById("measure_canvas");
measureCanvas.width = measureCanvas.clientWidth;
measureCanvas.height = measureCanvas.clientHeight;
if( SHOWING_RIGHT_SIDE == true )
{
	//measureCanvas.style.scale="-1,1;
}
const measureContext = measureCanvas.getContext("2d");
measureContext.lineWidth = DEFAULT_LINE_WIDTH;
measureContext.setLineDash([5, 5]);
measureContext.font = "bold 15px Courier New";
measureContext.textAlign="center";
const measurePoint = new Point();
measureContext.fillStyle = "black";
measureContext.strokeStyle = "rgba(0, 0, 0, .75)";

function drawThePoint(point)
{
	measurePoint.copy(point);
	measureContext.beginPath();
	measurePoint.moveTo(measureContext);
	measurePoint.incY(1);
	measurePoint.lineTo(measureContext);
	measureContext.stroke();
}

function startMeasurement(point)
{
	measureContext.beginPath();
	point.moveTo(measureContext);
	measurePoint.copy(point);
}

const MeasureType = 
{
	LENGTH:"LN",
	OFFSET:"OS"
}

function toFraction(decimal) {
	const blankLengthNearest16 = (Math.round(decimal * 16) / 16);
	const blankLengthMod = blankLengthNearest16 % 1.0;
	const blankLengthFraction = Math.round(16 * blankLengthMod);
	var lengthString = "";
	if( blankLengthFraction == 0.0 )
	{
		lengthString += blankLengthNearest16;
	}
	else
	{
		lengthString += "" + (blankLengthNearest16 - blankLengthMod) + " " + blankLengthFraction + "/16";
	}
	
	return lengthString;
}

function endMeasurement(point, offset, measureType)
{
	const measureVec = new Point();
	const offsetVec = new Point();
	const drawPoint = new Point();
	drawPoint.copy(measurePoint);
	drawPoint.calcDeltaTo(point, measureVec);
	offsetVec.copy(measureVec);
	offsetVec.rotateBy(offset < 0 ? Math.PI/2.0 : -Math.PI/2.0);
	if( offset > 0 ) offsetVec.negate();
	offsetVec.setLength(offset);
	
	
	drawPoint.moveTo(measureContext);
	drawPoint.translateBy(offsetVec);
	drawPoint.lineTo(measureContext);
	measureVec.scaleByNumber(.5);
	drawPoint.translateBy(measureVec);
	const vecLength = measureVec.calcLength() * 2;
	var blankLength;
	
	if( measureType )
	{
		if( measureType == MeasureType.LENGTH )
		{
			blankLength = vecLength + PIPE_THROWAWAY_OVERHANG_WITH_HOLE + PIPE_THROWAWAY_OVERHANG_WITHOUT_HOLE
		}
		else
		{
			blankLength = vecLength;
		}
	}
	else
	{
		blankLength = vecLength;
	}
	
	//var lengthString = measureType+"='" + toFraction(blankLength) + "(" + toFraction(vecLength) + ")"
	var lengthString = measureType+"='" + toFraction(blankLength);
	measureContext.fillText(lengthString, drawPoint.getX() * PIXELS_PER_INCH, drawPoint.getY() * PIXELS_PER_INCH-3);
	drawPoint.translateBy(measureVec);
	drawPoint.lineTo(measureContext);
	point.lineTo(measureContext);
	
	measureContext.stroke();
}