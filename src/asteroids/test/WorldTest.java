package asteroids.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.*;
import asteroids.model.Util.*;

public class WorldTest {
	private SpaceObject defaultPosAsteroid, pos100xAsteroid, defaultPosShip, 
					pos100xShip,defaultPosBullet,pos100xBullet,terminatedSpaceObject;
	
	
	private World emptyWorld, worldWithSomeSpaceObjects, terminatedWorld;
	
	private double width50 = 50;
	private double height50 = 50;
	private double widthNeg50 = -50;
	private double heightNeg50 = -50;
	private double widthInfinite = Double.POSITIVE_INFINITY;
	private double heightInfinite = Double.POSITIVE_INFINITY;
	
	ArrayList<SpaceObject> someSpaceObjects;
	
	private double radius15 = 15;
	
	private double mass5000 = 5000;
	
	private Velocity vel20x = new Velocity(20,0);
	private Velocity velNeg20x = new Velocity(-20,0);
	
	private Position pos100x50y = new Position(100,50);

	@Before
	public void setUp() throws Exception {
		Ship defaultBulletSource = new Ship();
		Ship pos100xBulletSource = new Ship(pos100x50y, velNeg20x,Math.PI, radius15,5000);
		
		defaultPosAsteroid = new Asteroid( new Position(), vel20x, radius15);
		pos100xAsteroid = new Asteroid(pos100x50y, velNeg20x, radius15);
		defaultPosShip = new Ship(new Position(), vel20x,0,radius15,5000);
		pos100xShip = new Ship(pos100x50y, velNeg20x,Math.PI, radius15,5000);
		defaultPosBullet = new Bullet(defaultBulletSource);
		pos100xBullet = new Bullet(pos100xBulletSource);
		terminatedSpaceObject = new Asteroid();
		terminatedSpaceObject.terminate();
		
		someSpaceObjects = new ArrayList<SpaceObject>();
		someSpaceObjects.add(defaultPosAsteroid);
		someSpaceObjects.add(pos100xAsteroid);
		
		emptyWorld = new World();
		worldWithSomeSpaceObjects = new World();
		defaultPosAsteroid.setWorld(worldWithSomeSpaceObjects);
		pos100xAsteroid.setWorld(worldWithSomeSpaceObjects);
		worldWithSomeSpaceObjects.addAsSpaceObjects(someSpaceObjects);
		terminatedWorld = new World();
		terminatedWorld.terminate();
		
		
	}
	@Test
	public void ExtendedConstructor_legalCase(){
		World world = new World(width50,height50);
		assertTrue(world.getWidth() == width50);
		assertTrue(world.getHeight() == height50);
		assertTrue(world.getNbSpaceObjects() == 0);
	}
	@Test
	public void ExtendedConstructor_NegativeBoundaryCase(){
		World world = new World(widthNeg50,heightNeg50);
		assertTrue(world.getWidth() == World.getMaxWidth());
		assertTrue(world.getHeight() == World.getMaxHeight());
		assertTrue(world.getNbSpaceObjects() == 0);
	}
	@Test
	public void ExtendedConstructor_InfiniteBoundaryCase(){
		World world = new World(widthInfinite,heightInfinite);
		assertTrue(world.getWidth() == World.getMaxWidth());
		assertTrue(world.getHeight() == World.getMaxHeight());
		assertTrue(world.getNbSpaceObjects() == 0);
	}
	@Test
	public void DefaultConstructor_legalCase(){
		World world = new World();
		assertTrue(world.getWidth() == World.getMaxWidth());
		assertTrue(world.getHeight() == World.getMaxHeight());
		assertTrue(world.getNbSpaceObjects() == 0);
	}
	@Test
	public void terminate_worldWithoutSpaceObjects(){
		emptyWorld.terminate();
		assertTrue(emptyWorld.isTerminated());
	}
	@Test
	public void terminate_worldWithSpaceObjects(){
		worldWithSomeSpaceObjects.terminate();
		assertTrue(worldWithSomeSpaceObjects.isTerminated());
		for(SpaceObject obj: someSpaceObjects){
			assertTrue(obj.getWorld() != null); 
		}
	}
	@Test
	public void terminate_worldAlreadyTerminated(){
		terminatedWorld.terminate();
		assertTrue(terminatedWorld.isTerminated());
	}
	@Test
	public void canhaveAsSpaceObject_acceptableSpaceObject()throws Exception{
		SpaceObject obj = new Asteroid(new Position(1000,1000), new Velocity(),92);
		assertTrue(worldWithSomeSpaceObjects.canHaveAsSpaceObject(obj));
	}
	@Test
	public void canhaveAsSpaceObject_NotAcceptableSpaceObject()throws Exception{
		SpaceObject obj = new Asteroid(new Position(1000,1000), new Velocity(),92, new World(3000,3000));
		obj.terminate();
		assertFalse(worldWithSomeSpaceObjects.canHaveAsSpaceObject(obj));
	}
	@Test
	public void canhaveAsSpaceObject_NotEffectiveSpaceObject()throws Exception{
		assertFalse(worldWithSomeSpaceObjects.canHaveAsSpaceObject(null));
	}
	@Test public void canHaveAsSpaceObjectAt_LegalCase() {
		assertTrue(worldWithSomeSpaceObjects.canHaveAsSpaceObjectAt(pos100xShip, 1));
	}
	@Test public void canHaveAsSpaceObjectAt_NonPositiveIndex() {
		assertFalse(worldWithSomeSpaceObjects.canHaveAsSpaceObjectAt(defaultPosShip, 0));
	}
	
