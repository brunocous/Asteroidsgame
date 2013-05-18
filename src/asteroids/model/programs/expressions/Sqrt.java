package asteroids.model.programs.expressions;





public class Sqrt extends UnaryMath{
		
	
		
	public Sqrt(Expression argument) {
		
		super(argument);
		
	}

	@Override
	public Double getRealValue() {
			
			DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
			
			return Math.sqrt(constant1.getRealValue());
			
		}

	

	
		}