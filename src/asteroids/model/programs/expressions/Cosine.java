package asteroids.model.programs.expressions;

import java.util.ArrayList;

public class Cosine extends UnaryComposedExpression {
		
		private ArrayList<Expression> subexpression;
		
		public Cosine(Constant subexpression){
			
			super(subexpression);
			
		}
		
		public boolean equals(Expression other){
			
			return this.getValue() == other.getValue();
		}
		
		public Expression getValue(){
			
			return new Constant(Math.cos(((Constant)subexpression.get(0)).getConstantValue()));
		}
		
		public String toString(){
			
			return "((Constant)getValue()).getConstantValue()";
		}

		public boolean isMutable(){
			return false;
		}
		
		

	}

