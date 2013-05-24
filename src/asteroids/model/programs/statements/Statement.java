package asteroids.model.programs.statements;


import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;

public abstract class Statement implements IEntry,IComposedStructure {

	private Program program = null;
	public abstract boolean execute();
	
	@Override
	@Raw
	public boolean canHaveAsOperandAt(int index, IEntry entry){
		return  entry != null && (!entry.hasAsSubEntry(this)) && (index > 0);
	}

	@Override
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
	@Basic
	public Program getProgram(){
		return program;
	}
	@Basic
	public void setProgram(Program program){
		if(canHaveAsProgram( program))
			this.program = program;
			
	}
	@Raw
	public boolean canHaveAsProgram(Program program){
		return (getProgram() == null) ? true:false;
	}
}
