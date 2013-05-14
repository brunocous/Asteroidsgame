package asteroids.model.programs;

public interface ITernaryStructure extends IBinaryStructure {

	public abstract IEntry getThirdOperand();
	public abstract void setThirdOperand(IEntry tOperand);
	public abstract boolean canHaveAsThirdOperand(IEntry tOperand);
}
