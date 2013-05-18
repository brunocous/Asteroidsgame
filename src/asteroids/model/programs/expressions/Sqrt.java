package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



public class Sqrt extends UnaryExpression{
		
	
		
	public Sqrt(Expression argument) {
		
		super(argument);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			return true;
			}
			else{
				return false;
				//TODO implementeren
			}
			
	
	}
		
	

		@Override
		public Double getRealValue() {
			
			DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
			
			return Math.sqrt(constant1.getRealValue());
			
		}

		@Override
		public Expression getValue() {
			
			return new DoubleLiteral(getRealValue());
		}
	

	
		}