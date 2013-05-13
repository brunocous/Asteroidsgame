package asteroids.model.programs;

public interface IComposedStructure {
public abstract IEntry getOperandAt(int index);

public abstract void setOperandAt(int index);

public abstract boolean canHaveAsOperandAt(int index);
}
