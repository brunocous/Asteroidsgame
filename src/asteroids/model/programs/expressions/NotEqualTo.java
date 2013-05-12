package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.Util;

public class NotEqualTo extends BinaryComposedExpression{
	
	private ArrayList<Expression> subexpressions;

	public NotEqualTo(Expression expression1, Expression expression2){
		
		super(expression1, expression2);
		
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Bool getValue(){
		
		return new Bool(!subexpressions.get(0).getValue().equals(subexpressions.get(1).getValue()));
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}

