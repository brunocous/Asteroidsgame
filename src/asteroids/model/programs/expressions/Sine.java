package asteroids.model.programs.expressions;





public class Sine extends UnaryMath{
		
	
		
	public Sine(Expression argument) {
		
		super(argument);
		
	}
	

		@Override
		public Double getRealValue() {
			
			DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
			
			return Math.sin(constant1.getRealValue());
			
		}

	

	
		}