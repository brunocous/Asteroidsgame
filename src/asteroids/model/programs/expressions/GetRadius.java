package asteroids.model.programs.expressions;

import java.util.ArrayList;

public class GetRadius extends UnaryComposedExpression {
	
	private ArrayList<Expression> subexpression;
	
	public GetRadius(Entity subexpression){
		
		super(subexpression);
		
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public Expression getValue(){
		
		return new Constant(((Entity)subexpression.get(0)).getEntityValue().getRadius());
		
	}
	
	public String toString(){
		
		return "((Constant)getValue()).getConstantValue()";
	}

	public boolean isMutable(){
		return false;
	}
	
	

}
