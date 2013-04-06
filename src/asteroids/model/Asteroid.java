package asteroids.model;

import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class of asteroids involving a position, a velocity and a radius.
 */
public class Asteroid extends SpaceObject {

	/**
	 * The density of an asteroid in kg/km³.
	 */
	private static final double RHO_ASTEROID = 2.65 * Math.pow(10, 12);

	/**
	 * Initializes a new asteroid with given position, velocity, radius and world.
	 * @param pos
	 *        The position for this new asteroid.
	 * @param vel
	 *        The velocity for this new asteroid.
	 * @param radius
	 *        The radius for this new asteroid.
	 * @param world
	 * 		  The World where this new asteroid belongs to.
	 * @effect This new Asteroid is initialized with the given position as its position,
	 * 			the given velocity as its velocity, the given radius as its radius and 
	 * 			belongs to the given world.
	 * 			| this(pos, vel, radius, world)
	 */
	public Asteroid(Position pos, Velocity vel, double radius, World world)
			throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		super(pos, vel, radius, world);
	}
	/**
	 * Initializes a new asteroid with given position, velocity, radius.
	 * @param pos
	 *        The position for this new asteroid.
	 * @param vel
	 *        The velocity for this new asteroid.
	 * @param radius
	 *        The radius for this new asteroid.
	 * @effect This new Asteroid is initialized with the given position as its position,
	 * 			the given velocity as its velocity, the given radius as its radius and 
	 * 			does not belong to any world.
	 * 			| this(pos, vel, radius, new World())
	 */
	public Asteroid(Position pos, Velocity vel, double radius)throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		this(pos,vel,radius, null);
	}
	/**
	 * Initializes this new asteroid as a default asteroid.
	 * @effect This new Asteroid is initialized with the default position as its position,
	 * 			the default velocity as its velocity, a radius of 15 km as its radius and 
	 * 			does not belong to any world.
	 * 			| this(new Position(), new Velocity(), 15, null)
	 */
	public Asteroid() throws IllegalPositionException, IllegalRadiusException,IllegalMaxSpeedException{
		this(new Position(), new Velocity(), 15);

	}

	/**
	 * Returns the density of an asteroid.
	 * @return
	 */
	@Basic @Immutable
	public static double getRhoAsteroid(){
		
		return RHO_ASTEROID;
		
	}
	
	/**
	 * Returns the mass of this asteroid.
	 * 
	 * @return result ==4/3*Math.PI*Math.pow(radius,3)*RHO_ASTEROID
	 */
	@Override
	public double getMass() {
		
		return 4 / 3 * Math.PI * Math.pow(this.getRadius(), 3) * getRhoAsteroid();
		
	}

	
}
