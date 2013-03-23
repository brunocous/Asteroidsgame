package asteroids.model;

import asteroids.Util;
import asteroids.Error.IllegalRadiusException;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Vector;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.*;

/** 
 * An abstract class of space objects involving a position, a velocity, a direction
 * , a maximum speed, a mass and a radius with moving facilities.
 * 
 * @invar The position that applies to all space objects must be a valid position.
 *        | isValidPosition(getPos())
 * @invar The velocity that applies to all space objects must be a valid velocity.
 *        | isValidVelocity(getVel())
 * @invar The radius that applies to all space objects must be a valid radius.
 *        | isValidRadius(getRadius())
 * @invar The mass that applies to all space objects must be a valid mass.
 *        | isValidMass(getMass())
 * 
 * @version 1.0
 * @author Bruno Coussement and Simon Telen
 *
 */

public abstract class SpaceObject {

	/**
	 *  the position vector of a space object.
	 */
	protected Position pos;
	/**
	 *  the velocity vector of a space object.
	 */
	protected Velocity vel;
	/**
	 *  the direction a space object is orientated in.
	 */
	protected double direction;
	/**
	 *  the radius of a space object.
	 */
	protected final double radius;
	/**
	 *  the maximum speed of a space object.
	 */
	protected final double maxSpeed;
	/**
	 *  the state of a space object.
	 */
	protected State state = State.ACTIVE;
	/**
	 * the minimum radius of a space object.
	 */
	protected static final double MIN_RADIUS = 0;
	/**
	 * the minimum mass of a space object.
	 */
	protected static final double MIN_MASS = 0;
	
	public SpaceObject( Position pos, Velocity vel, double direction, double radius, double maxSpeed) throws IllegalRadiusException{
		this.setPos(pos);
		this.setVel(vel);
		this.setDirection(direction);
		
		this.setRadius(radius);

		if(Velocity.isLessThanOrEqualToSpeedOfLight(new Velocity(maxSpeed,maxSpeed))){
		this.maxSpeed = maxSpeed;
		} else this.maxSpeed = Velocity.getSpeedOfLight();
		
		
	}
	public SpaceObject( Position pos, Velocity vel, double direction, double radius) throws IllegalRadiusException{
		this.setPos(pos);
		this.setVel(vel);
		this.setDirection(direction);
		this.setRadius(radius);
		this.maxSpeed = Velocity.getSpeedOfLight();
	}
	
	public SpaceObject(){
		this.setPos(new Position());
		this.setVel(new Velocity());
		this.setDirection(0);
		this.radius = 15;
		this.maxSpeed = Velocity.getSpeedOfLight();
	}
	/**
	 * @return the pos
	 */
	@Basic
	public Position getPos() {
		return pos;
	}
	
