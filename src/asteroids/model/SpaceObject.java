package asteroids.model;

import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.*;

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
	protected double radius;
	/**
	 *  the maximum speed of a space object.
	 */
	protected final double maxSpeed;
	/**
	 *  the mass of a space object.
	 */
	protected final double mass;
	/**
	 *  the state of a space object.
	 */
	protected State state = State.ACTIVE;
	

	public SpaceObject( Position pos, Velocity vel, double direction, double radius, double maxSpeed, double mass){
		this.setPos(pos);
		this.setVel(vel);
		this.setDirection(direction);
		this.setRadius(radius);
		this.maxSpeed = maxSpeed;
		this.mass = mass;
	}
	public SpaceObject( Position pos, Velocity vel, double direction, double radius){
		this.setPos(pos);
		this.setVel(vel);
		this.setDirection(direction);
		this.setRadius(radius);
		this.maxSpeed = Velocity.getSpeedOfLight();
		this.setMass();
	}
	
	public SpaceObject(){
		this.setPos(new Position());
		this.setVel(new Velocity());
		this.setDirection(0);
		this.setRadius(15);
		this.maxSpeed = Velocity.getSpeedOfLight();
		this.mass = 50000;
	}
	/**
	 * @return the pos
	 */
	@Basic
	public Position getPos() {
		return pos;
	}
	/**
	 * @param pos the position to set
	 */
	@Basic
	public void setPos(Position pos) {
		this.pos = pos;
	}
	/**
	 * @return the velocity
	 */
	@Basic
	public Velocity getVel() {
		return vel;
	}
	/**
	 * @param vel the velocity to set
	 */
	@Basic
	public void setVel(Velocity vel) {
		this.vel = vel;
	}
	/**
	 * @return the direction
	 */
	@Basic
	public double getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	@Basic
	public void setDirection(double direction) {
		this.direction = direction;
	}
	/**
	 * @return the radius
	 */
	@Basic
	public double getRadius() {
		return radius;
	}
	/**
	 * @param radius the radius to set
	 */
	@Basic
	public void setRadius(double radius) {
		this.radius = radius;
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
	 * @return the mass
	 */
	@Basic
	@Immutable
	public double getMass() {
		return mass;
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
	 * Sets the mass of this space object.
	 */
	@Basic
	protected abstract void setMass();
		
	}

