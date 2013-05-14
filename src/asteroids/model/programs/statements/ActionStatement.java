package asteroids.model.programs.statements;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;

public abstract class ActionStatement extends Statement {

	}

	@Override
	public Expression clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Statement other) {
		// TODO Auto-generated method stub
		return false;
	}

	public abstract void execute();

}