	/**
	 * Set the pos for this space object to the given pos. 
	 * 
	 * @param pos
	 *        The new pos for this space object.
	 * @post The new pos for this space object is equal to the given pos.
	 *       |new.getPos()= pos
	 * @throws IllegalArgumentException
	 * 		   The given pos is not valid.
	 *         | isValidPosition(pos) == false
	 */
	@Basic
	public void setPos(Position pos) throws IllegalArgumentException{
		if(!isValidPosition(pos)){
			throw new IllegalArgumentException();
		}
		else{
	    this.pos = pos;
		}
	}
	/**
	 * @return the velocity
	 */
	@Basic
	public Velocity getVel() {
		return vel;
	}
	/**
	 * Set the vel of this space object to the given vel.
	 * 
	 * @param vel
	 *        The new velocity for this space object.
	 * @post If the given vel is a valid velocity, in other words if its norm is less than or 
	 *       equal to the maximum speed and the speed of light, the new vel for this space object is equal to the given
	 *       vel. Else the new vel of this space object has the direction of the given vel but a norm
	 *       equal to the maximum speed. If the given vel has an infinite component, the new 
	 *       vel of this spaceObject will be zero (both x- and y-component).
	 *       |if (isValidVelocity(vel))
	 *       |then new.getVel()== vel
	 *       |else new.getVel() == correctSpeed(vel)
	 */
	@Basic
	public void setVel(Velocity vel) {
		if (isValidVelocity(vel)){
			this.vel = vel;			
		}
		else{ 
			if(Double.isInfinite(vel.getX()) || Double.isInfinite(vel.getY())){
			this.vel = new Velocity(0,0);
			} else{
			Velocity result = correctSpeed(vel);
			this.vel = result;
			}
		}
	}
	/**
	 * @return the direction
	 */
	@Basic
	public double getDirection() {
		return direction;
	}
	/**
	 * Set the direction of this space object to the given direction.
	 * 
	 * @pre	 The value of the given direction must be finite.
	 * 		 |	isValidDirection(direction)
	 * @param direction
	 *        The new direction for this space object in radians.
	 * @post The new direction for this space object is the given direction.
	 *       |	new.getDirection() = direction
	 */
	@Model @Basic
	public void setDirection(double direction) {
		
		assert(isValidDirection(direction));
		
		if(direction>=0){
			
		this.direction = direction%(2*Math.PI);
		
		}
		else{
			
	    this.direction = 2*Math.PI + direction%(2*Math.PI);
		
		}
		
	}
	/**
	 * @return the radius
	 */
	@Basic
	@Immutable
	public double getRadius() {
		return radius;
	}
	/**
	 * Set the radius for this space object to the given mass.
	 * @param radius
	 * 			The new radius for this space object.
	 * @post The new radius for this space object is equal to the given space object.
	 *       |new.getRadius()= radius
	 * @throws IllegalRadiusException
	 * 		   The given radius is not valid.
	 *         | isValidRadius(radius) == false
	 */
	@Basic
	@Immutable
	protected void setRadius(double radius) throws IllegalRadiusException{
	if(!isValidRadius(radius)){
		throw new IllegalRadiusException();
	}
	else{
    this.radius = radius;
	}
	}
	/**
	 * @return the maxSpeed
	 */
	@Basic 
	@Immutable
	public double getMaxSpeed() {
		return maxSpeed;
	}
	/**
	 * @return the minimum radius of a space object.
	 */
	@Basic
	@Immutable
	public static double getMinRadius(){
		return MIN_RADIUS;
	}
	/**
	 * @return the minimum mass of a space object.
	 */
	@Basic
	@Immutable
	public static double getMinMass(){
		return MIN_MASS;
	}
	/**
	 * Enumeration of all possible states of a space object.
	 */
	protected static enum State{
		ACTIVE,TERMINATED;
	}
	
	/**
	 * Return the state of this space object.
	 */
	@Raw
	@Basic
	protected State getState() {
		return this.state;
	}

