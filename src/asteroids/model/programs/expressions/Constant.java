package asteroids.model.programs.expressions;



public class Constant extends DoubleRepresentation{

private double value;

public Constant(double value){
		
		this.value= (value);
}
	

public Constant getValue(){
		
		return this;
}	

@Override
public boolean isMutable() {
	
	return false;
}

@Override
public double getJavaDouble(){
	
	return value;
	
}

@Override
public boolean hasAsSubExpression(Expression expression) {

	return expression.equals(this);
}


}

