
	package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



	public class BooleanLiteral extends BooleanRepresentation{

		private boolean value;
		
		
		public BooleanLiteral(boolean value) {
			this.value = (value);
		}
		


	@Override
	public boolean getJavaBoolean(){
		return value;
	}
	


	@Override
	public boolean hasAsSubEntry(IEntry expression) {
		
		return expression.equals(this);
	}
	
	


	}



