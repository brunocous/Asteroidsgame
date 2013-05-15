package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;



public abstract class DoubleRepresentation extends Expression {

	public abstract double getJavaDouble();
	
	@Override
	public boolean equals(Object other){
		
		if( !DoubleRepresentation.class.isAssignableFrom(other.getClass())){
			return false;
		}
		else if(((DoubleRepresentation)other).getValue() == new Constant(this.getJavaDouble())){
			return true;
		}
		else{
			return false;
		}
	}
	


	public Constant getValue(){
		
		return new Constant(this.getJavaDouble());
		
	}
	
	public abstract void setOperandAt(int index, IEntry entry) throws IllegalOperandException;
	public abstract IEntry getOperandAt(int index) throws IndexOutOfBoundsException;
	public abstract int getNbOperands();
}
