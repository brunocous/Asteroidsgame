package asteroids.model.programs.statements;

import java.util.Map;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.type.Type;

public abstract class ShipActionStatement extends ActionStatement {

	private Expression ship;
	
	public ShipActionStatement(){
		this.ship = null;
	}
	@Override
	public boolean isTypeChecked(Map<String, Type> globals) {
		if(getShip() == null)
			return true;
		else return canHaveAsOperandAt(1,getShip());
	}

	public Expression getShip(){
		return ship;
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getShip();
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNbOperands() {
		return 1;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index,operand))
			throw new IllegalOperandException();
		if(index == 1)
			this.ship = (Expression) operand;
	}

	@Override
	public boolean canHaveAsOperandAt(int index, IEntry entry){
		if(super.canHaveAsOperandAt(index, entry)){
			if(index == 1)
				return entry instanceof Entity;
			else return index>1;
		}
		return false;
	}

	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		setOperandAt(1,ship);
	}

}
