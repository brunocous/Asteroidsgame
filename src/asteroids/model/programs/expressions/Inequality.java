package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.model.programs.type.*;

import asteroids.model.programs.IEntry;

public abstract class Inequality extends BinaryExpression{
	
	public final static Type TYPE = Type.BOOLEAN;
	
	public Inequality (Expression leftExpression, Expression rightExpression){
	
		super(leftExpression, rightExpression);
		
	}
	
	@Override
	@Raw
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		
		else{
		return true; 
		}
		}
	
	@Override
	public abstract Boolean getRealValue();

	@Override
	public Expression getValue(){
		
		return new BooleanLiteral(getRealValue());
	}
	
	@Override
	@Basic
	public Type getType(){
		return TYPE;
	}

	@Override
	public boolean isTypeChecked(){
		return ( getOperandAt(1).getType() == Type.DOUBLE
				&& getOperandAt(2).getType() == Type.DOUBLE)
				&& getOperandAt(1).isTypeChecked()
				&& getOperandAt(2).isTypeChecked();
	}

}
