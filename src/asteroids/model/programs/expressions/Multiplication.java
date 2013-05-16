package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class Multiplication extends BinaryDoubleRepresentation implements IComposedStructure{
	

	public Multiplication(DoubleRepresentation leftExpression, DoubleRepresentation rightExpression) throws IllegalOperandException{
	
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
		return true; 
		//TODO implementeren
		}
		}
	}
	

	
	@Override
	public double getJavaDouble(){
		
		return ((DoubleRepresentation)getOperandAt(1)).getJavaDouble()*((DoubleRepresentation)getOperandAt(2)).getJavaDouble();
			
	}


}
