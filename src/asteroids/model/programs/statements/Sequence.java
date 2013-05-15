package asteroids.model.programs.statements;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;

public class Sequence extends StructuralStatement {

	private ArrayList<Statement> statements;
	private static final double MAX_WAITING_TIME = 0.2;
	
	public Sequence(){
		statements = new ArrayList<Statement>();
	}
	public Sequence(List<Statement> statementsToAdd) throws IllegalOperandException{
		this();
		addAllStatements(statementsToAdd);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		return this.getAllStatements().get(index - 1);
	}
	@Override
	public int getNbOperands() {
		return statements.size();
	}
	public List<Statement> getAllStatements(){
		return statements;
	}

	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		return (super.canHaveAsOperandAt(index, operand) 
				&& (index <= getNbOperands() +1) 
				&& operand != null
				&& operand.getClass().isAssignableFrom(Statement.class));
	}
	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		getAllStatements().add(index-1, (Statement) operand);

	}
	public void addAsStatement(Statement statement) throws IllegalOperandException{
		setOperandAt(getNbOperands()+1,statement);
	}
	public void addAllStatements(List<Statement> statementsToAdd) throws IllegalOperandException{
		for(Statement statement: statementsToAdd){
			addAsStatement(statement);	
		}
	}
	@Raw
	public void removeStatement(Statement statement){
		assert statement != null;
		assert hasAsStatement(statement);
		getAllStatements().remove(statement);
	}

	public boolean hasAsStatement(Statement statement){
		return getAllStatements().contains(statement);
	}
	@Override
	public void execute() {
		// time stamp before any action
		long timeStampBeforeAction = System.currentTimeMillis();
		// starting the execution of this sequence
		for(Statement statement: getAllStatements()){
			//executes statement
			statement.execute();
			
			//if statement is an action, then wait the amount of time equal to 
			// the maximum amount of waiting time minus the amount of time until 
			// an action statements was executed. 
			if(statement.getClass().isAssignableFrom(ActionStatement.class)){
				long timeStampAfterAction = System.currentTimeMillis();
				long timeToWait = ((long) getMaxWaitingTime()) - (timeStampAfterAction - timeStampBeforeAction);
				long now = System.currentTimeMillis();
				while((now - timeStampAfterAction) < timeToWait){
					now = System.currentTimeMillis();
				}
				timeStampBeforeAction = System.currentTimeMillis();
			}
		}
	}
	public boolean containsActionStatements(){
		for(Statement statement: getAllStatements()){
			if(statement.getClass().isAssignableFrom(ActionStatement.class))
				return true;
		}
		return false;
	}
	@Override
	public String toString(){
		String result = "";
		for(Statement statement: getAllStatements()){
			result += "\n" + statement;
		}
		return result;
	}
	public static double getMaxWaitingTime() {
		return MAX_WAITING_TIME;
	}

}
