package asteroids.model.programs.expressions;


import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;

public abstract class BinaryExpression extends Expression implements IComposedStructure{
	
	private Expression argument1;
	private Expression argument2;
	
	public BinaryExpression(Expression argument1, Expression argument2) {
		

		setOperandAt(1, argument1);
		setOperandAt(2, argument2);
		
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry argument){
		
		if(canHaveAsOperandAt(index,argument) && argument instanceof Expression ){
			
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


	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		getOperandAt(1).setProgram(program);
		getOperandAt(2).setProgram(program);
		
		if(getOperandAt(1) instanceof Variable){
			setOperandAt(1, program.getVariable(((Variable)getOperandAt(1)).getName()));
		}
		if(getOperandAt(2) instanceof Variable){
			setOperandAt(2, program.getVariable(((Variable)getOperandAt(2)).getName()));
		}
	}


}
