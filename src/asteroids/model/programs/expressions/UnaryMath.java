package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Raw;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.type.Type;

public abstract class UnaryMath extends UnaryExpression {

	public static final Type TYPE = Type.DOUBLE;
public UnaryMath(Expression argument) {
	
	super(argument);
	
}

@Override
@Raw
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

@Override
public abstract Double getRealValue();
@Override
public Expression getValue() {
	
	return new DoubleLiteral(getRealValue());
}

@Override
public boolean isTypeChecked(){
	return (getOperandAt(1).getType() == Type.DOUBLE) && getOperandAt(1).isTypeChecked();
}

}
