package asteroids.model.programs.expressions;

import asteroids.Error.IllegalVariableValueException;
import asteroids.model.programs.IEntry;



public class Variable extends Expression{

private Expression value;

public Variable(Expression value){
		
		this.value= (value);
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


}

