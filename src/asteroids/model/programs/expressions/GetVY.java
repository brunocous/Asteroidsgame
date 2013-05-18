package asteroids.model.programs.expressions;




public class GetVY extends SpaceObjectInspector{
		
	
		
	public GetVY(Expression argument) {
		
		super(argument);
		
	}
	
	

	@Override
		
public Double getRealValue() {
			
	Entity entity1 = (Entity) (getOperandAt(1).getValue());
			
	return entity1.getRealValue().getVel().getY();
			
}


	
		}