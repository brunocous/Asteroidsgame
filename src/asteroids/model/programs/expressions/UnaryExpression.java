package asteroids.model.programs.expressions;


import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public abstract class UnaryExpression extends Expression implements IComposedStructure {

	private Expression argument;
	
	public UnaryExpression(IEntry argument){
		
		setOperandAt(1, argument);
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry argument){
		
		if(canHaveAsOperandAt(index,argument) && Expression.class.isAssignableFrom(argument.getClass())){
			
			this.argument = (Expression) argument;
		}
		else{
			
		}
	}
	


	@Override
	public boolean hasAsSubEntry(IEntry expression) {
	
		return argument.equals(expression);
	}

	
	@Override
	public Expression getOperandAt(int index) throws IndexOutOfBoundsException {

		if(index ==1){
			return argument;
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public int getNbOperands() {
		
		return 1;
	}

}
