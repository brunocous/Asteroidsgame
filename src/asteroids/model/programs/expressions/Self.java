package asteroids.model.programs.expressions;

import asteroids.model.Ship;
import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;


public class Self extends EntityRepresentation {

	private final SpaceObject value;
	
public Self(Ship ship){
	
	value =ship;
	
}

public SpaceObject getSpaceObject(){
	return value;
}

@Override
public boolean hasAsSubEntry(IEntry subEntry) {
	
	return subEntry.equals(this);
}
}

