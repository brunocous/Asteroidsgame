package asteroids.model.programs.expressions;




public class GetDirection extends SpaceObjectInspector{
		
	public GetDirection(Expression argument) {
		
		super(argument);
		
	}
	@Override
	public Double getRealValue() {
			
			return getProgram().getShip().getDirection();
			
	}

	

	
	}
