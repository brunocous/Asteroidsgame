package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;



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
public boolean hasAsSubEntry(IEntry subEntry) {

	return subEntry.equals(this);
}


}

