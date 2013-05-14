
	package asteroids.model.programs.expressions;



	public class Bool extends BooleanRepresentation{

		private boolean value;
		
		
		public Bool(boolean value) {
			this.value = (value);
		}
		

	@Override	
	public Bool getValue(){
			return this;
	}	

	@Override
	public boolean getJavaBoolean(){
		return value;
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}


	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return expression == this;
	}
	
	@Override
	public BooleanRepresentation clone(){
		return new Bool(this.getJavaBoolean());
	}



	}



