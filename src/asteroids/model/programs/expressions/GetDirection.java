package asteroids.model.programs.expressions;

import asteroids.model.programs.Program;
import asteroids.model.programs.type.Type;



public class GetDirection extends BasicExpression{
		
	private Program program;
	private static final Type TYPE = Type.DOUBLE;
	

	@Override
	public Double getRealValue() {
			
			return getProgram().getShip().getDirection();
			
	}
@Override
public void setProgram(Program program){
	this.program = program;
}
@Override
public Program getProgram(){
	return program;
}




@Override
public Expression getValue() {
	return (getProgram() != null) ? new DoubleLiteral(getProgram().getShip().getDirection()): null;
}





@Override
public Type getType() {
	return TYPE;
}
	

	
	}
