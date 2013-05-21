package asteroids.model.programs;

public interface IEntry extends IStructure{
	
public abstract boolean equals(Object other);
public abstract String toString();

public abstract Program getProgram();
public abstract void setProgram(Program program);
public abstract boolean canHaveAsProgram(Program program);

}
