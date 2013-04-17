package asteroids.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;
import asteroids.Error.*;
import asteroids.model.Ship;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;

public class ShipTest {

	private Ship speedOfLightShip,   
				positiveRadiusShip,  negativePositionShip, zeroPositionShip, positivePositionShip,
				smallNegativeDirectionShip,nearInfinitePositionShip, nearNegativeInfinitePositionShip,
				smallPositiveDirectionShip, largeRadiusAndNegativePositionShip,
				zeroPositionZeroDirectionShip, fiftyXPositionPiDirectionShip;
	
	private Position zeroPosition, negativePosition, positivePosition, infinitePosition, fiftyXPosition, nearInfinitePosition, nearNegativeInfinitePosition;
	private Velocity speedOfLightVelocity, positiveVelocity, positiveInfiniteVelocity, zeroVelocity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		zeroPosition = new Position(0,0);
		negativePosition = new Position(-50,-50);
		positivePosition = new Position(50,50);
		infinitePosition = new Position(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		fiftyXPosition = new Position(50,0);
		nearInfinitePosition = new Position(Double.MAX_VALUE-2000, Double.MAX_VALUE - 2000);
		nearNegativeInfinitePosition = new Position(-Double.MAX_VALUE + 2000, Double.MAX_VALUE + 2000 );
		
		speedOfLightVelocity = new Velocity(Velocity.getSpeedOfLight(),0);
		positiveVelocity = new Velocity(50,50);
		positiveInfiniteVelocity = new Velocity(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		zeroVelocity = new Velocity(0,0);
		
		speedOfLightShip = new Ship(positivePosition, speedOfLightVelocity, 0 , 15, 100);
		
		positiveRadiusShip = new Ship(positivePosition, positiveVelocity, 0, 20, 100);
		largeRadiusAndNegativePositionShip = new Ship(negativePosition, positiveVelocity, 0, 5000, 100);
		
		negativePositionShip = new Ship(negativePosition, positiveVelocity, 0,15,100);
		zeroPositionShip = new Ship(zeroPosition, positiveVelocity, 0, 15, 100);
		positivePositionShip = new Ship(positivePosition, positiveVelocity, 0, 15, 100);
		nearInfinitePositionShip = new Ship(nearInfinitePosition, positiveVelocity,0,15, 100);
		nearNegativeInfinitePositionShip = new Ship(nearNegativeInfinitePosition, positiveVelocity,0,15,100);
		
		smallNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, -1, 15, 100);
		smallPositiveDirectionShip = new Ship(positivePosition, positiveVelocity, 1, 15, 100);
		
		zeroPositionZeroDirectionShip = new Ship(zeroPosition, new Velocity(50,0), 0, 15, 100);
		fiftyXPositionPiDirectionShip = new Ship(fiftyXPosition, new Velocity(-50,0), 0, 15, 100);
			
	}

