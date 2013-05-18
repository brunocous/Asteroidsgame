package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public class NotEqualTo extends BinaryExpression{
	

	public NotEqualTo(Expression leftExpression, Expression rightExpression){
	
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
		
		if(getOperandAt(1).getClass().isAssignableFrom(BooleanLiteral.class)){
		BooleanLiteral bool1 = (BooleanLiteral) (getOperandAt(1).getValue());
		BooleanLiteral bool2 = (BooleanLiteral) (getOperandAt(2).getValue());
		return !(bool1).equals(bool2);
		}
		else if(getOperandAt(1).getClass().isAssignableFrom(DoubleLiteral.class)){
		DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
		DoubleLiteral constant2 = (DoubleLiteral) (getOperandAt(2).getValue());
		return !(constant1).equals(constant2);
		}
		else if(getOperandAt(1).getClass().isAssignableFrom(Entity.class)){
		Entity entity1 = (Entity) (getOperandAt(1).getValue());
		Entity entity2 = (Entity) (getOperandAt(2).getValue());
		return !(entity1).equals(entity2);
		}
		else{
			return false;
		}
				
	}

	public Expression getValue(){
		
		return new BooleanLiteral(getRealValue());
	}






}

