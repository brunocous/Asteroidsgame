package asteroids.model.programs.expressions;


public class Constant extends BasicExpression{

	private double value;
	
	public Constant(double value){
		
		this.value= (value);
}
	
@Override
	public Expression getValue(){
		
		return this;
}	
@Override
public boolean isMutable() {
	
	return false;
}

public double getConstantValue(){
	
	return value;
	
}
@Override
public boolean equals(Expression other) {
	
	return other.getValue() == getValue();
}

@Override
public String toString() {
	
	return null;
}
public boolean hasAsSubExpression(Expression expression) {
	
	return expression == this;
}
}