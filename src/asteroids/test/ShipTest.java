package asteroids.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asteroids.Util;
import asteroids.Error.NegativeTimeException;
import asteroids.model.Ship;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;

public class ShipTest {

	private Ship speedOfLightShip, negativeVelocityShip, negativeSpeedOfLightVelocityShip, zeroVelocityShip, positiveVelocityShip, infiniteNegativePositionShip, zeroRadiusShip, negativeRadiusShip,
				positiveRadiusShip, infiniteRadiusShip, negativePositionShip, zeroPositionShip, positivePositionShip,
				infinitePositionShip, infiniteNegativeDirectionShip, largeNegativeDirectionShip, smallNegativeDirectionShip,
				zeroDirectionShip, smallPositiveDirectionShip, largePositiveDirectionShip, infinitePositiveDirectionShip, largeRadiusAndNegativePositionShip,
				zeroPositionZeroDirectionShip, fiftyXPositionPiDirectionShip;
	
	private Position zeroPosition, negativePosition, positivePosition, infinitePosition, fiftyXPosition, infiniteNegativePosition;
	private Velocity speedOfLightVelocity, negativeVelocity, negativeSpeedOfLightVelocity, zeroVelocity, positiveVelocity, positiveInfiniteVelocity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		zeroPosition = new Position(0,0);
		negativePosition = new Position(-50,-50);
		positivePosition = new Position(50,50);
		infinitePosition = new Position(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		infiniteNegativePosition = new Position(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY);
		fiftyXPosition = new Position(50,0);
		
		speedOfLightVelocity = new Velocity(Velocity.getSpeedOfLight(),0);
		negativeVelocity = new Velocity(-50,-50);
		negativeSpeedOfLightVelocity = new Velocity(-Velocity.getSpeedOfLight(),0);
		zeroVelocity = new Velocity(0,0);
		positiveVelocity = new Velocity(50,50);
		positiveInfiniteVelocity = new Velocity(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		
		speedOfLightShip = new Ship(positivePosition, speedOfLightVelocity, 0 , 15);
		negativeVelocityShip = new Ship(positivePosition, negativeVelocity, 0 , 15);
		negativeSpeedOfLightVelocityShip = new Ship(positivePosition, negativeSpeedOfLightVelocity, 0, 15);
		zeroVelocityShip = new Ship(positivePosition, zeroVelocity, 0,15);
		positiveVelocityShip = new Ship(positivePosition, positiveVelocity, 0,15);
		
		zeroRadiusShip = new Ship(positivePosition, positiveVelocity, 0, 0);
		negativeRadiusShip = new Ship(positivePosition, positiveVelocity, 0, -10);
		positiveRadiusShip = new Ship(positivePosition, positiveVelocity, 0, 20);
		infiniteRadiusShip = new Ship(positivePosition, positiveVelocity, 0, Double.POSITIVE_INFINITY);
		largeRadiusAndNegativePositionShip = new Ship(negativePosition, positiveVelocity, 0, 5000);
		
		negativePositionShip = new Ship(negativePosition, positiveVelocity, 0,1);
		zeroPositionShip = new Ship(zeroPosition, positiveVelocity, 0, 1);
		positivePositionShip = new Ship(positivePosition, positiveVelocity, 0, 1);
		infinitePositionShip = new Ship(infinitePosition, positiveVelocity, 0, 1);
		infiniteNegativePositionShip = new Ship(infiniteNegativePosition, positiveVelocity, 0, 1);
		
		infiniteNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, Double.NEGATIVE_INFINITY, 1);
		largeNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, -2000, 1);
		smallNegativeDirectionShip = new Ship(positivePosition, positiveVelocity, -1, 1);
		zeroDirectionShip = new Ship(positivePosition, positiveVelocity, 0, 1);
		smallPositiveDirectionShip = new Ship(positivePosition, positiveVelocity, 1, 1);
		largePositiveDirectionShip = new Ship(positivePosition, positiveVelocity, 2000, 1);
		infinitePositiveDirectionShip = new Ship(positivePosition, positiveVelocity, Double.POSITIVE_INFINITY, 1);
		
