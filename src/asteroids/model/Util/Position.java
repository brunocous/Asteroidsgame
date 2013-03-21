package asteroids.model.Util;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of two dimensional position vectors involving an x- and a y-component.
 * 
 * @version 1.0
 * @author Bruno Coussement and Simon Telen
 * 
 */
@Value
public class Position extends Vector{


/**
 * Initialize this new position with given posX and given posY.
 * 
 * @param posX
 *        the initial x-coördinate for this new position.
 * @param posY
 *        the initial y-coördinate for this new position.
 * @post the new posX for this new position is equal to the given posX
 *       |new.getX()==posX
 * @post the new posY for this new position is equal to the given posY
 *       |new.getY()==posY   
 */
@Raw
public Position(double posX, double posY) {
	
	super(posX,posY);
	
}

public Position(){
	super();
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
@Override
public Vector add(Vector posToAdd) throws IllegalArgumentException{
	
	if(posToAdd == null){
		
		throw new IllegalArgumentException();
		
	}
	else{
		
	double xCoord=(posToAdd.getX() + getX());
	double yCoord=(posToAdd.getY() + getY());
	
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
 *         | result ==Math.sqrt(Math.pow(getX()-position.getX(),2)+Math.pow(getY()-position.getY(),2))
 *        
 */

public double getDistanceTo(Position position){
	
	double distance = Math.sqrt(Math.pow(getX()-position.getX(),2)+Math.pow(getY()-position.getY(),2));
	return distance;
	
}

/**
 * Check whether the given position is a valid position. In other words, check whether it has
 * finite components and components that are not 'NaN'.
 *
 * @param pos
 *        the position object to be checked in.
 * @return true if and only if the given position has finite components and components that
 *         are not NaN.
 *         |result == !(Double.isInfinite(pos.getX()) || Double.isNaN(pos.getX()) || Double.isInfinite(pos.getY()) || Double.isNaN(pos.getY()));
 */
public boolean isValidPosition(Position pos){
	
	return !(Double.isInfinite(pos.getX()) || Double.isNaN(pos.getX()) || Double.isInfinite(pos.getY()) || Double.isNaN(pos.getY()));

}

}