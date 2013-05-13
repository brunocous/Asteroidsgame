package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.types.EntityLiteral;
import asteroids.model.programs.types.Type;

public class Entity extends BasicExpression{
	
	private SpaceObject value;
	private Type type = new EntityLiteral();
	
	public Entity (SpaceObject value){
		this.value = (value);
	}
	@Override
	public Expression getValue(){
			
			return this;
	}	
	
	public Type getType(){
		return type;
	}
	
	public SpaceObject getEntityValue(){
		
		return value;
		
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}

	public boolean equals(Expression other) {
		
		if(other.getType() == getType()){
		return ((Entity)other).getEntityValue() == getEntityValue();
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
