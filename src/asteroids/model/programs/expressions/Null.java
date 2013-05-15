package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;


public class Null extends EntityRepresentation {

	private SpaceObject value;
	
public Null(){
	
	value = null;
	
}

public SpaceObject getSpaceObject(){
	return value;
}

@Override
public boolean hasAsSubEntry(IEntry subEntry) {
	
	return subEntry == null;
}
}
