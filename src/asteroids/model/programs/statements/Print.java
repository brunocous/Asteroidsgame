package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;

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
		System.out.println(getExpression().getRealValue());
		return false;
	}
	
	@Override
	public String toString(){
		return "\n Printing the following: \n\t" + getExpression();
	}
	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(1, getExpression());
	}

	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		
		if (getExpression() != null) {
			getExpression().setProgram(program);

			if (getExpression() instanceof Variable) {
				try{setOperandAt(1,program.getVariable(((Variable) getExpression()).getName()));
				
				}catch(IllegalOperandException e){
					assert!(canHaveAsOperandAt(1, program.getVariable(((Variable) getExpression()).getName())));
				}
			}
		}
	}
}
