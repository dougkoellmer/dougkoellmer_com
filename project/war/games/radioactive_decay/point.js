function Point (x, y)
{
	this.m_x = x;
	this.m_y = y;
}

Point.prototype.getX = function()
{
	return this.m_x;
}

Point.prototype.getY = function()
{
	return this.m_y;
}
 
Point.prototype.calcDistanceTo = function(point)
{
	var diffX = point.m_x - this.m_x;
	var diffY = point.m_y - this.m_y;
	
	return Math.sqrt(diffX*diffX + diffY*diffY);
};

Point.prototype.copy = function(point)
{
	this.m_x = point.m_x;
	this.m_y = point.m_y;
}

Point.prototype.set = function(x, y)
{
	this.m_x = x;
	this.m_y = y;
}

Point.prototype.translateBy = function(point)
{
	this.m_x += point.m_x;
	this.m_y += point.m_y;
}

Point.prototype.scaleByNumber = function(value_float)
{
	this.m_x *= value_float;
	this.m_y *= value_float;
}

Point.prototype.negate = function()
{
	this.m_x *= -1;
	this.m_y *= -1;
}

Point.prototype.calcLength = function()
{
	return Math.sqrt(this.calcLengthSquared());
}

Point.prototype.calcLengthSquared = function()
{
	return this.m_x * this.m_x + this.m_y * this.m_y;
}

Point.prototype.setLength = function(value)
{
	var mag = this.calcLength();
	
	if ( mag != 0 )
	{
		this.m_x /= mag;
		this.m_y /= mag;
	}
	
	this.scaleByNumber(value);
}

Point.prototype.rotateBy = function(radians, origin_nullable)
{
	var originX, originY;
	
	if ( origin_nullable )
	{
		originX = origin_nullable.m_x;
		originY = origin_nullable.m_y;
	}
	else
	{
		originX = 0;
		originY = 0;
	}
	
	var sinRad = Math.sin(radians);
	var cosRad = Math.cos(radians);
	var newVertX = originX + cosRad * (this.m_x - originX) - sinRad * (this.m_y - originY);
	var newVertY = originY + sinRad * (this.m_x - originX) + cosRad * (this.m_y - originY);
	
	this.set(newVertX, newVertY);
}