package asteroids.model.programs.expressions;

public class Null extends BasicExpression {

	private Expression value;
	
	public Null(){
		value=null;
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

	@Override
	public boolean equals(Object other) {
		
		return false;
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
