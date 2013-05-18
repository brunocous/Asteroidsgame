package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



public class DoubleLiteral extends Expression{

private double value;

public DoubleLiteral(double value){
		
		this.value= (value);
}
	
public Expression getValue(){
	return new DoubleLiteral(getRealValue());
}


public Double getRealValue(){
	
	return value;
	
}

public boolean hasAsSubEntry(IEntry subEntry) {

	return subEntry.equals(this);
}


}

