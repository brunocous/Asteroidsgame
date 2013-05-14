package asteroids.model.programs.expressions;

public abstract class BooleanRepresentation extends Expression {
	
	public abstract boolean getJavaBoolean();
	
	@Override
	public boolean equals(Expression other){
		
		if(other.getValue() == new Bool(this.getJavaBoolean())){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public abstract boolean hasAsSubExpression(Expression expression);
	
	@Override
	public Bool getValue(){
		
		return new Bool(this.getJavaBoolean());
	}

	@Override
	public abstract boolean isMutable();
	
	@Override
	public Bool clone(){
		return new Bool(this.getJavaBoolean());
	}

}
