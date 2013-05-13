package asteroids.model.programs.expressions;

public abstract class BasicExpression extends Expression {

	private Expression value;
	
	
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
	/**
	 * Return the hash code of this expression.
	 */
	@Override
	public int hashCode() {
		if (! this.isMutable())
			return getValue();
		else
			return super.hashCode();
	}
	

	



}