	/**
	 * Set the state of this space object to the given state.
	 * 
	 * @param  state
	 *         The new state for this space object.
	 * @pre    The given state must be effective.
	 *       | state != null
	 * @post   The state of this space object is the same as the
	 *         given state.
	 *       | new.getState() == state
	 */
	@Basic
	protected void setState(State state) {
		assert (state != null);
		this.state = state;
	}

/**
 * Check whether the given velocity is a valid velocity. In other words, check whether its 
 * norm is less than or equal to the maximum speed and the speed of light.
 * 
 * @param velocity
 *        The velocity to be checked.
 * @return true if and only if the given velocity's norm is less than or equal to the maximum speed and the speed of light.
 *         |result == (Util.fuzzyLessThanOrEqualTo(getNorm(),getMaxSpeed()) && Velocity.isLessThanOrEqualToSpeedOfLight(velocity))
 */
public boolean isValidVelocity(Velocity velocity){
	
	return (Util.fuzzyLessThanOrEqualTo(velocity.getNorm(),getMaxSpeed()) && Velocity.isLessThanOrEqualToSpeedOfLight(velocity));
	
}
/**
 * Check whether the given position is a valid position. In other words, check whether its
 * x- and y- components are inside or on the boundaries.
 * 
 * @param position
 * 			The position to be checked.
 * @return true if and only if the position is valid.
 * 			| result == (Position.isValidPosition(position) && World.isSituatedInOrOnBoundaries(position))
 */
public boolean isValidPosition(Position position){
	return (Position.isValidPosition(position) && World.isSituatedInOrOnBoundaries(position));
}
/** 
 * Check whether the given direction is a valid direction.
 * 
 * @param direction
 *        The direction to be checked in radians.
 * @return true if and only if the given direction is greater than -Pi and less than or equal to
 * 		   Pi.
 *         | result == (direction > -Math.PI) && (direction <= Math.PI)
 *        
 */
public static boolean isValidDirection(double direction){
	
	return (!Double.isInfinite(direction));
	
}
/**
 * Check whether the given radius is a valid radius. In other words, check whether it is
 * higher than the minimum radius.
 *
 * @param radius
 *        The radius to be checked in km.
 * @return true if and only if the given radius is higher than the minimum radius.
 *         |result == (!Util.fuzzyLessThanOrEqualTo(radius, getMinRadius()))
 */
public static boolean isValidRadius(double radius){
	
	return (!Util.fuzzyLessThanOrEqualTo(radius, getMinRadius()));

}

/**
 * Check whether the given mass is a valid mass. In other words, check whether it is
 * higher than the minimum mass.
 *
 * @param mass
 *        The mass to be checked in kg.
 * @return true if and only if the given mass is higher than the minimum mass.
 *         |result == (!Util.fuzzyLessThanOrEqualTo(mass, getMinMass()))
 */
public static boolean isValidMass(double mass){
	
	return (!Util.fuzzyLessThanOrEqualTo(mass, getMinMass()));

}
/**
 * Returns the distance between two given space objects.  
 * 
 * @pre The given obj1 and obj2 should not be null.
 * @param obj1
 * 	      The first space object of which the position will be compared to the given SpaceObject obj2.
 * @param obj2
 *        The second space object of which the position will be compared to the given SpaceObject obj1.
 * @return The distance between the outer side of obj1 and obj2 if obj1 and obj2 are different space objects, 
 *         0.0 if obj1 and obj2 are the same space object. 
 *         The result will be negative if obj1 and obj2 overlap.
 *         |if(obj1==obj2)
 *         |  then result == 0.0
 *         |else result == obj1.getPos().getDistanceTo(obj2.getPos())-(obj1.getRadius()+obj2.getRadius())
 */
public static double getDistanceBetween(SpaceObject obj1, SpaceObject obj2) {
	if(obj1==obj2){
		
		return 0;
		
	}
	else{
		
	double distanceBetweenCentres = obj1.getPos().getDistanceTo(obj2.getPos());
	double sumOfRadii= obj1.getRadius()+obj2.getRadius();
	return distanceBetweenCentres - sumOfRadii;
	
	}
	
	
}
/**
 * Check if 2 space objects overlap.
 * 
 * @pre The given obj1 and obj2 should not be null.
 * @param obj1
 * 	      The first space object of which the position will be compared to the position of obj2
 * @param obj2
 * 	      The second space object of which the position will be compared to the position of obj1
 * @return true if and only if obj1 and obj2 are the same space object or the distance between
 *         obj1 and obj2 is less than zero. 
 *         | if( obj1==obj2 || (asteroids.Util.fuzzyLessThanOrEqualTo(getDistanceBetween(obj1,obj2),0) && !asteroids.Util.fuzzyEquals(getDistanceBetween(obj1,obj2), 0)))
 *         | then result == true
 *         | else result == false
 */
public static boolean overlap(SpaceObject obj1, SpaceObject obj2) {
	
	boolean result =false;
	if(obj1==obj2){
		result=true;
	} 
	else{
			if(asteroids.Util.fuzzyLessThanOrEqualTo(getDistanceBetween(obj1,obj2),0) && !asteroids.Util.fuzzyEquals(getDistanceBetween(obj1,obj2), 0)){
				result= true;
			} 
	}
	return result;
}

/**
 * Calculates the scalar product of two 2-dimensional vectors.
 * 
 * @param x1
 *        The x-coördinate of the first vector.
 * @param y1
 *        The y-coördinate of the first vector.
 * @param x2
 *        The x-coördinate of the second vector.
 * @param y2
 *        y-coördinate of the second vector.
 * @return the scalar product of vector1(x1,y1) and vector2(x2,y2)
 *         |result==x1*y1+x2*y2
 */

public static double scalarProduct(double x1, double y1, double x2, double y2){
	
	return x1*x2+y1*y2;
	
}

/**
 * Finds out whether and in how many seconds 2 space objects will collide. 
 * 
 * @pre the given obj1 and obj2 should not be null.
 * @param obj1
 * 		  The first space object that will or will not collide with obj2 after an amount of time.
 * @param obj2
 * 		  The second space object that will or will not collide with obj1 after an amount of time.
 * @return The amount of time (in seconds) it will take for obj1 and obj2 to collide or 
 * 	       POSITIVE_INFINITY if they will never collide (given their current position and 
 *         velocity). If obj1 and obj2 are the same space object, they will never collide.
 *         | double deltavx = obj2.getVel().getX()- obj1.getVel().getX()
 *		   | double deltavy = obj2.getVel().getY()- obj1.getVel().getY()
 *         | double deltarx = obj2.getPos().getX()- obj1.getPos().getX()
 *         | double deltary = obj2.getPos().getY()- obj1.getPos().getY()
 *         | double sigma = obj1.getRadius()+obj2.getRadius()
 *         | double d = Math.pow(scalarProduct(deltavx, deltavy, deltarx, deltary), 2)-scalarProduct(deltavx,deltavy,deltavx,deltavy)*(scalarProduct(deltarx,deltary,deltarx,deltary)-Math.pow(sigma, 2))
 *         | if(obj1 == obj2)
 *         | then result == Double.POSITIVE_INFINITY
 *         | else if(Util.fuzzyLessThanOrEqualTo(-scalarProduct(deltavx,deltavy,deltarx,deltary),0) || Util.fuzzyLessThanOrEqualTo(d,0) )
 *         | else result == -(scalarProduct(deltavx, deltavy, deltarx,deltary)+Math.sqrt(d))/scalarProduct(deltavx,deltavy,deltavx,deltavy)
 *         
 */
                      
public static double getTimeToCollision(SpaceObject obj1, SpaceObject obj2) throws NullPointerException{
	
	double result;
	
	if(obj1==obj2) {
		
		result = Double.POSITIVE_INFINITY;
		
	} else{
	
	double deltavx = obj2.getVel().getX()- obj1.getVel().getX();
	double deltavy = obj2.getVel().getY()- obj1.getVel().getY();
	double deltarx = obj2.getPos().getX()- obj1.getPos().getX();
	double deltary = obj2.getPos().getY()- obj1.getPos().getY();
	double sigma = obj1.getRadius()+obj2.getRadius();
	double d = Math.pow(scalarProduct(deltavx, deltavy, deltarx, deltary), 2)-scalarProduct(deltavx,deltavy,deltavx,deltavy)*(scalarProduct(deltarx,deltary,deltarx,deltary)-Math.pow(sigma, 2));
	
	if(Util.fuzzyLessThanOrEqualTo(-scalarProduct(deltavx,deltavy,deltarx,deltary),0) || Util.fuzzyLessThanOrEqualTo(d,0) ){
		
		result = Double.POSITIVE_INFINITY;
		
	}
	else{
		
		result = -(scalarProduct(deltavx, deltavy, deltarx,deltary)+Math.sqrt(d))/scalarProduct(deltavx,deltavy,deltavx,deltavy);
		
	}
	}
	
	return result;
}

/**
 * Calculates the position where 2 space objects will collide, if they will collide within a 
 * finite amount of time.
 * 
 * @pre The given obj1 and obj2 should not be null.
 * @param obj1
 *        The first space object of which we want to know at which position it will collide with
 *        obj2.
 * @param obj2
 *        The second space object of which we want to know at which position it will collide with
 *        obj1.
 * @return The position where obj1 and obj2 will collide if they will collide eventually 
 *         or 'null' if obj1 and obj2 will never collide. 
 *         | if(deltaT==Double.POSITIVE_INFINITY)
 *	       | then result== null
 *	       | else 
 *		   | double radius1 = obj1.getRadius();
 *         | double radius2 = obj2.getRadius();
 *		   | double fraction =(radius2/(radius1 + radius2));
 *		   | double xPosObj1 = obj2.getPos().getX()+deltaT*obj2.getVel().getX();
	 *         | double yPosObj1 = obj1.getPos().getY()+deltaT*obj1.getVel().getY();
 *  	   | double xPosObj2 = obj2.getPos().getX()+deltaT*obj2.getVel().getX();
 *		   | double yPosObj2 = obj2.getPos().getY()+deltaT*obj2.getVel().getY();
 *		   | double xCoordCollision = fraction*xPosObj1 + (1-fraction)*xPosObj2;
 *		   | double yCoordCollision = fraction*yPosObj1 + (1-fraction)*yPosObj2;
 *		   | result == new Position(xCoordCollision, yCoordCollision);
 */

public static Position getCollisionPosition(SpaceObject obj1, SpaceObject obj2) {
	double deltaT= getTimeToCollision(obj1,obj2);
	
	if(Double.isInfinite(deltaT)){
		
		return null;
	}
	else{
		
		double radius1 = obj1.getRadius();
		double radius2 = obj2.getRadius();
		double fraction =(radius2/(radius1 + radius2));
		
		double xPosObj1 = obj1.getPos().getX()+deltaT*obj1.getVel().getX();
		double yPosObj1 = obj1.getPos().getY()+deltaT*obj1.getVel().getY();
		double xPosObj2 = obj2.getPos().getX()+deltaT*obj2.getVel().getX();
		double yPosObj2 = obj2.getPos().getY()+deltaT*obj2.getVel().getY();
		
		double xCoordCollision = fraction*xPosObj1 + (1-fraction)*xPosObj2;
		double yCoordCollision = fraction*yPosObj1 + (1-fraction)*yPosObj2;
		
		return new Position(xCoordCollision, yCoordCollision);
		
	}
}
/**
 * Correct a given velocity vector speed (which has a norm that is bigger than the maximum speed) 
 * to a velocity vector with the same direction and a norm equal to the speed 
 * of light.
 * 
 * @param speed
 *        The velocity vector that is to be corrected.
 * @return the corrected velocity vector, which has the same direction as the given speed
 *         and has a norm equal to the speed of light.
 *         | result == new Velocity (speed.getX()/(speed.getNorm()/Velocity.getSpeedOfLight()),speed.getY()/(speed.getNorm()/Velocity.getSpeedOfLight()))
 *         
 */

protected Velocity correctSpeed(Velocity speed){
	
	double correctingFactor = speed.getNorm()/getMaxSpeed();
	Velocity correctedSpeed = new Velocity (speed.getX()/correctingFactor,speed.getY()/correctingFactor);
	return correctedSpeed;
}
/**
 * Moves the space object during a fixed amount of time.
 * 	  
 * @param elapsedTime
 * 		  The amount of time during which the space object is moving in seconds.
 * @post The position of the space object has been changed according to the previous position,
 * 		   The current velocity of the space object and the given duration elapsedTime. 
 * 		   |(new this).getPos() == this.getPos().add(new Position(vel.getX()*elapsedTime, vel.getY()*elapsedTime));
 * @throws NegativeTimeException
 *         The given elapsedTime is negative and therefore unvalid.
 *         |!isValidElapsedTime()
 */

public void move(double elapsedTime) throws NegativeTimeException{
	if(!isValidElapsedTime(elapsedTime)){
		
		throw new NegativeTimeException() ;
		
	}
	else{
		
		Vector displacement = new Position(vel.getX()*elapsedTime, vel.getY()*elapsedTime);
		Vector newPosition = pos.add( displacement);
		setPos((Position) newPosition);
		
	} 
}
/** 
 * Check whether the given time is a valid amount of time. 
 * 
 * @param time
 *        The amount of time to be checked in seconds.
 * @return true if and only if the given time is greater than or equal to zero.
 *         | result == !(time<0)
 *        
 */
public static boolean isValidElapsedTime(double time){
	
	return !(time < 0);
	
}
}

