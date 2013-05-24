package asteroids.model.Util;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;
/**
 * An Abstract Class of vectors.
 * 
 * @author	Bruno Coussement and Simon Telen
 * @version 3.0
 *
 */
@Value
public abstract class Vector {

	protected final double xComp;
	protected final double yComp;
/**
 * Initialize a vector with a given x component and a given y component.
 * @param xComp
 * 			The x component.
 * @param yComp
 * 			The y component.
 * @post The new x component of this new vector is equal to the given x component 
 * 			if the given x component is a valid x component. If not, then the new
 * 			x component of this new world is equal to 0.
 * 			| if(isValidComponent(xComp)
 * 			| then new.getX() = xComp
 * 			| else new.getX() = 0
 * @post The new y component of this new vector is equal to the given y component 
 * 			if the given y component is a valid y component. If not, then the new
 * 			y component of this new world is equal to 0.
 * 			| if(isValidComponent(yComp)
 * 			| then new.getY() = yComp
 * 			| else new.getY() = 0
 */
	@Model
	public Vector(double xComp, double yComp) {
		
			this.xComp = xComp;
		
	
			this.yComp = yComp;

	}
/**
 * Initializes a default Vector.
 * @Effect This new vector is initialized with 50 as 
 * 			its new x-component and 50 as its new y-component.
 */
	@Model
	public Vector() {
		this(50,50);
	}
/**
 * @return the x-compononent of this vector.
 */
	@Basic
	@Raw
	@Immutable
	public double getX() {
		return xComp;
	}
/**
 * 
 * @return the y-component of this vector.
 */
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
		return v1.getX()*v2.getX() + v1.getY()*v2.getY();
		
	}
	/**
	 * Checks if the given component is a valid component.
	 * @param comp
	 * 			The component to check.
	 * @Return True if and only if the given component is
	 * 			not infinite and a number.
	 * 			| result ==  !(Double.isInfinite(comp) || Double.isNaN(comp))
	 */
	public static boolean isValidComponent(double comp){
		return !(Double.isInfinite(comp) || Double.isNaN(comp));
	}
	/**
	 * Checks if the given x component and the given y component are valid components.
	 * 
	 * @param x
	 * 			The x component to check.
	 * @param y
	 * 			The y component to check.
	 * @return True if and only if the given x component and the given y-component are
	 * 			both not infinite and are both a number.
	 * 			| result == (isValidComponent(x) && isValidComponent(y))
	 */
	public static boolean isValidVector(double x, double y){
		return (isValidComponent(x) && isValidComponent(y));
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
