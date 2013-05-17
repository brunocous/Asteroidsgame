package asteroids.model.programs;

import java.util.List;
import java.util.Map;

import asteroids.Error.IllegalOperandException;
import asteroids.model.Ship;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.statements.Statement;

public class Program {

	private final Map<String,Expression> globals;
	private final Statement statement;
	private Ship ship = null;
	private final List<String> errors;
	private boolean isRunning;

	public Program(Map<String,Expression> globals, Statement statement, List<String> errors){
		if(!canHaveAsGlobals(globals))
			throw new IllegalArgumentException();
		else
			this.globals = globals;
		if(!canHaveAsStatement(statement))
			throw new IllegalArgumentException();
		else
			System.out.println("Ik zet deze statement: " + statement.toString());
			this.statement = statement;
		this.errors = errors;
	}

	public Map<String,Expression> getGlobals() {
		return globals;
	}

	public Statement getStatement() {
		return statement;
	}
	public boolean canHaveAsGlobals(Map<String,Expression> globals){
		return globals != null;
	}
	public boolean canHaveAsStatement(Statement statement){
		return statement != null;
	}
	public void execute(){
		setIsRunning(true);
		if(!hasTypeCheckingErrors())
		this.getStatement().execute();
		setIsRunning(false);
	}
	public boolean hasShip(){
		return getShip() != null;
	}
	public Ship getShip(){
		return ship;
	}
	public boolean canHaveAsShip(Ship ship){
		return ship!= null 
				&& !ship.hasAProgram() 
				&& !hasShip() 
				&& !hasTypeCheckingErrors();
	}
	public void setShip(Ship ship) throws IllegalOperandException{
		assert canHaveAsShip(ship);
		this.ship = ship;
		Entity shipEntity = new Entity(getShip());
		getStatement().setShip(shipEntity);
		
	}

	public List<String> getErrors() {
		return errors;
	}
	public boolean hasTypeCheckingErrors(){
		return !getErrors().isEmpty();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
