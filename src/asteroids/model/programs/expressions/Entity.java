package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;




public class Entity extends BasicExpression{

private SpaceObject value;

public Entity(SpaceObject value){
		this.value = value;
		
}


@Override
public Expression getValue() {
	return new Entity(getRealValue());
}

public SpaceObject getRealValue(){
	return value;
}


}

