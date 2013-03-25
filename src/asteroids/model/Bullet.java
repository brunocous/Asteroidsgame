package asteroids.model;

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

	// The ship that fired this bullet.
	private final Ship source;
	// The density of a bullet in kg/km³.
	private static final double RHO_BULLET = 7.8 * Math.pow(10, 12);

	/**
	 * The constructor for this new bullet.
	 */
	public Bullet(Position pos, Velocity vel, double radius, Ship source)
			throws IllegalRadiusException, IllegalPositionException {

		super(pos, vel, radius);
		if (source != null) {

			this.source = source;
		} else {

			throw new IllegalArgumentException();

		}
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
	public double getMass(double radius) {

		return 4 / 3 * Math.PI * Math.pow(radius, 3) * getRhoBullet();

	}



	/**
	 * Returns the source of this bullet
	 */
	@Basic
	public Ship getSource() {

		return this.source;

	}
}
