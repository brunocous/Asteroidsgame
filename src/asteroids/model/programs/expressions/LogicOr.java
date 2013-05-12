package asteroids.model.programs.expressions;


import java.util.ArrayList;



public class LogicOr extends BinaryComposedExpression{
	
	private ArrayList<Expression> subexpressions;

	public LogicOr(Expression expression1, Expression expression2){
		
		super(expression1, expression2);
		
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Bool(subexpressions.get(0).getBooleanValue()||subexpressions.get(1).getBooleanValue());
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}
