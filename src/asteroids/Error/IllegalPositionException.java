package asteroids.Error;

public class IllegalPositionException extends Exception{
	/**
	 * The x-component of the boundarie where the exception is thrown.
	 */
private final double xCompBoundarie;
/**
 * The y-component of the boundarie where the exception is thrown.
 */
private final double yCompBoundarie;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1099638215286822665L;
	
	public IllegalPositionException(double xCompBoundarie, double yCompBoundarie){
		this.xCompBoundarie = xCompBoundarie;
		this.yCompBoundarie = yCompBoundarie;
	}

	/**
	 * @return the xCompBoundarie
	 */
	public double getxCompBoundarie() {
		return xCompBoundarie;
	}

	/**
	 * @return the yCompBoundarie
	 */
	public double getyCompBoundarie() {
		return yCompBoundarie;
	}
	
	

}
