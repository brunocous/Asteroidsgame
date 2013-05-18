package asteroids.model.programs.expressions;





public class Cosine extends UnaryMath{
		
		
	public Cosine(Expression argument) {
		
		super(argument);
		
	}
	

		@Override
		public Double getRealValue() {
			
			DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
			
			return Math.cos(constant1.getRealValue());
			
		}

		
	
		}
