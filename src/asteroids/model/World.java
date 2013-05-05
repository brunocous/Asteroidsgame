
package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.CollisionListener;
import asteroids.Util;
import asteroids.Error.NegativeTimeException;
import asteroids.Error.NotOfThisWorldException;
import asteroids.Error.UnhandledCombinationException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Vector;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
/**
 * A class representing a world involving a width, a height and a list of space objects
 * with among others evolving and collision resolving facilities.
 * 
 * @Invar The width that applies to all worlds must be a valid width.
 * 			| isValidWidth(getWidth())
 * @Invar The height that applies to all worlds must be a valid height.
 * 			| isValidHeight(getHeight())
 * @Invar Each world must have proper space objects.
 * 			| hasProperSpaceObjects()
 * 
 * @version 1.0
 * @author Bruno Coussement and Simon Telen
 * 
 *
 */
public class World {

	/**
	 * The maximum width of the world.
	 */
	private static final double MAX_WIDTH = Double.MAX_VALUE;
	/**
	 * The maximum height of the world.
	 */
	private static final double MAX_HEIGHT = Double.MAX_VALUE;
	/**
	 * Variable referencing a list collecting all the space objects of
	 * this world.
	 * 
	 * @Invar   The referenced list is effective.
	 *        | spaceObjects != null
	 * @Invar   Each space object registered in the referenced list is
	 *          effective and not yet terminated.
	 *        | for each spaceObject in spaceObjects:
	 *        |   ( (spaceObject != null) && (!spaceObject.isTerminated()) )
	 */
	private ArrayList<SpaceObject> spaceObjects;
	/**
	 * The width of this new world.
	 */
	private final double width;
	/**
	 * The height of this new world.
	 */
	private final double height;
	/**
	 * Variable reflecting whether or not this world is terminated.
	 */
	private boolean isTerminated;
	
	/**
	 * Initializes this new world with a given width and height.
	 * @param width
	 * 			The width for this new world.
	 * @param height
	 * 			The height for this new world.
	 * @post The new width of this new world is equal to the given width if 
	 * 		the given width is a valid width. If the given width is not a valid
	 * 		width, then the new width of this new world is equal to the maximum 
	 * 		width.
	 * 			| if(isValidWidth(width))
	 * 			| then new.getWidth() == width
	 * 			| else new.getWidth() == getMaxWidth()
	 * @post The new height of this new world is equal to the given height if 
	 * 		the given height is a valid height. If the given height is not a valid
	 * 		height, then the new height of this new world is equal to the maximum 
	 * 		height.
	 * 			| if(isValidHeight(height))
	 * 			| then new.getHeight() == height
	 * 			| else new.getHeight() == getMaxHeight()
	 * @post This new world does not have any space objects yet.
	 * 			| new.getNbSpaceObjects() == 0
	 */
	public World(double width, double height){
		
		if(isValidWidth(width))
			this.width = width;
		else this.width = getMaxWidth();
		
		if(isValidHeight(height))
			this.height = height;
		else this.height = getMaxHeight();
		
		this.spaceObjects = new ArrayList<SpaceObject>();
		
	}
	/**
	 * Initialize this new world as a default world.
	 * @effect This new world is initialized with the maximum width as its new width,
	 * 			the maximum height as its new height and does not have any space objects yet.
	 * 			| this(MAX_WIDTH, MAX_HEIGHT)
	 */
	public World(){
		this(MAX_WIDTH, MAX_HEIGHT);
	}
	/**
	 * 
	 * @return The width of this world.
	 */
	@Basic
	public double getWidth() {
		return width;
	}
/**
 * checks whether the given width is valid.
 * @param width
			The width to check.
 * @return True if and only if the given width is greater than 0 
 * 			and less than or equal to the maximum width of a world.
 * 			| result == (!Util.fuzzyLessThanOrEqualTo(width,0 )
 * 			| && Util.fuzzyLessThanOrEqualTo(width, getMaxWidth()))
 */
	public boolean isValidWidth(double width){
		
		return (!Util.fuzzyLessThanOrEqualTo(width,0 )&& Util.fuzzyLessThanOrEqualTo(width, getMaxWidth()));
	}

