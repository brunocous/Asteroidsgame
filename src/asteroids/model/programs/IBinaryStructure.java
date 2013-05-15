package asteroids.model.programs;

import asteroids.Error.IllegalOperandException;

public interface IBinaryStructure extends IComposedStructure {

	public abstract IEntry getFirstOperand();
	public abstract void setFirstOperand(IEntry fOperand) throws IllegalOperandException;
	public abstract boolean canHaveAsFirstOperand(IEntry fOperand);
	public abstract IEntry getSecondOperand();
	public abstract void setSecondOperand(IEntry sOperand) throws IllegalOperandException;
	public abstract boolean canHaveAsSecondOperand(IEntry sOperand);
	
}
