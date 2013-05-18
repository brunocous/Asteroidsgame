package asteroids.model.programs.expressions;



public class LogicAnd extends Logic {
	

	public LogicAnd(Expression leftExpression, Expression rightExpression){
	
		super(leftExpression, rightExpression);
		
	}

	

	public Boolean getRealValue(){
		
		BooleanLiteral constant1 = (BooleanLiteral) (getOperandAt(1).getValue());
		BooleanLiteral constant2 = (BooleanLiteral) (getOperandAt(2).getValue());
		return (constant1).getRealValue() && (constant2).getRealValue();
			
	}


}