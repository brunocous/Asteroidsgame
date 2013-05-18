package asteroids.model.programs.expressions;


public class GetVX extends SpaceObjectInspector{
		
	
		
	public GetVX(Expression argument) {
		
		super(argument);
		
	}
	
		
	

@Override
public Double getRealValue() {
			
		Entity entity1 = (Entity) (getOperandAt(1).getValue());
			
		return entity1.getRealValue().getVel().getX();
			
	}
}