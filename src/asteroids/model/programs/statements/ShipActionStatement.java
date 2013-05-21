package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;

public abstract class ShipActionStatement extends ActionStatement {
	
	@Override
	public boolean isTypeChecked() {
		return (getNbOperands() ==1) ? true: this.isTypeChecked();
	}

	public Expression getShip(){
		return new Entity(getProgram().getShip());
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
	public void setOperandAt(int index, IEntry entry)throws IllegalOperandException{
		if(index <0)
			throw new IllegalOperandException();
	}

}
