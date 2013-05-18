package asteroids.model.programs.expressions;

import asteroids.Util;


public class LessThanOrEqualTo extends Inequality {
	

	public LessThanOrEqualTo (Expression leftExpression, Expression rightExpression){
	
		super(leftExpression, rightExpression);
		
	}
	
	
	@Override
	public Boolean getRealValue(){
		
		DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
		DoubleLiteral constant2 = (DoubleLiteral) (getOperandAt(2).getValue());
		return (Util.fuzzyLessThanOrEqualTo(constant1.getRealValue(), constant2.getRealValue()));
			
	}


}
