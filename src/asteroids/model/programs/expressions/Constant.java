package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



public class Constant extends Expression{

private double value;

public Constant(double value){
		
		this.value= (value);
}
	
public Expression getValue(){
	return new Constant(getRealValue());
}


public double getRealValue(){
	
	return value;
	
}

public boolean hasAsSubEntry(IEntry subEntry) {

	return subEntry.equals(this);
}


}

