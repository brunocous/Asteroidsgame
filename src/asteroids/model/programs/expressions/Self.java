package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;



public class Self extends BasicExpression {

private SpaceObject value;
	
public Self(){
	this.value =(null);
}

public SpaceObject getRealValue(){
	return value;
}
@Override
public void setShip(Entity ship){
	this.value = ship.getRealValue();
}


public Expression getValue(){
	
	return new Entity(value);
	
}
}

