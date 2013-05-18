package asteroids.model.programs.expressions;


import asteroids.model.programs.IEntry;

public class LogicOr extends BinaryExpression {
	

	public LogicOr(Expression leftExpression, Expression rightExpression){
	
		super(leftExpression, rightExpression);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		
		else{
		return true; 
		}
		}
	
	

	public Boolean getRealValue(){
		
		BooleanLiteral constant1 = (BooleanLiteral) (getOperandAt(1).getValue());
		BooleanLiteral constant2 = (BooleanLiteral) (getOperandAt(2).getValue());
		return (constant1).getRealValue() || (constant2).getRealValue();
			
	}

	public Expression getValue(){
		
		return new BooleanLiteral(getRealValue());
	}


}
