function setTransform(element, transform)
{
	element.style.webkitTransform = transform;
	element.style.MozTransform = transform;
	element.style.msTransform = transform;
	element.style.OTransform = transform;
	element.style.transform = transform;
}

function createRotationTransform(radians)
{
	return "rotate("+radians+"rad)";
}

function create3dTranslation(x, y, z)
{
	return "translate3d(" + x + "px," + y + "px," + z + "px)";
}

function randomSign()
{
	return Math.random() < .5 ? -1 : 1;
}


function FpsTracker()
{
	this.m_frameCount = 0;
	this.m_time = 0;
	this.m_frameRate = 0;
	
	this.m_updateRate = .5;	
	
	this.m_justUpdated = false;
}

FpsTracker.prototype.wasJustUpdated = function()
{
	return this.m_justUpdated;
}

FpsTracker.prototype.update = function(timeStep)
{
	this.m_justUpdated = false;
	
	this.m_frameCount++;
	this.m_time += timeStep;
	
	//if ( m_updateMode == qb2E_FpsUpdateMode.EVERY_N_SECONDS )
	//{
		if( this.m_time >= this.m_updateRate )
		{
			this.updateFrameRate();
		}
	/*}
	else
	{
		if( m_frameCount >= m_updateRate )
		{
			updateFrameRate();
		}
	}*/
}

FpsTracker.prototype.updateFrameRate = function()
{
	this.m_frameRate = Math.round((this.m_frameCount) / this.m_time);

	this.m_frameCount = 0;
	this.m_time = 0;
	
	this.m_justUpdated = true;
}

FpsTracker.prototype.getFps = function()
{
	return this.m_frameRate;
}



function FpsViewer(element)
{
	this.m_element = element;
	this.m_tracker = new FpsTracker();
}

FpsViewer.prototype.update = function(timeStep)
{
	this.m_tracker.update(timeStep);
	
	if( this.m_tracker.wasJustUpdated() )
	{
		this.m_element.innerHTML = this.m_tracker.getFps() + " FPS";
	}
}