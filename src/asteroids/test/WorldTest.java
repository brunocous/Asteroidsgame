package asteroids.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.model.*;
import asteroids.model.Util.*;
import asteroids.Error.*;

public class WorldTest {
	private SpaceObject defaultPosAsteroid, pos100xAsteroid, defaultPosShip, 
					pos100xShip,defaultPosBullet,pos100xBullet,terminatedSpaceObject;
	private Ship defaultBulletSource;
	private Ship pos100xBulletSource;
	
	
	private World emptyWorld, worldWithSomeSpaceObjects, terminatedWorld;
	
	private double width50 = 50;
	private double height50 = 50;
	private double widthNeg50 = -50;
	private double heightNeg50 = -50;
	private double widthInfinite = Double.POSITIVE_INFINITY;
	private double heightInfinite = Double.POSITIVE_INFINITY;
	
	private ArrayList<SpaceObject> someSpaceObjects;
	
	private double radius15 = 15;
	
	private double mass5000 = 5000;
	
	private Velocity vel20x = new Velocity(20,0);
	private Velocity velNeg20x = new Velocity(-20,0);
	
	private Position pos100x50y = new Position(100,50);
	private Position pos100x150y = new Position(100,150);

	@Before
	public void setUp() throws Exception {
		defaultBulletSource = new Ship();
		pos100xBulletSource = new Ship(pos100x50y, velNeg20x,Math.PI, radius15,mass5000);
		
		defaultPosAsteroid = new Asteroid( new Position(), vel20x, radius15);
		pos100xAsteroid = new Asteroid(pos100x50y, velNeg20x, radius15);
		defaultPosShip = new Ship(new Position(), vel20x,0,radius15,mass5000);
		pos100xShip = new Ship(pos100x50y, velNeg20x,Math.PI, radius15,mass5000);
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
    @Test public void updatePositions_singleCase()throws Exception{
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
    @Test public void updateVelocitties_SingleCase() throws Exception{
    	defaultPosShip.setWorld(emptyWorld);
    	emptyWorld.addAsSpaceObject(defaultPosShip);
    	((Ship) defaultPosShip).setEnableThruster(true);
    	emptyWorld.updateVelocities(0.02);
    	assertTrue(defaultPosShip.getVel().getX() == Velocity.getSpeedOfLight());
    }
    @Test public void getTimeToFirstCollision_BoundaryCollision(){
    	pos100xAsteroid.setWorld(emptyWorld);
    	emptyWorld.addAsSpaceObject(pos100xAsteroid);
    	assertTrue(emptyWorld.getTimeToFirstCollision() == 4.25);
    }
    @Test public void getTimeToFirstCollision_2spaceObjectsCollision(){
    	emptyWorld.addAsSpaceObject(defaultPosAsteroid);
		emptyWorld.addAsSpaceObject(pos100xAsteroid);
		assertTrue(emptyWorld.getTimeToFirstCollision() == 0.5);
    }
    @Test public void getTimeToFirstCollision_NoCollision()throws Exception{
    	pos100xAsteroid.setPos(pos100x150y);
    	emptyWorld.addAsSpaceObject(defaultPosAsteroid);
		emptyWorld.addAsSpaceObject(pos100xAsteroid);
		assertTrue(emptyWorld.getTimeToFirstCollision() == 4.25);
    }
    @Test public void getTimeToFirstCollision_NoSpaceObjectsInWorld(){
    	assertTrue(Double.isInfinite(emptyWorld.getTimeToFirstCollision()));
    }
    @Test (expected = IllegalStateException.class)
    public void getTimeToFirstCollision_terminatedWorld(){
    	terminatedWorld.getTimeToFirstCollision();
    }
    @Test public void getTimeToBoundaryCollision_NormalCase() throws Exception{
    	pos100xAsteroid.setWorld(emptyWorld);
    	emptyWorld.addAsSpaceObject(pos100xAsteroid);
    	assertTrue(emptyWorld.getTimeToBoundaryCollision(pos100xAsteroid) == 4.25);
    }
    @Test (expected = NullPointerException.class)
    public void getTimeToBoundaryCollision_NotEffectiveSpaceObject() throws Exception{
		assertTrue(emptyWorld.getTimeToBoundaryCollision(null) == 0.5);
    }
    @Test (expected = NotOfThisWorldException.class)
    public void getTimeToBoundaryCollision_NotOfTheWorldSpaceObject()throws Exception{
    	pos100xAsteroid.setWorld(worldWithSomeSpaceObjects);
    	assertTrue(Double.isInfinite(emptyWorld.getTimeToBoundaryCollision(pos100xAsteroid)));
    }
    @Test (expected = IllegalStateException.class)
    public void getTimeToBoundaryCollision_terminatedWorld() throws Exception{
    	terminatedWorld.getTimeToBoundaryCollision(pos100xAsteroid);
    }
    @Test public void evolve_noCollision() throws Exception{
    	Position[] somePositions = new Position[worldWithSomeSpaceObjects.getNbSpaceObjects()];
    	int i = 0;
    	for(SpaceObject obj :worldWithSomeSpaceObjects.getAllSpaceObjects()){
    		somePositions[i] = obj.getPos();
    		i++;
    	}
    	i=0;
    	worldWithSomeSpaceObjects.evolve(0.003,null);
    	for(SpaceObject obj: worldWithSomeSpaceObjects.getAllSpaceObjects()){
    	assertTrue(obj.getPos().getX() ==  somePositions[i].getX()+(obj.getVel().getX()*0.003));
    	assertTrue(obj.getPos().getY() ==  somePositions[i].getY()+(obj.getVel().getY()*0.003));
    			i++;
    	}
    }
    @Test (expected = NegativeTimeException.class )
    public void evolve_negativeTime() throws Exception{
    	worldWithSomeSpaceObjects.evolve(-1,null);
    }
    @Test public void evolve_BoundaryCollision()throws Exception{
    	pos100xAsteroid.setWorld(emptyWorld);
    	emptyWorld.addAsSpaceObject(pos100xAsteroid);
    	emptyWorld.evolve(5,null);
    	assertTrue(emptyWorld.getSpaceObjectAt(1).getVel().getX() == velNeg20x.getX());
    	assertTrue(emptyWorld.getSpaceObjectAt(1).getVel().getY() == velNeg20x.getY());

    }
    @Test public void evolve_SpaceObjectCollision()throws Exception{
    	Velocity[] someVelocities = new Velocity[worldWithSomeSpaceObjects.getNbSpaceObjects()];
    	int i = 0;
    	for(SpaceObject obj :worldWithSomeSpaceObjects.getAllSpaceObjects()){
    		someVelocities[i] = obj.getVel();
    		i++;
    	}
    	i=0;
    	worldWithSomeSpaceObjects.evolve(5,null);
    	for(SpaceObject obj: worldWithSomeSpaceObjects.getAllSpaceObjects()){
    	assertTrue(obj.getVel().getX() ==  - someVelocities[i].getX());
    	assertTrue(obj.getVel().getY() ==  someVelocities[i].getY());
    			i++;
    	}
    }
    @Test (expected = IllegalStateException.class)
    public void evolve_terminatedWorld() throws Exception{
    	terminatedWorld.evolve(1,null);
    }
    @Test public void boundaryCollide_Ship()throws Exception{
    	pos100xShip.setWorld(emptyWorld);
    	pos100xShip.setPos(new Position(15,50));
    	emptyWorld.addAsSpaceObject(pos100xShip);
    	emptyWorld.boundaryCollide(pos100xShip);
    	assertTrue(Util.fuzzyEquals(emptyWorld.getSpaceObjectAt(1).getVel().getX(), - velNeg20x.getX()));
    	assertTrue(Util.fuzzyEquals(emptyWorld.getSpaceObjectAt(1).getVel().getY(), velNeg20x.getY()));
    }
    @Test public void boundaryCollide_Asteroid()throws Exception{
    	pos100xAsteroid.setWorld(emptyWorld);
    	pos100xAsteroid.setPos(new Position(15,50));
    	emptyWorld.addAsSpaceObject(pos100xAsteroid);
    	emptyWorld.boundaryCollide(pos100xAsteroid);
    	assertTrue(Util.fuzzyEquals(emptyWorld.getSpaceObjectAt(1).getVel().getX(), -velNeg20x.getX()));
    	assertTrue(Util.fuzzyEquals(emptyWorld.getSpaceObjectAt(1).getVel().getY(), velNeg20x.getY()));
    }
    @Test public void boundaryCollide_BulletBounceLegal() throws Exception{
    	pos100xBullet.setWorld(emptyWorld);
    	emptyWorld.addAsSpaceObject(pos100xBullet);
    	emptyWorld.boundaryCollide(pos100xBullet);
    	assertTrue(Util.fuzzyEquals(emptyWorld.getSpaceObjectAt(1).getVel().getX(), - Bullet.getInitialSpeed()));
    	assertTrue(Util.fuzzyEquals(emptyWorld.getSpaceObjectAt(1).getVel().getY(), 0));
    }
    @Test public void boundaryCollide_BulletBounceIllegal()throws Exception{
    	pos100xBullet.setWorld(emptyWorld);
    	emptyWorld.addAsSpaceObject(pos100xBullet);
    	((Bullet) pos100xBullet).bounce();
    	emptyWorld.boundaryCollide(pos100xBullet);
    	assertTrue(pos100xBullet.isTerminated());
    }
    @Test (expected = NotOfThisWorldException.class)
    public void boundaryCollide_spaceObjectFromOtherWorld()throws Exception{
    	worldWithSomeSpaceObjects.boundaryCollide(pos100xBullet);
    }
    @Test (expected = IllegalStateException.class)
    public void boundaryCollide_terminatedWorld() throws Exception{
    	terminatedWorld.boundaryCollide(defaultPosAsteroid);
    }
    @Test (expected = NullPointerException.class)
    public void boundaryCollide_notEffectiveSpaceObject()throws Exception{
    	worldWithSomeSpaceObjects.boundaryCollide(null);
    }
    @Test public void BounceOff_legalCase()throws Exception{
    	World.bounceOff(pos100xAsteroid, defaultPosAsteroid);
    	assertTrue(Util.fuzzyEquals(pos100xAsteroid.getVel().getX(), 91.11111111111109));
    	assertTrue(Util.fuzzyEquals(pos100xAsteroid.getVel().getY(), 0));
    	assertTrue(Util.fuzzyEquals(defaultPosAsteroid.getVel().getX(), -91.11111111111109));
    	assertTrue(Util.fuzzyEquals(defaultPosAsteroid.getVel().getY(), 0));
    }
    @Test (expected = NullPointerException.class)
    public void BounceOff_NotEffectiveSpaceObjects() throws Exception{
    	World.bounceOff(null, null);
    }
	@Test
	public void resolve_2Asteroids() throws Exception{
		World.bounceOff(pos100xAsteroid, defaultPosAsteroid);
    	assertTrue(Util.fuzzyEquals(pos100xAsteroid.getVel().getX(), 91.11111111111109));
    	assertTrue(Util.fuzzyEquals(pos100xAsteroid.getVel().getY(), 0));
    	assertTrue(Util.fuzzyEquals(defaultPosAsteroid.getVel().getX(), -91.11111111111109));
    	assertTrue(Util.fuzzyEquals(defaultPosAsteroid.getVel().getY(), 0));
	}
	@Test
	public void resolve_2Ships() throws Exception{
		World w = new World();
		pos100xShip.setWorld(w);
		defaultPosShip.setWorld(w);
		World.bounceOff(pos100xShip, defaultPosShip);
    	assertTrue(Util.fuzzyEquals(pos100xShip.getVel().getX(), 91.111111111111));
    	assertTrue(Util.fuzzyEquals(pos100xShip.getVel().getY(), 0));
    	assertTrue(Util.fuzzyEquals(defaultPosShip.getVel().getX(), -91.111111111111));
    	assertTrue(Util.fuzzyEquals(defaultPosShip.getVel().getY(), 0));
	}
	@Test
	public void resolve_1asteroidAnd1Ship() throws Exception{
		defaultPosAsteroid.setWorld(emptyWorld);
		pos100xShip.setWorld(emptyWorld);
		emptyWorld.addAsSpaceObject(pos100xShip);
		emptyWorld.addAsSpaceObject(defaultPosAsteroid);
		emptyWorld.resolve(defaultPosAsteroid, pos100xShip);
		assertTrue(pos100xShip.isTerminated());
	}
	@Test (expected = NullPointerException.class)
	public void resolve_NotEffectiveSpaceObjects()throws Exception{
		worldWithSomeSpaceObjects.resolve(null, null);
	}
	@Test (expected = IllegalStateException.class)
	public void resolve_TerminatedWorld()throws Exception{
		terminatedWorld.resolve(defaultPosAsteroid, pos100xAsteroid);
		
	}
	@Test (expected = NotOfThisWorldException.class)
	public void resolve_spaceObjectFromOtherWorld()throws Exception{
		emptyWorld.resolve(defaultPosAsteroid, pos100xShip);
	}
	@Test
	public void resolveBullet_2bulletsSameSource()throws Exception{
		SpaceObject bul = new Bullet((Ship) defaultPosShip);
		defaultPosBullet.setWorld(emptyWorld);
		bul.setWorld(emptyWorld);
		bul.setPos(pos100x50y);
		bul.setVel(velNeg20x);
		emptyWorld.addAsSpaceObject(bul);
		emptyWorld.addAsSpaceObject(defaultPosBullet);
		emptyWorld.resolveBullet(defaultPosBullet, bul);
		assertTrue(Util.fuzzyEquals(bul.getVel().getX(), -20));
    	assertTrue(Util.fuzzyEquals(bul.getVel().getY(), 0));
    	assertTrue(Util.fuzzyEquals(defaultPosBullet.getVel().getX(), 250));
    	assertTrue(Util.fuzzyEquals(defaultPosBullet.getVel().getY(), 0));
	}
	@Test
	public void resolveBullet_2bulletsDifferentSource()throws Exception{
		defaultPosBullet.setWorld(emptyWorld);
		pos100xBullet.setWorld(emptyWorld);
		emptyWorld.addAsSpaceObject(defaultPosBullet);
		emptyWorld.addAsSpaceObject(pos100xBullet);
		emptyWorld.resolveBullet(defaultPosBullet,pos100xBullet);
		assertTrue(defaultPosBullet.isTerminated());
		assertTrue(pos100xBullet.isTerminated());
	}
	@Test
	public void resolveBullet_1bulletOfShip()throws Exception{
		defaultPosBullet.setWorld(emptyWorld);
		defaultBulletSource.setWorld(emptyWorld);
		defaultPosBullet.setPos(pos100x50y);
		defaultPosBullet.setVel(velNeg20x);
		emptyWorld.addAsSpaceObject(defaultPosBullet);
		emptyWorld.addAsSpaceObject(defaultBulletSource);
		emptyWorld.resolveBullet(defaultPosBullet, defaultBulletSource);
		assertFalse(defaultBulletSource.isTerminated());
	}
	@Test
	public void resolveBullet_1bulletOtherShip()throws Exception{
		defaultPosBullet.setWorld(emptyWorld);
		pos100xShip.setWorld(emptyWorld);
		emptyWorld.addAsSpaceObject(defaultPosBullet);
		emptyWorld.addAsSpaceObject(pos100xShip);
		emptyWorld.resolveBullet(defaultPosBullet, pos100xShip);
		assertTrue(pos100xShip.isTerminated());
	}
	@Test
	public void resolveBullet_1bulletAndAsteroid()throws Exception{
		defaultPosBullet.setWorld(emptyWorld);
		pos100xAsteroid.setWorld(emptyWorld);
		emptyWorld.addAsSpaceObject(defaultPosBullet);
		emptyWorld.addAsSpaceObject(pos100xAsteroid);
		emptyWorld.resolveBullet(defaultPosBullet, pos100xAsteroid);
		assertTrue(pos100xAsteroid.isTerminated());
	}
	@Test (expected = NullPointerException.class)
	public void resolveBullet_NotEffectiveSpaceObject()throws Exception{
		worldWithSomeSpaceObjects.resolveBullet(null, null);
	}
	@Test (expected = IllegalStateException.class)
	public void resolveBullet_TerminatedWorld()throws Exception{
		terminatedWorld.resolveBullet(defaultPosAsteroid, defaultPosShip);
	}
	@Test (expected = NotOfThisWorldException.class)
	public void resolveBullet_spaceObjectFromOtherWorld()throws Exception{
		emptyWorld.resolveBullet(defaultPosAsteroid, pos100xAsteroid);
	}
	
	

}

