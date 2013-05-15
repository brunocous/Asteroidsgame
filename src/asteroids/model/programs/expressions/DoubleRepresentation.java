package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public abstract class DoubleRepresentation extends Expression {

	public abstract double getJavaDouble();
	
	@Override
	public boolean equals(Expression other){
		
		if(other.getValue() == new Constant(this.getJavaDouble())){
			return true;
		}
		else{
			return false;
		}
	}
	


	public Constant getValue(){
		
		return new Constant(this.getJavaDouble());
		
	}

}
