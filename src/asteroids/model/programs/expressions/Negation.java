package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;



public class Negation extends UnaryBooleanRepresentation implements IComposedStructure{
		

		
	public Negation(DoubleRepresentation argument) throws IllegalOperandException{
		
		super(argument);
		
	}
	
		public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			if(BooleanRepresentation.class.isAssignableFrom(argument.getClass())){
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
		
		public boolean getJavaBoolean(){
			
			return !(((BooleanRepresentation)getOperandAt(1)).getJavaBoolean());
			
		}
	

		public String toString(){
			
			return "getJavaBoolean()";
		}

	
		}
