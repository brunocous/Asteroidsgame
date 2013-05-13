package asteroids.model.programs.expressions;

import asteroids.model.programs.types.NullLiteral;
import asteroids.model.programs.types.Type;

public class Null extends BasicExpression {

	private Expression value;
	private Type type = new NullLiteral();
	
	public Null(){
		value=null;
	}
	
	public Type getType(){
		return type;
	}
	
	@Override
	public Expression getValue(){
			
			return this;
	}	
	public Expression getNullValue(){
		return value;
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}


	public boolean equals(Expression other) {
		
		if(other.getType() == getType()){
		return ((Null)other).getNullValue() == getNullValue();
		}
		else{
			return false;
		}

	}
	
	@Override
	public String toString() {
		
		return "null";
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return expression == this;
	}

}
