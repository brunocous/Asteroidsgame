package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.type.Type;




public class Entity extends BasicExpression{

private SpaceObject value;
private static final Type TYPE = Type.ANY;

public Entity(SpaceObject value){
	this.value = value;
		
}

@Override
public Type getType(){
	return TYPE;
}
@Override
public Expression getValue() {
	return new Entity(getRealValue());
}

public SpaceObject getRealValue(){
	return value;
}


}

