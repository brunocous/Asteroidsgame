package asteroids.model.programs.expressions;

import java.util.ArrayList;

public abstract class UnaryComposedExpression extends ComposedExpression {

	private ArrayList<Expression> subExpression;
	
	public UnaryComposedExpression(Expression expression){
		
		this.subExpression = new ArrayList<Expression>();
		subExpression.add(expression);
	}
	
	
}
