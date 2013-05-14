package asteroids.model.programs.statements;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.*;

public abstract class StructuralStatement extends Statement implements IComposedStructure{

	@Override
	public boolean equals(IEntry other) {
		// TODO Auto-generated method stub
		return false;
	}

	public abstract void execute();


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
	
}
