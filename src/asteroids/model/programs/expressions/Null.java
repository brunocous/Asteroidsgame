package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.type.Type;



public class Null extends  BasicExpression {

	private SpaceObject value;
	private static final Type TYPE = null;
	
public Null(){
	this.value = (null);
	
}


@Override
public Object getRealValue() {

return value;
}

@Override
public Expression getValue() {

	return new Entity(null);
	
}


@Override
public Type getType() {
	return TYPE;
}
}
