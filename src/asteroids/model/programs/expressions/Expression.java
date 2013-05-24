package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Util;
import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.type.Type;

public abstract class Expression implements IEntry {

	private Program program = null;

	public abstract Object getRealValue();

	public abstract Expression getValue();

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof Expression)) {
			return false;
		} else {

			if (!(this.getType() == ((Expression) other).getType())) {
				return false;
			} else {
				if (this.getType() == Type.BOOLEAN) {
					return this.getValue().getRealValue() == ((Expression) other)
							.getValue().getRealValue();
				} else if (this.getType() == Type.DOUBLE) {
					return Util.fuzzyEquals((Double) (this.getValue()
							.getRealValue()), (Double) (((Expression) other)
							.getValue().getRealValue()));
				} else if (this.getType() == Type.ANY) {
					return this.getValue().getRealValue() == ((Expression) other)
							.getValue().getRealValue();
				} else
					return false;
			}
		}
	}

	@Immutable
	@Basic
	public abstract Type getType();

	public String toString() {

		if (getValue().getType() == Type.DOUBLE) {
			return Double.toString((Double) getRealValue());
		} else if (getValue().getType() == Type.BOOLEAN) {
			return String.valueOf((Boolean) getRealValue());
		} else if (getValue().getType() == Type.ANY) {
			return ((SpaceObject) getRealValue()).toString();
		} else {
			return null;
		}
	}

	public abstract boolean isTypeChecked();

	@Basic
	public Program getProgram() {
		return program;
	}

	@Basic
	public void setProgram(@Raw Program program) {
		if (canHaveAsProgram(program))
			this.program = program;
	}

	@Raw
	public boolean canHaveAsProgram(Program program) {
		return (getProgram() == null) ? true : false;
	}

}