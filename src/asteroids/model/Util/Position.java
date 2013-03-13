package asteroids.model.Util;

import be.kuleuven.cs.som.annotate.*;

public class Position {

//the x-component of a position vector.
private double posX;
// the y-component of a position vector.
private double posY;

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

public Position(double posX, double posY) {
	this.setPosX(posX);
	this.setPosY(posY);
}
/**
 * Returns the posX of this position.
 */
@Basic 
public double getPosX() {
	return posX;
}
/**
 * Sets the value for posX of this position to the given posX.
 * 
 * @param posX 
 * 		  The new value for the posX of this position.
 * @post the new posX of this position is equal to the given posX.
 *       |new.getPosX()==posX
 */

@Basic
public void setPosX(double posX) {
	
	this.posX = posX;
	
}

/**
 * Return the posY of this position.
 */
@Basic
public double getPosY() {
	
	return posY;
	
}

/**
 *Set the value for y the posY of this position to the given posY. 
 *
 * @param posY
 * 		  the new posY for this position.
 * @post the new posY of this position is equal to the given posY.
 *       |new.getPosY()==posY
 */

@Basic
public void setPosY(double posY) {
	
	this.posY = posY;
}

/**
 * Add a position vector to this position vector.
 * 
 * @param posToAdd
 *        the position vector to be added to this position
 * @post the new posX of this position is equal to the old posX of this position plus the
 *       posX of the given posToAdd
 *       |new.getPosX()==this.getPosX()+posToAdd.getPosX()
 * @post the new posY of this position is equal to the old posY of this position plus the
 *       posY of the given posToAdd
 *       |new.getPosY()==this.getPosY()+posToAdd.getPosY()
 */

public void add(Position posToAdd){
	setPosX(posToAdd.getPosX() + getPosX());
	setPosY(posToAdd.getPosY() + getPosY());
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

}