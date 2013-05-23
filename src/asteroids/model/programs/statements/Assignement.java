package asteroids.model.programs.statements;

import asteroids.Error.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Null;
import asteroids.model.programs.expressions.Variable;

public class Assignement extends StructuralStatement {

	private Expression newValue;
	private String variableName;

	public Assignement(String variableName, Expression newValue) {

		this.newValue = newValue;
		this.variableName = variableName;

	}

	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if ((index == 1))
			return getNewValue();
		throw new IndexOutOfBoundsException();
	}

	@Override
	public boolean execute() {
		getProgram().getVariable(getVariableName()).setValue(getNewValue());
		return false;
	}

	@Override
	public int getNbOperands() {
		return 1;
	}

	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		if (super.canHaveAsOperandAt(index, operand) && operand != null)
			if (index == 1
					&& operand instanceof Expression)
				return true;
		return false;

	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if (!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		else if (index == 1)
			this.newValue = (Expression) operand;
	}

	@Override
	public String toString() {
		return getVariableName() + " is set to " + getOperandAt(1) + ".";
	}

	@Override
	public boolean isTypeChecked() {
		if(getNewValue() instanceof Null)
			return true;
		return (getProgram().getVariable(getVariableName()).getType().getGeneralType() 
				== getNewValue().getType().getGeneralType() 
				&& getNewValue().isTypeChecked()) ? true : false;
	}

	public Expression getNewValue() {
		return newValue;
	}

	public String getVariableName() {
		return variableName;
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		
		if(!program.hasVariable(getVariableName()))
			program.addAsVariable(new Variable(getVariableName(), getNewValue().getType()));
		if (getNewValue() != null) {
			getNewValue().setProgram(program);

			if (getNewValue() instanceof Variable) {
				try {
					setOperandAt(1,program.getVariable(((Variable) getNewValue()).getName()));
				} catch (IllegalOperandException e) {
					assert !canHaveAsOperandAt(1, program.getVariable(((Variable) getNewValue()).getName()));
				}
			}
		}
	}
}
