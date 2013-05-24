package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Error.IllegalOperandException;

public interface IComposedStructure extends IStructure{
	
public abstract IEntry getOperandAt(int index) throws IndexOutOfBoundsException;

public abstract int getNbOperands();

public abstract void setOperandAt(int index, @Raw IEntry operand) throws IllegalOperandException;

@Raw
public abstract boolean canHaveAsOperandAt(int index, IEntry operand);
}
