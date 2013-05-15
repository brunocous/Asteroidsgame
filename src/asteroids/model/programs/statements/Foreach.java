package asteroids.model.programs.statements;

import java.util.List;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Variable;

public class Foreach extends StructuralStatement {

	private List<Entity> entities;
	private Variable localVar;
	private StructuralStatement body;
	
	public Foreach(List<Entity> entities, Variable localVar, StructuralStatement body){
		setOperandAt(1,entities);
		setOperandAt(2,localVar);
		setOperandAt(3,body);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return entities;
		if(index == 2)
			return localVar;
		if(index == 3)
			return body;
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNbOperands() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
