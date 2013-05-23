package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.Util;
import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalOperandException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Util.*;
import asteroids.model.programs.Program;
import be.kuleuven.cs.som.annotate.*;

/** 
 * A class of spaceships involving a position, a velocity, a direction, 
 * a mass, a radius, a collection of bullets and a world with among others turning and accelerating facilities. 
 * 
 * @Invar The mass that applies to all ships must be a valid mass.
 *        	| isValidMass(getMass())
 * @Invar The direction that applies to all ships must be a valid direction.
 * 			| isValidDirection(getDirection())
 * @Invar Each ship must have proper bullets.
 * 			| hasProperBullets()
 * @Invar If a ship has a program, then it must have a proper program.
 * 			| if(hasProgram)
 * 			| then hasProperProgram()
 * 
 * @version 2.0
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
	 *  The direction a ship is orientated in.
	 */
	private double direction;
	/**
	 * The maximum number of bullets a ship can be associated with in a world.
	 */
	private static final double MAX_NB_OF_BULLETS = 10;
	/**
	 * Variable referencing a list collecting all the bullets of
	 * this ship.
	 * 
	 * @Invar   The referenced list is effective.
	 *        | bullets != null
	 * @Invar   Each bullet registered in the referenced list is
	 *          effective and not yet terminated.
	 *        | for each bullet in bullets:
	 *        |   ( (bullet != null) && (!bullet.isTerminated()) )
	 */
	private ArrayList<Bullet> bullets;
	/**
	 * Variable referencing a program for this ship to execute.
	 */
	private Program program = null;

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
	 * @post This new ship does not have any bullets yet.
	 * 		 | new.getNbBullets() == 0
	 * @post This new ship doen not have a program yet.
	 * 		 | new.getProgram == null
	 * @throws IllegalRadiusException
	 *         The given radius is not a valid radius.
	 *         |!Bullet.isValidRadius(radius)
	 * @throws IllegalPositionException
	 *         The given pos is not a valid position.
	 *         |!Bullet.isValidPosition((Position) pos)
	 *         
	 * @note This constructor inherits a constructor of bullet.
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
		
		this.bullets = new ArrayList<Bullet>();
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
	 *         |!Bullet.isValidRadius(radius)
	 * @throws IllegalPositionException
	 *         The given pos is not a valid position.
	 *         |!Bullet.isValidPosition((Position) pos)
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
		Vector newSpeed = new Velocity(this.getVel().getX(), this.getVel().getY());
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

	/**
	 * @return the maxNbOfBullets
	 */
	@Basic
	@Immutable
	public static double getMaxNbOfBullets() {
		return MAX_NB_OF_BULLETS;
	}
	/**
	 * Return the bullet of this ship at the given index.
	 * 
	 * @param   index
	 *          The index of the bullet to return.
	 * @throws  IndexOutOfBoundsException
	 *          The given index is not positive or it exceeds the
	 *          number of bullets of this ship.
	 *        | (index < 1) || (index > getNbBullets())
	 */
	@Basic
	@Raw
	public Bullet getBulletAt(int index) throws IndexOutOfBoundsException {
		return this.getAllBullets().get(index - 1);
	}

	/**
	 * Return the number of bullets of this ship.
	 */
	@Basic
	@Raw
	public int getNbBullets() {
		return this.getAllBullets().size();
	}

	/**
	 * Check whether this ship can have the given bullet
	 * as one of its bullets.
	 * 
	 * @param   bullet
	 *          The bullet to check.
	 * @return  True if and only if the given bullet is effective, and
	 *          if that bullet can have this ship as its ship, and if 
	 *          this ship has less than the maximum number of bullets a ship
	 *          can have and if the given bullet and the this ship belong to the
	 *          same world.
	 *        | result ==
	 *        |   ((bullet != null) &&
	 *        |   bullet.canHaveAsSource(this)
	 *        |   && getNbBullets()<getMaxNbBullets
	 *        |	  && this.getWorld() == bullet.getWorld())
	 */
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		return (bullet != null) && bullet.canHaveAsSource(this) 
				&& this.getNbBullets()< getMaxNbOfBullets()
				&& this.getWorld() == bullet.getWorld();
	}

	/**
	 * Check whether this ship can have the given bullet
	 * as one of its bullets at the given index.
	 * 
	 * @param   bullet
	 *          The bullet to check.
	 * @param   index
	 *          The index to check.
	 * @return  False if the given index is not positive or exceeds
	 *          the number of bullets of this ship + 1.
	 *        | if ( (index < 1) || (index > this.getNbBullets()+1) )
	 *        |   then result == false
	 *          Otherwise, false if this ship cannot have the
	 *          given bullet as one of its bullets.
	 *        | else if (! canHaveAsBullet(bullet))
	 *        |   then result == false
	 *          Otherwise, true if and only if the given bullet is
	 *          not already registered at another index.
	 *        | else result ==
	 *        |   for each I in 1..getNbBullets():
	 *        |     ( (I == index) || (getBulletAt(I) != bullet) )
	 */
	@Raw
	public boolean canHaveAsBulletAt(Bullet bullet, int index) {
		if ((index < 1) || (index > this.getNbBullets() + 1))
			return false;
		if (!canHaveAsBullet(bullet))
			return false;
		for (int pos = 1; pos <= this.getNbBullets(); pos++)
			if ((pos != index) && (this.getBulletAt(pos) == bullet))
				return false;
		return true;
	}

	/**
	 * Check whether this ship has a proper list of bullets.
	 * 
	 * @return  True if and only if this ship can have each of its
	 *          bullets at their index, and if each of these bullets
	 *          references this ship as their ship.
	 *        | for each bullet in 1..this.getNbBullets():
	 *        |   this.canHaveAsBulletAt(getOwningAt(index),index) &&
	 *        |   (getBulletAt(index).getSource() == this)
	 */
	public boolean hasProperBullets() {
		for (int index = 1; index <= this.getNbBullets(); index++) {
			if (!this.canHaveAsBulletAt(this.getBulletAt(index), index))
				return false;
			if (this.getBulletAt(index).getSource() != this)
				return false;
		}
		return true;
	}

	/**
	 * Check whether this ship has the given bullet as one of
	 * its bullets.
	 *
	 * @param   bullet
	 *          The bullet to check.
	 * @return  True if and only if this ship has the given bullet
	 *          as one of its bullets at some index.
	 *        | result ==
	 *        |   for some index in 1..this.getNbBullets():
	 *        |     this.getOwningAt(index).equals(bullet)
	 */
	@Raw
	public boolean hasAsBullet(Bullet bullet) {
		return this.getAllBullets().contains(bullet);
	}

	/**
	 * Return the index at which the given bullet is registered
	 * in the list of bullets for this ship.
	 *  
	 * @param  bullet
	 *         The bullet to search for.
	 * @return If this ship has the given bullet as one of its
	 *         bullets, that bullet is registered at the resulting
	 *         index. Otherwise, the resulting value is -1.
	 *       | if (this.hasAsBullet(bullet))
	 *       |    then this.getBulletAt(result) == bullet
	 *       |    else result == -1
	 */
	@Raw
	public int getIndexOfBullet(Bullet bullet) {
		return this.getAllBullets().indexOf(bullet);
	}

	/**
	 * Return a list of all the bullets of this ship.
	 * 
	 * @return  The size of the resulting list is equal to the number of
	 *          bullets of this ship.
	 *        | result.size() == getNbBullets()
	 * @return  Each element in the resulting list is the same as the
	 *          bullet of this ship at the corresponding index.
	 *        | for each index in 0..result-size()-1 :
	 *        |   result.get(index) == getBulletAt(index+1)
	 */
	public List<Bullet> getAllBullets() {
		return new ArrayList<Bullet>(bullets);
	}
	/**
	 * Add the given bullet at the end of the list of
	 * bullets of this ship.
	 * 
	 * @param   bullet
	 *          The bullet to be added.
	 * @pre     The given bullet is effective and already references
	 *          this ship as its ship.
	 *        | (bullet != null) && (bullet.getSource() == this)
	 * @pre     This ship does not not yet have the given bullet
	 *          as one of its bullets.
	 *        | ! hasAsBullet(bullet)
	 * @post    The number of bullets of this ship is incremented
	 *          by 1.
	 *        | new.getNbBullets() == getNbBullets() + 1
	 * @post    This ship has the given bullet as its new last
	 *          bullet.
	 *        | (new this).getBulletAt(this.getNbBullets()+1) == bullet
	 */
	@Raw
	public void addAsBullet(@Raw Bullet bullet) {
		assert (bullet != null) && (bullet.getSource() == this);
		assert !this.hasAsBullet(bullet);
		bullets.add(bullet);
	}
	/**
	 * Adds the given bullets at the end of the list of bullets of this ship.
	 * 
	 * @param bulletsToAdd
	 * 		  	The bullets to be added.
	 * TODO hier documentatie afmaken
	 * @effect ...
	 * 			| for each index in 0..bulletsToAdd.size()-1
	 * 			| addAsBullet(bulletsToAdd.get(index))
	 */
	@Raw 
	public void addAsBullets(@Raw List<Bullet> bulletsToAdd){
		for(Bullet objectToAdd: bulletsToAdd){
			try{ this.addAsBullet(objectToAdd);
			}catch (Exception ex){
				assert ((objectToAdd == null) || (objectToAdd.getSource() != this));
				assert this.hasAsBullet(objectToAdd);
			}
		}
	}

	/**
	 * Remove the given bullet from the bullets of this ship.
	 * 
	 * @param   bullet
	 *          The bullet to be removed.
	 * @pre     The given bullet is effective and does not have any
	 *          ship.
	 *        | (bullet != null) && (bullet.getSource() == null)
	 * @pre     This ship has the given bullet as one of
	 *          its bullets.
	 *        | hasAsOwning(bullet)
	 * @post    The number of bullets of this ship is decremented
	 *          by 1.
	 *        | new.getNbBullets() == getNbBullets() - 1
	 * @post    This ship no longer has the given bullet as
	 *          one of its bullets.
	 *        | (! (new this).hasAsBullet(bullet))
	 * @post    All bullets registered beyond the removed bullet
	 *          shift one position to the left.
	 *        | for each index in getIndexOfBullet(bullet)+1..getNbBullets():
	 *        |   (new this).getBulletAt(index-1) == this.getBulletAt(index) 
	 */
	@Raw
	public void removeAsBullet(Bullet bullet) {
		assert (bullet != null) && (bullet.getSource() == null);
		assert (this.hasAsBullet(bullet));
		bullets.remove(bullet);
	}	
	/**
	 * Terminates a Ship.
	 * @post    Each of the bullets of this ship no longer has
	 *          a source.
	 *        | for each space object in getAllSpaceObjects():
	 *        |	  (! (new space object).hasSpaceObject())
	 */
	@Override
	public void terminate(){
			for (Bullet bullet : this.getAllBullets())
				bullet.unsetSource();
			super.terminate();
	}
	/**
	 * Makes this ship fire a given projectile.
	 * 
	 * @param projectile
	 * 			The projectile to fire
	 * @effect If the given space object is a bullet, then make this ship
	 * 			fire the given space object.
	 * 			| if(Bullet.class.isAssignableFrom(projectile.getClass())
	 * 			| then fireBullet(projectile)
	 */
	@Override
	public void fireObject(SpaceObject projectile){
			if(Bullet.class.isAssignableFrom(projectile.getClass())){
				this.fireBullet((Bullet) projectile);
			}
	}
	/**
	 * Makes this ship fire a given bullet
	 * 
	 * @post If this ship can the given bullet as its bullet,
	 * 			then the number of spaceObjects of the world of this ship
	 * 			is increased by 1 and the number of bullets of this ship
	 * 			is increased by 1.
	 * 			| if(canFireBullet(bullet))
	 * 			| then new.getWorld().getNbSpaceObjects() == getNbSpaceObjects() +1
	 * 			| new.getNbBullets() == getNbBullets +1
	 */
	public void fireBullet(Bullet bullet){
		if(this.canHaveAsBullet(bullet)){
			this.getWorld().addAsSpaceObject(bullet);
			this.addAsBullet(bullet);
		}
		
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(@Raw Program program) throws IllegalOperandException{
		assert canHaveAsProgram(program);
		this.program = program;
		
		program.setShip(this);
	}
	public boolean canHaveAsProgram(Program program){
		return (program.canHaveAsShip(this));
	}
	public boolean hasAProgram(){
		return this.getProgram() != null;
	}
	public boolean hasProperProgram(){
		return canHaveAsProgram(getProgram());
	}
	public void runProgram(){
		if(hasAProgram() && !getProgram().isRunning()){
			getProgram().execute();
		}
	}
	}
