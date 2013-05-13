package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;



public class LogicAnd extends BinaryComposedExpression{
	
	private ArrayList<Expression> subexpressions;
	private Type type = new BooleanLiteral();

	public LogicAnd(Expression expression1, Expression expression2){
		
		super(expression1, expression2);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Bool(subexpressions.get(0).getBooleanValue()&&subexpressions.get(1).getBooleanValue());
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}