package asteroids.model.programs.expressions;


import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;

public abstract class BasicExpression extends Expression{
	

	@Override
	public boolean hasAsSubEntry(IEntry subEntry) {

		return subEntry.equals(this);
	}
	

	@Override
	public boolean isTypeChecked(){
		return true;
	}
	
}
