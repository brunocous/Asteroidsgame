package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.Util;
import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;

public class GreaterThan extends BinaryComposedExpression{
	
	private ArrayList<Constant> subexpressions;
	private Type type = new BooleanLiteral();
	

	public GreaterThan(Constant expression1, Constant expression2){
		
		super(expression1, expression2);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Bool(!Util.fuzzyLessThanOrEqualTo(subexpressions.get(0).getConstantValue(), subexpressions.get(1).getConstantValue()));
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}
