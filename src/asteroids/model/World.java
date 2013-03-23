package asteroids.model;

import asteroids.Util;
import asteroids.model.Util.Position;
import be.kuleuven.cs.som.annotate.*;

public class World {

	/**
	 * The maximum width of the world.
	 */
	private static final double MAX_WIDTH = Double.MAX_VALUE;
	/**
	 * The maximum height of the world.
	 */
	private static final double MAX_HEIGHT = Double.MAX_VALUE;
	
	public World(){
		
	}

	/**
	 * @return the MAX_WIDTH
	 */
	@Basic
	@Immutable
	public static double getMaxWidth() {
		return MAX_WIDTH;
	}

	/**
	 * @return the MAX_HEIGHT
	 */
	@Basic
	@Immutable
	public static double getMaxHeight() {
		return MAX_HEIGHT;
	}
	
	/**
	 * Checks if the x- and y-component of the given position are within or equal to the values of the boundaries.
	 * @param pos 
	 * 			The position to be checked.
	 * @return True if the x- and y- components of the given pos are both within or on the values of the boundaries.
	 * 		 	| result == (Util.fuzzyEquals(pos.getX(), 0) && Util.fuzzyEquals(pos.getY(), 0) && (pos.getX() > 0) && (pos.getY() > 0) 
				| && Util.fuzzyLessThanOrEqualTo(pos.getX(), getMaxWidth()) && Util.fuzzyLessThanOrEqualTo(pos.getY(), getMaxHeight()))
	 */
	public static boolean isSituatedInOrOnBoundaries(Position pos){
		return (Util.fuzzyEquals(pos.getX(), 0) && Util.fuzzyEquals(pos.getY(), 0) && (pos.getX() > 0) && (pos.getY() > 0) 
				&& Util.fuzzyLessThanOrEqualTo(pos.getX(), getMaxWidth()) && Util.fuzzyLessThanOrEqualTo(pos.getY(), getMaxHeight()));
	}
}
