package asteroids.model.programs.kind;

import java.util.ArrayList;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.EntityRepresentation;

public abstract class Kind implements IEntry {

	public abstract ArrayList<EntityRepresentation> getAllEntities();
}
