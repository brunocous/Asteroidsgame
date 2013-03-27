package asteroids.model;

import java.util.ArrayList;

import asteroids.Util;
import asteroids.Error.IllegalPositionException;
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
	private ArrayList<SpaceObject> spaceObjects;
	
	public World(){
		
		this.spaceObjects = new ArrayList<SpaceObject>();
		
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
	
	public void updatePositions(double deltaT) throws IllegalPositionException{
		
		for(SpaceObject object : spaceObjects){
			
			double posX=object.getPos().getX()+ deltaT*object.getVel().getX();
			double posY=object.getPos().getY()+ deltaT*object.getVel().getY();
			
			object.setPos(new Position(posX,posY));
		}
		
	}
	
	public void updateVelocities(double deltaT){
		
		for(SpaceObject object : spaceObjects){
			
				if(Ship.class.isAssignableFrom(object.getClass())){
					
					((Ship)object).thrust(deltaT);
					
				}
			
		 }
	}
	
	public void evolve(double deltaT){
		
		double tC=getTimeToFirstCollision();
		
		if(tC > deltaT){
			
			updatePositions(deltaT);
			updateVelocities(deltaT);
			
		}
		
		else{
			
			updatePositions(tC);
			updateVelocities(tC);
			
			for(SpaceObject object1 : spaceObjects){
				
				for(SpaceObject object2 : spaceObjects){
					
					if(Util.fuzzyEquals(getDistanceBetween(object1,object2),0) && object1!=object2){
						resolve(object1,object2);
					}
				}
			}
		}
	}
	
	public void resolve(SpaceObject object1, SpaceObject object2){
		
		if(Ship.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
			double mi = object1.getMass();
			double mj = object2.getMass();
	}
	}
	/**
	 * Returns the distance between two given space objects.  
	 * 
	 * @pre The given obj1 and obj2 should not be null.
	 * @param obj1
	 * 	      The first space object of which the position will be compared to the given SpaceObject obj2.
	 * @param obj2
	 *        The second space object of which the position will be compared to the given SpaceObject obj1.
	 * @return The distance between the outer side of obj1 and obj2 if obj1 and obj2 are different space objects, 
	 *         0.0 if obj1 and obj2 are the same space object. 
	 *         The result will be negative if obj1 and obj2 overlap.
	 *         |if(obj1==obj2)
	 *         |  then result == 0.0
	 *         |else result == obj1.getPos().getDistanceTo(obj2.getPos())-(obj1.getRadius()+obj2.getRadius())
	 */
	public static double getDistanceBetween(SpaceObject obj1, SpaceObject obj2) {
		if(obj1==obj2){
			
			return 0;
			
		}
		else{
			
		double distanceBetweenCentres = obj1.getPos().getDistanceTo(obj2.getPos());
		double sumOfRadii= obj1.getRadius()+obj2.getRadius();
		return distanceBetweenCentres - sumOfRadii;
		
		}
		
		
	}
	}

