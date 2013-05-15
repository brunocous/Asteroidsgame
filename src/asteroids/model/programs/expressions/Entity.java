package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;



public class Entity extends EntityRepresentation{

private SpaceObject value;

public Entity(SpaceObject value){
		
		this.value= (value);
}
	



@Override
public SpaceObject getSpaceObject(){
	
	return value;
	
}

@Override
public boolean hasAsSubEntry(IEntry subEntry) {

	return subEntry.equals(this);
}


}

