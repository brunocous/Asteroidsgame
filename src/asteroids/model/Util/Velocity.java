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
public class Velocity extends Vector{
	
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
		
	super(velX,velY);
	}
	//TODO Commentaar?
	public Velocity(){
		super();
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
		
		double result = Math.sqrt(Math.pow(getX(), 2.0) + Math.pow(getY(), 2.0));
		return result;
		
	}
	//TODO documentatie
	/**
	 * Add a given velocity vector to this velocity vector.
	 * @param velToAdd
	 *        the velocity vector to be added to this velocity 
	 * @return The resulting velocity has the exact same X-component and the exact same Y-component as this velocity.
	 * 			|	result == this
	 * @return The new velX of this velocity is equal to the old velX of this velocity plus the
	 *       	velX of the given velToAdd, and the new velY of this velocity is equal to the old velY of this velocity plus the 
	 *       	velY of the given velToAdd.
	 *			|	(result.getX()==velToAdd.getX() + this.getX() )&&
	 *      	|	(result.getY()==velToAdd.getY() + this.getY() )
	 */
	@Override
	public Vector add(Vector velToAdd){
		
		if(velToAdd == null){
			
			return this;
			
		}
		else{
			
		return new Velocity(getX() + velToAdd.getX(), getY() + velToAdd.getY());
		
		}
	}
	
}
