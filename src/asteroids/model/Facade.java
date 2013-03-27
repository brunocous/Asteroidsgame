package asteroids.model;

import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.Error.ModelException;
import asteroids.model.Util.*;

public class Facade implements IFacade<World, Ship, Asteroid,Bullet>{

	@Override
	public World createWorld(double width, double height) {
		return new World(width,height);
	}

	@Override
	public double getWorldWidth(World world) {
		return world.getWidth();
	}

	@Override
	public double getWorldHeight(World world) {
		
		return world.getHeight();
	}

	@Override
	public Set<Ship> getShips(World world) {
		
		return new HashSet<Ship>(world.getAllSpaceObjectsByType(Ship.class()));
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		// TODO Auto-generated method stub
		return HashSet<Asteroid>(world.getAllSpaceObjectsByType(Asteroid.class()));
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		// TODO Auto-generated method stub
		return HashSet<Bullet>(world.getAllSpaceObjectsByType(Bullet.class()));
	}

	@Override
	public void addShip(World world, Ship ship) {
		world.addSpaceObject(ship);
	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) {
		world.addSpaceObject(asteroid);
		
	}

	@Override
	public void removeShip(World world, Ship ship) {
		world.removeSpaceObjectAs(ship);
		
	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		world.removeSpaceObjectAs(world);
		
	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) {
		world.evolve(dt);
		
		
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) throws ModelException{
		try{
		return new Ship(new Position(x,y),new Velocity(xVelocity,yVelocity),direction,radius,mass);
		} catch(Exception ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public boolean isShip(Object o) {
		return Ship.class.isAssignableFrom(o.getClass());
	}

	@Override
	public double getShipX(Ship ship) {
		return ship.getPos().getX();
	}

	@Override
	public double getShipY(Ship ship) {
		return ship.getPos().getY();
	}

	@Override
	public double getShipXVelocity(Ship ship) {
		return ship.getVel().getX();
	}

	@Override
	public double getShipYVelocity(Ship ship) {
		return ship.getVel().getY();
	}

	@Override
	public double getShipRadius(Ship ship) {
		return ship.getRadius();
	}

	@Override
	public double getShipDirection(Ship ship) {
		return ship.getDirection();
	}

	@Override
	public double getShipMass(Ship ship) {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Ship ship, double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireBullet(Ship ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsteroid(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getAsteroidX(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidY(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidXVelocity(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidYVelocity(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBullets(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getBulletX(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletY(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletXVelocity(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletYVelocity(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletRadius(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletMass(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getBulletWorld(Bullet bullet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		// TODO Auto-generated method stub
		return null;
	}

	
}