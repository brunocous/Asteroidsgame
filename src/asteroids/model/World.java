package asteroids.model;

public class World {

	/**
	 * The maximum width of the world.
	 */
	private final double maxWidth;
	/**
	 * The maximum height of the world.
	 */
	private final double maxHeight;
	
	public World(double maxWidth, double maxHeight){
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}
	
	public World(){
		this.maxWidth = Double.MAX_VALUE;
		this.maxHeight = Double.MAX_VALUE;
	}

	/**
	 * @return the maxWidth
	 */
	public double getMaxWidth() {
		return maxWidth;
	}

	/**
	 * @return the maxHeight
	 */
	public double getMaxHeight() {
		return maxHeight;
	}
	
}
