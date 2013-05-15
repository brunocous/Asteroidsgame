package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class Substraction extends DoubleRepresentation implements IComposedStructure{
	
	private DoubleRepresentation leftExpression;
	private DoubleRepresentation rightExpression;


	public Substraction(DoubleRepresentation leftExpression, DoubleRepresentation RightExpression) throws IllegalOperandException{
	
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
	
	public IEntry getFirstOperand(){
		return leftExpression;
	}
	
	public IEntry getSecondOperand(){
		return rightExpression;
	}
	
	@Override
	public double getJavaDouble(){
		
		return ((DoubleRepresentation)getOperandAt(1)).getJavaDouble()-((DoubleRepresentation)getOperandAt(2)).getJavaDouble();
			
	}

	@Override
	public boolean hasAsSubEntry(IEntry expression){
		return (expression == getOperandAt(1) || expression == getOperandAt(2));
	}

	@Override
	public IEntry getOperandAt(int index) {
		if(index ==1){
			return getFirstOperand();
		}
		else if(index ==2){
			return getSecondOperand();
		}
		else{
			return null;
		}
	}

	@Override
	public int getNbOperands() {
		// TODO Auto-generated method stub
		return 2;
	}
}
