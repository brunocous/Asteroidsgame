package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.type.Type;

public abstract class Comparison extends BinaryExpression {

	public final static Type TYPE = Type.BOOLEAN;
	
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
	public Type getType(){
		return TYPE;
	}

	@Override
	public boolean isTypeChecked(){
		return (( getOperandAt(1).getType() == Type.DOUBLE
				&& getOperandAt(2).getType() == Type.DOUBLE)
				|| ( getOperandAt(1).getType() == Type.BOOLEAN
						&& getOperandAt(2).getType() == Type.BOOLEAN)
				|| ( getOperandAt(1).getType() == Type.ANY
						&& getOperandAt(2).getType()== Type.ANY));
	}





}
