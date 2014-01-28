
var s_utilPoint1 = new Point();
var s_utilPoint2 = new Point();

var ATOM_IMAGES =
[
	calcRandomVersion("img/molecule01.png"),
	calcRandomVersion("img/molecule02.png"),
	calcRandomVersion("img/molecule03.png")
];

var ATOM_IMAGE_RADII = [-1, -1, -1];

function AtomImage(atomImageType)
{
	this.m_velocity = new Point();
	this.m_position = new Point();
	this.m_type = atomImageType;
	this.m_elapsedDirectionTime = 0;
	
	this.m_element = document.createElement("div");
	this.m_element.className = "atom_image";
	
	if( atomImageType == AtomImageType.FULL_ATOM )
	{
		this.m_glowElement = document.createElement("div");
		this.m_glowElement.className = "atom_glow";
		this.m_glowElement.style.display = "none";
		this.m_element.appendChild(this.m_glowElement);
	}
	
	var image = document.createElement("img");
	image.style.position = "absolute";
	image.onload = function()
	{
		var cachedRadius = ATOM_IMAGE_RADII[atomImageType];
		
		if( cachedRadius == -1 )
		{
			cachedRadius = ATOM_IMAGE_RADII[atomImageType] = image.width/2;
		}
		
		image.style.left = -cachedRadius + "px";
		image.style.top = -cachedRadius + "px";
	}
	
	this.m_element.appendChild(image);
	
	this.m_image = image;
	image.setAttribute("src", ATOM_IMAGES[atomImageType]);
}

AtomImage.prototype.setPosition = function(x, y)
{
	this.m_position.set(x, y);
	
	setTransform(this.m_element, create3dTranslation(x, y, 0));
}

AtomImage.prototype.getVelocity = function()
{
	return this.m_velocity;
}

AtomImage.prototype.getPosition = function()
{
	return this.m_position;
}

AtomImage.prototype.getElement = function()
{
	return this.m_element;
}

AtomImage.prototype.getGlowElement = function()
{
	return this.m_glowElement;
}

AtomImage.prototype.getImageElement = function()
{
	return this.m_image;
}

AtomImage.prototype.update = function(timeStep)
{
	if( this.m_type == AtomImageType.FULL_ATOM )
	{
		this.m_elapsedDirectionTime += timeStep;
		
		if( this.m_position.calcLengthSquared() > VIBRATION_RADIUS_SQUARED )
		{
			var speed = this.m_velocity.calcLength();
			this.m_velocity.copy(this.m_position);
			this.m_velocity.negate();
			this.m_velocity.setLength(speed);
		}
		else if( this.m_elapsedDirectionTime >= VIBRATION_DIRECTION_CHANGE_TIME )
		{
			this.m_velocity.rotateBy((Math.PI*2) * Math.random());
		}
		
		//this.setPosition(this.m_position.getX() + this.m_velocity.getX() * timeStep, this.m_position.getY() + this.m_velocity.getY() * timeStep);
		
		/*while
		(
			this.m_velocity.getX() > 0 && this.m_position.getX() > VIBRATION_RADIUS ||
			this.m_velocity.getX() < 0 && this.m_position.getX() < -VIBRATION_RADIUS
		)
		{
			if( this.m_position.getX() > VIBRATION_RADIUS )
			{
				this.setPosition(VIBRATION_RADIUS - (this.m_position.getX() - VIBRATION_RADIUS), 0);
			}
			else if( this.m_position.getX() < -VIBRATION_RADIUS )
			{
				this.setPosition(-VIBRATION_RADIUS + (Math.abs(this.m_position.getX()) - VIBRATION_RADIUS), 0);
			}
			
			this.m_velocity.negate();
		}*/
	}
	else
	{
		this.m_elapsedDirectionTime = 0;
	}
	
	this.setPosition(this.m_position.getX() + this.m_velocity.getX() * timeStep, this.m_position.getY() + this.m_velocity.getY() * timeStep);
}


var AtomState =
{
	NORMAL:0,
	AGITATED:1,
	BLOWING_UP:2,
	DEAD:3
};

