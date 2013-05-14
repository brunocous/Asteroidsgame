package asteroids.model.programs.expressions;

public abstract class DoubleRepresentation extends Expression {

	public abstract double getJavaDouble();
	
	@Override
	public boolean equals(Expression other){
		
		if(other.getValue() == new Constant(this.getJavaDouble())){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public DoubleRepresentation clone(){
		return new Constant(this.getJavaDouble());
	}

	public abstract boolean hasAsSubExpression(Expression expression);

	public Constant getValue(){
		
		return new Constant(this.getJavaDouble());
		
	}

	public abstract boolean isMutable() ;
}
