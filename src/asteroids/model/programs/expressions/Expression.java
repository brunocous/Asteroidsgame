package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;




public abstract class Expression implements IEntry{


	 
	public abstract boolean hasAsSubEntry(Expression expression);

	public abstract Expression getValue();


	public abstract boolean equals(Expression other);

	
}
