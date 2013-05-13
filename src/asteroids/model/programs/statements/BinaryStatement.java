package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Raw;
import asteroids.model.programs.IBinaryOperator;
import asteroids.model.programs.expressions.Expression;

public abstract class BinaryStatement extends ComposedStatement implements
		IBinaryOperator {

	@Override
	public abstract void execute();

	@Raw
	public abstract boolean canHaveAsStatement(Statement statement);

	@Raw
	public abstract boolean hasAsSubStatement(Statement statement);
	
}
