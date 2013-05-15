package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;

import asteroids.model.programs.IEntry;

import asteroids.model.programs.IComposedStructure;


public abstract class UnaryBooleanRepresentation extends BooleanRepresentation implements IComposedStructure{

private IEntry argument;
	
	public UnaryBooleanRepresentation(IEntry argument) throws IllegalOperandException{
		
		setOperandAt(1, argument);
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry argument) throws IllegalOperandException{
		
		if(canHaveAsOperandAt(index,argument)){
			
			this.argument = argument;
		}
		else{
			throw new IllegalOperandException();
		}
	}
	
	public abstract boolean canHaveAsOperandAt(int index, IEntry argument);




	@Override
	public boolean hasAsSubEntry(IEntry expression) {
	
		return argument.equals(expression);
	}

	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {

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

