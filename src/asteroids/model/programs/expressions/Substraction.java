package asteroids.model.programs.expressions;


import java.util.ArrayList;



public class Substraction extends BinaryComposedExpression{
	
	private ArrayList<Constant> subexpressions;

	public Substraction(Constant expression1, Constant expression2){
		
		super(expression1, expression2);
		
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Constant(subexpressions.get(0).getConstantValue()-subexpressions.get(1).getConstantValue());
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}
