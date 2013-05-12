package asteroids.model.programs.expressions;

public class Variable extends BasicExpression {
	
	private Expression value;
	
	public Variable(Expression value){
		super(value);
	}
	@Override
	public Expression getValue(){
			
			return value;
	}	
	@Override
	public boolean isMutable() {
		
		return true;
	}

	@Override
	public boolean equals(Object other) {
		
		return false;
	}

	@Override
	public String toString() {
		
		return null;
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return expression == this;
	}
	
	public void setValue(Expression value){
		
		this.value = value;
	}

}
