package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.Error.IllegalOperandException;
import asteroids.model.*;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.type.Type;

public class Turn extends ShipActionStatement {

	private Expression amount;
	
	public Turn( Expression amount){
		this.amount = amount;
	}
	
	@Override 
	public boolean isTypeChecked(){
		return getAmount().getType() == Type.DOUBLE;
	}
	@Override
	@Basic
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getShip();
		if(index == 2)
			return getAmount();
		throw new IndexOutOfBoundsException();
	}

	@Override
	public boolean execute() {
		Ship ship = (Ship) getShip().getRealValue();
			ship.turn((Double) getAmount().getRealValue());
			return true;
	}

	@Override
	@Raw
	public boolean canHaveAsOperandAt( int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand) && index == 2
				&& operand instanceof Expression)
				return ((Expression) operand).getType() == Type.DOUBLE;
		return false;
	}
	@Override
	@Basic
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(index == 2 && canHaveAsOperandAt(index, operand))
			this.amount = (Expression) operand;
	}

	@Override
	public String toString(){
		return getOperandAt(1) + " turns with an amount of " + getOperandAt(2) + " radians.";
	}
	public Expression getAmount(){
		return amount;
	}
	
	@Override
	@Basic
	public void setProgram(Program program){
		super.setProgram(program);
		getAmount().setProgram(program);
		
		if(getOperandAt(2) instanceof Variable){
			try{setOperandAt(2, program.getVariable(((Variable)getOperandAt(2)).getName()));
			
			} catch(IllegalOperandException e){
				assert !canHaveAsOperandAt(2, program.getVariable(((Variable)getOperandAt(1)).getName()));
			}
		}
	}
}
