package asteroids.model.programs;

public interface IComposedStructure extends IStructure{
	
public abstract IEntry getOperandAt(int index);

public abstract int getNbOperands();

public abstract void setOperandAt(int index, IEntry operand);

public abstract boolean canHaveAsOperandAt(int index, IEntry operand);
}
