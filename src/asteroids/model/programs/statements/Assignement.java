package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;
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
			if ((index == 1))
				return getNewValue();
			throw new IndexOutOfBoundsException();
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public int getNbOperands() {
		return 1;
	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		if(super.canHaveAsOperandAt(index, operand) && operand != null )
			if(index == 1 && operand.getClass().isAssignableFrom(Expression.class))
				return true;
		return false;
				
	}
	@Override
	public void setOperandAt(int index, IEntry operand) throws IllegalOperandException{
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		else 
			if(index == 1)
				this.newValue = (Expression) operand;
	}
	@Override
	public String toString(){
		return getVariableName() + " is set to " + getOperandAt(1) + ".";
	}

	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		getNewValue().setShip(ship);
	}

	@Override
	public boolean isTypeChecked(Map<String, Type> globals) {
		return (globals.get(getVariableName()) == newValue.getType()) ? true:false;
			
	}

	public Expression getNewValue(){
		return newValue;
	}

	public String getVariableName() {
		return variableName;
	}
	}

