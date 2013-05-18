package asteroids.model.programs.expressions;



public class GetY extends SpaceObjectInspector{
		
	
		
	public GetY(Expression argument) {
		
		super(argument);
		
	}
	


@Override
public Double getRealValue() {
			
	Entity entity1 = (Entity) (getOperandAt(1).getValue());
			
	return entity1.getRealValue().getPos().getY();
			
}


	
}