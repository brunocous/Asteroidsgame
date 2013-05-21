package asteroids.model.programs.statements;


import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;

public abstract class Statement implements IEntry,IComposedStructure {

	private Program program = null;
	public abstract boolean execute();
	
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry entry){
		return  entry != null && (!entry.hasAsSubEntry(this)) && (index > 0);
	}

	public boolean hasAsSubEntry(IEntry entry){
		if (entry == this)
			return true;
		for (int pos = 1; pos <= this.getNbOperands(); pos++){
			if(this.getOperandAt(pos) ==  null)
				return false;
			if (this.getOperandAt(pos).hasAsSubEntry(entry))
				return true;
		}
		return false;
	}
	
	public abstract boolean isTypeChecked();
	
	public Program getProgram(){
		return program;
	}
	public void setProgram(Program program){
		if(canHaveAsProgram( program))
			this.program = program;
			
	}
	public boolean canHaveAsProgram(Program program){
		return (getProgram() == null) ? true:false;
	}
}
