package asteroids.model.programs.expressions;

import asteroids.model.programs.types.Type;

public class Variable extends BasicExpression {
	
	private Expression value;
	private Type type = null;
	
	public Variable(Expression value){
		super(value);
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
