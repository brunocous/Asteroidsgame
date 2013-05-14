package asteroids.model.programs.expressions;


import java.util.ArrayList;


import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;

public class NotEqualTo extends ComposedExpression{
	
	private ArrayList<Expression> subexpressions;
	private Type type = new BooleanLiteral();

	public NotEqualTo(ArrayList<Expression> subexpressions){
		
		super(subexpressions);
		
	}
	
	public Type getType(){
		return type;
	}
	
	public boolean isMutable(){
		
		return false;
		
	}
	
	public Bool getValue(){
		
		return new Bool(!subexpressions.get(0).getValue().equals(subexpressions.get(1).getValue()));
	}
	
	public boolean equals(Expression other){
		
		return this.getValue() == other.getValue();
	
	}
	
	public String toString(){
		
		return getValue().toString();
	}
}

