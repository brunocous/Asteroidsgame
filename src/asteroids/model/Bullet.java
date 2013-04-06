package asteroids.model;

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
	 * The constructor for this new bullet.
	 * @Pre The source of this new bullet must be effective
	 * 		| (source != null)
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
	public static double getInitialSpeed() {
		return INITIAL_SPEED;
	}

	/**
	 * @return the initialRadius
	 */
	public static double getInitialRadius() {
		return INITIAL_RADIUS;
	}
}
