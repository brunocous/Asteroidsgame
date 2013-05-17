package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.Error.IllegalVariableValueException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.EntitySequence;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.kind.Kind;

public class Foreach extends StructuralStatement {

	private EntitySequence entities = null;
	private Variable localVar;
	private StructuralStatement body;
	private final Kind kind;
	
	public Foreach(Kind kind, Variable localVar, StructuralStatement body) throws IllegalOperandException{
		setOperandAt(2,localVar);
		setOperandAt(3,body);
		if(canHaveAsOperandAt(4, kind))
			this.kind = kind;
		else throw new IllegalOperandException();
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return entities;
		if(index == 2)
			return localVar;
		if(index == 3)
			return body;
		if(index == 4)
			return kind;
		throw new IndexOutOfBoundsException();
		
	}

	@Override
	public int getNbOperands() {
		return 4;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index,operand))
			throw new IllegalOperandException();
		if(index == 1)
			this.entities = (EntitySequence) operand;
		if(index == 2)
			this.localVar = (Variable) operand;
		if(index == 3)
			this.body = (StructuralStatement) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand.getClass().isAssignableFrom(EntitySequence.class))
				return true;
			if(index == 2 && operand.getClass().isAssignableFrom(Variable.class))
				return true;
			if(index == 3 && operand.getClass().isAssignableFrom(StructuralStatement.class)){
				if(operand.getClass().isAssignableFrom(Sequence.class))
					return !((Sequence) operand).containsActionStatements();
				else 
					return true;
			}
			if(index == 4 && operand.getClass().isAssignableFrom(Kind.class))
				return Kind.isValidKind((Kind) operand);
		}
			return false;
	}

	@Override
	public void execute() {
		for(int pos=1;pos <= ((EntitySequence) getOperandAt(1)).getNbOperands();pos++){
			try{ Entity entityVar = (Entity) ((Variable) getOperandAt(pos)).getValue();
			((Variable) getOperandAt(2)).setValue(entityVar);
			((StructuralStatement) getOperandAt(3)).execute();
			}catch(IllegalVariableValueException e){
				assert !((Variable) getOperandAt(2)).canHaveAsValue((Entity) ((Variable) getOperandAt(pos)).getValue());
			}
		}

	}
	public void setShip(Entity ship) throws IllegalOperandException{
		setOperandAt(1, EntitySequence.getEntitySequence( ship.getSpaceObject().getWorld(), (Kind) getOperandAt(4)));
		((Statement) getOperandAt(3)).setShip( ship );
	}
	@Override
	public String toString(){
		return "For each " + getOperandAt(4) + "\nof  " + getOperandAt(1) + "\ndo " + getOperandAt(3);
	}
	
		
		 

}
