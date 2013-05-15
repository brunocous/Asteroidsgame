package asteroids.model.programs;

import asteroids.Error.IllegalOperandException;

public interface IComposedStructure extends IStructure{
	
public abstract IEntry getOperandAt(int index) throws IndexOutOfBoundsException;

public abstract int getNbOperands();

public abstract void setOperandAt(int index, IEntry operand) throws IllegalOperandException;

public abstract boolean canHaveAsOperandAt(int index, IEntry operand);
}
