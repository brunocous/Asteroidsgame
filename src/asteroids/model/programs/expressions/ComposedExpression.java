package asteroids.model.programs.expressions;

import java.util.ArrayList;

import asteroids.model.programs.IComposedOperator;
import asteroids.model.programs.types.Type;

public abstract class ComposedExpression extends Expression implements IComposedOperator{
	

	
	public abstract int getNbOperands();
	

	public Expression getOperandAt(int index) throws IndexOutOfBoundsException {
		
		return expressions.get(index);
		
	}
	
	public void setOperandAt(int index, Expression operand){
		
		expressions.set(index, operand);
		
	}
	public boolean equals(Expression other){
		
		return this.getValue().equals(other.getValue());
	}
	
	public abstract Expression getValue();
	
	public boolean hasAsSubExpression(Expression expression){
		return expressions.contains(expression);
	}
		
	
}