	@Test
	public final void extendedConstructor_LegalCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveVelocity, Math.PI, 11, 100);
		assertTrue(theShip.getPos().equals(positivePosition));
		assertTrue(theShip.getVel().equals(positiveVelocity));
		assertTrue(Util.fuzzyEquals(Math.PI, theShip.getDirection()));
		assertTrue(Util.fuzzyEquals(11, theShip.getRadius()));
	}
	 
	@Test
	public final void extendendConstructor_infiniteSpeedCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveInfiniteVelocity, Math.PI, 11, 100);
		assertTrue(theShip.getVel().equals(zeroVelocity));
	}
	
	@Test (expected = IllegalPositionException.class)
	public final void extendenConstructor_infinitePositionCase() throws Exception{
		new Ship(infinitePosition, positiveVelocity, Math.PI, 20.0, 100);
	}
	
	@Test (expected = IllegalRadiusException.class)
	public final void extendenConstructor_negativeRadiusCase() throws Exception{
		new Ship(positivePosition, positiveVelocity, Math.PI, -2.0, 100);
	}
	@Test 
	public final void defaultConstructor_legalCase() throws Exception{
		Ship theShip = new Ship();
		assertTrue(theShip.getPos().equals(new Position()));
		assertTrue(theShip.getVel().equals(new Velocity()));
		assertTrue(Util.fuzzyEquals(0, theShip.getDirection()));
		assertTrue(Util.fuzzyEquals(15, theShip.getRadius()));
	}
	
	@Test (expected = IllegalPositionException.class)
	public final void move_infiniteBorderCase() throws Exception{
		Ship theShip = new Ship( infinitePosition, positiveVelocity, Math.PI, 15, 100);
		theShip.move(0.0);
	}
	
	@Test (expected = IllegalPositionException.class)
	public final void move_overInfiniteBorderCase() throws Exception{
		positivePositionShip.move(Double.MAX_VALUE-2000);
	}
	@Test
	public final void move_toZeroPosition() throws Exception{
		negativePositionShip.move(1.0);
		assertTrue(negativePositionShip.getPos().equals(zeroPosition));
	}
	@Test
	public final void move_noChange() throws Exception{
		positivePositionShip.move(0.0);
		assertTrue(positivePositionShip.getPos().equals(positivePosition));
	}
	@Test (expected = NegativeTimeException.class)
	public final void move_negativeTime() throws Exception{
		positivePositionShip.move(-10);
		fail("Exception Expected!");
	}
	
	@Test
	public final void turn_zeroAngle(){
		positivePositionShip.turn(0.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), 0.0));
	}
	@Test
	public final void turn_lessThan2PiCase(){
		positivePositionShip.turn(Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	@Test 
	public final void turn_greaterThan2PiCase(){
		positivePositionShip.turn(9*Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	@Test
	public final void turn_negativeLessThan2PiCase(){
		positivePositionShip.turn(-Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	
	@Test 
	public final void turn_negativeGreaterThan2PiCase(){
		positivePositionShip.turn(-9*Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	@Test
	public final void isValidDirection_legalCase(){
		assertTrue(Ship.isValidDirection(Math.PI));
	}
	@Test
	public final void thrust_positiveAmount() throws Exception{
		positivePositionShip.thrust(10);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getX(), positiveVelocity.getX() + 10.0*Math.cos(positivePositionShip.getDirection())*positivePositionShip.getForcePerSecond()/positivePositionShip.getMass()));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getY(), positiveVelocity.getY() + 10.0*Math.sin(positivePositionShip.getDirection())*positivePositionShip.getForcePerSecond()/positivePositionShip.getMass()));	
	}
	@Test (expected = NegativeTimeException.class)
	public final void thrust_negativeAmount() throws Exception{
		positivePositionShip.thrust( -10);
	}
	@Test
	public final void thrust_speedOfLight() throws Exception{
		speedOfLightShip.thrust(0);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getX(), speedOfLightVelocity.getX() + speedOfLightShip.getDirection()*Math.cos(0.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getY(), speedOfLightVelocity.getY() + speedOfLightShip.getDirection()*Math.sin(0.0)));
	}
	@Test
	public final void thrust_overSpeedOfLight() throws Exception{
		speedOfLightShip.thrust(10);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getX(), speedOfLightVelocity.getX() + speedOfLightShip.getDirection()*Math.cos(10.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getY(), speedOfLightVelocity.getY() + speedOfLightShip.getDirection()*Math.sin(10.0)));
	}
	
	@Test
	public final void getDistanceBetween_zeroAndPositivePositionedShips(){
		double distanceToCheck = Ship.getDistanceBetween(zeroPositionShip, positivePositionShip);
		assertTrue(Util.fuzzyEquals(distanceToCheck, Math.sqrt(5000) - 30));
	}
	
	@Test
	public final void getDistanceBetween_sameShip(){
		double distanceToCheck = Ship.getDistanceBetween(negativePositionShip, negativePositionShip);
		assertTrue(Util.fuzzyEquals(distanceToCheck, 0));
	}
	@Test
	public final void getDistanceBetween_overlap(){
		double distanceToCheck = Ship.getDistanceBetween(positiveRadiusShip , largeRadiusAndNegativePositionShip);
		assertTrue(Util.fuzzyLessThanOrEqualTo(distanceToCheck, 0));
	}
	@Test
	public final void getDistanceBetween_negativeAndPositiveInfinitePositionedShips(){
		double distanceToCheck = Ship.getDistanceBetween(nearInfinitePositionShip, nearNegativeInfinitePositionShip);
		assertTrue(Double.isInfinite(distanceToCheck));
	}
	@Test
	public final void overlap_overlappingShips(){
		assertTrue(Ship.overlap(positiveRadiusShip, largeRadiusAndNegativePositionShip));
	}
	@Test 
	public final void overlap_nonOverlappingShips(){
		assertFalse(Ship.overlap(positivePositionShip, negativePositionShip));
	}
	@Test
	public final void overlap_sameShip(){
		assertTrue(Ship.overlap(positivePositionShip, positivePositionShip));
	}
	@Test
	public final void overlap_nearInfiniteNonOverlappingShip(){
		assertFalse(Ship.overlap(nearInfinitePositionShip, nearNegativeInfinitePositionShip));
	}
	
	@Test
	public final void getTimeToCollision_sameShip(){
		double timeToCheck = Ship.getTimeToCollision(positivePositionShip, positivePositionShip);
		assertTrue(Util.fuzzyEquals(timeToCheck, Double.POSITIVE_INFINITY));
	}
	@Test
	public final void getTimeToCollision_noCollisionPossibleCase(){
		double timeToCheck = Ship.getTimeToCollision(smallNegativeDirectionShip, smallPositiveDirectionShip);
		assertTrue(Util.fuzzyEquals(timeToCheck, Double.POSITIVE_INFINITY));
	}
	@Test

	public final void getTimeToCollision_sameOppositeDirectionAndSameVelocity(){
		double timeToCheck = Ship.getTimeToCollision(zeroPositionZeroDirectionShip, fiftyXPositionPiDirectionShip);
		
		assertTrue(Util.fuzzyEquals(timeToCheck, 0.2));
	}	
	@Test
	public final void getTimeToCollision_angledDirection(){
		zeroPositionShip.setVel(new Velocity(0,50));
		zeroPositionShip.setDirection(Math.PI/2);
		positivePositionShip.setVel(new Velocity(-50,0));
		positivePositionShip.setDirection(Math.PI);
		double timeToCheck = Ship.getTimeToCollision(zeroPositionShip, positivePositionShip);
		
		assertTrue(Util.fuzzyEquals(timeToCheck,0.57573593129));
		
	}
	@Test
	public final void getCollisionPosition_sameShip(){
		Position posToCheck = Ship.getCollisionPosition(positivePositionShip, positivePositionShip);
		assertTrue((posToCheck == null));
	}
	@Test
	public final void getCollisionPosition_noCollisionPossibleCase(){
		Position posToCheck = Ship.getCollisionPosition(smallNegativeDirectionShip, smallPositiveDirectionShip);
		assertTrue((posToCheck == null));
	}
	@Test
	public final void getCollisionPosition_sameDirrectionAndSameVelocity(){
		Position posToCheck = Ship.getCollisionPosition(zeroPositionZeroDirectionShip, fiftyXPositionPiDirectionShip);
		assertTrue(Util.fuzzyEquals(posToCheck.getX(), 25));
		assertTrue(Util.fuzzyEquals(posToCheck.getY(), 0));
	}
	
	@Test
	public final void getCollisionPosition_angledDirection(){
		zeroPositionShip.setVel(new Velocity(0,50));
		zeroPositionShip.setDirection(Math.PI/2);
		positivePositionShip.setVel(new Velocity(-50,0));
		positivePositionShip.setDirection(Math.PI);
		Position posToCheck = Ship.getCollisionPosition(zeroPositionShip, positivePositionShip);
		System.out.println(posToCheck.toString());
		assertTrue(posToCheck.equals(new Position(10.606601717798211,39.39339828220179)));
		
	}
}
