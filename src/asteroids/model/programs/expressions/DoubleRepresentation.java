package asteroids.model.programs.expressions;





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
	

}
