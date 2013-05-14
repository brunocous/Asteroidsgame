package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;




public abstract class Expression implements Cloneable,IEntry{


	 
	public abstract boolean hasAsSubExpression(Expression expression);

	public abstract Expression getValue();


	public abstract boolean isMutable();

	@Override
	public abstract Expression clone();
	@Override
	public abstract boolean equals(Expression other);

	
}
