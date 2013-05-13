package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.types.EntityLiteral;
import asteroids.model.programs.types.Type;

public class Self extends BasicExpression{
	
	private SpaceObject value;
	private Type type = new EntityLiteral();
	
	public Self(SpaceObject value){
		this.value = value;
	}
	
	public Type getType(){
		return type;
	}
	@Override
	public Expression getValue(){
			
			return this;
	}	
	public SpaceObject getSelfValue(){
		
		return value;
		
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}


	public boolean equals(Expression other) {
		
		if(other.getType() == getType()){
		return ((Self)other).getSelfValue() == getSelfValue();
		}
		else{
			return false;
		}

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
