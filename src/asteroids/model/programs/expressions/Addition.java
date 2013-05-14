package asteroids.model.programs.expressions;







public class Addition extends DoubleRepresentation{
	
	private DoubleRepresentation leftExpression;
	private DoubleRepresentation rightExpression;


	public Addition(DoubleRepresentation leftExpression, DoubleRepresentation RightExpression){
	
		setRightExpression(rightExpression);
		setLeftExpression(leftExpression);
		
	}
	
	public void setRightExpression(DoubleRepresentation expression){
		if(canHaveAsRightExpression(expression)){
			rightExpression = expression;
		}
		else{
			rightExpression = new Constant(0);
		}
	}
	
	public boolean canHaveAsRightExpression(DoubleRepresentation expression){
		
		return true; 
		//TODO implementeren
	}
	
	public void setLeftExpression(DoubleRepresentation expression){
		if(canHaveAsLeftExpression(expression)){
			leftExpression=expression;
		}
		else{
			leftExpression=new Constant(0);
		}
	}
	
	public boolean canHaveAsLeftExpression(DoubleRepresentation expression){
		
		return true;
		//TODO implementeren
		
	}
	public boolean isMutable(){
		
		return false;
		
	}
	
	
	public DoubleRepresentation getLeftExpression(){
		return leftExpression.clone();
	}
	
	public DoubleRepresentation getRightExpression(){
		return rightExpression.clone();
	}
	
	@Override
	public double getJavaDouble(){
		
		return getLeftExpression().getJavaDouble()+getRightExpression().getJavaDouble();
			
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		return (expression == getLeftExpression() || expression == getRightExpression());
	}
}

