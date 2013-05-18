package asteroids.model.programs.expressions;

public abstract class BooleanRepresentation extends Expression {
	
	public abstract boolean getJavaBoolean();
	
	@Override
	public boolean equals(Object other){
		
		if( !BooleanRepresentation.class.isAssignableFrom(other.getClass())){
			return false;
		}
		else if(((BooleanRepresentation)other).getValue() == new BooleanLiteral(this.getJavaBoolean())){
			return true;
		}
		else{
			return false;
		}
	}


	
	@Override
	public BooleanLiteral getValue(){
		
		return new BooleanLiteral(this.getJavaBoolean());
	}

	@Override
	public String toString(){
		return String.valueOf(getJavaBoolean());
	}

}
