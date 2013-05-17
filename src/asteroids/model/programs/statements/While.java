package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.BooleanRepresentation;
import asteroids.model.programs.expressions.Entity;

public class While extends StructuralStatement {

	private BooleanRepresentation condition;
	private Statement body;
	
	public While(BooleanRepresentation condition, Statement body)throws IllegalOperandException{
		setOperandAt(1, condition);
		setOperandAt(2, body);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index ==1)
			return condition;
		if(index == 2)
			return body;
		throw new IndexOutOfBoundsException();
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
			this.condition = (BooleanRepresentation) operand;
		if(index == 2)
			this.body = (Statement) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand.getClass().isAssignableFrom(BooleanRepresentation.class))
				return true;
			if(index == 2 && operand.getClass().isAssignableFrom(Statement.class))
				return true;
		}
		return false;
	}

	@Override
	public void execute() {
		while(((BooleanRepresentation) getOperandAt(1)).getJavaBoolean()){
			((Statement) getOperandAt(2)).execute();
		}
	}
	@Override 
	public String toString(){
		return "While " + getOperandAt(1) + " do " + getOperandAt(2);
	}
	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		((Statement) getOperandAt(2)).setShip(ship);
	}

}
