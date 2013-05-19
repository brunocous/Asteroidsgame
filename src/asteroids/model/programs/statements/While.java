package asteroids.model.programs.statements;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Entity;

public class While extends StructuralStatement {

	private Expression condition;
	private Statement body;
	
	public While(Expression condition, Statement body)throws IllegalOperandException{
		this.condition = condition;
		this.body = body;
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index ==1)
			return getCondition();
		if(index == 2)
			return getBody();
		throw new IndexOutOfBoundsException();
	}
	public Expression getCondition(){
		return condition;
	}
	public Statement getBody(){
		return body;
	}

	@Override
	public int getNbOperands() {
		return 2;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		if(index == 1)
			this.condition = (Expression) operand;
		if(index == 2)
			this.body = (Statement) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand.getClass().isAssignableFrom(Expression.class))
				return ((Expression) operand).getRealValue().getClass().isAssignableFrom(boolean.class);
			if(index == 2 && operand.getClass().isAssignableFrom(Statement.class))
				return true;
		}
		return false;
	}

	@Override
	public void execute() {
		while((boolean) getCondition().getRealValue()){
			getBody().execute();
		}
	}
	@Override 
	public String toString(){
		return "While " + getOperandAt(1) + " do " + getOperandAt(2);
	}
	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		getCondition().setShip(ship);
		getBody().setShip(ship);
	}
	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(1, getCondition())
				&& getBody().isTypeChecked();
	}

}
