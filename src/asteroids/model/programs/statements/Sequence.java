package asteroids.model.programs.statements;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;

public class Sequence extends StructuralStatement {

	private ArrayList<Statement> statements;
	private int executionPosition = 1;
	
	public Sequence(){
		statements = new ArrayList<Statement>();
	}
	public Sequence(List<Statement> statementsToAdd){
		this();
		addAllStatements(statementsToAdd);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index >0 && index <= getNbOperands())
		return this.getAllStatements().get(index - 1);
		else throw new IndexOutOfBoundsException();
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
				&& operand instanceof Statement);
	}
	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		getAllStatements().add(index-1, (Statement) operand);

	}
	public void addAsStatement(Statement statement) {
			getAllStatements().add(statement);
	}
	public void addAllStatements(List<Statement> statementsToAdd) {
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
	public boolean execute() {
		int i = executionPosition;
		boolean encounteredAction = false;
		while(i<= getNbOperands() && encounteredAction == false){
			
			encounteredAction= ((Statement)getOperandAt(i)).execute();
			i++;
		}
		executionPosition = i;
		return !(i == getNbOperands()+1);
	}
	
	@Override
	public String toString(){
		String result = "";
		for(Statement statement: getAllStatements()){
			result += "\n" + statement;
		}
		return result;
	}
	@Override
	public boolean isTypeChecked() {
		for(Statement st: getAllStatements()){
			if(!st.isTypeChecked())
				return false;
		}
		return true;
	}
}
