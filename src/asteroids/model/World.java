package asteroids.model;

import java.util.ArrayList;

import asteroids.Util;
import asteroids.Error.IllegalPositionException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Vector;
import asteroids.model.Util.Velocity;
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
	//A list of space objects for this new world.
	private ArrayList<SpaceObject> spaceObjects;
	//The width of this new world.
	private double width;
	//The height of this new world.
	private double height;
	
	public World(double width, double height){
		
		this.setWidth(width);
		this.setHeight(height);
		this.spaceObjects = new ArrayList<SpaceObject>();
		
	}
	

	@Basic
	public ArrayList<SpaceObject> getSpaceObjects() {
		return spaceObjects;
	}


	@Basic
	public void setSpaceObjects(ArrayList<SpaceObject> spaceObjects) {
		this.spaceObjects = spaceObjects;
	}


	@Basic
	public double getWidth() {
		return width;
	}


	@Basic
	public void setWidth(double width) {
		if(isValidWidth(width)){
		
			this.width = width;
			
		}
		else{
			
			this.width = getMaxWidth();
			
		}
	}

	public boolean isValidWidth(double width){
		
		return (!Util.fuzzyLessThanOrEqualTo(width,0 )&& Util.fuzzyLessThanOrEqualTo(width, getMaxWidth()));
	}

	@Basic
	public double getHeight() {
		return height;
	}


	@Basic
	public void setHeight(double height) {
		if(isValidHeight(height)){
			
			this.width = height;
			
		}
		else{
			
			this.width = getMaxHeight();
			
		}
	}

	public boolean isValidHeight(double height){
		
		return (!Util.fuzzyLessThanOrEqualTo(height,0 )&& Util.fuzzyLessThanOrEqualTo(height, getMaxHeight()));
	
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
	public static boolean isSituatedInOrOnBoundaries(SpaceObject spaceObject){
		
		double x = spaceObject.getPos().getX();
		double y = spaceObject.getPos().getY();
		double r = spaceObject.getRadius();
		
		return ((!Util.fuzzyLessThanOrEqualTo(x-r, 0) && (!Util.fuzzyLessThanOrEqualTo(y-r, 0)) 
				&& Util.fuzzyLessThanOrEqualTo(x+r, getMaxWidth()) && Util.fuzzyLessThanOrEqualTo(y+r, getMaxHeight())));
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
	
	public double getTimeToFirstCollision() {
		
		int i = 0;
		int j = 0;
		double result = SpaceObject.getTimeToCollision(spaceObjects.get(1), spaceObjects.get(2));
		
		while(i!=spaceObjects.size()){
			
			while(j!=spaceObjects.size()){
				
				double timeToCollision = SpaceObject.getTimeToCollision(spaceObjects.get(i), spaceObjects.get(j));
				
				if (timeToCollision!=Double.POSITIVE_INFINITY && Util.fuzzyLessThanOrEqualTo(timeToCollision,result)){
					
					result = timeToCollision;
				
				}
				j++;
			}
			double timeToBoundaryCollision = getTimeToBoundaryCollision(spaceObjects.get(i));
			if (Util.fuzzyLessThanOrEqualTo(timeToBoundaryCollision, result)){
				
				result = timeToBoundaryCollision;
				
			}
			i++;
		}
		
		return result;
		
	}
	
	public double getTimeToBoundaryCollision(SpaceObject spaceObject){
		
		return 1;
		
	}
	
	public void evolve(double deltaT) throws IllegalPositionException{
		
		double tC=getTimeToFirstCollision();
		
		if(!Util.fuzzyLessThanOrEqualTo(tC, deltaT)){
			
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
	
	public void bounceOff(SpaceObject obj1, SpaceObject obj2){
		
		double mi = obj1.getMass();
		double mj = obj2.getMass();
		double sigma = obj1.getRadius()+ obj2.getRadius();
		Velocity deltaV = new Velocity(obj2.getVel().getX()-obj1.getVel().getX(),obj2.getVel().getY()-obj1.getVel().getY());
		Position deltaR = new Position(obj2.getPos().getX()-obj1.getPos().getX(),obj2.getPos().getY()-obj1.getPos().getY());
		
		double J=(2*mi*mj*Vector.scalarProduct(deltaV, deltaR))/(sigma*(mi+mj));
		
		Velocity velToAddI = new Velocity(J*deltaR.getX()/(sigma*mi), J*deltaR.getY()/(sigma*mi));
		Velocity velToAddJ = new Velocity(J*deltaR.getX()/(sigma*mj), J*deltaR.getY()/(sigma*mj));
		
		obj1.getVel().add(velToAddI);
		obj2.getVel().add(velToAddJ);
		
	}
	
	
	public void resolve(SpaceObject object1, SpaceObject object2){
		
		if(Ship.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
			bounceOff(object1,object2);
			
		}
		else if(Asteroid.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())){
			
			bounceOff(object1,object2);
			
		}
		else if(Bullet.class.isAssignableFrom(object1.getClass()) || Bullet.class.isAssignableFrom(object2.getClass())){
			
			object1.terminate();
			object2.terminate();
			
		}
		else if(Asteroid.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
			object2.terminate();
			
		}
		else if(Ship.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())){
			
			object1.terminate();
			
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

