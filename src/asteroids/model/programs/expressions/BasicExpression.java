package asteroids.model.programs.expressions;


import asteroids.model.programs.IEntry;

public abstract class BasicExpression extends Expression{
	
	private Object value;
	
	public BasicExpression(Object value){
		this.value = value;
	}
	

	@Override
	public boolean hasAsSubEntry(IEntry subEntry) {

		return subEntry.equals(this);
	}

}
