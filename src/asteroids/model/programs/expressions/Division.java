package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.model.programs.types.DoubleLiteral;
import asteroids.model.programs.types.Type;



public class Division extends ComposedExpression{
	
	private ArrayList<Expression> subexpressions;
	private Type type = new DoubleLiteral();
	

	public Division(ArrayList<Expression> subexpressions){
		
		super(subexpressions);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Constant(subexpressions.get(0).getConstantValue()/subexpressions.get(1).getConstantValue());
	}
	
	public boolean equals(Expression other){
		
		return this.getValue().equals(other.getValue());
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}

