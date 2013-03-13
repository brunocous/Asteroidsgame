package asteroids.model.Util;
import be.kuleuven.cs.som.annotate.*;


public class Velocity {
	// the x-component of a velocity vector
	private double velX;
	// the y-component of a velocity vector 
	private double velY;
	// the value for the speed of light
	private static final double SPEED_OF_LIGHT=300000000.0;
	
	/**
	 * Initialize this new velocity with given velX and velY.
	 * @param velX
	 *        the velX for this new velocity.
	 * @param velY
	 *        the velY for this new velocity.
	 * @post the initial velX for this new velocity is equal to the given velX.
	 *       |new.getVelX()==velX
	 * @post the initial velY for this new velocity is equal to the given velY.
	 *       |new.getVelY()==velY
	 */
	public Velocity(double velX, double velY) {
	 this.velX=velX;
	 this.velY=velY;
	}
	
	/**
	 * Return the velX of this velocity
	 */
	@Basic
	public double getVelX() {
		return velX;
	}
	
	/**
	 * Set the velX of this velocity to the given velX.
	 * @param velX
	 *        the new velX for this velocity.
	 * @post the new velX for this velocity is equal to the given velX.
	 *       |new.getVelX()==velX
	 */
	@Basic
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	/**
	 * Return the velY of this velocity.
	 */
	@Basic
	public double getVelY() {
		return velY;
	}
	
	/**
	 * Set the velY of this velocity to the given velY.
	 * @param velY
	 *        the new velY for this velocity.
	 * @post the new velY for this velocity is equal to the given velY.
	 *       |new.getVelY()==velY
	 */
	@Basic
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	/**
	 * Return the speed of light.
	 */
	// TODO @immutable?
	@Basic 
	public static double getSpeedOfLight(){
		return SPEED_OF_LIGHT;
	}
	
	/**
	 * Calculate the norm of this velocity vector.
	 * 
	 * @return the norm of this velocity vector.
	 *         |result == Math.sqrt(Math.pow(getVelX(), 2.0) + Math.pow(getVelY(), 2.0))
	 */
	public double getNorm(){
		double result = Math.sqrt(Math.pow(getVelX(), 2.0) + Math.pow(getVelY(), 2.0));
		return result;
	}
	
	/**
	 * Add a given velocity vector to this velocity vector.
	 * @param velToAdd
	 *        the velocity vector to be added to this velocity 
	 * @post The new velX of this velocity is equal to the old velX of this velocity plus the
	 *       velX of the given velToAdd.
	 *       |new.getVelX()==velToAdd.getVelX() + this.getVelX()
	 * @post The new velY of this velocity is equal to the old velY of this velocity plus the 
	 *       velY of the given velToAdd.
	 *       |new.getVelY()==velToAdd.getVelY() + this.getVelY()
	 */
	public void add(Velocity velToAdd){
		setVelX(velToAdd.getVelX() + getVelX());
		setVelY(velToAdd.getVelY() + getVelY());
	}

}
