package asteroids.model.programs.expressions;


import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.model.programs.type.*;

import asteroids.model.programs.IEntry;



public class Negation extends UnaryExpression{

	public static final Type TYPE = Type.BOOLEAN;
	
	public Negation(Expression argument) {
		
		super(argument);
		
	}
	
	@Override
	@Basic
	public Type getType(){
		return TYPE;
	}
	@Override
	@Raw
	public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			return true;
			}
			else{
				return false;
				
			}
			
	
	}
		
	

		@Override
		public Boolean getRealValue() {
			
			BooleanLiteral bool = (BooleanLiteral) (getOperandAt(1).getValue());
			
			return !bool.getRealValue();
			
		}

		@Override
		public Expression getValue() {
			
			return new BooleanLiteral(getRealValue());
		}
	
		@Override
		public boolean isTypeChecked(){
			return getOperandAt(1).getType() == Type.BOOLEAN
					&& getOperandAt(1).isTypeChecked();
		}
	
		}
