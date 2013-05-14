package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;


public class Self extends EntityRepresentation{
	
	private SpaceObject value;
	
	
	public Self(SpaceObject value){
		this.value = value;
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
