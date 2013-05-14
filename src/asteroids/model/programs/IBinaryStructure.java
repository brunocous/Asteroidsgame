package asteroids.model.programs;

public interface IBinaryStructure extends IComposedStructure {

	public abstract IEntry getFirstOperand();
	public abstract void setFirstOperand(IEntry fOperand);
	public abstract boolean canHaveAsFirstOperand(IEntry fOperand);
	public abstract IEntry getSecondOperand();
	public abstract void setSecondOperand(IEntry sOperand);
	public abstract boolean canHaveAsSecondOperand(IEntry sOperand);
	
}
