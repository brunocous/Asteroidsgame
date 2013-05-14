package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;


public class Entity extends EntityRepresentation{
	
	private SpaceObject value;
	
	
	public Entity (SpaceObject value){
		this.value = (value);
	}
	@Override
	public Entity getValue(){
			
			return this;
	}	
	
	@Override
	public SpaceObject getSpaceObject(){
		
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

}
