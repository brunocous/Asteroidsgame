package asteroids.model.programs.expressions;

import asteroids.Error.IllegalStringNameException;
import asteroids.model.programs.types.Type;

public class Variable extends Expression {
	
	private final String name;
	private Expression value;
	private final Type type = null;
	
	public Variable(Expression value, String name){
		this.setValue(value);
		if (canHaveAsName(name)){
			this.name = name;
		}
		else{ 
			throw new IllegalStringNameException();
		}
	}
	

	
	public boolean canHaveAsName(String name){
		return !(name.equals("")||name.equals(null));
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public Expression getValue(){
			
			return value;
	}	
	
	public Type getType(){
		return type;
	}
	
	public void setType(Type type){
		
		this.type= type;
		
	}
	@Override
	public boolean isMutable() {
		
		return true;
	}

	public boolean equals(Expression other) {
		
		if(other.getType() == getType()){
		return ((Variable)other).getVariableValue() == getVariableValue();
		}
		else{
			return false;
		}

	}
	
	
	@Override
	public String toString() {
		
		return null;
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return expression == this;
	}
	
	public void setValue(Expression value){
		
		this.value = value;
	}

}
