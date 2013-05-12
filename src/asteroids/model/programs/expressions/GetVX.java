package asteroids.model.programs.expressions;

import java.util.ArrayList;

public class GetVX extends UnaryComposedExpression {
	
private ArrayList<Expression> subexpression;
	
	public GetVX(Entity subexpression){
		
		super(subexpression);
		
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public Expression getValue(){
		
		return new Constant(((Entity)subexpression.get(0)).getEntityValue().getVel().getX());
		
	}
	
	public String toString(){
		
		return "((Constant)getValue()).getConstantValue()";
	}

	public boolean isMutable(){
		return false;
	}

}
