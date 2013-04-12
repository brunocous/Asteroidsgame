package asteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.lang.Throwable;

import asteroids.Util;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Vector;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

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
	 * 			| else new.getWidth() == getMaxWidth();
	 * @post The new height of this new world is equal to the given height if 
	 * 		the given height is a valid height. If the given height is not a valid
	 * 		height, then the new height of this new world is equal to the maximum 
	 * 		height.
	 * 			| if(isValidHeight(height))
	 * 			| then new.getHeight() == height
	 * 			| else new.getHeight() == getMaxHeight();
	 * @effect This new world does not have any space objects yet.
	 * 			| getNbSpaceObjects() == 0
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
	 * Default constructor for a world.
	 * @effect This new world is initialized with the maximum width as its new width,
	 * 			the maximum height as its new height and does not have any space objects yet.
	 * 			| this(MAX_WIDTH, MAX_HEIGHT)
	 */
	public World(){
		this(MAX_WIDTH, MAX_HEIGHT);
	}

	@Basic
	public double getWidth() {
		return width;
	}

	public boolean isValidWidth(double width){
		
		return (!Util.fuzzyLessThanOrEqualTo(width,0 )&& Util.fuzzyLessThanOrEqualTo(width, getMaxWidth()));
	}

	@Basic
	public double getHeight() {
		return height;
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
	 *        | (new this).getOwningAt(this.getNbSpaceObjects()+1) == spaceObject
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
	 * @post ...
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
	 *        | for each index in getIndexOfOwning(spaceObject)+1..getNbSpaceObjects():
	 *        |   (new this).getSpaceObjectAt(index-1) == this.getSpaceObjectAt(index) 
	 */
	@Raw
	public void removeAsSpaceObject(SpaceObject spaceObject) {
		assert (spaceObject != null) && (spaceObject.getWorld() == null);
		assert (this.hasAsSpaceObject(spaceObject));
		spaceObjects.remove(spaceObject);
	}

	/**
	 * Checks if all the points within the radius of the space object 
	 * from the x- and y-component of the position of the space object 
	 * are within or equal to the values of the boundaries.
	 * @param pos
	 * 		  The position of the Space Object to be checked. 
	 * @param radius
	 * 		  The radius of the Space Object to be checked in km.
	 * @param world
	 * 		  The world in which the Space Object to be checked exists.
	 * @return True if all the points within the radius of the given position from the x- and y-components 
	 * 			of the position of the center of the given space object are both within or on the values of the boundaries.
	 * 		 	| result == ((!Util.fuzzyLessThanOrEqualTo(x-r, 0) && (!Util.fuzzyLessThanOrEqualTo(y-r, 0)) 
	 * 			| && Util.fuzzyLessThanOrEqualTo(x+r, world.getWidth()) && Util.fuzzyLessThanOrEqualTo(y+r, world.getHeight())))
	 * @return true if the given world is a null object.
	 * 			| if(world == null) result == true
	 */
	public static boolean isSituatedInOrOnBoundaries(Position pos, double radius, World world){
		
		if(world != null){
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
	 * 
	 * @param deltaT
	 * @throws IllegalPositionException
	 */
	public void updatePositions(double deltaT) throws IllegalPositionException{
		
		for(SpaceObject object : spaceObjects){
			
			double posX=object.getPos().getX()+ deltaT*object.getVel().getX();
			double posY=object.getPos().getY()+ deltaT*object.getVel().getY();
			
			object.setPos(new Position(posX,posY),object.getRadius());
		}
		
	}
	
	/**
	 * 
	 * @param deltaT
	 * @throws NegativeTimeException
	 */
	public void updateVelocities(double deltaT) throws NegativeTimeException{
		
		for(SpaceObject object : spaceObjects){
			
				if(Ship.class.isAssignableFrom(object.getClass())){
					
					((Ship)object).thrust(deltaT);
					
				}
			
		 }
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTimeToFirstCollision() {
		
		int i = 0;
		int j = 0;
		double result = Double.POSITIVE_INFINITY;
		
		while(i!=getNbSpaceObjects()){
			
			while(j!=getNbSpaceObjects()){
				
				double timeToCollision = SpaceObject.getTimeToCollision(spaceObjects.get(i), spaceObjects.get(j));
				
				if (((!Double.isInfinite(timeToCollision) && Util.fuzzyLessThanOrEqualTo(timeToCollision,result)) || Double.isInfinite(result)) && timeToCollision !=0){
					
					result = timeToCollision;
				
				}
				j++;
			}
			i++;
		}
		
		int k =0;
		
		while(k!=getNbSpaceObjects()){
			
			double timeToBoundaryCollision = getTimeToBoundaryCollision(spaceObjects.get(i));
			if (Util.fuzzyLessThanOrEqualTo(timeToBoundaryCollision, result) && timeToBoundaryCollision !=0){
				
				result = timeToBoundaryCollision;
				
			}
			k++;
		}
			
		return result;
		
	}
	
	/**
	 * 
	 * @param spaceObject
	 * @return
	 */
	public double getTimeToBoundaryCollision(SpaceObject spaceObject){
		
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
	 * 
	 * @param deltaT
	 * @throws IllegalPositionException
	 * @throws NegativeTimeException
	 */
	public void evolve(double deltaT) throws IllegalPositionException, NegativeTimeException{
		
		double tC=getTimeToFirstCollision();
		
		if(!Util.fuzzyLessThanOrEqualTo(tC, deltaT)){
			
			updatePositions(deltaT);
			updateVelocities(deltaT);
			
		}
		
		else{
			
			updatePositions(tC);
			updateVelocities(tC);
			
			int i = 0;
			boolean resolved = false;
			
			while(i < this.getNbSpaceObjects() && resolved == false){
				
				if(Util.fuzzyEquals(spaceObjects.get(i).getPos().getX()-spaceObjects.get(i).getRadius(), 0) || Util.fuzzyEquals(spaceObjects.get(i).getPos().getX()+spaceObjects.get(i).getRadius(), getWidth()) || Util.fuzzyEquals(spaceObjects.get(i).getPos().getY()-spaceObjects.get(i).getRadius(), 0) || Util.fuzzyEquals(spaceObjects.get(i).getPos().getY()+spaceObjects.get(i).getRadius(), getHeight())){
					
					boundaryCollide(spaceObjects.get(i));
					resolved=true;
					
				}
				
				else{
				for(SpaceObject object2 : this.getAllSpaceObjects()){
					
					if(Util.fuzzyEquals(getDistanceBetween(spaceObjects.get(i),object2),0) && spaceObjects.get(i)!=object2){
						resolve(spaceObjects.get(i),object2);
						resolved = true;
					}
				}
				
			}
		i++;
		}
	}
	}
	
	/**
	 * 
	 * @param spaceObject
	 */
	public void boundaryCollide(SpaceObject spaceObject){
		
		if(Ship.class.isAssignableFrom(spaceObject.getClass())){
			
			bounceOffBoundary(spaceObject);
			((Ship) spaceObject).setDirection(-((Ship) spaceObject).getDirection());
			
		}
		else if(Asteroid.class.isAssignableFrom(spaceObject.getClass())){
			
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
	 * Makes a given Space Object obj bounce off a boundary of this world. 
	 * @param obj
	 * @effect	...
	 * 			| if(Util.fuzzyEquals(obj.getPos().getX(), obj.getRadius())|| Util.fuzzyEquals(obj.getPos().getX(), getWidth()-obj.getRadius()))
	 *		    | obj.setVel(new Velocity( -obj.getVel().getX(), obj.getVel().getY()))
	 *		    | else obj.setVel(new Velocity(obj.getVel().getX(), -obj.getVel().getY()))
	 */
	public void bounceOffBoundary(SpaceObject obj){
		
		if(Util.fuzzyEquals(obj.getPos().getX(), obj.getRadius())|| Util.fuzzyEquals(obj.getPos().getX(), getWidth()-obj.getRadius())){
			
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
	 * @param obj2
	 * 
	 */
	public void bounceOff(SpaceObject obj1, SpaceObject obj2){
		
		double mi = obj1.getMass();
		double mj = obj2.getMass();
		double sigma = obj1.getRadius()+ obj2.getRadius();
		Velocity deltaV = new Velocity(obj2.getVel().getX()-obj1.getVel().getX(),obj2.getVel().getY()-obj1.getVel().getY());
		Position deltaR = new Position(obj2.getPos().getX()-obj1.getPos().getX(),obj2.getPos().getY()-obj1.getPos().getY());
		
		double J=(2*mi*mj*Vector.scalarProduct(deltaV, deltaR))/(sigma*(mi+mj));
		
		Velocity velToAddI = new Velocity(J*deltaR.getX()/(sigma*mi), J*deltaR.getY()/(sigma*mi));
		Velocity velToAddJ = new Velocity(J*deltaR.getX()/(sigma*mj), J*deltaR.getY()/(sigma*mj));
		
		obj1.setVel((Velocity) obj1.getVel().add(velToAddI));
		obj2.setVel((Velocity) obj2.getVel().add(velToAddJ));
		
	}
	
	/**
	 * 
	 * @param object1
	 * @param object2
	 */
	public void resolve(SpaceObject object1, SpaceObject object2){
		
		if(Ship.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
			bounceOff(object1,object2);
			
		}
		else if(Asteroid.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())){
			
			bounceOff(object1,object2);
			
		}
		else if(Bullet.class.isAssignableFrom(object1.getClass()) || Bullet.class.isAssignableFrom(object2.getClass())){
		
			resolveBullet(object1, object2);
		}
		
		else if(Asteroid.class.isAssignableFrom(object1.getClass()) && Ship.class.isAssignableFrom(object2.getClass())){
			
			object2.terminate();
			
			
		}
		else if(Ship.class.isAssignableFrom(object1.getClass()) && Asteroid.class.isAssignableFrom(object2.getClass())){
			
			object1.terminate();
			
		}
	
		
	}
	
	public void resolveBullet(SpaceObject object1, SpaceObject object2){
		
		if(Asteroid.class.isAssignableFrom(object1.getClass())){
			
			try{Asteroid asteroidCopy = new Asteroid((Asteroid) object1);
			object1.terminate();
			object2.terminate();
			this.addAsSpaceObjects( asteroidCopy.split());
			} catch (Exception ex){
				
			}
			
		}else if(Asteroid.class.isAssignableFrom(object2.getClass())){
			
			try{Asteroid asteroidCopy = new Asteroid((Asteroid) object2);
			object2.terminate();
			object1.terminate();
			this.addAsSpaceObjects( asteroidCopy.split());
			} catch (Exception ex){
				
			}
			
		} else if(Ship.class.isAssignableFrom(object1.getClass())){
			
			if(((Bullet) object2).getSource() != (Ship)object1){
				
				object1.terminate();
				object2.terminate();
			}
			else{
				
			}
		} else if(Ship.class.isAssignableFrom(object2.getClass())){
			
			if(((Bullet) object1).getSource() != (Ship)object2){
				
				object1.terminate();
				object2.terminate();
			}
		}
		
		else{
			
		object1.terminate();
		object2.terminate();
		
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
	 * Fires an object.
	 *  
	 * @param projectile
	 * 			The projectile.
	 * 
	 */
	public void fireObject(SpaceObject projectile){
		if(Bullet.class.isAssignableFrom(projectile.getClass())){
				projectile.getWorld().addAsSpaceObject(projectile);
		}
	}
}

