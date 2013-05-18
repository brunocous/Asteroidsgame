package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public abstract class BinaryExpression extends DoubleRepresentation implements IComposedStructure{
	
	private IEntry argument1;
	private IEntry argument2;
	
	public BinaryExpression(IEntry argument1, IEntry argument2) throws IllegalOperandException{
		
		setOperandAt(1, argument1);
		setOperandAt(2, argument2);
		
		
	}
	
	@Override
	public void setOperandAt(int index, IEntry argument){
		
		if(canHaveAsOperandAt(index,argument)){
			
			if(index ==1){
			this.argument1 = argument;
			}
			else if(index==2){
			this.argument2 = argument;
			}
			
		}
		else{
			
		}
	}
	
	public abstract boolean canHaveAsOperandAt(int index, IEntry argument);




	@Override
	public boolean hasAsSubEntry(IEntry expression) {
	
		return (argument1.equals(expression) || argument2.equals(expression));
	}

	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {

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