		zeroPositionZeroDirectionShip = new Ship(zeroPosition, positiveVelocity, 0, 15);
		fiftyXPositionPiDirectionShip = new Ship(fiftyXPosition, positiveVelocity, Math.PI, 15);
		
		
		
		
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
	
	@Test
	public final void extendenConstructor_infinitePositionCase() throws Exception{
		Ship theShip = new Ship(infinitePosition, positiveVelocity, Math.PI, 20.0);
		assertTrue(Util.fuzzyEquals(theShip.getPos().getPosX(), infinitePosition.getPosX()));
	}
	
	@Test
	public final void extendenConstructor_negativeRadiusCase() throws Exception{
		Ship theShip = new Ship(positivePosition, positiveVelocity, Math.PI, -2.0);
		assertTrue(Util.fuzzyEquals(theShip.getRadius(), 15));
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
	
	@Test 
	public final void move_infiniteBorderCase() throws Exception{
		infinitePositionShip.move(0.0);
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosX(), infinitePosition.getPosX()));
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosY(), infinitePosition.getPosY()));
	}
	
	@Test
	public final void move_overInfiniteBorderCase() throws Exception{
		infinitePositionShip.move(10.0);
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosX(), infinitePosition.getPosX()));
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosY(), infinitePosition.getPosY()));
	}
	@Test
	public final void move_underInfiniteBorderCase() throws Exception{
		infinitePositionShip.setDirection(4*Math.PI/3);
		infinitePositionShip.move(10.0);
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosX(), infinitePosition.getPosX() + 10.0*infinitePositionShip.getVel().getVelX()));
		assertTrue(Util.fuzzyEquals(infinitePositionShip.getPos().getPosY(), infinitePosition.getPosY() + 10.0*infinitePositionShip.getVel().getVelY()));
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
	public final void turn_infiniteCase(){
		positivePositionShip.turn(Double.POSITIVE_INFINITY);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Double.POSITIVE_INFINITY%(Math.PI*2)));
	}
	@Test
	public final void turn_negativeLessThan2PiCase(){
		positivePositionShip.turn(-Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), -Math.PI));
	}
	@Test 
	public final void turn_negativeGreaterThan2PiCase(){
		positivePositionShip.turn(-9*Math.PI);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), -Math.PI));
	}
	@Test
	public final void turn_negativeInfiniteCase(){
		positivePositionShip.turn(Double.NEGATIVE_INFINITY);
		assertTrue(Util.fuzzyEquals(positivePositionShip.getDirection(), Double.NEGATIVE_INFINITY%(Math.PI*2)));
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
	public final void thrust_underSpeedOfLight() throws Exception{
		speedOfLightShip.thrust(-10);
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelX(), speedOfLightVelocity.getVelX() + speedOfLightShip.getDirection()*Math.cos(-10.0)));
		assertTrue(Util.fuzzyEquals(speedOfLightShip.getVel().getVelY(), speedOfLightVelocity.getVelY() + speedOfLightShip.getDirection()*Math.sin(-10.0)));
	}
	
	@Test
	public final void getDistanceBetween_positveAndNegativePositionedShips(){
		double distanceToCheck = Ship.getDistanceBetween(negativePositionShip, positivePositionShip);
		assertTrue(Util.fuzzyEquals(distanceToCheck, Math.sqrt(10000+10000)));
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
		double distanceToCheck = Ship.getDistanceBetween(infiniteNegativePositionShip, infinitePositionShip);
		assertTrue(Util.fuzzyEquals(distanceToCheck, Double.POSITIVE_INFINITY));
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
	public final void overlap_infiniteNonOverlappingShip(){
		assertFalse(Ship.overlap(infiniteNegativePositionShip, infinitePositionShip));
	}
	//TODO zal mss weg moeten
	/**
	@Test
	public final void correctSpeed_overSpeedOfLight(){
		assertTrue()
	}
	@Test
	public final void correctSpeed_speedOfLight(){
		
		
	}
	@Test
	public final void correctSpeed_underSpeedOfLight(){
		
	}
	*/
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
