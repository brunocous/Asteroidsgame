package asteroids.model.programs.expressions;

import asteroids.model.programs.types.DoubleLiteral;
import asteroids.model.programs.types.Type;


public class Constant extends BasicExpression{

private double value;
private Type type = new DoubleLiteral();

	
public Constant(double value){
		
		this.value= (value);
}
	
@Override
public Type getType(){
	
	return type;
	
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

public boolean equals(Expression other) {
	
	if(other.getType() == getType()){
	return ((Constant)other).getConstantValue() == getConstantValue();
	}
	else{
		return false;
	}

}


@Override
public String toString() {
	
	return null;
}


}

