package asteroids.model.programs.expressions;


import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public abstract class BinaryExpression extends Expression implements IComposedStructure{
	
	private Expression argument1;
	private Expression argument2;
	
	public BinaryExpression(Expression argument1, Expression argument2) {
		
		setOperandAt(1, argument1);
		setOperandAt(2, argument2);
		
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry argument){
		
		if(canHaveAsOperandAt(index,argument) && Expression.class.isAssignableFrom(argument.getClass())){
			
			if(index ==1){
			this.argument1 = (Expression) argument;
			}
			else if(index==2){
			this.argument2 = (Expression) argument;
			}
			
		}
		else{
			
		}
	}




	@Override
	public boolean hasAsSubEntry(IEntry expression) {
	
		return (argument1.equals(expression) || argument2.equals(expression));
	}

	
	@Override
	public Expression getOperandAt(int index) throws IndexOutOfBoundsException {

		if(index ==1){
			return argument1;
		}
		else if(index ==2){
			return argument2;
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public int getNbOperands() {
		
		return 2;
	}


	

}
