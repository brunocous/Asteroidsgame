package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IBinaryStructure;
import asteroids.model.programs.IEntry;







public class Addition extends DoubleRepresentation implements IComposedStructure{
	
	private DoubleRepresentation leftExpression;
	private DoubleRepresentation rightExpression;


	public Addition(DoubleRepresentation leftExpression, DoubleRepresentation RightExpression){
	
		setOperandAt(2, rightExpression);
		setOperandAt(1, leftExpression);
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry expression) throws IllegalOperandException{
		if(canHaveAsOperandAt(index, expression)){
			if(index == 2){
				rightExpression = (DoubleRepresentation) expression;
			}
			if(index == 1){
				leftExpression = (DoubleRepresentation) expression;
			}
		}
		else{
			throw new IllegalOperandException();
		}
	
	}
	
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(!DoubleRepresentation.class.isAssignableFrom(expression.getClass())){
			return false;
		}
		else{
		return true; 
		//TODO implementeren
		}
	}
	
	
	
	
	public DoubleRepresentation getFirstOperand(){
		return leftExpression;
	}
	
	public DoubleRepresentation getSecondOperand(){
		return rightExpression;
	}
	
	@Override
	public double getJavaDouble(){
		
		return getLeftExpression().getJavaDouble()+getRightExpression().getJavaDouble();
			
	}

	@Override
	public boolean hasAsSubEntry(IEntry expression){
		return (expression == getLeftExpression() || expression == getRightExpression());
	}
}

