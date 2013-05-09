package asteroids.model.programs.expressions;

public abstract class BasicExpression extends Expression {

	private Expression value;
	
	public BasicExpression(Expression value){
		this.value = value;
	}
	@Override
	public boolean hasAsSubExpression(Expression expression) {
	
		return expression == this;
	}

	@Override
	public Expression getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
