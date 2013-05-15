package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public abstract class EntityRepresentation extends Expression {
	
	public abstract SpaceObject getSpaceObject();
	
	@Override
	public boolean equals(Object other){
		
		if( !EntityRepresentation.class.isAssignableFrom(other.getClass())){
			return false;
		}
		else if(((EntityRepresentation)other).getValue() == new Entity(this.getSpaceObject())){
			return true;
		}
		else{
			return false;
		}
	}


	
	@Override
	public Entity getValue(){
		
		return new Entity(this.getSpaceObject());
	}


}
