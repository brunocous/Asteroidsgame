package asteroids.model.programs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.Error.IllegalOperandException;
import asteroids.model.Ship;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.statements.Statement;
import asteroids.model.programs.type.Type;

public class Program {

	private final Map<String, Variable> variables;
	private final Statement statement;
	private Ship ship = null;
	private final List<String> errors;
	private boolean isRunning;
	

	public Program(Map<String,Type> globals, Statement statement, List<String> errors){
		if(!canHaveAsVariables(globals))
			throw new IllegalArgumentException();
		else
			this.variables = convertToVariables(globals);
		if(!canHaveAsStatement(statement))
			throw new IllegalArgumentException();
		else{
			this.statement = statement;
			statement.setProgram(this);
		}
		this.errors = errors;
	}
	public boolean canHaveAsVariables(Map<String,Type> globals){
		return globals != null;
	}
	public boolean canHaveAsStatement(Statement statement){
		return statement != null && statement.canHaveAsProgram(this);
	}
	public Statement getStatement(){
		return statement;
	}
	public void execute(){
		setIsRunning(true);
		if(!hasErrors())
			System.out.println("Nu runnen in program");
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
				&& !hasErrors();
	}
	public void setShip(Ship ship) throws IllegalOperandException{
		assert canHaveAsShip(ship);
		this.ship = ship;
		
	}

	public List<String> getErrors() {
		return errors;
	}
	public boolean hasErrors(){
		return !getErrors().isEmpty();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public boolean typeCheck(){
		return statement.isTypeChecked();
	}
	public Map<String,Variable> convertToVariables(Map<String,Type> globals){
		HashMap<String,Variable> result = new HashMap<String,Variable>();
		for(String name: globals.keySet()){
			result.put(name, new Variable(name,globals.get(name)));
		}
		return result;
	}
	public Map<String,Variable> getVariables(){
		return variables;
	}
	public Variable getVariable(String variable){
		return getVariables().get(variable);
	}
	public boolean hasVariable(String variable){
		return getVariables().containsKey(variable);
	}
}
