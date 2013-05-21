package asteroids.model.programs.expressions;

import asteroids.model.programs.type.*;

import asteroids.model.programs.IEntry;

public abstract class Inequality extends BinaryExpression{
	
	public final static Type TYPE = Type.BOOLEAN;
	
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
	
	@Override
	public Type getType(){
		return TYPE;
	}

	@Override
	public boolean isTypeChecked(){
		return ( getOperandAt(1).getType() == Type.DOUBLE
				&& getOperandAt(2).getType() == Type.DOUBLE);
	}

}
