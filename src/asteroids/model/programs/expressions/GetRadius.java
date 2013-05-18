package asteroids.model.programs.expressions;





public class GetRadius extends SpaceObjectInspector{
		
	
		
	public GetRadius(Expression argument) {
		
		super(argument);
		
	}
	

		
	

	@Override
	public Double getRealValue() {
			
			Entity entity1 = (Entity) (getOperandAt(1).getValue());
			
			return entity1.getRealValue().getRadius();
			
	}

	

	
	}
