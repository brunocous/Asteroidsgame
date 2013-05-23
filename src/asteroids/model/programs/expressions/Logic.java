package asteroids.model.programs.expressions;

import asteroids.model.programs.type.*;

import asteroids.model.programs.IEntry;

public abstract class Logic extends BinaryExpression {

	public static final Type TYPE = Type.BOOLEAN;
	
	public Logic(Expression leftExpression, Expression rightExpression){
		
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
		return ( getOperandAt(1).getType() == Type.BOOLEAN
				&& getOperandAt(2).getType()== Type.BOOLEAN)
				&& getOperandAt(1).isTypeChecked()
				&& getOperandAt(2).isTypeChecked();
	}
	
	@Override
	public Type getType(){
		return TYPE;
	}
}
