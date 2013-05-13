package asteroids.model.programs.statements;

import asteroids.model.programs.Expression;

public class Assignement extends BinaryStatement {

	private Variable var;
	private Expression expression;
	
	public Assignement(Variable var, Expression expression){
		
	}
	@Override
	public int getNbOperands() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public Expression getOperandAt(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public protected void setOperandAt(int index, Expression operand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canHaveAsStatement(Statement statement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAsSubStatement(Statement statement) {
		// TODO Auto-generated method stub
		return false;
	}

}
