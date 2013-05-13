package asteroids.model.programs.expressions;

import java.util.ArrayList;

import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;

public class Negation extends UnaryComposedExpression {
		
		private ArrayList<Expression> subexpression;
		private Type type = new BooleanLiteral();
		
		public Negation(Expression subexpression){
			
			super(subexpression);
			
		}
		
		public Type getType(){
			return type;
		}
		
		public boolean equals(Expression other){
			
			return this.getValue() == other.getValue();
		}
		
		public Expression getValue(){
			
			return new Bool(!(((Bool)subexpression.get(0)).getBooleanValue()));
		}
		
		public String toString(){
			
			return "((Boolean)getValue()).getBooleanValue()";
		}

		public boolean isMutable(){
			return false;
		}
		
		

	}
