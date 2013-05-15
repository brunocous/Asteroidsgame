package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.BooleanRepresentation;

public class IfThenElse extends StructuralStatement {

	private BooleanRepresentation condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public IfThenElse(BooleanRepresentation condition, Statement ifBody, Statement elseBody) throws IllegalOperandException{
		setOperandAt(1,condition);
		setOperandAt(2,ifBody);
		setOperandAt(3,elseBody);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return condition;
		if(index == 2)
			return ifBody;
		if(index == 3)
			return elseBody;
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNbOperands() {
		return 3;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)throws IllegalOperandException{
			if(!canHaveAsOperandAt(index,operand))
				throw new IllegalOperandException();
			if(index == 1)
				this.condition = (BooleanRepresentation) operand;
			if(index == 2)
				this.ifBody = (Statement) operand;
			if(index == 3)
				this.elseBody = (Statement)	operand;
	}

	@Override
	public void execute() {
		if(((BooleanRepresentation) getOperandAt(1)).getJavaBoolean()){
			((Statement) getOperandAt(2)).execute();
		}else{
			((Statement) getOperandAt(3)).execute();
		}

	}

	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand.getClass().isAssignableFrom(BooleanRepresentation.class))
				return true;
			if(index == 2 && operand.getClass().isAssignableFrom(Statement.class))
				return true;
			if(index == 3 && operand.getClass().isAssignableFrom(Statement.class))
				return true;
		}
			return false;
	}
	@Override
	public String toString(){
		return "If " + getOperandAt(1) + ", then \n" + getOperandAt(2) + ". \nElse " + getOperandAt(3) + ".";
	}
}
