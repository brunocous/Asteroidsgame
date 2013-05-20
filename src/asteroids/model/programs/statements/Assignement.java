package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.Ship;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Self;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.type.*;
import java.util.*;

public class Assignement extends StructuralStatement{

	private Expression newValue;
	private String variableName;
	
	public Assignement(String variableName, Expression newValue){
		
		this.newValue = newValue;
		this.variableName = variableName;
			
	}
	
	@Override
	public IEntry getOperandAt(int index) 
		throws IndexOutOfBoundsException {
			if (index == 1)
				return getVar();
			if ((index == 2))
				return getNewValue();
			if ((index == 3))
				return getVariableName();
			throw new IndexOutOfBoundsException();
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
				return ((Variable) getVar()).getType().getClassReference().isAssignableFrom(((Expression) operand).getRealValue().getClass())
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
	public boolean isTypeChecked(Map<String, Type> globals) {
		if(globals.get(getVariableName()) == newValue.getType()){
			return true;
		}
		else {
			return false;
		}
	}

	public Expression getNewValue(){
		return newValue;
	}

	public String getVariableName() {
		return variableName;
	}
	}

