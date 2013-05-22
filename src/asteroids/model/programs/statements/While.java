package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.type.Type;

public class While extends StructuralStatement {

	private Expression condition;
	private Statement body;
	
	public While(Expression condition, Statement body){
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
			if(index == 1 && operand instanceof Expression)
				return ((Expression) operand).getType() == Type.BOOLEAN;
			if(index == 2 && operand instanceof Statement)
				return true;
		}
		return false;
	}

	@Override
	public boolean execute() {
		boolean encounterdActionStatement = false;
		while( !encounterdActionStatement && (boolean) getCondition().getRealValue()){
			encounterdActionStatement = getBody().execute();
		}
		return encounterdActionStatement;
	}
	@Override 
	public String toString(){
		return "While " + getOperandAt(1) + " do " + getOperandAt(2);
	}
	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(1, getCondition())
				&& getBody().isTypeChecked();
	}
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		getBody().setProgram(program);
		getCondition().setProgram(program);
		if (getCondition() instanceof Variable) {
			try {
				setOperandAt(1,program.getVariable(((Variable) getCondition()).getName()));
			} catch (IllegalOperandException e) {
				assert !canHaveAsOperandAt(1,program.getVariable(((Variable) getCondition()).getName()));
			}
		}
	}
}
