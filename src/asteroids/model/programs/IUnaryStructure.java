package asteroids.model.programs;

public interface IUnaryStructure extends IComposedStructure {
 
	public abstract IEntry getOperand();
	public abstract void setOperand(IEntry operand);
	public abstract boolean canHaveAsOperand();
	
}
