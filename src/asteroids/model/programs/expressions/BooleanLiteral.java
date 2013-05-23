
	package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Value;
import asteroids.model.programs.type.*;



@Value
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



