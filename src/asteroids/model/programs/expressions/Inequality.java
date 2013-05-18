package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public abstract class Inequality extends BinaryExpression{
	

	public Inequality (Expression leftExpression, Expression rightExpression){
	
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
	
	public abstract Boolean getRealValue();

	public Expression getValue(){
		
		return new BooleanLiteral(getRealValue());
	}


}