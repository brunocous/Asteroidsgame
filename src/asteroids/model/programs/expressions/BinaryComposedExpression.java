package asteroids.model.programs.expressions;

import java.util.ArrayList;

public abstract class BinaryComposedExpression extends ComposedExpression {

	private ArrayList<Expression> subExpressions;
	
	public BinaryComposedExpression(Expression expression1, Expression expression2){
		
		this.subExpressions = new ArrayList<Expression>();
		subExpressions.add(expression1);
		subExpressions.add(expression2);
	}
	
}
