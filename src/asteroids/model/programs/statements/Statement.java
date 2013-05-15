package asteroids.model.programs.statements;

import deel3.les8.opl.expressions.Expression;
import asteroids.model.programs.IEntry;

public abstract class Statement implements IEntry {
	
	@Override
	public abstract boolean equals(Object other);

	public abstract void execute();
	
}
