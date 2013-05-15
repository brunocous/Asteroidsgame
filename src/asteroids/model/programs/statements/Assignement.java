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
				return var;
			else
				return value;
	}

	@Override
	public void execute() {
		try{((Variable) getOperandAt(1)).setValue((Expression) getOperandAt(2));
		
		} catch(IllegalVariableValueException e){
			assert !getOperandAt(1).getClass().isAssignableFrom(getOperandAt(2).getClass());
		}
		
	}

	@Override
	public boolean equals(Object other) {
		if(this.getClass() == other.getClass()){
			for(int pos = 1; pos <= getNbOperands(); pos++){
				if(!getOperandAt(pos).equals(((Assignement) other).getOperandAt(pos)))
						return false;
			}
			return true;
		}
		else return false;
	}

	@Override
	public int getNbOperands() {
		return 2;
	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		if(super.canHaveAsOperandAt(index, operand))
			if(index == 1)
				return operand.getClass().isAssignableFrom(Variable.class);
			else if(index == 2)
				return operand.getClass().isAssignableFrom(Expression.class);
		return false;
				
	}
	@Override
	public void setOperandAt(int index, IEntry operand) throws IllegalOperandException{
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		else
			if(index == 1)
				this.var = (Variable) operand;
			else
				this.value = (Expression) operand;
	}
	@Override
	public String toString(){
		return getOperandAt(1) + " is set to " + getOperandAt(2) + ".";
	}
		
	}

