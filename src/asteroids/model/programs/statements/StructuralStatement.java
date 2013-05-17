package asteroids.model.programs.statements;

import asteroids.model.Ship;
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
	public boolean equals(Object other){
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		StructuralStatement otherStat = (StructuralStatement) other;
		if (getNbOperands() != otherStat.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).equals(otherStat.getOperandAt(pos)))
				return false;
		return true;
	}


}
