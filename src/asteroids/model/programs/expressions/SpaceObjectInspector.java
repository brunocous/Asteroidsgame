package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;
import asteroids.model.programs.type.IGeneralType.GeneralType;
import asteroids.model.programs.type.Type;

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
	
	return (getOperandAt(1).getType().getGeneralType() == GeneralType.ENTITY)
			&& getOperandAt(1).isTypeChecked();
}

}
