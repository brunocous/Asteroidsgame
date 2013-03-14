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
	private Velocity speedOfLightVelocity, positiveVelocity, positiveInfiniteVelocity;
	
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
		
		speedOfLightShip = new Ship(positivePosition, speedOfLightVelocity, 0 , 15);
		
		positiveRadiusShip = new Ship(positivePosition, positiveVelocity, 0, 20);
		largeRadiusAndNegativePositionShip = new Ship(negativePosition, positiveVelocity, 0, 5000);
		
		negativePositionShip = new Ship(negativePosition, positiveVelocity, 0,15);
		zeroPositionShip = new Ship(zeroPosition, positiveVelocity, 0, 15);
		positivePositionShip = new Ship(positivePosition, positiveVelocity, 0, 15);
		nearInfinitePositionShip = new Ship(nearInfinitePosition, positiveVelocity,0,15);
		nearNegativeInfinitePositionShip = new Ship(nearNegativeInfinitePosition, positiveVelocity,0,15);
		
		smallNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, -1, 15);
		smallPositiveDirectionShip = new Ship(positivePosition, positiveVelocity, 1, 15);
		
		zeroPositionZeroDirectionShip = new Ship(zeroPosition, new Velocity(50,0), 0, 15);
		fiftyXPositionPiDirectionShip = new Ship(fiftyXPosition, new Velocity(-50,0), Math.PI, 15);
			
	}

	@Test
	public final void extendedConstructor_LegalCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveVelocity, Math.PI, 11);
		assertTrue(Util.fuzzyEquals(positivePosition.getPosX(), theShip.getPos().getPosX()) );
		assertTrue(Util.fuzzyEquals(positivePosition.getPosY(), theShip.getPos().getPosY()));
		assertTrue(Util.fuzzyEquals(positiveVelocity.getVelX(), theShip.getVel().getVelX()));
		assertTrue(Util.fuzzyEquals(positiveVelocity.getVelY(), theShip.getVel().getVelY()));
		assertTrue(Util.fuzzyEquals(Math.PI, theShip.getDirection()));
		assertTrue(Util.fuzzyEquals(11, theShip.getRadius()));
	}
	 
	@Test
	public final void extendendConstructor_infiniteSpeedCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveInfiniteVelocity, Math.PI, 11);
		assertTrue(Util.fuzzyEquals(theShip.getVel().getVelX(), speedOfLightVelocity.getVelX()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void extendenConstructor_infinitePositionCase() throws Exception{
		new Ship(infinitePosition, positiveVelocity, Math.PI, 20.0);
	}
	
	@Test (expected = IllegalRadiusException.class)
	public final void extendenConstructor_negativeRadiusCase() throws Exception{
		new Ship(positivePosition, positiveVelocity, Math.PI, -2.0);
	}
	@Test 
	public final void defaultConstructor_legalCase(){
		Ship theShip = new Ship();
		assertTrue(Util.fuzzyEquals(0, theShip.getPos().getPosX()) );
		assertTrue(Util.fuzzyEquals(0, theShip.getPos().getPosY()));
		assertTrue(Util.fuzzyEquals(0, theShip.getVel().getVelX()));
		assertTrue(Util.fuzzyEquals(0, theShip.getVel().getVelY()));
		assertTrue(Util.fuzzyEquals(0, theShip.getDirection()));
		assertTrue(Util.fuzzyEquals(15, theShip.getRadius()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void move_infiniteBorderCase() throws Exception{
		Ship theShip = new Ship( infinitePosition, positiveVelocity, Math.PI, 15 );
		theShip.move(0.0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void move_overInfiniteBorderCase() throws Exception{
		positivePositionShip.move(Double.MAX_VALUE - 2000);
	}
	@Test
	public final void move_toZeroPosition() throws Exception{
		negativePositionShip.move(1.0);
		assertTrue(Util.fuzzyEquals(negativePositionShip.getPos().getPosX(), zeroPosition.getPosX()));
		assertTrue(Util.fuzzyEquals(negativePositionShip.getPos().getPosY(), zeroPosition.getPosY()));
	}
	@Test
	public final void move_noChange() throws Exception{
		positivePositionShip.move(0.0);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getPos().getPosX(), positivePosition.getPosX()));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getPos().getPosY(), positivePosition.getPosY()));
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
	// TODO WAT MOET ER GEBEUREN BIJ ONEINDIG ALS ARGUMENT BIJ DIRECTION??
	@Test 
	public final void turn_negativeGreaterThan2PiCase(){
		positivePositionShip.turn(-9*Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Math.PI));
	}
	@Test
	public final void turn_negativeInfiniteCase(){
		positivePositionShip.turn(Double.NEGATIVE_INFINITY);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Double.NEGATIVE_INFINITY%(Math.PI*2) + 2*Math.PI));
	}
	@Test
	public final void isValidDirection_legalCase(){
		assertTrue(Ship.isValidDirection(Math.PI));
	}
	@Test
	public final void thrust_positiveAmount() throws Exception{
		positivePositionShip.thrust(10);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelX(), positiveVelocity.getVelX() + 10.0*Math.cos(positivePositionShip.getDirection())));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelY(), positiveVelocity.getVelY() + 10.0*Math.sin(positivePositionShip.getDirection())));	
	}
	@Test
	public final void thrust_negativeAmount() throws Exception{
		positivePositionShip.thrust( -10);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelX(), positiveVelocity.getVelX() + (-10.0)*Math.cos(positivePositionShip.getDirection())));
		assertTrue(Util.fuzzyEquals(positivePositionShip.getVel().getVelY(), positiveVelocity.getVelY() + (-10.0)*Math.sin(positivePositionShip.getDirection())));	
	}
	@Test
	public final void thrust_speedOfLight() throws Exception{
		speedOfLightShip.thrust(0);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelX(), speedOfLightVelocity.getVelX() + speedOfLightShip.getDirection()*Math.cos(0.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelY(), speedOfLightVelocity.getVelY() + speedOfLightShip.getDirection()*Math.sin(0.0)));
	}
	@Test
	public final void thrust_overSpeedOfLight() throws Exception{
		speedOfLightShip.thrust(10);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelX(), speedOfLightVelocity.getVelX() + speedOfLightShip.getDirection()*Math.cos(10.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelY(), speedOfLightVelocity.getVelY() + speedOfLightShip.getDirection()*Math.sin(10.0)));
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
	//TODO ni zeker wa ik hier moet doen
	public final void getDistanceBetween_negativeAndPositiveInfinitePositionedShips(){
		negativePositionShip.setPos(new Position(Double.MAX_VALUE-1000,Double.MAX_VALUE - 1000));
		positivePositionShip.setPos(new Position(Double.MAX_VALUE-1000,Double.MAX_VALUE - 1000));
		double distanceToCheck = Ship.getDistanceBetween(negativePositionShip, positivePositionShip);
		
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
	//TODO uitrekenen
	public final void getTimeToCollision_sameOppositeDirectionAndSameVelocity(){
		double timeToCheck = Ship.getTimeToCollision(zeroPositionZeroDirectionShip, fiftyXPositionPiDirectionShip);
		assertTrue(Util.fuzzyEquals(timeToCheck, 0));
	}
	@Test
	public final void getTimeToCollision_sameDirectionAndDifferentVelocity(){
		
	}
	public final void getTimeToCollision_sameDirectionAndSameVelocity(){
		
		
	}
	@Test
	public final void getTimeToCollision_angledDirectionAndDifferentSpeed(){
		
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
	public final void getCollisionPosition_sameDirrectionAndDifferentVelocity(){
		Position posToCheck = Ship.getCollisionPosition(zeroPositionZeroDirectionShip, fiftyXPositionPiDirectionShip);
		assertTrue(Util.fuzzyEquals(posToCheck.getPosX(), 25));
		assertTrue(Util.fuzzyEquals(posToCheck.getPosY(), 0));
	}
	@Test
	public final void getCollisionPosition_angledDirectionAndDifferentSpeed(){
		
	}
	@Test
	public final void getCollisionPosition_angledDirectionAndSameSpeed(){
		
	}
}
