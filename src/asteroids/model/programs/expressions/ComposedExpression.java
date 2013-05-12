package asteroids.model.programs.expressions;

import java.util.ArrayList;

import asteroids.model.programs.IComposedOperator;

public abstract class ComposedExpression extends Expression implements IComposedOperator{
	
	private ArrayList<Expression> expressions;
	
	public int getNbOperands(){
		
		return expressions.size();
		
	}

	public Expression getOperandAt(int index) throws IndexOutOfBoundsException {
		
		return expressions.get(index);
		
	}
	
	public void setOperandAt(int index, Expression operand){
		
		expressions.set(index, operand);
		
	}
	
	public abstract Expression getValue();
	
	public boolean hasAsSubExpression(Expression expression){
		return expressions.contains(expression);
	}
		
	
}
