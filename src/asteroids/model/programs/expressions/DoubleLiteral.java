package asteroids.model.programs.expressions;

import asteroids.model.programs.type.*;

public class DoubleLiteral extends BasicExpression{

private double value;
private static final Type TYPE = Type.DOUBLE;

public DoubleLiteral(double value){
		
		this.value = (value);
}

@Override
public Type getType(){
	return TYPE;
}
public Expression getValue(){
	return new DoubleLiteral(getRealValue());
}


public Double getRealValue(){
	
	return value;
	
}



}

