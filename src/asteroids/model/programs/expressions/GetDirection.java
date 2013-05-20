package asteroids.model.programs.expressions;

import asteroids.model.Ship;



public class GetDirection extends SpaceObjectInspector{
		
	
		
	public GetDirection(Expression argument) {
		
		super(argument);
		
	}
	

		
	

	@Override
	public Double getRealValue() {
			
			Entity entity1 = (Entity) (getOperandAt(1).getValue());
			
			return ((Ship)(entity1.getRealValue())).getDirection();
			
	}

	

	
	}
