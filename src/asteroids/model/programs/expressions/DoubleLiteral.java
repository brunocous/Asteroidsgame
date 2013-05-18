package asteroids.model.programs.expressions;





public class DoubleLiteral extends BasicExpression{

private double value;

public DoubleLiteral(double value){
		
		super(value);
}
	
public Expression getValue(){
	return new DoubleLiteral(getRealValue());
}


public Double getRealValue(){
	
	return value;
	
}



}

