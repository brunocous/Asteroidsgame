package asteroids.model.programs.expressions;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;



public class GetVX extends UnaryDoubleRepresentation implements IComposedStructure{
		

		
	public GetVX(DoubleRepresentation argument) throws IllegalOperandException{
		
		super(argument);
		
	}
	
		public boolean canHaveAsOperandAt(int index, IEntry argument){
			
			if(index ==1){
			if(EntityRepresentation.class.isAssignableFrom(argument.getClass())){
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
			
			return (((EntityRepresentation)getOperandAt(1)).getSpaceObject().getVel().getX());
			
		}
	

	
		}