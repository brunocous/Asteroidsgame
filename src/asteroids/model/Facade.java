package asteroids.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.Error.ModelException;
import asteroids.model.Util.*;
import asteroids.model.programs.Program;
import asteroids.model.programs.ProgramFactoryImpl;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.parsing.ProgramParser;
import asteroids.model.programs.statements.Statement;
import asteroids.model.programs.type.*;

public class Facade implements IFacade<World, Ship, Asteroid,Bullet,Program>{

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
		Set<Ship> ships = new HashSet<Ship>();
		for(SpaceObject spaceObject:world.getAllSpaceObjects()){
			if(Ship.class.isAssignableFrom(spaceObject.getClass()))
				ships.add((Ship) spaceObject);
		}
		return ships;
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		Set<Asteroid> asteroids = new HashSet<Asteroid>();
		for(SpaceObject spaceObject:world.getAllSpaceObjects()){
			if(Asteroid.class.isAssignableFrom(spaceObject.getClass()))
				asteroids.add((Asteroid) spaceObject);
		}
		return asteroids;
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		Set<Bullet> bullets = new HashSet<Bullet>();
		for(SpaceObject spaceObject:world.getAllSpaceObjects()){
			if(Bullet.class.isAssignableFrom(spaceObject.getClass()))
				bullets.add((Bullet) spaceObject);
		}
		return bullets;
	}

	@Override
	public void addShip(World world, Ship ship) {
		try{
		ship.setWorld(world);
		world.addAsSpaceObject(ship);
		} catch (Exception ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) {
		try{
		asteroid.setWorld(world);
		world.addAsSpaceObject(asteroid);
		} catch (Exception ex){
			throw new ModelException(ex);
		}
		
	}

	@Override
	public void removeShip(World world, Ship ship) {
		ship.terminate();
	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		asteroid.terminate();
	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) {
		try{world.evolve(dt,collisionListener);
		
		} catch(Exception ex){
			throw new ModelException(ex);
		}
		
		
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
		return ship.isEnableThruster();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) {
		ship.setEnableThruster(active);
		
	}

	@Override
	public void turn(Ship ship, double angle) {
		try{ship.turn(angle);
		
		}catch(Exception ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public void fireBullet(Ship ship) {
		
		try{
		try {
			SpaceObject bullet = new Bullet(ship);
			ship.fireObject(bullet);
		} catch (IllegalRadiusException | IllegalMaxSpeedException e) {
			throw new ModelException(e);
		}
		}catch(IllegalPositionException ex){
			//Do Nothing
		} 
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		try{
			return new Asteroid(new Position(x,y)
			, new Velocity(xVelocity,yVelocity), radius);
		} catch(Exception ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		try{
		return new Asteroid(new Position(x,y),
							new Velocity(xVelocity,yVelocity), 
							radius, 
							random);
		} catch (Exception ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public boolean isAsteroid(Object o) {
		return Asteroid.class.isAssignableFrom(o.getClass());
	}

	@Override
	public double getAsteroidX(Asteroid asteroid) {
		return asteroid.getPos().getX();
	}

	@Override
	public double getAsteroidY(Asteroid asteroid) {
		return asteroid.getPos().getY();
	}

	@Override
	public double getAsteroidXVelocity(Asteroid asteroid) {
		return asteroid.getVel().getX();
	}

	@Override
	public double getAsteroidYVelocity(Asteroid asteroid) {
		return asteroid.getVel().getY();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) {
		return asteroid.getWorld();
	}

	@Override
	public boolean isBullets(Object o) {
		return Bullet.class.isAssignableFrom(o.getClass());
	}

	@Override
	public double getBulletX(Bullet bullet) {
		return bullet.getPos().getX();
	}

	@Override
	public double getBulletY(Bullet bullet) {
		return bullet.getPos().getY();
	}

	@Override
	public double getBulletXVelocity(Bullet bullet) {
		return bullet.getVel().getX();
	}

	@Override
	public double getBulletYVelocity(Bullet bullet) {
		return bullet.getVel().getY();
	}

	@Override
	public double getBulletRadius(Bullet bullet) {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		return bullet.getSource();
		}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> parseProgram(String text) {
		ProgramFactoryImpl factory = new ProgramFactoryImpl();
		ProgramParser<Expression, Statement, Type> parser = new ProgramParser<Expression ,Statement,Type>( factory );
		parser.parse(text);
		List<String> errors = parser.getErrors();
		if(! errors.isEmpty()) 
		return ParseOutcome.failure(errors.get(0));
		else return ParseOutcome.success(new Program(parser.getGlobals(), parser.getStatement(), parser.getErrors()));
		
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromStream(
			InputStream stream) throws IOException {
		String text = getStringFromInputStream(stream);
		return parseProgram(text);
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromUrl(URL url)throws IOException{
			InputStream stream = url.openStream();
			return this.loadProgramFromStream(stream);
	}

	@Override
	public boolean isTypeCheckingSupported() {
		return true;
	}

	@Override
	public asteroids.IFacade.TypeCheckOutcome typeCheckProgram(Program program) {
		if(program.typeCheck()) 
				return asteroids.IFacade.TypeCheckOutcome.failure("Program contains type errors.");
		else return TypeCheckOutcome.success();
				
	}

	@Override
	public void setShipProgram(Ship ship, Program program){
		try{ ship.setProgram(program);
		}catch(Exception e){
			throw new ModelException(e);
		}
		
	}
	private static String getStringFromInputStream(InputStream is) throws IOException{
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
					br.close();
				
			}
		}
 
		return sb.toString();
 
	}
	}