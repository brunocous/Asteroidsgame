
	package asteroids.model.programs.expressions;

import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;

	public class Bool extends BasicExpression{

		private boolean value;
		private Type type = new BooleanLiteral();
		
		public Bool(boolean value) {
			this.value = (value);
		}
		

	@Override
	public Expression getValue(){
			
			return this;
	}	

	public boolean getBooleanValue(){
		return value;
	}
	@Override
	public boolean isMutable() {
		
		return false;
	}



	@Override
	public String toString() {
		
		return null;
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return expression == this;
	}


	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public boolean equals(Expression other) {
		
		if(other.getType() == getType()){
		return ((Bool)other).getBooleanValue() == getBooleanValue();
		}
		else{
			return false;
		}

	}
	}



