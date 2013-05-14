package asteroids.model.programs.statements;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;

public abstract class Statement implements IEntry,Cloneable {

	@Override
	public boolean equals(IEntry obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public abstract Expression clone();

}
