package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.*;

public abstract class StructuralStatement extends Statement implements IComposedStructure{

	public boolean hasAsSubEntry(IEntry entry){
		if (entry == this)
			return true;
		for (int pos = 1; pos <= this.getNbOperands(); pos++)
			if (this.getOperandAt(pos).hasAsSubEntry(entry))
				return true;
		return false;
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry entry){
		return (entry != null) && (!entry.hasAsSubEntry(this)) && (index > 0);
	}
	@Override
	public void setOperandAt(int index, IEntry operand) throws IllegalOperandException{
		if(canHaveAsOperandAt(index, operand))
			this.setOperandAt(index, operand);
		
	}
}
