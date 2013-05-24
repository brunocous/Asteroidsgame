package asteroids.model.Util;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of two dimensional position vectors involving an x- and a y-component.
 * 
 * @version 3.0
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
 * @effect This new position is initialized with posX as its new 
 * 			x-component and with posY as its new y-component.
 * 			| super(posX,posY)  
 */
@Raw
public Position(double posX, double posY) {
	
	super(posX,posY);
	
}
/**
 * Initialize this new position as a default position.
 * @Effect This new position is initialized with 50 as its new x-component 
 * 			and 50 as its new y-component.
 */
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
 *         | result == new Position(posToAdd.getX() + getX(),posToAdd.getY() + getY())
 * @throws IllegalArgumentException
 * 			The posToAdd position is not effective.
 * 		   | posToAdd == null
 */
@Override
public Vector add(Vector posToAdd){
	
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
}