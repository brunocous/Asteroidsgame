package asteroids.model.programs.expressions;


import asteroids.model.programs.IEntry;



public class GetVY extends UnaryExpression{
		
	
		
	public GetVY(Expression argument) {
		
		super(argument);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			return true;
			}
			else{
				return false;
				
			}
			
	
	}
		
	

		@Override
		public Double getRealValue() {
			
			Entity entity1 = (Entity) (getOperandAt(1).getValue());
			
			return entity1.getRealValue().getVel().getY();
			
		}

		@Override
		public Expression getValue() {
			
			return new DoubleLiteral(getRealValue());
		}
	

	
		}