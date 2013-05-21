package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.EntitySequence;
import asteroids.model.programs.type.Type;

public class Foreach extends StructuralStatement {

	private Statement body;
	private final Type type;
	private String variableName;
	
	public Foreach(Type type, String variableName, Statement body){
		this.variableName = variableName;
		this.body = body;
		if(canHaveAsOperandAt(1, type))
			this.type = type;
		else this.type = null;
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 3)
			return getBody();
		if(index == 1)
			return getType();
		throw new IndexOutOfBoundsException();
		
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
		if(index == 3)
			this.body = (Statement) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 3 && operand instanceof StructuralStatement){
				return true;
			}
			if(index == 1 && operand instanceof Type)
				return Type.isValidType((Type) operand);
		}
			return false;
	}

	@Override
	public boolean execute() {
		for(Entity entity: EntitySequence.generateEntitySequence(getProgram().getShip().getWorld(), getType()).getAllEntities()){
			getProgram().getVariable(getVariableName()).setValue(entity);
				getBody().execute();
		}
		return false;
	}
	@Override
	public String toString(){
		return "For each " + getVariableName() + "\nof  " + getOperandAt(1) + "\ndo " + getOperandAt(3);
	}
	@Override
	public boolean isTypeChecked() {
		return Type.isValidEntityType(getType())
				&& getProgram().getVariable(getVariableName()).getType() == Type.ANY
				&& !(getBody() instanceof ActionStatement)
				&& !((StructuralStatement) getBody()).containsAnActionStatement();
	}
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		getBody().setProgram(program);
	}
	
		
		 

}
