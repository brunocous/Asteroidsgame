package asteroids.model.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of two dimensional velocity vectors involving an x- and a y-component.
 * 
 * @version 1.0
 * @author Bruno Coussement and Simon Telen
 *
 */
@Value
public class Velocity {
	
	//The x-component of a velocity vector.
	private double velX;
	//The y-component of a velocity vector. 
	private double velY;
    //The value for the speed of light.
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
	@Raw
	public Velocity(double velX, double velY) {
		
	 this.velX=velX;
	 this.velY=velY;
	 
	}
	
	/**
	 * Return the velX of this velocity
	 */
	@Basic @Raw @Immutable
	public double getVelX() {
		
		return velX;
		
	}

	/**
	 * Return the velY of this velocity.
	 */
	@Basic @Raw @Immutable
	public double getVelY() {
		
		return velY;
		
	}
	
	/**
	 * Return the speed of light.
	 */
	@Basic @Immutable
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
	 * @return The resulting velocity has the exact same X-component and the exact same Y-component as this velocity.
	 * 			|	result == this
	 * @return The new velX of this velocity is equal to the old velX of this velocity plus the
	 *       	velX of the given velToAdd, and the new velY of this velocity is equal to the old velY of this velocity plus the 
	 *       	velY of the given velToAdd.
	 *			|	(result.getVelX()==velToAdd.getVelX() + this.getVelX() )&&
	 *      	|	(result.getVelY()==velToAdd.getVelY() + this.getVelY() )
	 */
	public Velocity add(Velocity velToAdd){
		
		if(velToAdd == null){
			
			return this;
			
		}
		else{
			
		return new Velocity(getVelX() + velToAdd.getVelX(), getVelY() + velToAdd.getVelY());
		
		}
	}
	
	/**
	 * Check whether this velocity is equal to the given velocity.
	 * 
	 * @return True if and only if the given object is effective, and if this velocity and the given 
	 * 			velocity belong to the same class, and if this velocity and the other object 
	 * 			interpreted as a velocity have equal velX and equal velY.
	 * 			| result == ( (vel != null) && (this.getClass() == vel.getClass())
	 * 			| && asteroids.Util.fuzzyEquals(this.getVelX() , vel.getVelX())
	 * 			| && asteroids.Util.fuzzyEquals(this.getVelY() , vel.getVelY()) )
	 * 
	 */
	@Override
	public boolean equals(Object vel){
		if(vel == null)
			return false;
		return ( asteroids.Util.fuzzyEquals(this.getVelX() , ((Velocity) vel).getVelX()) 
				&& asteroids.Util.fuzzyEquals(this.getVelY() , ((Velocity) vel).getVelY()) );
	}
	/**
	 * Return the hash code for this velocity.
	 */
	@Override 
	public int hashCode(){
		return (new Double(this.getVelX()).hashCode()) + (new Double(this.getVelY()).hashCode());
	}
	/**
	 * Return a textual representation of this velocity.
	 * @return	A string consisting of the textual representation of the X- and Y-component of this velocity, 
	 * 			separated by a space and enclosed in square brackets.
	 * 			|	result.equals("[" + this.getVelX()+ " " + this.getVelY()+ "]" )
	 */
	@Override
	public String toString(){
		return "[" + this.getVelX()+ " " + this.getVelY()+ "]";
	}

}
