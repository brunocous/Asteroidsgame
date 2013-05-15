package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class Division extends BinaryDoubleRepresentation implements IComposedStructure{
	

	public Division(DoubleRepresentation leftExpression, DoubleRepresentation rightExpression) throws IllegalOperandException{
	
		super(leftExpression, rightExpression);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		else{
		if(!DoubleRepresentation.class.isAssignableFrom(expression.getClass())){
			return false;
		}
		else{
		if(index==2 && Util.fuzzyEquals(((DoubleRepresentation)expression).getJavaDouble(),0)){
			return false;
		}
		else{
			return true;
		}
		}
		
		}
	}
	

	
	@Override
	public double getJavaDouble(){
		
		return ((DoubleRepresentation)getOperandAt(1)).getJavaDouble()+((DoubleRepresentation)getOperandAt(2)).getJavaDouble();
			
	}

	public String toString(){
		return "getJavaDouble()";
	}



}


