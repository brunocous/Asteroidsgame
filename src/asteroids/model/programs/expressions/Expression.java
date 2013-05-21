package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.type.Type;




public abstract class Expression implements IEntry{
	 
	private Program program = null;
	
	public abstract Object getRealValue();

	public abstract Expression getValue();
	
	public boolean equals(Expression other){
		
	if (!this.getValue().getClass().isAssignableFrom(other.getValue().getClass())){
		return false;
	}
	else{
		if(this.getValue().getRealValue()==other.getValue().getRealValue()){
			return true;
		}
		else{
			return false;
		}
	}
	}
	
	public abstract Type getType();
	
	public String toString(){
		
		if(getValue().getClass().isAssignableFrom(DoubleLiteral.class)){
			return Double.toString((Double) getRealValue());
		}
		else if(getValue().getClass().isAssignableFrom(BooleanLiteral.class)){
			return String.valueOf((Boolean) getRealValue());
		}
		else if(getValue().getClass().isAssignableFrom(Entity.class)){
			return ((SpaceObject) getRealValue()).toString();
		}
		else{
			return null;
		}
	}
	public abstract void setShip(Entity ship);
	public abstract boolean isTypeChecked();
	
	public Program getProgram(){
		return program;
	}
	public void setProgram(Program program){
		if(canHaveAsProgram( program))
			this.program = program;
	}
	public boolean canHaveAsProgram(Program program){
		return (getProgram() == null) ? true:false;
	}
	
}
