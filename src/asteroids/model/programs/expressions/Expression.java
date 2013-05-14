package asteroids.model.programs.expressions;

import asteroids.model.programs.types.Type;


public abstract class Expression implements Cloneable{


	 
	public abstract boolean hasAsSubExpression(Expression expression);

	public abstract Expression getValue();


	public abstract boolean isMutable();

	@Override
	public abstract Expression clone();
	public abstract boolean equals(Expression other);

	
}
