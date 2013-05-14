package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.programs.IBinaryStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;

public class Assignement extends StructuralStatement implements
		IBinaryStructure {

	private final Variable var;
	private final Expression value;
	
	public Assignement(Variable var, Expression value) throws IllegalOperandException, UnhandledCombinationException{
		if(!var.getValue().getClass().isAssignableFrom(value.getValue().getClass()))
			throw new UnhandledCombinationException();
		if(!canHaveAsFirstOperand(var))
			throw new IllegalOperandException(this, var);
			
	}
	
	@Override
	public IEntry getOperandAt(int index) 
		throws IndexOutOfBoundsException {
			if ((index != 1) && (index != 2))
				throw new IndexOutOfBoundsException();
			if (index == 1)
				return getFirstOperand();
			else
				return getSecondOperand();
	}



	@Override
	public boolean hasAsSubEntry(IEntry entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEntry getFirstOperand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstOperand(IEntry fOperand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canHaveAsFirstOperand(IEntry fOperand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEntry getSecondOperand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSecondOperand(IEntry sOperand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canHaveAsSecondOperand(IEntry sOperand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Statement other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNbOperands() {
		return 2;
	}

}
