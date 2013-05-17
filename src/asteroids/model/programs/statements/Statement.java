package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;

public abstract class Statement implements IEntry,IComposedStructure {

	public abstract void execute();
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry entry){
		return  entry != null && (!entry.hasAsSubEntry(this)) && (index > 0);
	}
	public abstract void setShip(Entity ship) throws IllegalOperandException;

	public boolean hasAsSubEntry(IEntry entry){
		if (entry == this)
			return true;
		for (int pos = 1; pos <= this.getNbOperands(); pos++){
			if(this.getOperandAt(pos) ==  null)
				return false;
			if (this.getOperandAt(pos).hasAsSubEntry(entry))
				return true;
		}
		return false;
	}
}
