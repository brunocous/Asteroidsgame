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

	// The density of an asteroid in kg/km³.
	private static final double RHO_ASTEROID = 2.65 * Math.pow(10, 12);

	/**
	 * The constructor for this new asteroid.
	 */
	public Asteroid(Position pos, Velocity vel, double radius)
			throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		super(pos, vel, radius);
	}

	/**
	 * A default constructor for this new asteroid.
	 */
	public Asteroid() throws IllegalPositionException, IllegalRadiusException,IllegalMaxSpeedException{

		super(new Position(0, 0), new Velocity(0, 0), 10);

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
