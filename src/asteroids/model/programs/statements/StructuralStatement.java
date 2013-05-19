package asteroids.model.programs.statements;

import asteroids.model.programs.*;

public abstract class StructuralStatement extends Statement implements IComposedStructure{
		
	
	@Override 
	public boolean equals(Object other){
		if ((other == null) || (getClass() != other.getClass()))
			return false;
		StructuralStatement otherStat = (StructuralStatement) other;
		if (getNbOperands() != otherStat.getNbOperands())
			return false;
		for (int pos = 1; pos <= getNbOperands(); pos++)
			if (!getOperandAt(pos).equals(otherStat.getOperandAt(pos)))
				return false;
		return true;
	}

	public boolean containsAnActionStatement(){
		for(int pos  =1; pos <= getNbOperands(); pos++){
			IEntry operand  = getOperandAt(pos);
			if(operand.getClass().isAssignableFrom(Statement.class)){
				if(operand.getClass().isAssignableFrom(ActionStatement.class))
					return true;
				if( ((StructuralStatement) operand).containsAnActionStatement())
					return true;
			}
				
		}
		return false;
	}

}
