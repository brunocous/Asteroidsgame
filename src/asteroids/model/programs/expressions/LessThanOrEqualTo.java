package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class LessThanOrEqualTo extends BinaryBooleanRepresentation implements IComposedStructure{
	

	public LessThanOrEqualTo(DoubleRepresentation leftExpression, DoubleRepresentation rightExpression) throws IllegalOperandException{
	
		super(leftExpression, rightExpression);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		else{
		if(DoubleRepresentation.class.isAssignableFrom(expression.getClass())){
			return true;
		}
		else{
			return false;
		}
		}
	}
	

	
	@Override
	public boolean getJavaBoolean(){
		
		return (Util.fuzzyLessThanOrEqualTo(((DoubleRepresentation)getOperandAt(1)).getJavaDouble(),((DoubleRepresentation)getOperandAt(2)).getJavaDouble()));
			
	}

	public String toString(){
		return "getJavaBoolean()";
	}



}