var AtomImageType = 
{
	FULL_ATOM:0,
	BIG_CHUNK:1,
	LIL_CHUNK:2
};


function Atom()
{
	this.m_state = null;
	this.m_element = document.createElement("div");
	this.m_timeInState = 0;
	
	var rotation = Math.random() * (Math.PI * 2);
	var rotationStyle = createRotationTransform(rotation);
	setTransform(this.m_element, rotationStyle);
	
	this.m_atomImages = [];
	
	for( var i = 0; i < ATOM_IMAGES.length; i++ )
	{
		var atomImage = new AtomImage(i);
		
		setTransform(atomImage.getImageElement(), createRotationTransform(-rotation));
		
		this.m_element.appendChild(atomImage.getElement());
		
		this.m_atomImages.push(atomImage);
	}
	
	this.setState(AtomState.NORMAL);
}

Atom.prototype.setState = function(state)
{
	var oldState = this.m_state;
	
	if( oldState == AtomState.AGITATED )
	{
		this.m_atomImages[AtomImageType.FULL_ATOM].getGlowElement().style.display = "none";
	}
	
	var newState = state;
	
	if( newState == AtomState.NORMAL )
	{
		this.m_atomImages[AtomImageType.FULL_ATOM].getVelocity().set(NORMAL_VIBRATION_SPEED, 0);
		this.m_atomImages[AtomImageType.FULL_ATOM].getVelocity().rotateBy((Math.PI*2) * Math.random());
		
		if( oldState != AtomState.AGITATED )
		{
			this.m_atomImages[AtomImageType.FULL_ATOM].setPosition(0, 0);
		}
		
		this.m_atomImages[AtomImageType.FULL_ATOM].getElement().style.display = "";
		this.m_atomImages[AtomImageType.BIG_CHUNK].getElement().style.display = "none";
		this.m_atomImages[AtomImageType.LIL_CHUNK].getElement().style.display = "none";
	}
	else if( newState == AtomState.AGITATED )
	{
		this.m_atomImages[AtomImageType.FULL_ATOM].getVelocity().scaleByNumber(AGITATED_VIBRATION_FACTOR);
		this.m_atomImages[AtomImageType.FULL_ATOM].getGlowElement().style.display = "";
	}
	else if( newState == AtomState.BLOWING_UP )
	{
		this.m_atomImages[AtomImageType.LIL_CHUNK].getElement().style.opacity = 1;
		this.m_atomImages[AtomImageType.BIG_CHUNK].getElement().style.opacity = 1;
		
		this.m_atomImages[AtomImageType.FULL_ATOM].getElement().style.display = "none";
		this.m_atomImages[AtomImageType.BIG_CHUNK].getElement().style.display = "";
		this.m_atomImages[AtomImageType.LIL_CHUNK].getElement().style.display = "";
		
		var fullPosition = this.m_atomImages[AtomImageType.FULL_ATOM].getPosition();
		this.m_atomImages[AtomImageType.BIG_CHUNK].setPosition(fullPosition.getX(), fullPosition.getY());
		this.m_atomImages[AtomImageType.LIL_CHUNK].setPosition(fullPosition.getX(), fullPosition.getY());
		
		var bigVelocity = this.m_atomImages[AtomImageType.BIG_CHUNK].getVelocity();
		bigVelocity.set(BIG_CHUNK_VELOCITY, 0);
		bigVelocity.rotateBy(Math.random() * (Math.PI*2));
		
		var lilVelocity = this.m_atomImages[AtomImageType.LIL_CHUNK].getVelocity();
		lilVelocity.copy(bigVelocity);
		lilVelocity.negate();
		lilVelocity.setLength(LIL_CHUNK_VELOCITY);	
	}
	else if( newState == AtomState.DEAD )
	{
		this.m_atomImages[AtomImageType.FULL_ATOM].getElement().style.display = "none";
		this.m_atomImages[AtomImageType.BIG_CHUNK].getElement().style.display = "none";
		this.m_atomImages[AtomImageType.LIL_CHUNK].getElement().style.display = "none";
	}
	
	this.m_state = state;
	this.m_timeInState = 0;
}

