package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;



public class Sine extends UnaryDoubleRepresentation implements IComposedStructure{
		
	private DoubleRepresentation argument;
		
	public Sine(DoubleRepresentation argument) throws IllegalOperandException{
		
		super(argument);
		
	}
	
		public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			if(DoubleRepresentation.class.isAssignableFrom(argument.getClass())){
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
			
			return Math.sin(argument.getJavaDouble());
			
		}
	

		public String toString(){
			
			return "((Constant)getValue()).getConstantValue()";
		}

	
		}
