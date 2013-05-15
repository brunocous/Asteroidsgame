package asteroids.model.programs;

import asteroids.Error.IllegalOperandException;

public interface ITernaryStructure extends IBinaryStructure {

	public abstract IEntry getThirdOperand();
	public abstract void setThirdOperand(IEntry tOperand) throws IllegalOperandException;
	public abstract boolean canHaveAsThirdOperand(IEntry tOperand);
}
