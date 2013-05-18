package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public abstract class Comparison extends BinaryExpression {


	public Comparison(Expression leftExpression, Expression rightExpression){
	
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

	
	@Override
	public boolean isTypeChecked(){
		return (( getOperandAt(1).getValue().getClass().isAssignableFrom(DoubleLiteral.class)
				&& getOperandAt(2).getValue().getClass().isAssignableFrom(DoubleLiteral.class))
				|| ( getOperandAt(1).getValue().getClass().isAssignableFrom(BooleanLiteral.class)
						&& getOperandAt(2).getValue().getClass().isAssignableFrom(BooleanLiteral.class))
				|| ( getOperandAt(1).getValue().getClass().isAssignableFrom(Entity.class)
						&& getOperandAt(2).getValue().getClass().isAssignableFrom(Entity.class)));
	}





}
