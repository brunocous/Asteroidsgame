package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;



public class LogicAnd extends ComposedExpression{
	
	private ArrayList<Expression> subexpressions;
	private Type type = new BooleanLiteral();

	public LogicAnd(ArrayList<Expression> subexpressions){
		
		super(subexpressions);
		
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