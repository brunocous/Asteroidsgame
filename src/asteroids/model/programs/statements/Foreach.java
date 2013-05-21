package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.EntitySequence;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.type.Type;

public class Foreach extends StructuralStatement {

	private Expression entities = null;
	private Statement body;
	private final Type type;
	private String variableName;
	
	public Foreach(Type type, String variableName, Statement body){
		this.variableName = variableName;
		this.body = body;
		if(canHaveAsOperandAt(3, type))
			this.type = type;
		else this.type = null;
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getEntities();
		if(index == 2)
			return getBody();
		if(index == 4)
			return getType();
		throw new IndexOutOfBoundsException();
		
	}
	public Expression getEntities(){
		return entities;
	}
	public String getVariableName(){
		return variableName;
	}
	public Statement getBody(){
		return body;
	}
	public Type getType(){
		return type;
	}

	@Override
	public int getNbOperands() {
		return 3;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index,operand))
			throw new IllegalOperandException();
		if(index == 1)
			this.entities = (Expression) operand;
		if(index == 2)
			this.body = (Statement) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand instanceof EntitySequence)
				return true;
			if(index == 2 && operand instanceof StructuralStatement){
				return true;
			}
			if(index == 4 && operand instanceof Type)
				return Type.isValidType((Type) operand);
		}
			return false;
	}

	@Override
	public boolean execute() {
		for(Entity entity: ((EntitySequence) getEntities()).getAllEntities()){
			getProgram().getVariable(getVariableName()).setValue(entity);
				getBody().execute();
		}
		return false;
	}
	public void setShip(Entity ship) throws IllegalOperandException{
		setOperandAt(1, EntitySequence.makeEntitySequence( ship.getRealValue().getWorld(), getType()));
		getBody().setShip( ship );
	}
	@Override
	public String toString(){
		return "For each " + getVariableName() + "\nof  " + getOperandAt(1) + "\ndo " + getOperandAt(3);
	}
	@Override
	public boolean isTypeChecked() {
		return getProgram().getVariable(getVariableName()).getType() == getType()
				&& !(getBody() instanceof ActionStatement)
				&& !((StructuralStatement) getBody()).containsAnActionStatement();
	}
	
		
		 

}
