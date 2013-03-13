package asteroids.model;

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
			double yVelocity, double radius, double angle) {
		
		return new Ship(new Position(x,y), new Velocity(xVelocity,yVelocity), angle, radius);
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
//TODO exceptions omvormen naar modelexceptions??
	@Override
	public void move(IShip ship, double dt) {
		try{ Ship castedShip = (Ship) ship;
		castedShip.move(dt);
		} catch(NegativeTimeException ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public void thrust(IShip ship, double amount) {
		try{ Ship castedShip = (Ship) ship;
		castedShip.thrust(amount);
		} catch(ExceedsSpeedOfLightException ex){
			throw new ModelException(ex);
		}
		
	}

	@Override
	public void turn(IShip ship, double angle) {
		Ship castedShip = (Ship) ship;
		castedShip.turn(angle);
		
	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) {
		Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		return Ship.getDistanceBetween(castedShip1, castedShip2);
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) {
		Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		return Ship.overlap(castedShip1, castedShip2);
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) {
		Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		return Ship.getTimeToCollision(castedShip1, castedShip2);
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		Ship castedShip1 = (Ship) ship1;
		Ship castedShip2 = (Ship) ship2;
		Position colPos = Ship.getCollisionPosition(castedShip1, castedShip2);
		double[] colPosArray = new double[]{colPos.getPosX() , colPos.getPosY()};
		
		return colPosArray;
	}

}
