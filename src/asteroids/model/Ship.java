package asteroids.model;

import asteroids.Util;
import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Util.*;
import be.kuleuven.cs.som.annotate.*;

//TODO dit aanpassen
/** 
 * A class of spaceships involving a position, a velocity, a direction, 
 * a mass, a radius and a world with among others turning and accelerating facilities. 
 * 
 *  @invar The mass that applies to all ships must be a valid mass.
 *        | isValidMass(getMass())
 * 
 * @version 1.0
 * @author Bruno Coussement and Simon Telen
 *
 */

public class Ship extends SpaceObject{

	/**
	 * the mass of a ship.
	 */
	private final double mass;
	/**
	 * true is thruster is enabled.
	 */
	private boolean enableThruster;
	/**
	 * Force per seconds exerted by a ship.
	 */
	private final double forcePerSecond;
	/**
	 *  the direction a ship is orientated in.
	 */
	private double direction;

	/**
	 * Initialize this new ship with given pos, vel, direction, mass, radius and world.
	 * 
	 * @pre   The given direction must be a valid direction.
	 * 		  | isValidDirection(direction)
	 * @param pos
	 *        The position for this new ship.
	 * @param vel
	 *        The velocity for this new ship.
	 * @param direction
	 *        The direction for this new ship.
	 * @param radius
	 *        The radius for this new ship.
	 * @param world
	 * 		  The world for this new ship.
	 * @post The new direction for this new ship is equal to the given direction.
	 *       | new.getDirection()== direction
	 * @post The new mass of this ship is equal to the given mass if the given mass is valid.
	 * 		 | if(isValidMass(mass))
	 * 		 | then new.getMass() == mass
	 * @throws IllegalRadiusException
	 *         The given radius is not a valid radius.
	 *         |!SpaceObject.isValidRadius(radius)
	 * @throws IllegalPositionException
	 *         The given pos is not a valid position.
	 *         |!SpaceObject.isValidPosition((Position) pos)
	 *         
	 * @note This constructor inherits a constructor of space object.
	 */
	public Ship(Vector pos,Vector vel, double direction, double radius, double mass,World world) throws
	 		IllegalRadiusException, IllegalMaxSpeedException, IllegalPositionException{

		super(pos, vel, radius, world);

		if(!isValidMass(mass)){
			throw new IllegalArgumentException();
		}
		else this.mass = mass;
		
		this.setDirection(direction);
		this.forcePerSecond = 1.1 * Math.pow(10, 18);
		
	}
	
	/**
	 * Initialize this new ship with given pos, vel, direction, mass and radius.
	 * 
	 * @param pos
	 *        The position for this new ship.
	 * @param vel
	 *        The velocity for this new ship.
	 * @param direction
	 *        The direction for this new ship.
	 * @param radius
	 *        The radius for this new ship.
	 * @effect This new ship is initialized with the given position as its position,
	 * 			the given velocity as its velocity, the given direction as its direction
	 * 			, the given radius as its direction, the given mass as its mass and does 
	 * 			not belong to any world.
	 * 			| this(pos,vel,direction,radius,mass,null);
	 * @throws IllegalRadiusException
	 *         The given radius is not a valid radius.
	 *         |!SpaceObject.isValidRadius(radius)
	 * @throws IllegalPositionException
	 *         The given pos is not a valid position.
	 *         |!SpaceObject.isValidPosition((Position) pos)
	 */
	public Ship(Vector pos, Vector vel, double direction, double radius, double mass) throws IllegalRadiusException, IllegalMaxSpeedException, IllegalPositionException{
		this(pos,vel,direction,radius,mass,null);
	}
	

