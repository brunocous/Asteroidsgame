package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class Addition extends BinaryExpression implements IComposedStructure{
	

	public Addition(Expression leftExpression, Expression rightExpression){
	
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
	
	

	public double getRealValue(){
		
		Constant constant1 = (Constant) (getOperandAt(1).getValue());
		Constant constant2 = (Constant) (getOperandAt(2).getValue());
		return (constant1).getRealValue()+(constant2).getRealValue();
			
	}

	


}

