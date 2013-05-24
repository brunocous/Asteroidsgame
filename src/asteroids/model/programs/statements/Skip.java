package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;

public class Skip extends ActionStatement {

	
	public Skip(){
	}
	@Override
	@Basic
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
	@Basic
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		assert getNbOperands() == 0;
	}

	@Override 
	@Raw
	public boolean canHaveAsOperandAt(int indes,IEntry operand){
		return false;
	}
	@Override
	public String toString(){
		return "Take 0.2 seconds the time to think about how much time 0.2 seconds are";
	}
	@Override
	public boolean isTypeChecked() {
		return true;
	}
	@Override
	public boolean execute() {
		return true;
	}
	@Override
	@Basic
	public void setProgram(Program program){
		super.setProgram(program);
	}

}
