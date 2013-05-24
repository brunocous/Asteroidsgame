package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;

public abstract class ShipActionStatement extends ActionStatement {
	
	@Override
	public boolean isTypeChecked() {
		return (getNbOperands() ==1) ? true: this.isTypeChecked();
	}
	@Basic
	public Expression getShip(){
		return new Entity(getProgram().getShip());
	}
	@Override
	@Basic
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
	@Basic
	public void setOperandAt(int index, IEntry entry)throws IllegalOperandException{
		if(index <0)
			throw new IllegalOperandException();
	}
	@Override
	@Basic
	public void setProgram(Program program){
		super.setProgram(program);
	}

}