Atom.prototype.update = function(timeStep)
{
	this.m_timeInState += timeStep;
	
	if( this.m_state == AtomState.NORMAL )
	{
		this.m_atomImages[AtomImageType.FULL_ATOM].update(timeStep);
	}
	else if( this.m_state == AtomState.AGITATED )
	{
		if( this.m_timeInState >= AGITATED_TIME )
		{
			this.setState(AtomState.BLOWING_UP);
		}
		else
		{
			this.m_atomImages[AtomImageType.FULL_ATOM].update(timeStep);
		}
	}
	else if( this.m_state == AtomState.BLOWING_UP )
	{
		if( this.m_timeInState >= BLOWING_UP_TIME )
		{
			this.setState(AtomState.DEAD);
		}
		else
		{
			var alpha;
			
			if( this.m_timeInState >= LIL_CHUNK_ALPHA_FADE_START )
			{
				alpha = 1- (this.m_timeInState - LIL_CHUNK_ALPHA_FADE_START)/ (BLOWING_UP_TIME - LIL_CHUNK_ALPHA_FADE_START);
				this.m_atomImages[AtomImageType.LIL_CHUNK].getElement().style.opacity = alpha;
			}
			
			if( this.m_timeInState >= BIG_CHUNK_ALPHA_FADE_START )
			{
				alpha = 1- (this.m_timeInState - BIG_CHUNK_ALPHA_FADE_START)/ (BLOWING_UP_TIME - BIG_CHUNK_ALPHA_FADE_START);
				this.m_atomImages[AtomImageType.BIG_CHUNK].getElement().style.opacity = alpha;
			}
			
			this.m_atomImages[AtomImageType.LIL_CHUNK].update(timeStep);
			this.m_atomImages[AtomImageType.BIG_CHUNK].update(timeStep);
		}
	}
}

Atom.prototype.getElement = function()
{
	return this.m_element;
}

Atom.prototype.getState = function()
{
	return this.m_state;
}


function AtomWrapper (collisionRadius, decayTimeRatio)
{
	this.m_collisionRadius = collisionRadius;
	this.m_atom = new Atom();
	this.m_element = document.createElement("div");
	this.m_element.appendChild(this.m_atom.getElement());
	this.m_element.className = "atom_wrapper";
	
	var rand = decayTimeRatio;//Math.random();
	var totalFraction = 0;
	for( var i = 0; i < HALF_LIFE_COUNT; i++ )
	{
		var fraction = 1 / Math.pow(2, i+1);
		var newTotalFraction = totalFraction + fraction;
		if( rand < newTotalFraction )
		{
			var quotient = (rand-totalFraction) / (newTotalFraction - totalFraction);
			this.m_decayTime = (i * (HALF_LIFE * ONE_BILLION)) + quotient * (HALF_LIFE * ONE_BILLION);
			
			break;
		}
		
		totalFraction = newTotalFraction;
	}
	
	if( this.m_decayTime == undefined )
	{
		var quotient = (rand-totalFraction) / (1-totalFraction);
		this.m_decayTime = ((HALF_LIFE_COUNT-1) * (HALF_LIFE * ONE_BILLION)) + quotient * (HALF_LIFE * ONE_BILLION);
	}
	
	var roll = Math.random();
	
	for( var i = 0; i < DEPTH_CHANCES.length; i++ )
	{
		if( roll <= DEPTH_CHANCES[i] )
		{
			this.m_element.className += " " + DEPTH_BASE_CLASS + i;
			
			break;
		}
	}
}

AtomWrapper.prototype.getDecayTime = function()
{
	return this.m_decayTime;
}

AtomWrapper.prototype.setQuotients = function(xQuotient, yQuotient)
{
	this.m_xQuotient = xQuotient;
	this.m_yQuotient = yQuotient;
}

AtomWrapper.prototype.getElement = function()
{
	return this.m_element;
}

AtomWrapper.prototype.getAtom = function()
{
	return this.m_atom;
}

AtomWrapper.prototype.onScreenResize = function(containerWidth, containerHeight)
{
	this.m_element.style.left = containerWidth * this.m_xQuotient + "px";
	this.m_element.style.top = containerHeight * this.m_yQuotient + "px";
}

