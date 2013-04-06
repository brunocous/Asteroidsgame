package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import deel3.les7.opl.worlds.SpaceObject;

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
	//Indicates whether a world is indicated.
	private boolean isTerminated;
	
	public World(double width, double height){
		
		this.setWidth(width);
		this.setHeight(height);
		this.spaceObjects = new ArrayList<SpaceObject>();
		
	}
	

	@Basic
	public ArrayList<SpaceObject> getSpaceObjects() {
		return new ArrayList<SpaceObject>(spaceObjects);
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
	 * 
	 */
	
	/**
	 * @return the isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}


	/**
	 * @param isTerminated the isTerminated to set
	 */
	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}

	/**
	 * Terminates a world.
	 * TODO alle verbindingen verwijderen.
	 */
	public void terminate(){
		this.setTerminated(true);
	}
	
	/**
	 * Return the space object of this world at the given index.
	 * 
	 * @param   index
	 *          The index of the space object to return.
	 * @throws  IndexOutOfBoundsException
	 *          The given index is not positive or it exceeds the
	 *          number of space objects of this world.
	 *        | (index < 1) || (index > getNbSpaceObjects())
	 */
	@Basic
	@Raw
	public SpaceObject getSpaceObjectAt(int index) throws IndexOutOfBoundsException {
		return this.getAllSpaceObjects().get(index - 1);
	}

	/**
	 * Return the number of space objects of this world.
	 */
	@Basic
	@Raw
	public int getNbSpaceObjects() {
		return this.getAllSpaceObjects().size();
	}

	/**
	 * Check whether this world can have the given space object
	 * as one of its space objects.
	 * 
	 * @param   spaceObject
	 *          The space object to check.
	 * @return  True if and only if the given space object is effective, and
	 *          if that space object can have this world as its world.
	 *        | result ==
	 *        |   (spaceObject != null) &&
	 *        |   spaceObject.canHaveAsWorld(this)
	 */
	@Raw
	public boolean canHaveAsSpaceObject(SpaceObject spaceObject) {
		return (spaceObject != null) && spaceObject.canHaveAsWorld(this);
	}

	/**
	 * Check whether this world can have the given space object
	 * as one of its space objects at the given index.
	 * 
	 * @param   spaceObject
	 *          The space object to check.
	 * @param   index
	 *          The index to check.
	 * @return  False if the given index is not positive or exceeds
	 *          the number of space objects of this world + 1.
	 *        | if ( (index < 1) || (index > this.getNbSpaceObjects()+1) )
	 *        |   then result == false
	 *          Otherwise, false if this world cannot have the
	 *          given spaceObject as one of its spaceObjects.
	 *        | else if (! canHaveAsSpaceObject(spaceObject))
	 *        |   then result == false
	 *          Otherwise, true if and only if the given spaceObject is
	 *          not already registered at another index.
	 *        | else result ==
	 *        |   for each I in 1..getNbSpaceObjects():
	 *        |     ( (I == index) || (getSpaceObjectAt(I) != spaceObject) )
	 */
	@Raw
	public boolean canHaveAsSpaceObjectAt(SpaceObject spaceObject, int index) {
		if ((index < 1) || (index > this.getNbSpaceObjects() + 1))
			return false;
		if (!canHaveAsSpaceObject(spaceObject))
			return false;
		for (int pos = 1; pos <= this.getNbSpaceObjects(); pos++)
			if ((pos != index) && (this.getSpaceObjectAt(pos) == spaceObject))
				return false;
		return true;
	}

	/**
	 * Check whether this world has a proper list of space objects.
	 * 
	 * @return  True if and only if this world can have each of its
	 *          space objects at their index, and if each of these space objects
	 *          references this world as their world.
	 *        | for each spaceObject in 1..this.getNbSpaceObjects():
	 *        |   this.canHaveAsSpaceObjectAt(getOwningAt(index),index) &&
	 *        |   (getSpaceObjectAt(index).getOwner() == this)
	 */
	public boolean hasProperSpaceObjects() {
		for (int index = 1; index <= this.getNbSpaceObjects(); index++) {
			if (!this.canHaveAsSpaceObjectAt(this.getSpaceObjectAt(index), index))
				return false;
			if (this.getSpaceObjectAt(index).getWorld() != this)
				return false;
		}
		return true;
	}

	/**
	 * Check whether this world has the given space object as one of
	 * its space objects.
	 *
	 * @param   spaceObject
	 *          The space object to check.
	 * @return  True if and only if this world has the given space object
	 *          as one of its space objects at some index.
	 *        | result ==
	 *        |   for some index in 1..this.getNbSpaceObjects():
	 *        |     this.getOwningAt(index).equals(spaceObject)
	 */
	@Raw
	public boolean hasAsSpaceObject(SpaceObject spaceObject) {
		return this.getAllSpaceObjects().contains(spaceObject);
	}

	/**
	 * Return the index at which the given space object is registered
	 * in the list of space objects for this world.
	 *  
	 * @param  spaceObject
	 *         The space object to search for.
	 * @return If this world has the given space object as one of its
	 *         space objects, that space object is registered at the resulting
	 *         index. Otherwise, the resulting value is -1.
	 *       | if (this.hasAsSpaceObject(spaceObject))
	 *       |    then this.getOwningAt(result) == spaceObject
	 *       |    else result == -1
	 */
	@Raw
	public int getIndexOfSpaceObject(SpaceObject spaceObject) {
		return this.getAllSpaceObjects().indexOf(spaceObject);
	}

	/**
	 * Return a list of all the space objects of this world.
	 * 
	 * @return  The size of the resulting list is equal to the number of
	 *          space objects of this world.
	 *        | result.size() == getNbSpaceObjects()
	 * @return  Each element in the resulting list is the same as the
	 *          space object of this world at the corresponding index.
	 *        | for each index in 0..result-size()-1 :
	 *        |   result.get(index) == getOwningAt(index+1)
	 */
	public List<SpaceObject> getAllSpaceObjects() {
		return new ArrayList<SpaceObject>(spaceObjects);
	}

	/**
	 * TODO precondities in orde brengen, KLOPPEN NIET.
	 * Add the given spaceObject at the end of the list of
	 * space objects of this world.
	 * 
	 * @param   spaceObject
	 *          The space object to be added.
	 * @pre     The given space object is effective and already references
	 *          this world as its world.
	 *        | (spaceObject != null) && (spaceObject.getWorld() == this)
	 * @pre     This world does not not yet have the given space object
	 *          as one of its space objects.
	 *        | ! hasAsOwning(spaceObject)
	 * @post    The number of space objects of this world is incremented
	 *          by 1.
	 *        | new.getNbSpaceObjects() == getNbSpaceObjects() + 1
	 * @post    This world has the given space object as its new last
	 *          space object.
	 *        | (new this).getOwningAt(this.getNbSpaceObjects()+1) == spaceObject
	 */
	public void addAsSpaceObject(@Raw SpaceObject spaceObject) {
		assert (spaceObject != null) && (spaceObject.getWorld() == this);
		assert !this.hasAsSpaceObject(spaceObject);
		spaceObjects.add(spaceObject);
	}

	/**
	 * TODO zie todo hierboven.
	 * Remove the given space object from the space objects of this world.
	 * 
	 * @param   spaceObject
	 *          The space object to be removed.
	 * @pre     The given space object is effective and does not have any
	 *          world.
	 *        | (spaceObject != null) && (spaceObject.getOwner() == null)
	 * @pre     This world has the given space object as one of
	 *          its space objects.
	 *        | hasAsOwning(spaceObject)
	 * @post    The number of space objects of this world is decremented
	 *          by 1.
	 *        | new.getNbSpaceObjects() == getNbSpaceObjects() - 1
	 * @post    This world no longer has the given space object as
	 *          one of its space objects.
	 *        | (! (new this).hasAsSpaceObject(spaceObject))
	 * @post    All space objects registered beyond the removed space object
	 *          shift one position to the left.
	 *        | for each index in getIndexOfOwning(spaceObject)+1..getNbSpaceObjects():
	 *        |   (new this).getSpaceObjectAt(index-1) == this.getSpaceObjectAt(index) 
	 */
	@Raw
	public void removeAsOwning(SpaceObject spaceObject) {
		assert (spaceObject != null) && (spaceObject.getWorld() == null);
		assert (this.hasAsSpaceObject(spaceObject));
		spaceObjects.remove(spaceObject);
	}

	/**
	 * Checks if all the points within the radius of the space object 
	 * from the x- and y-component of the position of the space object 
	 * are within or equal to the values of the boundaries.
	 * @param spaceObject
	 * 			The space object to be checked.
	 * @return True if all the points within the radius of the given position from the x- and y-components 
	 * 			of the position of the center of the given space object are both within or on the values of the boundaries.
	 * 		 	| result == ((!Util.fuzzyLessThanOrEqualTo(x-r, 0) && (!Util.fuzzyLessThanOrEqualTo(y-r, 0)) 
	 * 			| && Util.fuzzyLessThanOrEqualTo(x+r, getMaxWidth()) && Util.fuzzyLessThanOrEqualTo(y+r, getMaxHeight())))
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
			
			object.setPos(new Position(posX,posY),object.getRadius());
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
	/**
	 * Add a space object to the space objectlist
	 */
	public void addSpaceObject(SpaceObject object){
		
	}
	}

