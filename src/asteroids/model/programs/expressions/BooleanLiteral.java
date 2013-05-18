
	package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



	public class BooleanLiteral extends BasicExpression{

		private boolean value;
		
		
		public BooleanLiteral(boolean value) {
			super(value);
		}
		


	@Override
	public Boolean getRealValue(){
		return value;
	}
	
	@Override
	public Expression getValue(){
		return new BooleanLiteral(getRealValue());
	}



	}