	@Basic
	public double getHeight() {
		return height;
	}
	/**
	 * checks whether the given height is valid.
	 * @param height
				The height to check.
	 * @return True if and only if the given height is greater than 0 
	 * 			and less than or equal to the maximum height of a world.
	 * 			| result == (!Util.fuzzyLessThanOrEqualTo(height,0 )
	 * 			| && Util.fuzzyLessThanOrEqualTo(height, getMaxHeight()))
	 */
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
	 * @return the isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}

	/**
	 * Terminates a world.
	 * @post    This world is terminated.
	 *        | new.isTerminated()
	 * @post    Each of the space objects of this world no longer has
	 *          a world.
	 *        | for each space object in getAllSpaceObjects():
	 *        |	  (! (new space object).hasSpaceObject())
	 */
	public void terminate(){
		if (!isTerminated()) {
			for (SpaceObject spaceObject : this.getAllSpaceObjects())
				spaceObject.unsetWorld();
			this.isTerminated = true;
		}
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
	 *        |   (getSpaceObjectAt(index).getWorld() == this)
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
	 *       |    then this.getSpaceObjectAt(result) == spaceObject
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
	 *        |   result.get(index) == getSpaceObjectAt(index+1)
	 */
	public List<SpaceObject> getAllSpaceObjects() {
		return new ArrayList<SpaceObject>(spaceObjects);
	}
	/**
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
	 *        | ! hasAsSpaceObject(spaceObject)
	 * @post    The number of space objects of this world is incremented
	 *          by 1.
	 *        | new.getNbSpaceObjects() == getNbSpaceObjects() + 1
	 * @post    This world has the given space object as its new last
	 *          space object.
	 *        | (new this).getSpaceObjectAt(this.getNbSpaceObjects()+1) == spaceObject
	 */
	@Raw
	public void addAsSpaceObject(@Raw SpaceObject spaceObject) {
		assert (spaceObject != null) && (spaceObject.getWorld() == this);
		assert !this.hasAsSpaceObject(spaceObject);
		spaceObjects.add(spaceObject);
	}
	/**
	 * Adds the given spaceObjects at the end of the list of space objects of this world.
	 * 
	 * @param spaceObjectsToAdd
	 * 		  	The space objects to be added.
	 * @effect ...
	 * 			| for each index in 0..spaceObjectsToAdd.size()-1
	 * 			| addAsSpaceObject(spaceObjectsToAdd.get(index))
	 */
	@Raw 
	public void addAsSpaceObjects(@Raw List<SpaceObject> spaceObjectsToAdd){
		for(SpaceObject objectToAdd: spaceObjectsToAdd){
			try{ this.addAsSpaceObject(objectToAdd);
			}catch (Exception ex){
				assert ((objectToAdd == null) || (objectToAdd.getWorld() != this));
				assert this.hasAsSpaceObject(objectToAdd);
			}
		}
	}

	/**
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
	 *        | for each index in getIndexOfSpaceObject(spaceObject)+1..getNbSpaceObjects():
	 *        |   (new this).getSpaceObjectAt(index-1) == this.getSpaceObjectAt(index) 
	 */
	@Raw
	public void removeAsSpaceObject(SpaceObject spaceObject) {
		assert (spaceObject != null) && (spaceObject.getWorld() == null);
		assert (this.hasAsSpaceObject(spaceObject));
		spaceObjects.remove(spaceObject);
	}

	/**
	 * Checks if all the points within the given radius from the given position  
	 * from the x- and y-component of the position are within or equal to the 
	 * values of the boundaries.
	 * 
	 * @param pos
	 * 		  The position to be checked. 
	 * @param radius
	 * 		  The radius to be checked in km.
	 * @param world
	 * 		  The world in which the position is to be checked.
	 * @return True if and only if all the points within the given radius of the 
	 * 			given position are within or on the values of the boundaries.
	 * 		 	| double x = pos.getX()
	 * 			| double y = pos.getY();
	 * 			| boolean xLow = (!Util.fuzzyLessThanOrEqualTo(x - radius, 0)
	 * 			| 					|| Util.fuzzyEquals(x - radius, 0))
	 * 			| boolean yLow = (!Util.fuzzyLessThanOrEqualTo(y - radius, 0)
	 * 			| 					|| Util.fuzzyEquals(y - radius, 0))
	 * 			| boolean xHigh = Util.fuzzyLessThanOrEqualTo(x + radius, world.getWidth())
	 * 			| boolean yHigh = Util.fuzzyLessThanOrEqualTo(y + radius, world.getHeight())
	 * 			| result == (xLow && yLow && xHigh && yHigh);
	 * @return true if the given world or the given position are not effective.
	 * 			| if(world == null || pos == null) 
	 * 			| then result == true
	 */
	public static boolean isSituatedInOrOnBoundaries(Position pos, double radius, World world){
		
		if(world != null && pos != null ){
		double x = pos.getX();
		double y = pos.getY();
	
		boolean xLow = (!Util.fuzzyLessThanOrEqualTo(x - radius, 0)|| Util.fuzzyEquals(x - radius, 0));
		boolean yLow = (!Util.fuzzyLessThanOrEqualTo(y - radius, 0)|| Util.fuzzyEquals(y - radius, 0));
		boolean xHigh = Util.fuzzyLessThanOrEqualTo(x + radius, world.getWidth());
		boolean yHigh = Util.fuzzyLessThanOrEqualTo(y + radius, world.getHeight());
	
		return (xLow && yLow && xHigh && yHigh);
		}
		else{
			return true;
		}
	}
	
	/**
	 * Updates the positions of each space object in this world for a given amount of time delta T.
	 * @param deltaT
	 * 			The given amount of time.
	 * @effect Each of the space objects of this world updates their position by adding their 
	 *			current velocity multiplied by the given amount of time delta T to their 
	 * 			current position.
	 * 			| for each spaceObject in spaceObjects:
	 * 			| spaceObject.move(deltaT)
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * @throws NegativeTimeException
	 * 			The given deltaT is negative.
	 * 			|!SpaceObject.isValidElapsedTime(deltaT)
	 */
	public void updatePositions(double deltaT) throws IllegalStateException, NegativeTimeException{
		if(isTerminated())
			throw new IllegalStateException();
		for(SpaceObject object : this.getAllSpaceObjects()){
			object.move(deltaT);
		}
		
	}
	
	/**
	 * Update the velocities of each space object in this world 
	 * for an amount of time delta T.
	 * 
	 * @param deltaT
	 * 			The amount of time.
	 * @effect Each ship of this world will thrust during an amount of time deltaT.
	 * 			| for each spaceObject in spaceObjects:
	 * 			| if(Ship.class.isAssignableFrom(spaceObject.getClass()))
	 * 			| ((Ship) spaceObject).thrust(deltaT)
	 * @throws NegativeTimeException
	 * 			The given amount of time is negative.
	 * 			|!SpaceObject.isValidElapsedTime(deltaT)
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| this.isTerminated()
	 */
	public void updateVelocities(double deltaT) throws NegativeTimeException,
					IllegalStateException{
		if(isTerminated())
			throw new IllegalStateException();
		for(SpaceObject object : this.getAllSpaceObjects()){
			
				if(Ship.class.isAssignableFrom(object.getClass())){
					
					((Ship)object).thrust(deltaT);
					
				}
			
		 }
	}
	
	/**
	 * The amount of time until the first collision in this world.
	 * 
	 * @return The amount of time until the first collision in this world.
	 * 			| double result = Double.POSITIVE_INFINITY
	 * 			| for each index1 in 1..getNbSpaceObjects():
	 * 			| for each index2 in 1..getNbSpaceObjects():
	 * 			| double timeToCollision = SpaceObject.
	 * 			| 					getTimeToCollision(getSpaceObjectAt(index1), 
	 * 			|					getSpaceObjectAt(index2))
	 * 			| if ( !Double.isInfinite(timeToCollision) 
	 * 			| 			&& Util.fuzzyLessThanOrEqualTo(timeToCollision, result) 
	 * 			|			&& !Util.fuzzyEquals(timeToCollision,0))
	 * 			| then tempResult = timeToCollision;
	 * 			| for each index in 1..getNbSpaceObjects():
	 * 			| double timeToBoundaryCollision = 
	 * 			|			getTimeToBoundaryCollision(getSpaceObjectAt(k))
	 * 			| if (Util.fuzzyLessThanOrEqualTo(timeToBoundaryCollision, result) 
	 * 			|			&& timeToBoundaryCollision !=0)
	 * 			| then tempResult = timeToBoundaryCollision
	 * 			| Result == tempResult
	 * @return Double.POSITIVE_INFINITY if this world does not have any
	 * 			space objects.
	 * 			| if(getNbSpaceObjects() == 0)
	 * 			| then result == Double.POSITIVE_INFINITY
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * 
	 */
	public double getTimeToFirstCollision() throws IllegalStateException{
		if(isTerminated())
			throw new IllegalStateException();
		double result = Double.POSITIVE_INFINITY;
		
		for(int i=1;i<=this.getNbSpaceObjects();i++){
			
			for(int j = 1;j<=this.getNbSpaceObjects();j++){
				
				double timeToCollision = SpaceObject.getTimeToCollision(this.getSpaceObjectAt(i), this.getSpaceObjectAt(j));
				
				if ( !Double.isInfinite(timeToCollision) && Util.fuzzyLessThanOrEqualTo(timeToCollision, result) && !Util.fuzzyEquals(timeToCollision,0)){
					
					result = timeToCollision;
				
				}
			
			}
		}
		
		int k =1;
		
		while(k<=getNbSpaceObjects()){
			
			try{double timeToBoundaryCollision = getTimeToBoundaryCollision(this.getSpaceObjectAt(k));
			
			if (Util.fuzzyLessThanOrEqualTo(timeToBoundaryCollision, result) && timeToBoundaryCollision !=0){
				
				result = timeToBoundaryCollision;
			}
			k++;
			} catch(NotOfThisWorldException ex){
				assert this.getSpaceObjectAt(k).getWorld() != this;
			}
		}
			
		return result;
		
	}
	
	/**
	 * The amount of time until the given space object collides with a boundary.
	 * 
	 * @param spaceObject
	 * 			The space object that may collide with a boundary.
	 * @return The amount of time that the given space object has before
	 * 			colliding with a boundary of this world.
	 * 			| double collisionTopOrBottom
	 * 			| double distanceTopOrBottom
	 * 			| if(Util.fuzzyLessThanOrEqualTo(spaceObject.getVel().getY(),0))
	 * 			| 	then distanceTopOrBottom = spaceObject.getPos().getY() - spaceObject.getRadius()
	 * 			| 	else distanceTopOrBottom = getHeight() - spaceObject.getPos().getY() - spaceObject.getRadius()
	 * 			| collisionTopOrBottom = Math.abs( distanceTopOrBottom/(spaceObject.getVel().getY()))
	 * 			| 
	 * 			| double collisionSides
	 * 			| double distanceSides
	 * 			| if(Util.fuzzyLessThanOrEqualTo(spaceObject.getVel().getX(),0))
	 * 			| 	then distanceSides = spaceObject.getPos().getX() - spaceObject.getRadius()
	 * 			| 	else distanceSides = getWidth() - spaceObject.getPos().getX() - spaceObject.getRadius()
	 * 			| collisionSides = Math.abs( distanceSides/(spaceObject.getVel().getX()))
	 * 			| result == Math.min(collisionTopOrBottom, collisionSides);
	 * 
	 * @throws NotOfThisWorldException
	 * 			This world does not have the given space object.
	 * 			| !hasAsSpaceObject(spaceObject)
	 * 
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * @throws NullPointerException
	 * 			The given space object is not effective.
	 * 			| spaceObject == null
	 */
	public double getTimeToBoundaryCollision(SpaceObject spaceObject) throws NullPointerException,
				IllegalStateException, NotOfThisWorldException{
		if(spaceObject == null)
			throw new NullPointerException();
		if(isTerminated())
			throw new IllegalStateException();
			if(!this.hasAsSpaceObject(spaceObject))
				throw new NotOfThisWorldException();
		double collisionTopOrBottom;
		double distanceTopOrBottom;
		if(Util.fuzzyLessThanOrEqualTo(spaceObject.getVel().getY(),0)){
			
			distanceTopOrBottom = spaceObject.getPos().getY() - spaceObject.getRadius();
			
		}
		else{
			distanceTopOrBottom = getHeight() - spaceObject.getPos().getY() - spaceObject.getRadius();
		}
		
		collisionTopOrBottom = Math.abs( distanceTopOrBottom/(spaceObject.getVel().getY()));
		
		double collisionSides;
		double distanceSides;
		
		if(Util.fuzzyLessThanOrEqualTo(spaceObject.getVel().getX(),0)){
			
			distanceSides = spaceObject.getPos().getX() - spaceObject.getRadius();
			
		}
		else{
			distanceSides = getWidth() - spaceObject.getPos().getX() - spaceObject.getRadius();
		}
		
			collisionSides = Math.abs( distanceSides/(spaceObject.getVel().getX()));
		
		return Math.min(collisionTopOrBottom, collisionSides);
		
	}
	
	/**
	 * Evolves the world during an amount of time delta t.
	 * 
	 * @param deltaT
	 * 			The amount of time the world has to evolve.
	 * @effect If the time to the first collision is greater than the given
	 * 			amount of time deltaT, then update the positions and velocities
	 * 			for a given amount of time deltaT of all the space objects of
	 * 			this world. Else update the positions and velocities of all 
	 * 			space objects of this world for an amount of time until the 
	 * 			first collision has occurred and resolve that collision.
	 * 			| double tC = getTimeToFirstCollision()
	 * 			| if(!tC > deltaT)
	 * 			| then updatePositions(deltaT)
	 * 			| 	   updateVelocitties(deltaT)
	 * 			| else updatePositions(tC)
	 * 			|	   updateVelocities(tC)
	 * 			| int i =1
	 * 			| boolean resolved = false
	 * 			| while(i < getNbSpaceObjects() && resolved == false)
	 * 			| 		if(Util.fuzzyEquals(getSpaceObjectAt(i).getPos().getX()
	 * 			|				-getSpaceObjectAt(i).getRadius(), 0) 
	 * 			| 			|| Util.fuzzyEquals(getSpaceObjectAt(i).getPos().getX()
	 * 			|				+getSpaceObjectAt(i).getRadius(), getWidth()) 
	 * 			| 			|| Util.fuzzyEquals(getSpaceObjectAt(i).getPos().getY()
	 * 			|				-getSpaceObjectAt(i).getRadius(), 0) 
	 * 			| 			|| Util.fuzzyEquals(getSpaceObjectAt(i).getPos().getY()
	 * 			|				+getSpaceObjectAt(i).getRadius(), getHeight()))
	 * 			|		then {boundaryCollide(getSpaceObjectAt(i)
	 * 			|			 resolved = true}
	 * 			| 		else for each spaceObject in spaceObjects:
	 * 			| 			if(Util.fuzzyEquals(SpaceObject.getDistanceBetween(this.getSpaceObjectAt(i),spaceObject),0) 
	 * 			|				&& this.getSpaceObjectAt(i)!= spaceObject)
	 * 			|			then resolve(this.getSpaceObjectAt(i), spaceObject)
	 * @throws NegativeTimeException
	 * 			The given amount of time deltaT is negative.
	 * 			| deltaT < 0
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * @note throws UnhandledCombinationException using the method resolve(...)
	 * 			
	 */
	public void evolve(double deltaT, CollisionListener coll) throws UnhandledCombinationException, NegativeTimeException, IllegalStateException{
		
		if(isTerminated())
			throw new IllegalStateException();
		double tC=getTimeToFirstCollision();
		if(!Util.fuzzyLessThanOrEqualTo(tC, deltaT)){
			
			updatePositions(deltaT);
			updateVelocities(deltaT);
			
		}
		
		else{
			
			updatePositions(tC);
			updateVelocities(tC);
			
			int i = 1;
			boolean resolved = false;
			
			while(i <= this.getNbSpaceObjects() && resolved == false){
				
				if(Util.fuzzyEquals(this.getSpaceObjectAt(i).getPos().getX()
						-this.getSpaceObjectAt(i).getRadius(), 0) 
						|| Util.fuzzyEquals(this.getSpaceObjectAt(i).getPos().getX()
								+this.getSpaceObjectAt(i).getRadius(), getWidth()) 
						|| Util.fuzzyEquals(this.getSpaceObjectAt(i).getPos().getY()
								-this.getSpaceObjectAt(i).getRadius(), 0) 
						|| Util.fuzzyEquals(this.getSpaceObjectAt(i).getPos().getY()
								+this.getSpaceObjectAt(i).getRadius(), getHeight()))
				{
					try{if(coll != null)
						
						boundaryCollide(this.getSpaceObjectAt(i));
					
					}catch (NotOfThisWorldException ex){
						assert this.getSpaceObjectAt(i).getWorld() != this;
					}
					resolved=true;
					
				}
				
				else{
				for(SpaceObject object2 : this.getAllSpaceObjects()){
					
					if(Util.fuzzyEquals(SpaceObject.getDistanceBetween(this.getSpaceObjectAt(i),object2),0) 
							&& this.getSpaceObjectAt(i)!=object2){
						try {if(coll != null)
							coll.objectCollision(this.getSpaceObjectAt(i), object2, SpaceObject.getCollisionPosition(object2, this.getSpaceObjectAt(i)).getX(), SpaceObject.getCollisionPosition(this.getSpaceObjectAt(i), object2).getY());
							resolve(this.getSpaceObjectAt(i),object2);
							
						} catch (NotOfThisWorldException e) {
							assert this.getSpaceObjectAt(i).getWorld() != this;
							assert object2.getWorld() != this;
						}
						resolved = true;
					}
				}
				
			}
		i++;
		}
	}
	}
	
	/**
	 * Makes a given Space Object bounce off a boundary.
	 * 
	 * @param spaceObject
	 * 			The space object that's going to bounce.
	 * @effect The the given space object is a ship or if the given space object
	 * 			is a an asteroid, then the given space object bounces off a boundary.
	 * 			Else if the given space object is a bullet, then if the bullet can bounce
	 * 			then the given space object bounces off a boundary. Else the given space
	 * 			object terminates.
	 * 			| if(Ship.class.isAssignableFrom(spaceObject.getClass())
	 * 			| || Asteroid.class.isAssignableFrom(spaceObject.getClass()))
	 * 			| then bounceOffBoundary(spaceObject)
	 * 			| else if(Bullet.class.isAssignableFrom(spaceObject.getClass()) )
	 * 			|	   then if((Bullet) spaceObject).canBounce()
	 * 			|			then bounceOffBoundary(spaceObject)
	 * 			|				 spaceobject.bounce()
	 * 			|			else spaceObject.terminate() 
	 * @throws NotOfThisWorldException
	 * 			This world does not have the given space object.
	 * 			| !hasAsSpaceObject(spaceObject)
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * @throws NullPointerException
	 * 			The given space object is not effective.
	 * 			| spaceObject == null
	 */
	public void boundaryCollide(SpaceObject spaceObject) throws NullPointerException, IllegalStateException, 
					NotOfThisWorldException{
		if(spaceObject == null)
			throw new NullPointerException();
		if(this.isTerminated())
			throw new IllegalStateException();
				if(Ship.class.isAssignableFrom(spaceObject.getClass()) 
						|| Asteroid.class.isAssignableFrom(spaceObject.getClass())){
			
						bounceOffBoundary(spaceObject);
			
				}
				else if(Bullet.class.isAssignableFrom(spaceObject.getClass()) ){
			
						if(((Bullet)spaceObject).canBounce()){
				
							bounceOffBoundary(spaceObject);
							((Bullet) spaceObject).bounce();
				
						}
						else{
				
							spaceObject.terminate();
				
						}
			
		}
	}
	
	/**
	 * Makes a given Space Object obj bounce off a boundary in this world. 
	 * @param obj
	 * 			The space object that bounces off a boundary.
	 * @effect	...
	 * 			| if(Util.fuzzyEquals(obj.getPos().getX(), obj.getRadius())|| Util.fuzzyEquals(obj.getPos().getX(), getWidth()-obj.getRadius()))
	 *		    | then obj.setVel(new Velocity( -obj.getVel().getX(), obj.getVel().getY()))
	 *		    | else obj.setVel(new Velocity(obj.getVel().getX(), -obj.getVel().getY()))
	 *@throws NotOfThisWorldException
	 * 			The given space object does not belong to this world.
	 * 			| !hasAsSpaceObject(obj) 
	 */
	public void bounceOffBoundary(SpaceObject obj) throws NotOfThisWorldException{
		if(!this.hasAsSpaceObject(obj))
			throw new NotOfThisWorldException();
		if(Util.fuzzyEquals(obj.getPos().getX(), obj.getRadius()) || Util.fuzzyEquals(obj.getPos().getX(), getWidth()-obj.getRadius())){
			
			obj.setVel(new Velocity( -obj.getVel().getX(), obj.getVel().getY()));
			
		}
		else{
			
			obj.setVel(new Velocity(obj.getVel().getX(), -obj.getVel().getY()));
			
		}
	}
	
	/**
	 * Makes two given Space Object bounce off each other. 
	 * 
	 * @param obj1
	 * 		The first space object.
	 * @param obj2
	 * 		The second space object.
	 * @post	...
	 * 			| double mi = obj1.getMass()
	 * 			| double mj = obj2.getMass()
	 * 			| double sigma = obj1.getRadius()+ obj2.getRadius()
	 * 			| Vector deltaV = new Velocity(obj2.getVel().getX()-obj1.getVel().getX(),obj2.getVel().getY()-obj1.getVel().getY())
	 * 			| Vector deltaR = new Position(obj2.getPos().getX()-obj1.getPos().getX(),obj2.getPos().getY()-obj1.getPos().getY())
	 * 			| double J=(2*mi*mj*Vector.scalarProduct(deltaV, deltaR))/(sigma*(mi+mj))
	 * 			| Vector velToAddI = new Velocity(J*deltaR.getX()/(sigma*mi), J*deltaR.getY()/(sigma*mi))
	 * 			| Vector velToAddJ = new Velocity(-J*deltaR.getX()/(sigma*mj), -J*deltaR.getY()/(sigma*mj));
	 * 			| (new obj1).getVel() = obj1.getVel().add(velToAddI)
	 * 			| (new obj2).getVel() = obj2.getVel().add(velToAddJ)
	 * @throws NullPointerException
	 * 			One of the given 2 space objects or both space objects
	 * 			are not effective.
	 * 			| (obj1 == null) || (obj2 == null)
	 */
	public static void bounceOff(SpaceObject obj1, SpaceObject obj2) throws NullPointerException{
		double mi = obj1.getMass();
		double mj = obj2.getMass();
		double sigma = obj1.getRadius()+ obj2.getRadius();
		Vector deltaV = new Velocity(obj2.getVel().getX()-obj1.getVel().getX(),obj2.getVel().getY()-obj1.getVel().getY());
		Vector deltaR = new Position(obj2.getPos().getX()-obj1.getPos().getX(),obj2.getPos().getY()-obj1.getPos().getY());
		
		double J=(2*mi*mj*Vector.scalarProduct(deltaV, deltaR))/(sigma*(mi+mj));
		
		Vector velToAddI = new Velocity(J*deltaR.getX()/(sigma*mi), J*deltaR.getY()/(sigma*mi));
		Vector velToAddJ = new Velocity(-J*deltaR.getX()/(sigma*mj), -J*deltaR.getY()/(sigma*mj));
		
		obj1.setVel((Velocity) obj1.getVel().add(velToAddI));
		obj2.setVel((Velocity) obj2.getVel().add(velToAddJ));
		
	}
	
	/**
	 * Resolves the collision between 2 space objects.
	 * 
	 * @param object1
	 * 			The first object to be resolved.
	 * @param object2
	 * 			The second object to be resolved.
	 * @effect If both given objects are ships or if both objects are asteroids, 
	 * 			then they bounce off. Else if the given object1 is a bullet or 
	 * 			the given object2 is a bullet, then they're resolved as a collision
	 * 			between bullets. Else if the given object1 is an asteroid and 
	 * 			object2 is a ship, then terminate object2. Else if the given object1
	 * 			is a ship and object2 is an asteroid, then terminate object1.
	 * 			| if((Asteroid.class.isAssignableFrom(object1.getClass()) 
	 * 			| && Asteroid.class.isAssignableFrom(object2.getClass())) 
	 * 			| || (Ship.class.isAssignableFrom(object2.getClass()) 
	 * 			| && Ship.class.isAssignableFrom(object1.getClass()))) 
	 * 			| then 
	 * 			| bounceOff(object1,object2)
	 * 			| else if(Bullet.class.isAssignableFrom(object1.getClass()) 
	 * 			|	   resolveBullet((Bullet) object1,object2)
	 * 			| else if(Bullet.class.isAssignableFrom(object2.getClass())
	 * 			|	   resolveBullet((Bullet) object2, object1)
	 * 			|	   else if(Asteroid.class.isAssignableFrom(object1.getClass()) 
	 * 			| 			&& Ship.class.isAssignableFrom(object2.getClass())) 
	 * 			|			then
	 * 			|			object2.terminate()
	 * 			|			else if(Ship.class.isAssignableFrom(object1.getClass()) 
	 * 			| 				 && Asteroid.class.isAssignableFrom(object2.getClass()))
	 * 			|				 then
	 * 			|				 object1.terminate() 
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * @throws NotOfThisWorldException
	 * 			None of the given space objects belong to this world.
	 * 			| !hasAsSpaceObject(object1) 
	 * 			| || !hasAsSpaceObject(object2)
	 * @throws NullPointerException
	 * 			one of the 2 given space objects or both given space objects
	 * 			are not effective.
	 * 			| object1 == null || object2 == null
	 * @throws UnhandledCombinationException
	 * 			|!isHandledCombination(object1,object2)
	 * */
	public void resolve(SpaceObject object1, SpaceObject object2) throws IllegalStateException
						, NotOfThisWorldException, NullPointerException, UnhandledCombinationException{
		
		checkResolvingConditions(object1,object2);
		
				if(Ship.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
					bounceOff(object1,object2);
			
				}
				else if(Asteroid.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())){
			
					bounceOff(object1,object2);
			
				}
				else if(Bullet.class.isAssignableFrom(object1.getClass())){
		
					resolveBullet((Bullet) object1, object2);
				}
				else if(Bullet.class.isAssignableFrom(object2.getClass())){
					
					resolveBullet((Bullet) object2, object1);
					
				}
		
				else if(Asteroid.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
					object2.terminate();
			
			
				}
				else if(Ship.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())){
			
					object1.terminate();
			
				}
				else {
					throw new UnhandledCombinationException();
				}
	}
	
	/**
	 * Check whether the combination of the types of the given object1 and object2 is handled in resolve(...)
	 * @param object1 the first Space Object to be resolved.
	 * @param object2 the second Space Object to be resolved.
	 * @return ((Ship.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass()))
	 *			  			 || Asteroid.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())
	 *			  			 || Bullet.class.isAssignableFrom(object1.getClass())
	 *			  			 || Bullet.class.isAssignableFrom(object2.getClass())
	 *			  			 || Asteroid.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())
	 *			  			 || Ship.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass()));
	 *
	 * @throws IllegalStateException, NotOfThisWorldException, NullPointerException using the method checkResolvingConditions(object1,object2)
	 */
	public boolean isHandledCombination(SpaceObject object1, SpaceObject object2) throws IllegalStateException,  NotOfThisWorldException, NullPointerException{
		checkResolvingConditions(object1,object2);
		boolean result =((Ship.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass()))
				  			 || Asteroid.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())
				  			 || Bullet.class.isAssignableFrom(object1.getClass())
				  			 || Bullet.class.isAssignableFrom(object2.getClass())
				  			 || Asteroid.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())
				  			 || Ship.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass()));
		return result;
	}
	/**
	 * checks whether the given Space Objects object1 and object2 can be resolved.
	 * 
	 * @param object1 The first object we want to resolve.
	 * @param object2 The seceond object we want to resolve.
	 * @throws IllegalStateException
	 *         |this.isTerminated()
	 * @throws NotOfThisWorldException
	 * 		   |(!this.hasAsSpaceObject(object1) || !this.hasAsSpaceObject(object2))
	 * @throws NullPointerException
	 * 		   |(object1 == null || object2 == null)
	 */
	
	public void checkResolvingConditions(SpaceObject object1, SpaceObject object2) throws IllegalStateException
	, NotOfThisWorldException, NullPointerException {
		
		if(object1 == null || object2 == null)
			throw new NullPointerException();
		if(this.isTerminated())
			throw new IllegalStateException();
		if(!this.hasAsSpaceObject(object1) || !this.hasAsSpaceObject(object2))
			throw new NotOfThisWorldException();
		
	}
	
	/**
	 * Resolve a collision between 2 spaceObject when at least one of the spaceObjects is a bullet.
	 * 
	 * @param object1
	 * 			The first object to be resolved, which is a bullet.
	 * @param object2
	 * 			The second object to be resolved.
	 * @effect If the given object2 is an asteroid, then terminate object1 and terminate object2.
	 * 		   If the given object 2 is a ship, then if the given object1 has not got object2 as 
	 *         its source then terminate object1 and terminate object2. Else 
	 * 			if both given objects are bullets, then if they have both a different
	 * 			source then terminate both objects. Else do nothing. Else terminate object1 and terminate object2.
	 * 			| if(Asteroid.class.isAssignableFrom(object2.getClass()) 
	 * 			| then
	 * 			| object1.terminate()
	 * 			| object2.terminate()
	 * 			|		else if( Ship.class.isAssignableFrom(object2.getClass()))
	 * 			| 			 then if(object1.getSource() != object2) 
	 * 			| 				  then 
	 * 			| 				  object1.terminate()
	 * 			| 				  object2.terminate()
	 * 			| 			 else if( Bullet.class.isAssignableFrom(object2.getClass()))
	 * 			|				  then if( object1.getSource() != object2.getSource())
	 * 			|					   then 
	 * 			|					   object1.terminate()
	 * 			|					   object2.terminate()					
	 * 			| 				  else 
	 * 			|			 	  object1.terminate()
	 * 			| 			      object2.terminate()
	 * 
	 * @post If the given object2 is an asteroid, then add the 2 child asteroids of object2 to this world.
	 * 			| if(( Asteroid.class.isAssignableFrom(object2.getClass())))
	 * 			| then new.getNbSpaceObjects() = getNbSpaceObjects() + 2
	 * 
	 * @throws IllegalStateException
	 * 			This world is already terminated.
	 * 			| isTerminated()
	 * @throws NotOfThisWorldException
	 * 			None of the given space objects belong to this world.
	 * 			| !hasAsSpaceObject(object1) 
	 * 			| || !hasAsSpaceObject(object2)
	 * @throws NullPointerException
	 * 			one of the 2 given space objects or both given space objects
	 * 			are not effective.
	 * 			| object1 == null || object2 == null
	 * @throws UnhandledCombinationException
	 * 			| !(Asteroid.class.isAssignableFrom(object2.getClass())||Ship.class.isAssignableFrom(object2.getClass())
	 * 			| ||Bullet.class.isAssignableFrom(object2.getClass()))
	 */
	public void resolveBullet(Bullet object1, SpaceObject object2)throws IllegalStateException,
				 NotOfThisWorldException, NullPointerException, UnhandledCombinationException{
		
		checkResolvingConditions(object1, object2);

		
					if(Asteroid.class.isAssignableFrom(object2.getClass())){
							
						try{Asteroid asteroidCopy = new Asteroid((Asteroid) object2);
						object1.terminate();
						object2.terminate();
						this.addAsSpaceObjects( asteroidCopy.split());
						} catch (Exception ex){
							
						}
							
						} else if(Ship.class.isAssignableFrom(object2.getClass())){
							
							if((object1).getSource() != (Ship)object2){
								
								object1.terminate();
								object2.terminate();
							}
							else{
								
							}
						
						} else if(Bullet.class.isAssignableFrom(object2.getClass())){
							if((object1).getSource() != ((Bullet) object2).getSource()){
								object1.terminate();
								object2.terminate();
							}
						}
						else{	
						
							throw new UnhandledCombinationException();
						
						}
	}
	
}


