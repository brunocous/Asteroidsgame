package asteroids.model.programs.statements;

import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public abstract class ActionStatement extends Statement implements IComposedStructure {

	@Override
	public boolean equals(Object other) {
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		ActionStatement otherStat = (ActionStatement) other;
		if (getNbOperands() != otherStat.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).equals(otherStat.getOperandAt(pos)))
				return false;
		return true;
	}
	@Override
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
