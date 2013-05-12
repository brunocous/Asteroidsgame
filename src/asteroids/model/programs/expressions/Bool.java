
	package asteroids.model.programs.expressions;

	public class Bool extends BasicExpression{

		private boolean value;
		
		public Bool(boolean value) {
			this.value = (value);
		}
		

	@Override
	public Expression getValue(){
			
			return this;
	}	

	public boolean getBooleanValue(){
		return value;
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}

	@Override
	public boolean equals(Expression other) {
		
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

	}



