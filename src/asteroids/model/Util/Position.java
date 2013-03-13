package asteroids.model.Util;

import be.kuleuven.cs.som.annotate.*;

@Value
public class Position {

/**	
*	the x-component of a position vector.
*/
private final double posX;

/**
 * the y-component of a position vector.
 */
private final double posY;

/**
 * Initialize this new position with given posX and given posY.
 * 
 * @param posX
 *        the initial x-coördinate for this new position.
 * @param posY
 *        the initial y-coördinate for this new position.
 * @post the new posX for this new position is equal to the given posX
 *       |new.getPosX()==posX
 * @post the new posY for this new position is equal to the given posY
 *       |new.getPosY()==posY   
 */
@Raw
public Position(double posX, double posY) {
	this.posX = posX;
	this.posY = posY;
}
/**
 * Returns the posX of this position.
 */
@Basic @Raw @Immutable
public double getPosX() {
	return this.posX;
}

/**
 * Return the posY of this position.
 */
@Basic @Raw @Immutable
public double getPosY() {
	
	return this.posY;
	
}

/**
 * Add a position vector to this position vector.
 * 
 * @param posToAdd
 *        the position vector to be added to this position
 * @return The resulting position has the same position as this position.
 * 		   | result == this
 * @return A position that is equal to this
 *         position plus the given posToAdd if the given posToAdd is not null.
 *         | result == new Position(posToAdd.getPosX() + getPosX(),posToAdd.getPosY() + getPosY())
 * @throws IllegalArgumentException
 * 			The posToAdd position is not effective.
 * 		   | posToAdd == null
 */

public Position add(Position posToAdd) throws IllegalArgumentException{
	
	if(posToAdd == null){
		
		throw new IllegalArgumentException();
		
	}
	else{
		
	double xCoord=(posToAdd.getPosX() + getPosX());
	double yCoord=(posToAdd.getPosY() + getPosY());
	
	return new Position(xCoord,yCoord);
	}
}

/**
 * Calculate the distance between this position and another given position.
 * 
 * @param position
 *        the position to which the distance is calculated.
 * @return the distance between this position and the given position if the given position is
 *         different from this position, else 0.0. (The distance between a position and itself 
 *         is zero).
 *         | result ==Math.sqrt(Math.pow(getPosX()-position.getPosX(),2)+Math.pow(getPosY()-position.getPosY(),2))
 *        
 */

public double getDistanceTo(Position position){
	double distance = Math.sqrt(Math.pow(getPosX()-position.getPosX(),2)+Math.pow(getPosY()-position.getPosY(),2));
	return distance;
}

/**
 * Check whether this position is equal to the given position.
 * 
 * @return True if and only if the given object is effective, if this position and the given 
 * 			position belong to the same class, and if this position and the other object 
 * 			interpreted as a position have equal posX and equal posY.
 * 			| result == ( (pos != null) && (this.getClass() == pos.getClass())
 * 			| && asteroids.Util.fuzzyEquals(this.getPosX() , pos.getPosX())
 * 			| && asteroids.Util.fuzzyEquals(this.getPosY() , pos.getPosY()) )
 * @throws IllegalArgumentException
 * 			The Object pos is not effective.
 * 			| pos == null
 */
@Override
public boolean equals(Object pos) throws IllegalArgumentException, ClassCastException{
	if(pos == null)
		throw new IllegalArgumentException();
	return ( asteroids.Util.fuzzyEquals(this.getPosX() , ((Position) pos).getPosX()) 
			&& asteroids.Util.fuzzyEquals(this.getPosY() , ((Position) pos).getPosY()) );
}
/**
 * Return the hash code for this position.
 */
@Override 
public int hashCode(){
	return (new Double(this.getPosX()).hashCode()) + (new Double(this.getPosY()).hashCode());
}
/**
 * Return a textual representation of this position.
 * @return	A string consisting of the textual representation of the X- and Y-component of this position, 
 * 			separated by a space and enclosed in square brackets.
 * 			|	result.equals("[" + this.getPosX()+ " " + this.getPosY()+ "]" )
 */
@Override
public String toString(){
	return "[" + this.getPosX()+ " " + this.getPosY()+ "]";
}

}