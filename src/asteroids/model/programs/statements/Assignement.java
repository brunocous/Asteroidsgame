package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.programs.IBinaryStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;

public class Assignement extends StructuralStatement implements
		IBinaryStructure {

	private Variable var;
	private Expression value;
	
	public Assignement(Variable var, Expression value) throws IllegalOperandException, UnhandledCombinationException{
		if(!var.getValue().getClass().isAssignableFrom(value.getValue().getClass()))
			throw new UnhandledCombinationException();
		if(!canHaveAsFirstOperand(var))
			throw new IllegalOperandException(this, var);
		if(!canHaveAsSecondOperand(value))
			throw new IllegalOperandException(this, value);
		this.setFirstOperand(var);
		this.setSecondOperand(value);
		
			
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
		return var;
	}

	@Override
	public void setFirstOperand(IEntry fOperand) throws IllegalOperandException{
		if(!canHaveAsFirstOperand(fOperand))
			throw new IllegalOperandException(this, fOperand);
		else
		this.var =(Variable) fOperand;
		
	}

	@Override
	public boolean canHaveAsFirstOperand(IEntry fOperand) {
		return canHaveAsOperandAt(1,fOperand);
	}

	@Override
	public IEntry getSecondOperand() {
		return value;
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
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		return super.canHaveAsOperandAt(index, operand) && ( (index == 1) || (index == 2) );
	}
	@Override
	public void setOperandAt(int index, IEntry operand) {
		if(canHaveAsOperandAt(index, operand)){
			if(index == 1)
				setFirstOperand(operand);
			else
				setSecondOperand(operand);
		}
		
	}

}
