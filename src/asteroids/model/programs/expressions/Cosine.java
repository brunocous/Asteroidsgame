package asteroids.model.programs.expressions;



public class Cosine extends DoubleRepresentation{
		
		
		public DoubleRepresentation argument;
			
		public Cosine(DoubleRepresentation argument){
			
			setArgument(argument);
			
		}
		
		public void setArgument(DoubleRepresentation argument){
			
			if(canHaveAsArgument(argument)){
				
				this.argument = argument;
			}
			else{
				this.argument = new Constant(0);
			}
		}
		
		public boolean canHaveAsArgument(DoubleRepresentation argument){
			
			return true;
			//TODO implementeren
		}
		
		public double getJavaDouble(){
			
			return Math.cos(argument.getJavaDouble());
			
		}
	

		
		public String toString(){
			
			return "((Constant)getValue()).getConstantValue()";
		}

		public boolean isMutable(){
			return false;
		}

		@Override
		public boolean hasAsSubExpression(Expression expression) {
		
			return argument.equals(expression);
		}
		
		

	}

