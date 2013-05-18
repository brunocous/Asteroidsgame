package asteroids.model.programs.expressions;


import asteroids.model.programs.IEntry;



public class GetX extends UnaryExpression{
		
	
		
	public GetX(Expression argument) {
		
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
			
			return entity1.getRealValue().getPos().getX();
			
		}

		@Override
		public Expression getValue() {
			
			return new DoubleLiteral(getRealValue());
		}
	

	
		}
