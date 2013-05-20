package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.type.Type;



public class Self extends BasicExpression {

private SpaceObject value;
private static final Type TYPE = Type.SHIP;
	
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

@Override
public Type getType() {
	return TYPE;
}
}

