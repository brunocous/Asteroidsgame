package asteroids.model;

import asteroids.Util;
import asteroids.Error.IllegalRadiusException;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Util.*;
import be.kuleuven.cs.som.annotate.*;

//TODO dit aanpassen
/** 
 * A class of spaceships involving a position, a velocity, a direction and a radius with 
 * among others moving, turning and accelerating facilities. 
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
	 * Initialize this new ship with given pos, vel, direction, mass and radius.
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
	 * @post The new pos for this new ship is equal to the given pos if the given pos is a 
	 * 		 valid position.
	 *       | if(isValidPosition(pos))
	 *       | then new.getPos()== pos 
	 * @effect The new vel for this new ship is equal to the given vel.
	 *       | this.setVel(vel)
	 * @post The new direction for this new ship is equal to the given direction.
	 *       | new.getDirection()== direction
	 * @post The new radius for this new ship is equal to the given radius if the given radius
	 * 		 is a valid radius.
	 *       | if(isValidRadius(radius)
	 *       | then new.getRadius()== radius 
	 * @throws IllegalRadiusException
	 *         The given radius is not a valid radius.
	 *         |!isValidRadius()
	 * @throws IllegalArgumentException
	 *         The given pos is not a valid position.
	 *         |!isValidPosition()
	 *         
	 */
	
	public Ship(Position pos,Velocity vel, double direction, double radius, double mass) throws IllegalRadiusException, IllegalArgumentException{

		super(pos, vel, radius);

		if(!isValidMass(mass)){
			throw new IllegalArgumentException();
		}
		else this.mass = mass;
		
		this.setDirection(direction);
		this.forcePerSecond = 1.1 * Math.pow(10, 18);
		
	}
	

	/**
	 * Initialize this new ship as a default ship. 
	 * 
	 * @post The new pos for this new ship is a position object with x-component 0 and 
	 *       y-component 0.
	 *       | new.getPos()== new Position(0,0)
	 * @post The new vel for this new ship is a velocity object with x-component 0 and
	 *       y-component 0.
	 *       | new.getVel()== new Velocity(0,0)
	 * @post The new direction for this new ship is 0.
	 *       | new.getDirection()== 0
	 * @post The new radius for this new ship is 15.
	 *       | new.getRadius()== 15  
	 */
	public Ship(){
		
		super();
		this.setDirection(0);
		this.mass = 5000;
		this.forcePerSecond = 1.1 * Math.pow(10, 18);
		
	}
	/**
	 * Set the mass for this ship to the given mass.
	 * @param mass
	 * 			The new mass for this ship.
	 * @post The new mass for this ship is equal to the given ship.
	 *       |new.getMass()= mass
	 * @throws IllegalArgumentException
	 * 		   The given mass is not valid.
	 *         | isValidMass(mass) == false
	 */
	private void setMass(double mass) throws IllegalArgumentException{
	
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
	 * @param amount
	 * 		  The amount by which the velocity of the ship will be increased.
	 * @effect If increasing the ship's velocity by the given amount does not result into a 
	 *       velocity that is higher than the speed of light, the ship's velocity will be 
	 *       increased by the given amount. If it does exceed the speed of light, the ship's new
	 *       velocity will be the speed of light. If amount is infinite, the new velocity for 
	 *       this ship will be zero.
	 *       |if(isValidVelocity((new Velocity(getVel().getX(), getVel().getY())).add(new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection()))))
	 *       |     then (this).setVel(this.getVel().add(new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection()))))
	 *       |else ((this).setVel(correctSpeed(new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection()))))
	 * @post The ship's direction will not be changed. 
	 *       |(new this).getDirection() == this.getDirection()
	 */
	// TODO amount aanpassen
	public void thrust() {
		setEnableThruster(true);
			double acceleration = this.getForcePerSecond() / this.getMass();
		
		Vector gainedSpeed = new Velocity(acceleration*Math.cos(this.getDirection()),acceleration*Math.sin(this.getDirection()));
		Vector newSpeed = new Velocity(getVel().getX(), getVel().getY());
		newSpeed = newSpeed.add(gainedSpeed);
		
		if(!isValidVelocity((Velocity) newSpeed)){
			
			Velocity result = correctSpeed((Velocity) newSpeed);
			this.setVel(result);
			
		}
		else
			this.setVel((Velocity) this.getVel().add( (Velocity) gainedSpeed));

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
	public double getMass() {
		return mass;
	}
		
	}