	/**
	 * Initialize this new ship as a default ship. 
	 * 
	 * @effect This new ship is initialized with the default position as its position
	 * 			, the default velocity as its velocity, a direction of 0 radians as its direction
	 * 			, a radius of 15 km as its radius, a mass of 5000 kg as its mass and does not
	 * 			belong to any world.
	 * 			| this(new Position(), new Velocity(), 0, 15, 5000, null)
	 */
	public Ship() throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		this(new Position(), new Velocity(), 0, 15, 5000, null);
	}
	/**
	 * @return the direction
	 */
	@Basic
	public double getDirection() {
		return direction;
	}
	/**
	 * Set the direction of this ship to the given direction.
	 * 
	 * @pre	 The value of the given direction must be finite.
	 * 		 |	isValidDirection(direction)
	 * @param direction
	 *        The new direction for this ship in radians.
	 * @post The new direction for this ship is the given direction.
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
	 * Turns the ship over a fixed angle. 
	 * 
	 * @param angle
	 * 		  The angle (in radians) to be added to the current direction of the ship
	 * @effect The new direction of the ship is the previous direction plus
	 *       the given angle.
	 *       |setDirection(this.getDirection()+ angle)
	 * @note this method has a precondition, namely
	 * 			isValidDirection(this.getDirection() + direction)
	 *       the resulting direction must be a valid direction. Other-
	 * 		 wise it will not be excepted.
	 */
	
	public void turn(double angle){
		
		setDirection(getDirection() + angle);
		
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
	 * Changes the ship's velocity by a given amount, does not change the ship's direction. 
	 * 
	 * @param deltaT
	 * 		  The amount of time in which the ship thrusts.
	 * 
	 * @effect If the thrusters are active of this ship, then if increasing the ship's velocity 
	 * 		 during a time amount deltaT does not result into a velocity that is higher than 
	 *       the maximum speed of this ship, the ship's velocity will be increased during 
	 *       an amount of time deltaT with a constant acceleration equal to the amount of 
	 *       time times the amount of force exerted by the thrusters per second divide by 
	 *       the mass of this ship. If it does exceed the maximum speed, the ship's new
	 *       velocity will be the maximum speed. If the amount of time is infinite, the 
	 *       new velocity for this ship will be zero.
	 *       | if(isEnableThruster())
	 *       | then if(Double.isInfinite(deltaT))
	 *       |		then (this).setVel(new Velocity())
	 *       |		else if(isValidVelocity((new Velocity(getVel().getX(), getVel().getY()))
	 *       |					.add(new Velocity(acceleration*Math.cos(getDirection()),acceleration*Math.sin(getDirection()))))
	 *       |     		 then (this).setVel(this.getVel().add(new Velocity(acceleration*Math.cos(getDirection()),acceleration*Math.sin(getDirection()))))
	 *       |			 else ((this).setVel(correctSpeed(new Velocity(acceleration*Math.cos(getDirection()),acceleration*Math.sin(getDirection()))))
	 * @post The ship's direction will not be changed. 
	 *       | new.getDirection() == getDirection()
 * @throws NegativeTimeException
 *         The given elapsedTime is negative and therefore invalid.
 *         |!isValidElapsedTime()		
	 */
	public void thrust(double deltaT) throws NegativeTimeException{
		
		if(!super.isValidElapsedTime(deltaT)){
			throw new NegativeTimeException();
		}
		else{ if(this.isEnableThruster()){
		
		if(Double.isInfinite(deltaT)){
			this.setVel(new Velocity());
		}else{
			double acceleration = deltaT*this.getForcePerSecond() / this.getMass();
		
		Vector gainedSpeed = new Velocity(acceleration*Math.cos(this.getDirection()),acceleration*Math.sin(this.getDirection()));
		Vector newSpeed = new Velocity(getVel().getX(), getVel().getY());
		newSpeed = newSpeed.add(gainedSpeed);
		
		this.setVel((Velocity)newSpeed);
		}
		}
		}
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
 * @return True is and only if the thruster is enabled.
 * 			| result == (enalbeThruster == true)
 */
	public boolean isEnableThruster() {
		return (enableThruster == true);
	}

/**
 * Sets if the thrusters are enabled.
 * @param enableThruster
 * @post EnableThrusters of this ship is equal to the given enable thrusters.
 * 			| new.isEnableThrusters() = enableThruster 
 */
	public void setEnableThruster(boolean enableThruster){
		this.enableThruster = enableThruster;
	}

/**
 * 
 * @return The force exerted by the thrusters per seconds.
 */
	@Basic 
	@Immutable
	public double getForcePerSecond() {
		return forcePerSecond;
	}
	/**
	 * @return Returns the mass of the ship.
	 */
	@Basic
	@Immutable
	@Override
	public double getMass() {
		return mass;
	}
		
	}
