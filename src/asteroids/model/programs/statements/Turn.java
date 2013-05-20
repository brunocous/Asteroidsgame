package asteroids.model.programs.statements;

import java.util.Map;

import asteroids.Error.IllegalOperandException;
import asteroids.model.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.type.Type;

public class Turn extends ShipActionStatement {

	private Expression amount;
	
	public Turn( Expression amount){
		super();
		this.amount = amount;
	}
	
	@Override 
	public boolean isTypeChecked(Map<String,Type> globals){
		return super.isTypeChecked(globals) 
				&& getAmount().getType() == Type.DOUBLE;
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
	public boolean execute() {
		Ship ship = (Ship) getShip().getRealValue();
			ship.turn((double) getAmount().getRealValue());
			return true;
	}

	@Override
	public boolean canHaveAsOperandAt( int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand) && index == 2 
				&& operand instanceof Expression)
				return ((Expression) operand).getType() == Type.DOUBLE;
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
