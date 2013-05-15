package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.DoubleRepresentation;
import asteroids.model.programs.expressions.Entity;

public class Turn extends ActionStatement {

	private Entity entity;
	private DoubleRepresentation amount;
	
	public Turn()
	
	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbOperands() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		// TODO Auto-generated method stub
		
	}

}
