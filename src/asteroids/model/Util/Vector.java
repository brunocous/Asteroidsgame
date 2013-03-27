package asteroids.model.Util;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

@Value
public abstract class Vector {

	protected final double xComp;
	protected final double yComp;

	public Vector(double xComp, double yComp) {
		this.xComp = xComp;
		this.yComp = yComp;
	}

	public Vector() {
		this.xComp = 0;
		this.yComp = 0;
	}

	@Basic
	@Raw
	@Immutable
	public double getX() {
		return xComp;
	}

	@Basic
	@Raw
	@Immutable
	public double getY() {
		return yComp;
	}
	
	/**
	 * Calculates the scalar product of two 2-dimensional vectors.
	 * 
	 * @param v1
	 *        The first vector.
	 * @param v2
	 *        The second vector.
	 * @return the scalar product of vector1 and vector2
	 *         |result==v1.getX()*v2.getX() + v1.getY()+v2.getY()
	 */
	public static double scalarProduct(Vector v1, Vector v2){
		return v1.getX()*v2.getX() + v1.getY()+v2.getY();
		
	}
	/**
	 * Check whether this vector is equal to the given vector.
	 * 
	 * @return True if and only if the given object is effective, and if this
	 *         vector and the given vector belong to the same class, and if this
	 *         vector and the other object interpreted as a vector have equal
	 *         xComp and equal yComp. 
	 *         | result == ( (vec != null) &&(Vector.isInstance(vec))) 
	 *         | && asteroids.Util.fuzzyEquals(this.getX() , vec.getX()) 
	 *         | && asteroids.Util.fuzzyEquals(this.getY() , vec.getY()) )
	 * 
	 */
	@Override
	public boolean equals(Object vec) {
		if (vec == null)
			return false;
		try {
			return (Util.fuzzyEquals(this.getX(),
					((Vector) vec).getX()) && Util.fuzzyEquals(
					this.getY(), ((Vector) vec).getY()));
		} catch (ClassCastException ex) {
			return false;
		}
	}

	public abstract Vector add(Vector vect);
	
	/**
	 * Return the hash code for this vector.
	 */
	@Override 
	public int hashCode(){
		return (new Double(this.getX()).hashCode()) + (new Double(this.getY()).hashCode());
	}
	
	/**
	 * Return a textual representation of this vector.
	 * @return	A string consisting of the textual representation of the X- and Y-component of this vector, 
	 * 			separated by a space and enclosed in square brackets.
	 * 			|	result.equals("[" + this.getX()+ " " + this.getY()+ "]" )
	 */
	@Override
	public String toString(){
		return "[" + this.getX()+ " " + this.getY()+ "]";
	}
}
