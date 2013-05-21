package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.type.Type;




public abstract class Expression implements IEntry{

private Program program = null;

public abstract Object getRealValue();

public abstract Expression getValue();

public boolean equals(Expression other){

if (!(this.getType() == other.getType())){
return false;
}
else{
if(this.getType() == Type.BOOLEAN){
return this.getValue().getRealValue()==other.getValue().getRealValue();
}
else if(this.getType() == Type.DOUBLE){
return Util.fuzzyEquals((Double)(this.getValue().getRealValue()),(Double)(other.getValue().getRealValue()));
}
else if(this.getType() == Type.ANY){
return this.getValue().getRealValue()==other.getValue().getRealValue();
}
else return false;
}
}

public abstract Type getType();

public String toString(){

if(getValue().getType() == Type.DOUBLE){
return Double.toString((Double) getRealValue());
}
else if(getValue().getType() == Type.BOOLEAN){
return String.valueOf((Boolean) getRealValue());
}
else if(getValue().getType() == Type.ANY){
return ((SpaceObject) getRealValue()).toString();
}
else{
return null;
}
}
public abstract boolean isTypeChecked();

public Program getProgram(){
return program;
}
public void setProgram(Program program){
if(canHaveAsProgram( program))
this.program = program;
}

public boolean canHaveAsProgram(Program program){
return (getProgram() == null) ? true:false;
}

}