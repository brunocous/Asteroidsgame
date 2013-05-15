package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;

public class Assignement extends StructuralStatement{

	private Variable var;
	private Expression value;
	
	public Assignement(Variable var, Expression value) throws IllegalOperandException, UnhandledCombinationException{
		if(!var.getValue().getClass().isAssignableFrom(value.getValue().getClass()))
			throw new UnhandledCombinationException();
		this.setOperandAt(1,var);
		this.setOperandAt(2,value);
		
			
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
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		return super.canHaveAsOperandAt(index, operand) && ( (index == 1) || (index == 2) );
	}
	@Override
	public void setOperandAt(int index, IEntry operand) {
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		else
			if(index == 1)
				this.var = operand;
			else
				this.value = operand;
		}
		
	}

}
