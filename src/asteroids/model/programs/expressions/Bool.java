
	package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



	public class Bool extends BooleanRepresentation{

		private boolean value;
		
		
		public Bool(boolean value) {
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



