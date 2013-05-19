package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;

public class Turn extends ShipActionStatement {

	private Expression amount;
	
	public Turn( Expression amount) throws IllegalOperandException{
		super();
		this.amount = amount;
	}
	
	@Override 
	public boolean isTypeChecked(){
		return super.isTypeChecked() && canHaveAsOperandAt(2, getAmount());
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getShip();
		if(index == 2)
			return getAmount();
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void execute() {
		Ship ship = (Ship) getShip().getRealValue();
			ship.turn((double) getAmount().getRealValue());
	}

	@Override
	public boolean canHaveAsOperandAt( int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand) && index == 2 
				&& operand.getClass().isAssignableFrom(Expression.class))
				return ((Expression) operand).getRealValue().getClass().isAssignableFrom(double.class);
		return false;
	}
	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		super.setOperandAt(index, operand);
		if(index == 2 && canHaveAsOperandAt(index, operand))
			this.amount = (Expression) operand;
	}

	@Override
	public String toString(){
		return getOperandAt(1) + " turns with an amount of " + getOperandAt(2) + " radians.";
	}
	public Expression getAmount(){
		return amount;
	}
}
