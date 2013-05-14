package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;


public class Null extends EntityRepresentation {

	private SpaceObject value;

	public Null(){
		value=null;
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
