package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public class Division extends BinaryMath{
	

	public Division(Expression leftExpression, Expression rightExpression){
	
		super(leftExpression, rightExpression);
		
	}
	

	
	@Override
	public Double getRealValue(){
		
		DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
		DoubleLiteral constant2 = (DoubleLiteral) (getOperandAt(2).getValue());
		return (constant1).getRealValue()/(constant2).getRealValue();
	}

	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands())
			return false;
		else return true;
		
		
	}
	
}
	
	




