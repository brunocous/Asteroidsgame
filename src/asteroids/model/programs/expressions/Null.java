package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;



public class Null extends  BasicExpression {

	private SpaceObject value;
	
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
}
