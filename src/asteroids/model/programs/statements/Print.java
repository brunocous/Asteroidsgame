package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.Expression;

public class Print extends StructuralStatement {

	private Expression expression;
	
	public Print(Expression expression)throws IllegalOperandException{
		setOperandAt(1,expression);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1) 
			return expression;
		throw new IndexOutOfBoundsException();
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
		if(index == 1 )
			this.expression = (Expression) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		return (super.canHaveAsOperandAt(index, operand)
				&& operand.getClass().isAssignableFrom(Expression.class)
				&& index == 1);
	}

	@Override
	public void execute() {
		System.out.println(getOperandAt(1));
	}
	
	@Override
	public String toString(){
		return "\n Printing the following: \n\t" + getOperandAt(1);
	}
	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		assert true;
		
	}

}
