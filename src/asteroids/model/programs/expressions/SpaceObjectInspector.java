package asteroids.model.programs.expressions;

import asteroids.model.programs.type.*;

import asteroids.model.programs.IEntry;

public abstract class SpaceObjectInspector extends UnaryExpression{

public final static Type TYPE = Type.DOUBLE;

public SpaceObjectInspector(Expression argument) {
		
		super(argument);
		
	}
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			return true;
			}
			else{
				return false;
				
			}

	}
			

@Override
public Type getType(){
	return TYPE;
}

public abstract Double getRealValue();
@Override
public Expression getValue() {
			
	return new DoubleLiteral(getRealValue());
}
	
@Override
public boolean isTypeChecked(){
	
	return getOperandAt(1).getValue() instanceof Entity;
}

}
