package asteroids.model.programs.statements;

import asteroids.model.Ship;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public abstract class Statement implements IEntry,IComposedStructure {

	public abstract void execute();
	public abstract void execute(Ship ship);
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry entry){
		return  (!entry.hasAsSubEntry(this)) && (index > 0);
	}
}
