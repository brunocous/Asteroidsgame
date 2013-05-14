package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public abstract class EntityRepresentation extends Expression{
	
	public abstract SpaceObject getSpaceObject();

	@Override
	public boolean equals(Expression other){
		
		if(other.getValue() == new Entity(this.getSpaceObject())){
			
			return true;
			
		}
		else{
			return false;
		}
	}
	

	@Override
	public EntityRepresentation clone(){
		return new Entity(this.getSpaceObject());
	}

	public abstract boolean hasAsSubExpression(Expression expression);

	public Entity getValue(){
		
		return new Entity(this.getSpaceObject());
		
	}

	public abstract boolean isMutable() ;
}
