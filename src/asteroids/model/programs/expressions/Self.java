package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.type.Type;



public class Self extends BasicExpression {

private static final Type TYPE = Type.SHIP;

public SpaceObject getRealValue(){
	return getProgram().getShip();
}


public Expression getValue(){
	
	return new Entity(getProgram().getShip());
	
}

@Override
public Type getType() {
	return TYPE;
}
}

