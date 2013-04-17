package asteroids.model;

import java.util.Vector;

import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class of bullets involving a position, a velocity, a source and a radius.
 */
public class Bullet extends SpaceObject {
	
	/**
	 * The ship that fired this bullet.
	 */
	private final Ship source;
	/**
	 * The density of a bullet in kg/km³.
	 */
	private static final double RHO_BULLET = 7.8 * Math.pow(10, 12);
	/**
	 * The speed of a bullet that has been fired.
	 */
	private static final double INITIAL_SPEED = 250;
	/**
	 * The radius of a bullet that has been fired.
	 */
	private static final double INITIAL_RADIUS = 3;
	/**
	 * The number of times a bullet has hit a boundary.
	 */
	private int nbBoundaryCollisions = 0;
	/**
	 * The number of times a bullet is allowed to hit a boundary.
	 */
	private static final int MAX_NUMBER_OF_BOUNDARY_COLLISIONS = 2;

	/**
	 * Initialize a new bullet with a given ship.
	 * @param source
	 * 		The source of this new bullet.
	 * @effect ...
	 * 			| super(
	 * 			| new Position(source.getPos().getX() + Math.cos(source.getDirection())*(getInitialRadius()+source.getRadius()),source.getPos().getY() + Math.sin(source.getDirection())*(getInitialRadius()+source.getRadius()))
	 * 			| , new Velocity(Math.cos(source.getDirection())*getInitialSpeed(),Math.sin(source.getDirection())*getInitialSpeed())
	 *			| , getInitialRadius(), source.getWorld())
	 * @post ...
	 * 			| (new this).getSource == source; 
	 * 			| (new this).getNbBoundaryCollisions() == 0
	 */
	public Bullet(Ship source)
			throws IllegalRadiusException, IllegalPositionException,IllegalMaxSpeedException{
		super(
				new Position(source.getPos().getX() + Math.cos(source.getDirection())*(getInitialRadius()+source.getRadius()),source.getPos().getY() + Math.sin(source.getDirection())*(getInitialRadius()+source.getRadius()))
				, new Velocity(Math.cos(source.getDirection())*getInitialSpeed(),Math.sin(source.getDirection())*getInitialSpeed())
				, getInitialRadius(), source.getWorld());
		
		this.source = source;
	}

	/**
	 * Returns the value of the density of a bullet.
	 * 
	 * @return result == RHO_BULLET
	 */
	@Basic
	@Immutable
	public static double getRhoBullet() {

		return RHO_BULLET;

	}

	/**
	 * Returns the mass of this bullet.
	 * 
	 * @return result ==4/3*Math.PI*Math.pow(radius,3)*RHO_BULLET
	 * 
	 */
	@Override
	public double getMass() {

		return 4 / 3 * Math.PI * Math.pow(this.getRadius(), 3) * getRhoBullet();

	}



	/**
	 * Returns the source of this bullet
	 */
	@Basic
	public Ship getSource() {

		return this.source;

	}

	/**
	 * @return the initialSpeed
	 */
	@Basic
	public static double getInitialSpeed() {
		return INITIAL_SPEED;
	}

	/**
	 * @return the initialRadius
	 */
	@Basic
	public static double getInitialRadius() {
		return INITIAL_RADIUS;
	}

	/**
	 * @return the nbBoundaryCollisions
	 */
	@Basic
	public int getNbBoundaryCollisions() {
		return nbBoundaryCollisions;
	}
	/**
	 * Checks if the given number of boundary collisions is valid.
	 * @return True if and only if the given number of boundary collisions
	 * 			is less than the maximum number of boundary collisions.
	 * 			| result == (nbBoundaryCollisions < getMaxNumberOfBoundaryCollisions())
	 */
	public boolean isValidNbBoundaryCollisions(int nbBoundaryCollisions){
		return (nbBoundaryCollisions < getMaxNumberOfBoundaryCollisions());
	}
	
	/**
	 * Sets the number of boundary collisions to the given number of
	 * boundary collisions.
	 * 
	 * @param nbBoundaryCollisions 
	 * 			the nbBoundaryCollisions to set
	 * @post If the given number of boundary collisions is valid, then
	 * 		the number of boundary collisions of this bullet is equal to
	 * 		the given number of boundary collisions.
	 * 		| if(isValidNbBoundaryCollisions(nbBoundaryCollisions))
	 * 		| then new.getNbBounderayCollisions 
	 * 		|					== nbBoundaryCollisions
	 */
	@Basic
	private void setNbBoundaryCollisions(int nbBoundaryCollisions) {
		if(isValidNbBoundaryCollisions(nbBoundaryCollisions)){
		this.nbBoundaryCollisions = nbBoundaryCollisions;
		}
	}

	/**
	 * @return True if and only if this bullet can bounce.
	 * 			| result == isValidNbBoundaryCollisions(getNbBoundaryCollisions()+1)
	 */
	public boolean canBounce(){
		return this.isValidNbBoundaryCollisions(this.getNbBoundaryCollisions()+1);
	}
	/**
	 * Makes this bullet bounce.
	 * @effect ...
	 * 		| setNbBoundaryCollisions(getNbBoundaryCollisions() + 1)
	 */
	public void bounce(){
		this.setNbBoundaryCollisions(this.getNbBoundaryCollisions() + 1);
	}
	/**
	 * @return the maxNumberOfBoundaryCollisions
	 */
	@Basic 
	@Immutable
	public static int getMaxNumberOfBoundaryCollisions() {
		return MAX_NUMBER_OF_BOUNDARY_COLLISIONS;
	}
}
