package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;



public class Sqrt extends UnaryDoubleRepresentation implements IComposedStructure{
		

		
	public Sqrt(DoubleRepresentation argument) throws IllegalOperandException{
		
		super(argument);
		
	}
	
		public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			if(DoubleRepresentation.class.isAssignableFrom(argument.getClass()) && (!Util.fuzzyLessThanOrEqualTo(((DoubleRepresentation)argument).getJavaDouble(),0) || Util.fuzzyEquals(((DoubleRepresentation)argument).getJavaDouble(),0))){
				return true;
			}
			else{
				return false;
				//TODO implementeren
			}
			}
			else {
				return false;
			}
		}
		
		public double getJavaDouble(){
			
			return Math.sqrt(((DoubleRepresentation)getOperandAt(1)).getJavaDouble());
			
		}
	

		public String toString(){
			
			return "((Constant)getValue()).getConstantValue()";
		}

	
		}