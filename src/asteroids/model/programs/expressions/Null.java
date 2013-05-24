package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;
import asteroids.model.SpaceObject;
import asteroids.model.programs.type.Type;


@Value
public class Null extends  BasicExpression {

	private SpaceObject value;
	private static final Type TYPE = Type.NULL;
	
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
@Basic
public Type getType() {
	return TYPE;
}
}
