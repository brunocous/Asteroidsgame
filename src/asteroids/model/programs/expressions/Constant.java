package asteroids.model.programs.expressions;



public class Constant extends DoubleRepresentation{

private double value;

public Constant(double value){
		
		this.value= (value);
}
	



@Override
public double getJavaDouble(){
	
	return value;
	
}

@Override
public boolean hasAsSubEntry(Expression expression) {

	return expression.equals(this);
}


}

