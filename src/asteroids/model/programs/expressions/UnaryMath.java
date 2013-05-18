package asteroids.model.programs.expressions;

import asteroids.model.programs.IEntry;

public abstract class UnaryMath extends UnaryExpression {

public UnaryMath(Expression argument) {
	
	super(argument);
	
}

@Override
public boolean canHaveAsOperandAt(int index, IEntry argument){
		
		if(index ==1){
		return true;
		}
		else{
			return false;
			//TODO implementeren
		}
		

}

@Override
public abstract Double getRealValue();
@Override
public Expression getValue() {
	
	return new DoubleLiteral(getRealValue());
}

@Override
public boolean isTypeChecked(){
	return getOperandAt(1).getValue().getClass().isAssignableFrom(DoubleLiteral.class);
}

}
