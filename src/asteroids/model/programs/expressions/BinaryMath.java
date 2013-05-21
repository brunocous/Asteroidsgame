package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.type.Type;

public abstract class BinaryMath extends BinaryExpression {
	
	public final static Type TYPE = Type.DOUBLE;
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

@Override
public Type getType(){
	return TYPE;
}

public abstract Double getRealValue();

	public Expression getValue(){
		
		return new DoubleLiteral(getRealValue());
	}

	@Override
	public boolean isTypeChecked(){
		return ( getOperandAt(1).getType() == Type.DOUBLE
				&& getOperandAt(2).getType() == Type.DOUBLE);
	}


}
