package asteroids.model.programs.expressions;

import java.util.ArrayList;

public class Sqrt extends UnaryComposedExpression {
		
		private ArrayList<Expression> subexpression;
		
		public Sqrt(Constant subexpression){
			
			super(subexpression);
			
		}
		
		public boolean equals(Expression other){
			
			return this.getValue() == other.getValue();
		}
		
		public Expression getValue(){
			
			return new Constant(Math.sqrt(((Constant)subexpression.get(0)).getConstantValue()));
		}
		
		public String toString(){
			
			return "((Constant)getValue()).getConstantValue()";
		}

		public boolean isMutable(){
			return false;
		}
		
		

	}

