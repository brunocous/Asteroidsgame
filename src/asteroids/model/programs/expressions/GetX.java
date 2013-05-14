package asteroids.model.programs.expressions;

import java.util.ArrayList;

import asteroids.model.programs.types.DoubleLiteral;
import asteroids.model.programs.types.Type;

public class GetX extends ComposedExpression{
	
	private ArrayList<Expression> subexpression;
	private Type type = new DoubleLiteral();
	
	
	public GetX(ArrayList<Expression> subexpression){
		
		super(subexpression);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	}
	
	public Expression getValue(){
		
		return new Constant(((Entity)subexpression.get(0)).getEntityValue().getPos().getX());
		
	}
	
	public String toString(){
		
		return "((Constant)getValue()).getConstantValue()";
	}

	public boolean isMutable(){
		return false;
	}
	
	
}
