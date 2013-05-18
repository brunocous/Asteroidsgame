package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.model.programs.IEntry;

public class Division extends BinaryExpression{
	

	public Division(Expression leftExpression, Expression rightExpression){
	
		super(leftExpression, rightExpression);
		
	}
	

	

	public Double getRealValue(){
		
		DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
		DoubleLiteral constant2 = (DoubleLiteral) (getOperandAt(2).getValue());
		return (constant1).getRealValue()/(constant2).getRealValue();
			
	}

	public Expression getValue(){
		
		return new DoubleLiteral(getRealValue());
	}




	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		else if(index==2 && Util.fuzzyEquals(((DoubleLiteral)(((Expression)expression).getValue())).getRealValue(),0)){
			return false;
		}
		else{
			return true;
		}
		
		
		}
}
	




