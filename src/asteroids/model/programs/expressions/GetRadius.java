package asteroids.model.programs.expressions;

import java.util.ArrayList;

import asteroids.model.programs.types.DoubleLiteral;
import asteroids.model.programs.types.Type;

public class GetRadius extends UnaryComposedExpression {
	
	private ArrayList<Expression> subexpression;
	private Type type = new DoubleLiteral();
	
	public GetRadius(Entity subexpression){
		
		super(subexpression);
		
	}
	
	public Type getType(){
		return type;
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
