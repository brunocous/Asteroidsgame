package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;


public class Equals extends BinaryComposedExpression{
public Type type = new BooleanLiteral();
	
	private ArrayList<Expression> subexpressions;

	public Equals(Expression expression1, Expression expression2){
		
		super(expression1, expression2);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Bool(subexpressions.get(0).equals(subexpressions.get(1)));
	}
	
	public boolean equals(Expression other){
		
		return this.getValue().equals(other.getValue());
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}

