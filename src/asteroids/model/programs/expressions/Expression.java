package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;




public abstract class Expression implements IEntry{


	 
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

	
}
