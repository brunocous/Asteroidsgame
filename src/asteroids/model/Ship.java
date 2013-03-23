package asteroids.model;

import asteroids.Error.IllegalRadiusException;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Util.*;
import be.kuleuven.cs.som.annotate.*;

//TODO dit aanpassen
/** 
 * A class of spaceships involving a position, a velocity, a direction and a radius with 
 * among others moving, turning and accelerating facilities. 
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

		super(pos, vel , direction, radius);
		setMass(mass);
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
		
		Position pos = new Position(0,0);
		this.setPos(pos);
		Velocity vel = new Velocity(0,0);
		this.setVel(vel);
		this.direction =0;
		this.radius=15;
		
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
	if(!isValidMass(mass)){
		throw new IllegalArgumentException();
	}
	else{
    this.mass = mass;
	}
	}
	
	/**
	 * Moves the ship during a fixed amount of time.
	 * 	  
	 * @param elapsedTime
	 * 		  The amount of time during which the ship is moving in seconds.
	 * @post The position of the ship has been changed according to the previous position,
	 * 		   The current velocity of the ship and the given duration elapsedTime. 
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
			Position newPosition = pos.add(displacement);
			setPos(newPosition);
			
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
	public void thrust(double amount) {
		
		Velocity gainedSpeed = new Velocity(amount*Math.cos(getDirection()),amount*Math.sin(getDirection()));
		Velocity newSpeed = new Velocity(getVel().getX(), getVel().getY());
		newSpeed = newSpeed.add(gainedSpeed);
		
		if(!isValidVelocity(newSpeed)){
			
			Velocity result = correctSpeed(newSpeed);
			setVel(result);
			
		}
		else{
		
		vel = vel.add(gainedSpeed);
		
		}

	}
	
	

		}
		
	}
