package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.DoubleRepresentation;
import asteroids.model.programs.expressions.Entity;

public class Turn extends ActionStatement {

	private Entity entity = null;
	private DoubleRepresentation amount;
	
	public Turn(Entity entity, DoubleRepresentation amount) throws IllegalOperandException{
		setOperandAt(2, amount);
	}
	
	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return entity;
		if(index == 2)
			return amount;
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNbOperands() {
		return 2;
	}

	@Override
	public void execute() {
		Ship ship = (Ship) ((Entity) getOperandAt(1)).getSpaceObject();
			ship.turn(amount.getJavaDouble());
	}

	@Override
	public boolean canHaveAsOperandAt( int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand))
			if(index == 1 && operand.getClass().isAssignableFrom(Entity.class)){
				return ((Entity) operand).getValue().getSpaceObject().getClass().isAssignableFrom(Ship.class);
			}
			else if(index == 2)
				return operand.getClass().isAssignableFrom(DoubleRepresentation.class);
		return false;
	}
	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index, operand))
		throw new IllegalOperandException();
		if(index == 1)
			this.entity = (Entity) operand;
		if(index == 2)
			this.amount = (DoubleRepresentation) operand;
	}

	@Override
	public String toString(){
		return getOperandAt(1) + " turns with an amount of " + getOperandAt(2) + " radians.";
	}


	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		setOperandAt(1, ship);
		
	}
}
