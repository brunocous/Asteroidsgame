package asteroids.model.programs;

import asteroids.Error.IllegalOperandException;

public interface IUnaryStructure extends IComposedStructure {
 
	public abstract IEntry getOperand();
	public abstract void setOperand(IEntry operand) throws IllegalOperandException;
	public abstract boolean canHaveAsOperand();
	
}