	@Test public void canHaveAsSpaceObjectAt_IndexTooLarge() {
		assertFalse(worldWithSomeSpaceObjects.canHaveAsSpaceObjectAt(defaultPosShip, worldWithSomeSpaceObjects.getNbSpaceObjects()+3));
	}
	
	@Test public void canHaveAsSpaceObjectAt_IllegalOwnable() {
		assertFalse(worldWithSomeSpaceObjects.canHaveAsSpaceObjectAt(terminatedSpaceObject, 1));
	}
	
	@Test public void canHaveAsSpaceObjectAt_OwnableAlreadyAtOtherIndex() {
		assertFalse(worldWithSomeSpaceObjects.canHaveAsSpaceObjectAt(worldWithSomeSpaceObjects.getSpaceObjectAt(1), worldWithSomeSpaceObjects.getNbSpaceObjects()+1));
	}

	@Test public void hasProperSpaceObjects_WorldWithSpaceObjects() {
	    assertTrue(worldWithSomeSpaceObjects.hasProperSpaceObjects());
	}
    
    @Test public void hasProperSpaceObjects_WorldWithoutSpaceObjects() {
        assertTrue(emptyWorld.hasProperSpaceObjects());
    }
    @Test public void getNbSpaceObjects_SingleCase() {
		assertTrue(someSpaceObjects.size() == worldWithSomeSpaceObjects.getNbSpaceObjects());
	}
	
	@Test public void getAllOwings_PersonWithoutSpaceObjects() {
	    List<SpaceObject> result = emptyWorld.getAllSpaceObjects();
	    assertNotNull(result);
	    assertTrue(0 == result.size());
	}
    
    @Test public void getAllOwings_PersonWitSpaceObjects() {
        List<SpaceObject> result = worldWithSomeSpaceObjects.getAllSpaceObjects();
        assertNotNull(result);
        assertTrue(worldWithSomeSpaceObjects.getNbSpaceObjects() == result.size());
        for (int index=0; index<result.size(); index++)
            assertSame(worldWithSomeSpaceObjects.getSpaceObjectAt(index+1),result.get(index));
    }
    @Test public void getIndexOfSpaceObject_singleCase(){
    	assertTrue(worldWithSomeSpaceObjects.getIndexOfSpaceObject(defaultPosAsteroid) == 0);
    }
    @Test public void addAsSpaceObject_legalCase() throws Exception{
    	SpaceObject obj = new Asteroid(new Position(200,200), new Velocity(), radius15);
    	obj.setWorld(worldWithSomeSpaceObjects);
    	worldWithSomeSpaceObjects.addAsSpaceObject(obj);
    	assertSame(worldWithSomeSpaceObjects.getSpaceObjectAt(worldWithSomeSpaceObjects.getNbSpaceObjects()), obj);
    }
    @Test public void removeSpaceObject_legalCase() throws Exception{
    	worldWithSomeSpaceObjects.removeAsSpaceObject(defaultPosAsteroid);
    	assertTrue(worldWithSomeSpaceObjects.getNbSpaceObjects() == 1);
    }
    @Test public void isSituatedInOrOnBoundaries_legalCase(){
    	assertTrue(World.isSituatedInOrOnBoundaries(new Position(), radius15, emptyWorld));
    }
    @Test public void isSituatedInOrOnBoundaries_OutOfBounce(){
    	assertFalse(World.isSituatedInOrOnBoundaries(new Position(-50,-50), radius15, emptyWorld));
    }
    @Test public void isSituatedInOrOnBoundaries_NotEffectivePosition(){
    	assertTrue(World.isSituatedInOrOnBoundaries(null, radius15, emptyWorld));
    }
    @Test public void isSituatedInOrOnBoundaries_NotEffectiveWorld(){
    	assertTrue(World.isSituatedInOrOnBoundaries(new Position(), radius15, null));
    }
    @Test public void updatePositions_singleCase(){
    	Position[] somePositions = new Position[worldWithSomeSpaceObjects.getNbSpaceObjects()];
    	int i = 0;
    	for(SpaceObject obj :worldWithSomeSpaceObjects.getAllSpaceObjects()){
    		somePositions[i] = obj.getPos();
    		i++;
    	}
    	i=0;
    	worldWithSomeSpaceObjects.updatePositions(0.5);
    	for(SpaceObject obj: worldWithSomeSpaceObjects.getAllSpaceObjects()){
    	assertTrue(obj.getPos().getX() ==  somePositions[i].getX()+(obj.getVel().getX()*0.5));
    	assertTrue(obj.getPos().getY() ==  somePositions[i].getY()+(obj.getVel().getY()*0.5));
    			i++;
    	}
    }
	@Test
	public void resolve_2asteroids()throws Exception{
		emptyWorld.addAsSpaceObject(defaultPosAsteroid);
		emptyWorld.addAsSpaceObject(pos100xAsteroid);
		emptyWorld.resolve(defaultPosAsteroid,pos100xAsteroid);
		assertTrue(defaultPosAsteroid.getVel().getX() == - 20);
		assertTrue(pos100xAsteroid.getVel().getX() == 20);
	}
	
	

}

