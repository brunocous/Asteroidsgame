package asteroids.model.programs.statements;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;

public class Sequence extends StructuralStatement {

	private ArrayList<Statement> statements;
	
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
		for(Statement statement: getAllStatements()){
			statement.execute();
		}
	}
	@Override
	public String toString(){
		String result = "";
		for(Statement statement: getAllStatements()){
			result += "\n" + statement;
		}
		return result;
	}

}
