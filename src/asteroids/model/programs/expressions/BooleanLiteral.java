
	package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



	public class BooleanLiteral extends Expression{

		private boolean value;
		
		
		public BooleanLiteral(boolean value) {
			this.value = (value);
		}
		


	@Override
	public Boolean getRealValue(){
		return value;
	}
	
	@Override
	public Expression getValue(){
		return new BooleanLiteral(getRealValue());
	}

	@Override
	public boolean hasAsSubEntry(IEntry expression) {
		
		return expression.equals(this);
	}
	
	


	}



