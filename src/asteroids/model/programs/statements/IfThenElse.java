package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.type.Type;

public class IfThenElse extends StructuralStatement {

	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public IfThenElse(Expression condition, Statement ifBody, Statement elseBody){
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	@Override
	@Basic
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getCondition();
		if(index == 2)
			return getIfBody();
		if(index == 3)
			return getElseBody();
		throw new IndexOutOfBoundsException();
	}
	@Basic
	public Expression getCondition(){
		return condition;
	}
	@Basic
	public Statement getIfBody(){
		return ifBody;
	}
	@Basic
	public Statement getElseBody(){
		return elseBody;
	}

	@Override
	public int getNbOperands() {
		return 3;
	}

	@Override
	@Basic
	public void setOperandAt(int index, IEntry operand)throws IllegalOperandException{
			if(!canHaveAsOperandAt(index,operand))
				throw new IllegalOperandException();
			if(index == 1)
				this.condition = (Expression) operand;
			if(index == 2)
				this.ifBody = (Statement) operand;
			if(index == 3)
				this.elseBody = (Statement)	operand;
	}

	@Override
	public boolean execute() {
		if((boolean) getCondition().getRealValue()){
			return getIfBody().execute();
		}else{
			return getElseBody().execute();
		}

	}

	@Override
	@Raw
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand instanceof Expression)
				return ((Expression) operand).getType() == Type.BOOLEAN;
			
			if((index == 2 || index == 3) && operand instanceof Statement)
				return true;
		}
			return false;
	}
	@Override
	public String toString(){
		return "If " + getOperandAt(1) + ", then \n" + getOperandAt(2) + ". \nElse " + getOperandAt(3) + ".";
	}
	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(1, getCondition())
				&& canHaveAsOperandAt(2, getIfBody())
				&& canHaveAsOperandAt(3, getElseBody())
				&& getIfBody().isTypeChecked()
				&& getElseBody().isTypeChecked();
	}
	@Override
	@Basic
	public void setProgram(Program program){
		super.setProgram(program);
		getCondition().setProgram(program);
		getIfBody().setProgram(program);
		getElseBody().setProgram(program);
		if (getCondition() instanceof Variable) {
			try {
				setOperandAt(1,program.getVariable(((Variable) getCondition()).getName()));
			} catch (IllegalOperandException e) {
				assert !canHaveAsOperandAt(1,program.getVariable(((Variable) getCondition()).getName()));
			}
		}
	}
}
