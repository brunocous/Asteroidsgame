package asteroids.model.programs.expressions;

import asteroids.Error.IllegalVariableValueException;
import asteroids.model.programs.IEntry;



public class Variable extends Expression{

private Expression value;

public Variable(){
		
		this.value= null;
}
	
public void setValue(Expression value) throws IllegalVariableValueException{
	if(canHaveAsValue(value)){
		this.value = value;
	}
	else{
		throw new IllegalVariableValueException();
	}
}

public boolean canHaveAsValue(Expression value){
	
	if(BooleanRepresentation.class.isAssignableFrom(value.getClass()) ||
			DoubleRepresentation.class.isAssignableFrom(value.getClass()) ||
			EntityRepresentation.class.isAssignableFrom(value.getClass())){
		return true;
	}
	else{
		return false;
	}
}

public Expression getValue(){
	return value;
}

@Override
public boolean hasAsSubEntry(IEntry subEntry) {

	return subEntry.equals(this);
}

public String toString(){
	if(BooleanRepresentation.class.isAssignableFrom(getValue().getClass()) ){
		return ((BooleanRepresentation) getValue()).toString();
	}
	else if(DoubleRepresentation.class.isAssignableFrom(getValue().getClass())){
		return ((DoubleRepresentation)getValue()).toString();
	}
	else if(EntityRepresentation.class.isAssignableFrom(getValue().getClass())){
		return ((EntityRepresentation)getValue()).toString();
	}
	else if(getValue()==null){
		return "null";
	}
	else{
		return "Unknown value";
	}
}
}

