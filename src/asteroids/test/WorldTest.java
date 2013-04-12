package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.*;
import asteroids.model.Util.*;

public class WorldTest {
	SpaceObject defaultPosAsteroid;
	SpaceObject pos100xAsteroid;
	
	World emptyWorld;

	@Before
	public void setUp() throws Exception {
		defaultPosAsteroid = new Asteroid( new Position(), new Velocity(20,0), 15);
		pos100xAsteroid = new Asteroid(new Position(100,50), new Velocity(-20,0), 15);
		
		emptyWorld = new World();
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void resolve_2asteroids(){
		emptyWorld.addAsSpaceObject(defaultPosAsteroid);
		emptyWorld.addAsSpaceObject(pos100xAsteroid);
		emptyWorld.resolve(defaultPosAsteroid,pos100xAsteroid);
		assertTrue(defaultPosAsteroid.getVel().getX() == - 20);
		assertTrue(pos100xAsteroid.getVel().getX() == 20);
	}

}