AtomWrapper.prototype.update = function(timeStep)
{
	this.m_atom.update(timeStep);
}

AtomWrapper.prototype.calcPosition = function(containerWidth, containerHeight, point_out)
{
	point_out.set(containerWidth * this.m_xQuotient, containerHeight * this.m_yQuotient);
}

AtomWrapper.prototype.calcIsIntersecting = function(otherAtomWrapper, containerWidth, containerHeight)
{
	this.calcPosition(containerWidth, containerHeight, s_utilPoint1);
	otherAtomWrapper.calcPosition(containerWidth, containerHeight, s_utilPoint2);
	
	var distance = s_utilPoint1.calcDistanceTo(s_utilPoint2);
	
	var distanceNeeded = (this.m_collisionRadius + otherAtomWrapper.m_collisionRadius);

	if ( distance > distanceNeeded )
	{
		return false;
	}
	else
	{
		return true;
	}
}



function AtomManager(atomContainer, containerWidth, containerHeight)
{
	this.m_active = [];
	this_m_active = this.m_active;
	
	var addAtomWrapper = function(atomWrapper)
	{
		atomWrapper.onScreenResize(containerWidth, containerHeight);
		this_m_active.push(atomWrapper);
		atomContainer.appendChild(atomWrapper.getElement());
	}
	
	var intersecting = 0;
	
	for ( var i = 0; i < ATOM_COUNT; i++ )
	{
		var newAtomWrapper = new AtomWrapper(ATOM_OVERLAP_RADIUS, i / ATOM_COUNT);
		var added = false;
		
		for ( var j = 0; j < RANDOM_PLACEMENT_ATTEMPT_COUNT; j++ )
		{
			var xQuotient = Math.random();
			var yQuotient = Math.random();
		
			newAtomWrapper.setQuotients(xQuotient, yQuotient);
			
			var intersectingAny = false;
				
			for ( var k = 0; k < this.m_active.length; k++ )
			{
				var kthAtomWrapper = this.m_active[k];
				
				if ( newAtomWrapper.calcIsIntersecting(kthAtomWrapper, containerWidth, containerHeight) )
				{
					intersectingAny = true;
					
					break;
				}
			}
			
			if( !intersectingAny )
			{
				//console.log("erERER1", j);
				added = true;
				addAtomWrapper(newAtomWrapper);
				
				break;
			}
			else
			{
				//console.log("erERER2", j);
			}
		}
		
		if ( !added )
		{
			intersecting++;
			addAtomWrapper(newAtomWrapper);
		}
	}
}

AtomManager.prototype.onScreenResize = function(containerWidth, containerHeight)
{
	for ( var i = 0; i < this.m_active.length; i++ )
	{
		this.m_active[i].onScreenResize(containerWidth, containerHeight);
	}
}

AtomManager.prototype.getLiveCount = function()
{
	return this.m_liveCount;
}

AtomManager.prototype.update = function(timeStep)
{
	this.m_liveCount = 0;
	
	for ( var i = 0; i < this.m_active.length; i++ )
	{
		this.m_active[i].update(timeStep);
		
		if( this.m_active[i].getAtom().getState() <= AtomState.AGITATED )
		{
			this.m_liveCount++;
		}
	}
}

AtomManager.prototype.onTimeChange = function(newTime, delta)
{
	if( delta < 0 )
	{
		for( var i = 0; i < this.m_active.length; i++ )
		{
			var atomWrapper = this.m_active[i];
			
			if( newTime <= atomWrapper.getDecayTime() )
			{
				if( atomWrapper.getAtom().getState() != AtomState.NORMAL )
				{
					atomWrapper.getAtom().setState(AtomState.NORMAL);
				}
			}
		}
	}
	else
	{
		for( var i = 0; i < this.m_active.length; i++ )
		{
			var atomWrapper = this.m_active[i];
			
			if( newTime >= atomWrapper.getDecayTime() )
			{
				if( atomWrapper.getAtom().getState() == AtomState.NORMAL )
				{
					atomWrapper.getAtom().setState(AtomState.AGITATED);
				}
			}
		}
	}
}