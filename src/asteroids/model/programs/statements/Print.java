package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;

public class Print extends StructuralStatement {

	private Expression expression;
	
	public Print(Expression expression){
		this.expression = expression;
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1) 
			return getExpression();
		throw new IndexOutOfBoundsException();
	}
	public Expression getExpression(){
		return expression;
	}

	@Override
	public int getNbOperands() {
		return 1;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		if(index == 1)
			this.expression = (Expression) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		return (super.canHaveAsOperandAt(index, operand)
				&& operand instanceof Expression)
				&& index == 1;
	}

	@Override
	public boolean execute() {
		System.out.println(getExpression());
		return false;
	}
	
	@Override
	public String toString(){
		return "\n Printing the following: \n\t" + getExpression();
	}
	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		getExpression().setShip(ship);
		
	}
	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(1, getExpression());
	}

}
