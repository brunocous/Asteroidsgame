
	package asteroids.model.programs.expressions;

import asteroids.model.programs.type.*;




	public class BooleanLiteral extends BasicExpression{

		private boolean value;
		private static final Type TYPE = Type.BOOLEAN;
		
		public BooleanLiteral(boolean value) {
			this.value = (value);
		}
		
	@Override
	public Type getType(){
			return TYPE;
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



