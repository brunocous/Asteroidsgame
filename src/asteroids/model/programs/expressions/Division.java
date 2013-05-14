package asteroids.model.programs.expressions;




import asteroids.Util;




public class Division extends DoubleRepresentation{
	
	private DoubleRepresentation numerator;
	private DoubleRepresentation denominator;
	

	public Division(DoubleRepresentation numerator, DoubleRepresentation denominator){
		
		setNumerator(numerator);
		setDenominator(denominator);
		
	}
	
	public void setNumerator(DoubleRepresentation numerator){
		if(canHaveAsNumerator(numerator)){
			this.numerator=numerator;
		}
		else{
			this.numerator= new Constant(0);
		}
	}
	
	public boolean canHaveAsNumerator(DoubleRepresentation numerator){
		return true;
		//TODO implementeren
	}
	
	public void setDenominator(DoubleRepresentation denominator){
		if(canHaveAsDenominator(denominator)){
			this.denominator=denominator;
		}
		else{
			this.denominator=new Constant(1);
		}
	}
	
	public boolean canHaveAsDenominator(DoubleRepresentation denominator){
		
		return !Util.fuzzyEquals(denominator.getJavaDouble(),0);
		
	}
	
	
	public boolean isMutable(){
		
		return false;
		
	}

	@Override
	public double getJavaDouble() {
		// TODO Auto-generated method stub
		return 0;
	}

	public DoubleRepresentation getNumerator(){
		return this.numerator.clone();
		
	}
	
	public DoubleRepresentation getDenominator(){
		return this.denominator.clone();
	}
	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return (getNumerator().equals(expression) || getDenominator().equals(expression));
	}

	
	
}

