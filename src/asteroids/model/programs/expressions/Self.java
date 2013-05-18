package asteroids.model.programs.expressions;

import asteroids.model.Ship;
import asteroids.model.SpaceObject;



public class Self extends BasicExpression {

private final SpaceObject value;
	
public Self(Ship ship){
	
	super(ship);
	
}

public SpaceObject getRealValue(){
	return value;
}



public Expression getValue(){
	
	return new Entity(value);
	
}
}

