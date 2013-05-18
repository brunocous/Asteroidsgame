package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;



public class Cosine extends UnaryExpression{
		
	
		
	public Cosine(Expression argument) throws IllegalOperandException{
		
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
			
			return constant1.getRealValue();
			
		}

		@Override
		public Expression getValue() {
			
			new DoubleLiteral(getRealValue());
		}
	

	
		}
