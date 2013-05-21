package asteroids.model.programs.expressions;


import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;

public abstract class UnaryExpression extends Expression implements IComposedStructure {

	private Expression argument;
	
	public UnaryExpression(Expression argument){
		
		this.argument = argument;
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry argument){
		
		if(canHaveAsOperandAt(index,argument) && argument instanceof Expression){
			
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
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		getOperandAt(1).setProgram(program);
		
		if(getOperandAt(1) instanceof Variable){
			setOperandAt(1, program.getVariable(((Variable)getOperandAt(1)).getName()));
		}
	}

}
