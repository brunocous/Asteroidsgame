package asteroids.model.programs.expressions;

import java.util.ArrayList;

public class Negation extends UnaryComposedExpression {
		
		private ArrayList<Expression> subexpression;
		
		public Negation(Bool subexpression){
			
			super(subexpression);
			
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
