package asteroids.model.programs.expressions;

import java.util.ArrayList;

import asteroids.Util;

public class LessThan extends BinaryComposedExpression{
	
	private ArrayList<Constant> subexpressions;

	public LessThan(Constant expression1, Constant expression2){
		
		super(expression1, expression2);
		
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Expression getValue(){
		
		return new Bool(Util.fuzzyLessThanOrEqualTo(subexpressions.get(0).getConstantValue(), subexpressions.get(1).getConstantValue())
				 && !Util.fuzzyEquals(subexpressions.get(0).getConstantValue(), subexpressions.get(1).getConstantValue()));
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}
