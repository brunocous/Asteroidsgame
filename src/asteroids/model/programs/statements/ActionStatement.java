package asteroids.model.programs.statements;

public abstract class ActionStatement extends Statement {

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
}
