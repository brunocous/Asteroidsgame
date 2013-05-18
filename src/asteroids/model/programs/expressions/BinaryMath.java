package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public abstract class BinaryMath extends BinaryExpression {
	
	public BinaryMath(Expression leftExpression, Expression rightExpression){
		
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
	
	

public abstract Double getRealValue();

	public Expression getValue(){
		
		return new DoubleLiteral(getRealValue());
	}

	@Override
	public boolean isTypeChecked(){
		return ( getOperandAt(1).getValue().getClass().isAssignableFrom(DoubleLiteral.class)
				&& getOperandAt(2).getValue().getClass().isAssignableFrom(DoubleLiteral.class));
	}


}
