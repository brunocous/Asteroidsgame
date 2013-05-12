package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class Entity extends BasicExpression{
	
	private SpaceObject value;
	
	public Entity (SpaceObject value){
		this.value = (value);
	}
	@Override
	public Expression getValue(){
			
			return this;
	}	
	
	public SpaceObject getEntityValue(){
		
		return value;
		
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}

	@Override
	public boolean equals(Object other) {
		
		return false;
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
