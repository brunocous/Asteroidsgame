package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.Ship;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Self;
import asteroids.model.programs.expressions.Variable;

public class Assignement extends StructuralStatement{

	private Expression var;
	private Expression newValue;
	
	public Assignement(Expression var, Expression newValue){
		this.var = var;
		this.newValue = newValue;
		
			
	}
	
	@Override
	public IEntry getOperandAt(int index) 
		throws IndexOutOfBoundsException {
			if ((index != 1) && (index != 2))
				throw new IndexOutOfBoundsException();
			if (index == 1)
				return getVar();
			else
				return getNewValue();
	}

	@Override
	public void execute() {
		((Variable) getVar()).setValue(newValue);
	}

	@Override
	public int getNbOperands() {
		return 2;
	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		if(super.canHaveAsOperandAt(index, operand) && operand != null )
			if(index == 1)
				return operand.getClass().isAssignableFrom(Variable.class);
			if(index == 2 && operand.getClass().isAssignableFrom(Expression.class))
				return ((Variable) getVar()).getType().getClass().isAssignableFrom(((Expression) operand).getRealValue().getClass())
						&& ((Expression) operand).isTypeChecked();
		return false;
				
	}
	@Override
	public void setOperandAt(int index, IEntry operand) throws IllegalOperandException{
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		else
			if(index == 1)
				this.var = (Variable) operand;
			if(index == 2)
				this.newValue = (Expression) operand;
	}
	@Override
	public String toString(){
		return getOperandAt(1) + " is set to " + getOperandAt(2) + ".";
	}

	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		getNewValue().setShip(ship);
	}

	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(2, getNewValue());
	}
	public Expression getVar(){
		return var;
	}
	public Expression getNewValue(){
		return newValue;
	}
	}

