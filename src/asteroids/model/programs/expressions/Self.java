package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.SpaceObject;
import asteroids.model.programs.type.Type;



public class Self extends BasicExpression {

private static final Type TYPE = Type.SHIP;

@Override
public SpaceObject getRealValue(){
	return getProgram().getShip();
}

@Override
public Expression getValue(){
	
	return new Entity(getProgram().getShip());
	
}

@Override
@Basic
public Type getType() {
	return TYPE;
}
}

