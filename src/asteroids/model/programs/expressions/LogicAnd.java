package asteroids.model.programs.expressions;


import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class LogicAnd extends BinaryBooleanRepresentation implements IComposedStructure{
	

	public LogicAnd(BooleanRepresentation leftExpression, BooleanRepresentation rightExpression) throws IllegalOperandException{
	
		super(leftExpression, rightExpression);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry expression){
		
		if(index > getNbOperands()){
			return false;
		}
		else{
		if(BooleanRepresentation.class.isAssignableFrom(expression.getClass())){
			return true;
		}
		else{
			return false;
		}
		}
	}
	

	
	@Override
	public boolean getJavaBoolean(){
		
		return (((BooleanRepresentation)getOperandAt(1)).getJavaBoolean()&&((BooleanRepresentation)getOperandAt(2)).getJavaBoolean());
			
	}

	


}