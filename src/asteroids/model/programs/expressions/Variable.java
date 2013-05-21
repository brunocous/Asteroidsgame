package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.type.Type;




public class Variable extends Expression{

private Expression value;
private final String name;
private Type type;

public Variable(String name){
	
	if(!canHaveAsName(name)){
		this.name = "defaultName";
	}
	else{
	this.name = name;
	}
	this.value= null;
	this.type = null;
}
public Variable(String name, Type type){
	this(name);
	this.type = type;
}
	
public void setValue(Expression value) {
	if(canHaveAsValue(value)){
		this.value = value;
	}
	else{
	
	}
}

public boolean canHaveAsValue(Expression value){
	
	return true;

}
public String getName(){
	return name;
}
public boolean canHaveAsName(String name){
	return name != null && name != "";
}
public Expression getValue(){
	return value.getValue();
}
public Expression getVariableValue(){
	return value;
}

@Override
public Object getRealValue() {
	if(getValue().getType()== Type.DOUBLE){
		return new DoubleLiteral(((DoubleLiteral)(value.getValue())).getRealValue());
	}
	else if(getValue().getType()== Type.BOOLEAN){
		return new BooleanLiteral(((BooleanLiteral)(value.getValue())).getRealValue());
	}
	else if(getValue().getType() == Type.ANY){
		return new Entity(((Entity)(value.getValue())).getRealValue());
	}
	else{
		return null;
	}
}

@Override
public boolean hasAsSubEntry(IEntry subEntry) {
	return getVariableValue().hasAsSubEntry(subEntry);
}


@Override
public boolean isTypeChecked(){
	return getVariableValue().isTypeChecked();
}
public void setType(Type type){
	if(Type.isValidType(type))
		this.type = type;
}
public Type getType(){
	return type;
}

@Override
public void setProgram(Program program){
	super.setProgram(program);
	getValue().setProgram(program);
	
	if(getVariableValue() instanceof Variable){
		setValue(program.getVariable(((Variable)getVariableValue()).getName()));
	}
}


}

