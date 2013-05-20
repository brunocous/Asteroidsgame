package asteroids.model.programs.statements;

import java.util.Map;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.type.*;

public class Skip extends ActionStatement {

	
	public Skip(){
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		return null;
	}
	@Override
	public boolean equals(Object other){
		return (other.getClass().isAssignableFrom(this.getClass())) ? (true): (false);
	}
	@Override
	public int getNbOperands() {
		return 0;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		assert getNbOperands() == 0;
	}

	@Override 
	public boolean canHaveAsOperandAt(int indes,IEntry operand){
		return false;
	}
	@Override
	public String toString(){
		return "Take 0.2 seconds the time to think about how much time 0.2 seconds are";
	}
	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		assert true;
		
	}
	@Override
	public boolean isTypeChecked(Map<String, Type> globals) {
		return true;
	}
	@Override
	public boolean execute() {
		return true;
	}

}
