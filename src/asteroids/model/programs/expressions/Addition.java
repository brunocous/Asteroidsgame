package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.model.programs.types.DoubleLiteral;
import asteroids.model.programs.types.Type;



public class Addition extends BinaryComposedExpression{
	
	private ArrayList<Expression> subexpressions;
	private Type type = new DoubleLiteral();

	public Addition(Expression expression1, Expression expression2){
		
		super(expression1, expression2);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Constant(subexpressions.get(0).getConstantValue()+subexpressions.get(1).getConstantValue());
	}
	

	public String toString(){
		
		return getValue().toString();
	}
}

