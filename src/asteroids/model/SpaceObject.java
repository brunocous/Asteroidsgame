package asteroids.model;

import asteroids.Util;
import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
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
 * @Invar The position that applies to all space objects must be a valid position.
 *        | isValidPosition(getPos())
 * @Invar The velocity that applies to all space objects must be a valid velocity.
 *        | isValidVelocity(getVel())
 * @Invar The radius that applies to all space objects must be a valid radius.
 *        | isValidRadius(getRadius())
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
	 * the world the space object belongs to.
	 */
	protected World world;
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
	
	/**
	 * Initialize this new space object with given pos, vel, radius and maxSpeed.
	 * 
	 * @param pos
	 *        The position for this new space object.
	 * @param vel
	 *        The velocity for this new space object.
	 * @param radius
	 *        The radius for this new space object.
	 * @param maxSpeed
	 *        The maximum speed for this new space object.
	 * @param world
	 * 		  The world this new space object belongs to.
	 * @effect The new pos for this new space object is equal to the given pos.
	 *       | this.setPos(pos)
	 * @effect The new vel for this new space object is equal to the given vel.
	 *       | this.setVel(vel)
	 * @post The new radius for this new space object is equal to the given radius if the given radius
	 * 		 is a valid radius.
	 *       | if(isValidRadius(radius))
	 *       | then new.getRadius()== radius 
	 * @post The new maximum speed for this new space object is equal to the given maximum speed if the given 
	 * 		 maximum speed is a valid maximum speed. If the given maximum speed is not a valid maximum speed
	 * 		 , then the new maximum speed of this space object is equal to the speed of light.
	 *       | if(isValidMaxSpeed(maxSpeed))
	 *       | then new.getMaxSpeed()== maxSpeed 
	 *       | else new.getMaxSpeed()== Velocity.getSpeedOfLight()
	 * @effect The new world for this new space object is equal to the given world.
	 * 		 | this.setWorld(world)
	 * @post The new state for this new space object is equal to active.
	 * 		 | new.getState() = state.ACTIVE
	 * @throws IllegalRadiusException
	 *         The given radius is not a valid radius.
	 *         |!isValidRadius()
	 * @throws IllegalPositionException
	 *         The given pos is not a valid position.
	 *         |!isValidPosition()
	 * @throws IllegalMaxSpeedException
	 * 			The given maxSpeed is not a valid maximum speed.
	 * 			|!isValidMaximumSpeed()
	 *         
	 */
	public SpaceObject( Position pos, Velocity vel, double radius, double maxSpeed, World world) 
			throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		if(!isValidMaxSpeed(maxSpeed)){
			throw new IllegalMaxSpeedException();
			} else this.maxSpeed = maxSpeed;
		this.setVel(vel);
		
		if(!isValidRadius(radius)){
			throw new IllegalRadiusException();
		}
		else this.radius = radius;
		this.setWorld(world);
		this.setPos(pos);
	}
	/**
	 * Initialize this new space object with a given position, velocity, radius and world.
	 * 
	 * @param pos
	 *        The position for this new space object.
	 * @param vel
	 *        The velocity for this new space object.
	 * @param radius
	 *        The radius for this new space object.
	 * @param world
	 * 		  The world for this new space object.
	 * @effect This new space object is initialized with the given position as its position,
	 * 			the given velocity as its velocity, the given radius as its radius, with the
	 * 			the speed of light as its maximum speed and the given world as its world. 
	 * 			| this(pos, vel, radius, Velocity.getSpeedOfLight(), world)
	 */
	public SpaceObject( Position pos, Velocity vel, double radius,World world) throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		this(pos, vel, radius, Velocity.getSpeedOfLight(), world);
	}
	/**
	 * Initialize this new space object with a given position, velocity and radius.
	 * 
	 * @param pos
	 *        The position for this new space object.
	 * @param vel
	 *        The velocity for this new space object.
	 * @param radius
	 *        The radius for this new space object.
	 * @effect This new space object is initialized with the given position as its position,
	 * 			the given velocity as its velocity, the given radius as its radius, with the
	 * 			the speed of light as its maximum speed and does not belong to a world. 
	 * 			| this(pos, vel, radius, Velocity.getSpeedOfLight(), null)
	 */
	public SpaceObject( Position pos, Velocity vel, double radius) throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		this(pos, vel, radius, Velocity.getSpeedOfLight(), null);
	}
	
	/**
	 * Initialize this new space object as a default space object 
	 * @effect This new space object is initialized with the default position as its position, 
	 * 			the default velocity as its velocity, a radius of 15 km as its radius and 
	 * 			does not belong to any world.
	 * 			| this(new Position(), new Velocity(), 15)
	 */
	public SpaceObject() throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		this(new Position(), new Velocity(), 15);
	}
	
	/**
	 * @return the pos
	 */
	@Basic
	public Position getPos() {
		return pos;
	}
	
	/**
	 * Set the position for this space object to the given position. 
	 * 
	 * @param pos
	 *        The new pos for this space object.
	 * @param radius
	 * 		  The new radius for this space object.
	 * @post The new pos for this space object is equal to the given pos.
	 *       |new.getPos()= pos
	 * @throws IllegalPositionException
	 * 		   The given pos is not valid.
	 *         | isValidPosition(pos) == false
	 */
	@Basic
	public void setPos(Position pos) throws IllegalPositionException{
		if(!isValidPosition(pos)){
			throw new IllegalPositionException();
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
	 * @return the radius
	 */
	@Basic
	@Immutable
	public double getRadius() {
		return radius;
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
	 * Returns the mass of a space object.
	 */
	@Basic
	@Immutable
	public abstract double getMass();
	/**
	 * @return the world of a space object.
	 */
	@Basic 
	@Raw
	public World getWorld() {
		return this.world;
	}
	/**
	 * Sets the world of this space object to the given world.
	 * @param   world
	 *          The new world for this space object.
	 * @pre     This space object can have the given world as its world.
	 *        | canHaveAsWorld(world)
	 * @post    The world of this space object is the same as the given space object.
	 *        | new.getWorld() == world
	 */
	@Basic
	@Raw
	public void setWorld(@Raw World world){
		if(this.canHaveAsWorld(world))
		this.world = world;
	}
	/**
	 * Checks whether this space object has a world.
	 * @return True if and only if this space object has an effective world.
	 * 			| result == (this.getWorld() != null)
	 */
	@Raw
	public boolean hasWorld(){
		return (this.getWorld() != null);
	}
	/**
	 * Check whether this space object can have the given world as its world.
	 * @param  world
	 *         The world to check.
	 * @return  If this world is not yet terminated, true if and
	 *          only if the given world is effective and not yet
	 *          terminated
	 *        | if (! isTerminated())
	 *        |   then result == (world != null) && (! world.isTerminated())
	 * @return  If this world is terminated, true if and only if
	 *          the given world is not effective.
	 *        | if (! this.isTerminated())
	 *        |   then result == (world == null)
	 */
	@Raw
	public boolean canHaveAsWorld(World world){
		if (this.isTerminated())
			return (world == null);
		return (world != null) && (!world.isTerminated());
	}
	/**
	 * Check whether this space object has a proper world.
	 * @return  True if and only if this space object can have its world as its
	 *          world, and if this space object is terminated or the world of
	 *          this space object has this space object as one of its space objects.
	 *        | result ==
	 *        |   canHaveAsShare(getShare()) &&
	 *        |   ( isTerminated() || getShare().hasAsPurchase(this))
	 */
	public boolean hasProperWorld(){
		return canHaveAsWorld(getWorld())
				&&((getWorld() == null) || (getWorld().hasAsSpaceObject(this)));
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
 *         |result == (Util.fuzzyLessThanOrEqualTo(getNorm(), getMaxSpeed()) && Velocity.isLessThanOrEqualToSpeedOfLight(velocity))
 */
public boolean isValidVelocity(Velocity velocity){
	
	return (Util.fuzzyLessThanOrEqualTo(velocity.getNorm(), this.getMaxSpeed()) && Velocity.isLessThanOrEqualToSpeedOfLight(velocity));
	
}

/**
 * Check whether the given position is a valid position for this space object. In other words, check 
 * whether all the points within the radius of this space object from the center of this space object 
 * are inside or on the boundaries of the world of this space object.
 * 
 * @param position
 * 			The position to be checked.
 * @return true if and only if all the points within the radius of this space object from the center of this space object 
 * 			are inside or on the boundaries of the world of this space object.
 * 			| result == (Vector.isValidVector(position.getX(),position.getY()) 
 * 			| && World.isSituatedInOrOnBoundaries(position, this.getRadius(), this.getWorld()))
 */
public boolean isValidPosition(Position position){
	return (Vector.isValidVector(position.getX(),position.getY()) && World.isSituatedInOrOnBoundaries(position, this.getRadius(), this.getWorld()));
	}

/**
 * Moves this Space Object according to the given amount of time deltaT and the current velocity of
 * this Space Object.
 * 
 * @param deltaT The amount of time it takes for this Space Object to reach its new position.
 * @post The new position vector of this ship is equal to the old position vector of this ship plus
 * 		 the current velocity of this ship multiplied by the given deltaT.
 * 		 |(new this).getPos() == this.getPos().add(new Position(deltaT*this.getVel().getX(),deltaT*this.getVel().getY()))
 */

public void move(double deltaT) throws NegativeTimeException{
	
	if(!isValidElapsedTime(deltaT)){
		throw new NegativeTimeException();
	}
	else{
	
	double posX=this.getPos().getX()+ deltaT*this.getVel().getX();
	double posY=this.getPos().getY()+ deltaT*this.getVel().getY();
	try{
		setPos(new Position(posX,posY));
	}catch (IllegalPositionException ex){
		assert !isValidPosition( new Position(posX,posY));
	}
}
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
 * Check whether the given maximum speed is a valid maximum speed. In other words, check whether it is
 *  greater than 0 and less than or equal to the speed of light.
 *
 * @param maxSpeed
 *        The maxSpeed to be checked in km/h.
 * @return true if and only if the given maximum speed is greater than 0 and less than or equal
 * 			to the speed of light.
 *         | result == (Util.fuzzyLessThanOrEqualTo(maxSpeed, Velocity.getSpeedOfLight()) && 
 *         | !Util.fuzzyLessThanOrEqualTo(maxSpeed, 0))
 */
public static boolean isValidMaxSpeed(double maxSpeed){
	return (Util.fuzzyLessThanOrEqualTo(maxSpeed, Velocity.getSpeedOfLight()) && 
			!Util.fuzzyLessThanOrEqualTo(maxSpeed, 0));
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
 *         | double d = Math.pow(Vector.scalarProduct(new Velocity(deltavx, deltavy),new Position(deltarx, deltary)), 2)
		   | -Vector.scalarProduct(new Velocity(deltavx,deltavy),new Velocity(deltavx,deltavy))*(Vector.scalarProduct(new Position(deltarx,deltary),new Position(deltarx,deltary))-Math.pow(sigma, 2));
 *         | if(obj1 == obj2)
 *         | then result == Double.POSITIVE_INFINITY
 *         | else if(Util.fuzzyLessThanOrEqualTo(-Vector.scalarProduct(new Velocity(deltavx,deltavy),new Position(deltarx,deltary)),0) || Util.fuzzyLessThanOrEqualTo(d,0) )
 *         | else result == -(Vector.scalarProduct(new Velocity(deltavx, deltavy),new Position( deltarx,deltary))+Math.sqrt(d))/Vector.scalarProduct(new Velocity(deltavx,deltavy), new Velocity(deltavx,deltavy))
 *         
 */
                      
public static double getTimeToCollision(SpaceObject obj1, SpaceObject obj2) throws NullPointerException{
	
	double result;
	
	if (overlap(obj1,obj2)){
		
		result = Double.POSITIVE_INFINITY;
		
	}
	else {
	
	double deltavx = obj2.getVel().getX()- obj1.getVel().getX();
	double deltavy = obj2.getVel().getY()- obj1.getVel().getY();
	double deltarx = obj2.getPos().getX()- obj1.getPos().getX();
	double deltary = obj2.getPos().getY()- obj1.getPos().getY();
	double sigma = obj1.getRadius()+obj2.getRadius();
	double d = Math.pow(Vector.scalarProduct(new Velocity(deltavx, deltavy),new Position(deltarx, deltary)), 2)
			-Vector.scalarProduct(new Velocity(deltavx,deltavy),new Velocity(deltavx,deltavy))*(Vector.scalarProduct(new Position(deltarx,deltary),new Position(deltarx,deltary))-Math.pow(sigma, 2));
	
	if(Util.fuzzyLessThanOrEqualTo(-Vector.scalarProduct(new Velocity(deltavx,deltavy),new Position(deltarx,deltary)),0) || Util.fuzzyLessThanOrEqualTo(d,0) ){
		
		result = Double.POSITIVE_INFINITY;
		
	}
	else{
		
		result = -(Vector.scalarProduct(new Velocity(deltavx, deltavy),new Position( deltarx,deltary))+Math.sqrt(d))/Vector.scalarProduct(new Velocity(deltavx,deltavy), new Velocity(deltavx,deltavy));
		
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
/**
 * Terminates a space object. 
 * @effect  The World, if any, is unset from this space object.
*       	| unsetOwner()
 * @effect The new state of this space object is terminated.
 * 			| setState(State.TERMINATED) 
 */
public void terminate(){
	this.unsetWorld();
	this.setState(State.TERMINATED);
	
}
/**
 * @return True if and only if the state of this object is terminated.
 * 			| result == (this.getState() == State.TERMINATED)
 */
public boolean isTerminated(){
	return (this.getState() == State.TERMINATED);
}
/**
 * Unset the world, if any, from this space object.
 *
 * @post    This space object no longer has a world.
 *        | ! new.hasOwner()
 * @post    The former world of this space object, if any, no longer
 *          has this space object as one of its space objects.
 *        |    (getWorld() == null)
 *        | || (! (new getOwner()).hasAsOwning(owning))
 * @post    All space objects registered beyond the position at which
 *          this space object was registered shift one position to the left.
 *        | (getWorld() == null) ||
 *        | (for each index in
 *        |        getWord().getIndexOfSpaceObject(spaceobject)+1..getWorld().getNbSpaceObjects():
 *        |    (new getWorld()).getSpaceObjectAt(index-1) == getWorld().getSpaceObjectAt(index) ) 
 */
public void unsetWorld() {
	if (this.hasWorld()) {
		World formerWorld = this.getWorld();
		this.setWorld(null);
		formerWorld.removeAsSpaceObject(this);
	}
}
/**
 * Makes this spaceObject fire a projectile.
 *  
 * @param projectile
 * 			The projectile to fire.
 * @post If this space object can fire the given bullet, then 
 * 			add the given bullet to the world where this space 
 * 			object belongs to.
 * 			| if(canFireBullet(projectile))
			| then new.getWorld().getNbSpaceObjects() == getWorld().getNbSpaceObjects() + 1;
 * @throws IllegalStateException
 * 			This space object is already terminated.
 * 			| isTerminated()
 * 
 */
public void fireObject(SpaceObject projectile)throws IllegalStateException{
	if(!isTerminated()){
	if(Bullet.class.isAssignableFrom(projectile.getClass())){
		if(this.canFireBullet((Bullet) projectile))
			this.getWorld().addAsSpaceObject(projectile);
	}else throw new IllegalStateException();
	}
}
/**
 * Checks if the given bullet is a valid bullet.
 * @return True if and only if the given bullet is effective
 * 			and if the given bullet has this space Object 
 * 			as its source and if the given bullet and this
 * 			space object belong to the same world and if 
 * 			the world of this space object does not yet have
 * 			the given bullet.
 * 			| result == ((bullet != null) && (bullet.getSource() == this))
 * @throws IllegalStateException
 * 			This space object is already terminated.
 * 			| isTerminated()
 */
public boolean canFireBullet(Bullet bullet) throws IllegalStateException{
	if(!isTerminated()){
	return ((bullet != null) && (bullet.getSource() == this) 
			&& (bullet.getWorld() == this.getWorld()) 
			&& !this.getWorld().hasAsSpaceObject(bullet));
	} throw new IllegalStateException();
}
}

