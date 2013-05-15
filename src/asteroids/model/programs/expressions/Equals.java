package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class Equals extends BinaryBooleanRepresentation implements IComposedStructure{
	

	public Equals(Expression leftExpression, Expression rightExpression) throws IllegalOperandException{
	
		super(leftExpression, rightExpression);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		else{
		if(index ==1 && getOperandAt(2) == null){
			return true;
		}
		else if(index ==1 && expression.getClass().isAssignableFrom(getOperandAt(2).getClass())){
			return true;
		}
		else if(index ==2 && getOperandAt(1) == null){
			return true;
		}
		else if(index ==2 &&expression.getClass().isAssignableFrom(getOperandAt(2).getClass())){
			return true;
		}
		else{
		return false; 
		
		}
		}
	}
	

	
	@Override
	public boolean getJavaBoolean(){
		
		return (getOperandAt(1).equals(getOperandAt(2)));
			
	}

	public String toString(){
		return "getJavaDouble()";
	}



}
