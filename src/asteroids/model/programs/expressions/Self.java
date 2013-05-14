package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.types.EntityLiteral;
import asteroids.model.programs.types.Type;

public class Self extends EntityRepresentation{
	
	private SpaceObject value;
	
	
	public Self(SpaceObject value){
		this.value = value;
	}
	

	@Override
	public Expression getValue(){
			
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
