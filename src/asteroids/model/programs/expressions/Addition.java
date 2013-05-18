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
		//TODO implementeren
		}
		}
	
	

	
	@Override
	public double getJavaDouble(){
		
		return ((DoubleRepresentation)getOperandAt(1)).getJavaDouble()+((DoubleRepresentation)getOperandAt(2)).getJavaDouble();
			
	}

	


}

