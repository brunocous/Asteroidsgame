package asteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.model.Util.Position;
import asteroids.model.Util.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class of asteroids involving a position, a velocity and a radius.
 */
public class Asteroid extends SpaceObject {

	/**
	 * The density of an asteroid in kg/km³.
	 */
	private static final double RHO_ASTEROID = 2.65 * Math.pow(10, 12);
	/**
	 * The random number generator of an asteroid.
	 */
	private final Random random;

	/**
	 * Initializes a new asteroid with given position, velocity, radius and world.
	 * @param pos
	 *        The position for this new asteroid.
	 * @param vel
	 *        The velocity for this new asteroid.
	 * @param radius
	 *        The radius for this new asteroid.
	 * @param world
	 * 		  The World where this new asteroid belongs to.
	 * @param random
	 * 		  The random number generator for this new asteroid.
	 * @effect This new Asteroid is initialized with the given position as its position,
	 * 			the given velocity as its velocity, the given radius as its radius, the given
	 * 			random as its random and belongs to the given world.
	 * 			| this(pos, vel, radius, random, world)
	 */
	public Asteroid(Position pos, Velocity vel, double radius,Random random, World world)
			throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		super(pos, vel, radius, world);
		this.random = random;
	}
	/**
	 * Initializes a new asteroid with given position, velocity, radius and world.
	 * @param pos
	 *        The position for this new asteroid.
	 * @param vel
	 *        The velocity for this new asteroid.
	 * @param radius
	 *        The radius for this new asteroid.
	 * @param world
	 * 		  The World where this new asteroid belongs to.
	 * @effect This new Asteroid is initialized with the given position as its new position,
	 * 			the given velocity as its new velocity, the given radius as its new radius, 
	 * 			a default random number generator as its new random generator and belongs to the given world.
	 * 			| this(pos, vel, radius, world)
	 */
	public Asteroid(Position pos, Velocity vel, double radius, World world)
			throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		this(pos, vel, radius, new Random(), world);
	}
	/**
	 * Initializes a new asteroid with given position, velocity, radius and world.
	 * @param pos
	 *        The position for this new asteroid.
	 * @param vel
	 *        The velocity for this new asteroid.
	 * @param radius
	 *        The radius for this new asteroid.
	 * @param random
	 * 		  The random number generator for this new asteroid.
	 * @effect This new Asteroid is initialized with the given position as its new position,
	 * 			the given velocity as its new velocity, the given radius as its new radius, the given
	 * 			random as its new random and does not belong to any world.
	 * 			| this(pos, vel, radius, random, world)
	 */
	public Asteroid(Position pos, Velocity vel, double radius,Random random)
			throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		this(pos, vel, radius, random, null);
	}
	/**
	 * Initializes a new asteroid with given position, velocity, radius.
	 * @param pos
	 *        The position for this new asteroid.
	 * @param vel
	 *        The velocity for this new asteroid.
	 * @param radius
	 *        The radius for this new asteroid.
	 * @effect This new Asteroid is initialized with the given position as its new position,
	 * 			the given velocity as its new velocity, the given radius as its new radius 
	 * 			, a default random number generator as its new random generator and 
	 * 			does not belong to any world.
	 * 			| this(pos, vel, radius, (World) null)
	 */
	public Asteroid(Position pos, Velocity vel, double radius)throws IllegalRadiusException, IllegalPositionException, IllegalMaxSpeedException {
		this(pos,vel,radius,(World) null);
	}
	/**
	 * Initializes this new asteroid as a default asteroid.
	 * @effect This new Asteroid is initialized with the default position as its new position,
	 * 			the default velocity as its new velocity, a radius of 15 km as its new radius, 
	 * 			a default random number generator as its new random generator and 
	 * 			does not belong to any world.
	 * 			| this(new Position(), new Velocity(), 15, (World) null)
	 */
	public Asteroid() throws IllegalPositionException, IllegalRadiusException,IllegalMaxSpeedException{
		this(new Position(), new Velocity(), 15);

	}
	/**
	 * Initializes this new asteroid equal to the given asteroid.
	 * 
	 * @param astToClone
	 * 			The asteroid to clone.
	 * @effect This new asteroid is initialized with the position of the given asteroid
	 * 			as its new position, the velocity of the given asteroid as its new velocity
	 * 			, the radius of the given asteroid as its new radius, the random number generator
	 * 			of the given asteroid as its new random number generator and belongs to the same
	 * 			world as the given asteroid does.
	 * 			| this(astToClone.getPos(), astToClone.getVel(), astToClone.getRadius(), 
	 * 			| astToClone.getRandom(), astToClone.getWorld() )
	 */
	public Asteroid(Asteroid astToClone) throws IllegalPositionException, IllegalRadiusException,IllegalMaxSpeedException{
		this(astToClone.getPos(), astToClone.getVel(), astToClone.getRadius(), astToClone.getRandom(), astToClone.getWorld() );
		
	}

	/**
	 * Returns the density of an asteroid.
	 * @return
	 */
	@Basic @Immutable
	public static double getRhoAsteroid(){
		
		return RHO_ASTEROID;
		
	}
	
	/**
	 * Returns the mass of this asteroid.
	 * 
	 * @return result ==4/3*Math.PI*Math.pow(radius,3)*RHO_ASTEROID
	 */
	@Override
	public double getMass() {
		
		return 4 / 3 * Math.PI * Math.pow(this.getRadius(), 3) * getRhoAsteroid();
		
	}

	/**
	 * Returns the random number generator of this asteroid.
	 */
	@Basic @Immutable
	public Random getRandom(){
		return this.random;
	}
	/**
	 * Generates the 2 child asteroids of this asteroid in this asteroids world.
	 * 
	 * @post ...
	 * 			| if(getRadius() >=30)
	 * 			| then 
	 * 			| double childSpeedNorm = getVel().getNorm()*1.5
	 * 			| double childDirection = getRandom().nextDouble()*2*Math.PI
	 * 			| double childRadius = this.getRadius()/2
	 * 			| for each index in 0..1:
	 * 			| new.childDirection == childDirection + index*Math.PI
	 * 			| result.get(index).getPos().getX() == getPos().getX()
	 * 			|				+Math.cos(childDirection)*childRadius/2
	 * 			| result.get(index).getPos().getY() == getPos().getY()
	 * 			|				+Math.sin(childDirection)*childRadius/2
	 * 			| result.get(index).getVel().getX() 
	 * 			|			== Math.cos(chilDirection)*childSpeedNorm
	 * 			| result.get(index).getVel().getY() 
	 * 			|			== Math.sin(chilDirection)*childSpeedNorm
	 * 			| result.get(index).getRadius() == childRadius
	 * 			| result.get(index).getWorld() == getWorld()
	 * 			| result.size() == 2
	 * 
	 */
	public List<SpaceObject> split(){
		List<SpaceObject> children = new ArrayList<SpaceObject>();
		if(this.getRadius() >= 30){
		double childSpeedNorm = this.getVel().getNorm()*1.5;
		double childDirection = this.getRandom().nextDouble()*2*Math.PI;
		double childRadius = this.getRadius()/2;
		
		try{
			for(int i = 0 ; i<=1 ;i++){
				childDirection += i*Math.PI;
				SpaceObject child = new Asteroid((Position) this.getPos().add(new Position(Math.cos(childDirection)*childRadius/2, Math.sin(childDirection)*childRadius/2)),
												new Velocity(Math.cos(childDirection)*childSpeedNorm,Math.sin(childDirection)*childSpeedNorm),
												childRadius, 
												this.getRandom(),
												this.getWorld());
				children.add(child);
			}	
		} catch(Exception ex){
			
		}
		} 
		return children;
	}
}
