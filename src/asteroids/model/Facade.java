package asteroids.model;

import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.IShip;
import asteroids.model.Util.*;
import asteroids.model.Ship;
import asteroids.Error.*;

public class Facade implements IFacade{
	
	
	public Facade(){

	}

	@Override
	public IShip createShip() {
		
		return new Ship();
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) throws ModelException{
		try{ 
			IShip newShip = new Ship(new Position(x,y), new Velocity(xVelocity,yVelocity), angle, radius);
			return newShip;
			
		} catch(IllegalRadiusException exc){
			throw new ModelException(exc);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		}
	}

	@Override
	public double getX(IShip ship) {
		Ship castedShip = (Ship) ship;
		return castedShip.getPos().getPosX();
	}

	@Override
	public double getY(IShip ship) {
		Ship castedShip = (Ship) ship;
		return castedShip.getPos().getPosY();
	}

	@Override
	public double getXVelocity(IShip ship) {
		Ship castedShip = (Ship) ship;
		return castedShip.getVel().getVelX();
	}

	@Override
	public double getYVelocity(IShip ship) {
		Ship castedShip = (Ship) ship;
		return castedShip.getVel().getVelY();
	}

	@Override
	public double getRadius(IShip ship) {
		Ship castedShip = (Ship) ship;
		return castedShip.getRadius();
	}

	@Override
	public double getDirection(IShip ship) {
		Ship castedShip = (Ship) ship;
		return castedShip.getDirection();
	}

	@Override
	public void move(IShip ship, double dt) throws ModelException {
		try{ Ship castedShip = (Ship) ship;
		castedShip.move(dt);
		} catch(NegativeTimeException ex){
			throw new ModelException(ex);
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		} catch(NullPointerException exc){
			throw new ModelException(exc);
		}
	}

	@Override
	public void thrust(IShip ship, double amount) {
		Ship castedShip = (Ship) ship;
		castedShip.thrust(amount);
	}

	@Override
	public void turn(IShip ship, double angle) {
		Ship castedShip = (Ship) ship;
		castedShip.turn(angle);
		
	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) throws ModelException {
		try{Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		return Ship.getDistanceBetween(castedShip1, castedShip2);
		} catch(NullPointerException ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) throws ModelException {
		try{ Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		return Ship.overlap(castedShip1, castedShip2);
		} catch(NullPointerException ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) throws ModelException{
		try{ Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		return Ship.getTimeToCollision(castedShip1, castedShip2);
		} catch(NullPointerException ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) throws ModelException {
		try{ Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		Position colPos = Ship.getCollisionPosition(castedShip1, castedShip2);
		if(colPos == null){
			return null;
		} else
		return new double[]{colPos.getPosX() , colPos.getPosY()};
	} catch(NullPointerException ex){
		throw new ModelException(ex);
	}
	}

	@Override
	public Object createWorld(double width, double height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWorldWidth(Object world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWorldHeight(Object world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set getShips(Object world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getAsteroids(Object world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getBullets(Object world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShip(Object world, Object ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAsteroid(Object world, Object asteroid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeShip(Object world, Object ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAsteroid(Object world, Object asteroid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evolve(Object world, double dt,
			CollisionListener collisionListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShip(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getShipX(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipY(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipXVelocity(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipYVelocity(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipRadius(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipDirection(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipMass(Object ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getShipWorld(Object ship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShipThrusterActive(Object ship) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThrusterActive(Object ship, boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Object ship, double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireBullet(Object ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity,
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
	public double getAsteroidX(Object asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidY(Object asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidXVelocity(Object asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidYVelocity(Object asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidRadius(Object asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidMass(Object asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getAsteroidWorld(Object asteroid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBullets(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getBulletX(Object bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletY(Object bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletXVelocity(Object bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletYVelocity(Object bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletRadius(Object bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletMass(Object bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getBulletWorld(Object bullet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getBulletSource(Object bullet) {
		// TODO Auto-generated method stub
		return null;
	}

}
