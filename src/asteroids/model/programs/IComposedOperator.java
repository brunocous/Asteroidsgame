package asteroids.model.programs;

import asteroids.model.programs.expressions.Expression;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public interface IComposedOperator {

	
	@Basic @Raw
	public abstract int getNbOperands();
	
	@Basic @Raw
	public abstract Expression getOperandAt(int index)
			throws IndexOutOfBoundsException;
	
	protected abstract void setOperandAt(int index, Expression operand);
}
