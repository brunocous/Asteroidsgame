package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public abstract class SpaceObjectInspector extends UnaryExpression{

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
		
	

public abstract Double getRealValue();
@Override
public Expression getValue() {
			
	return new DoubleLiteral(getRealValue());
}
	

}
