package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.programs.expressions.EntitySequence;
import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Variable;
import asteroids.model.programs.kind.Kind;

public class Foreach extends StructuralStatement {

	private Expression entities = null;
	private Expression localVar;
	private Statement body;
	private final Kind kind;
	
	public Foreach(Kind kind, Variable localVar, StructuralStatement body) throws IllegalOperandException{
		this.localVar = localVar;
		this.body = body;
		if(canHaveAsOperandAt(4, kind))
			this.kind = kind;
		else throw new IllegalOperandException();
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getEntities();
		if(index == 2)
			return getLocalVar();
		if(index == 3)
			return getBody();
		if(index == 4)
			return getKind();
		throw new IndexOutOfBoundsException();
		
	}
	public Expression getEntities(){
		return entities;
	}
	public Expression getLocalVar(){
		return localVar;
	}
	public Statement getBody(){
		return body;
	}
	public Kind getKind(){
		return kind;
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
			this.entities = (Expression) operand;
		if(index == 2)
			this.localVar = (Expression) operand;
		if(index == 3)
			this.body = (Statement) operand;

	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand.getClass().isAssignableFrom(EntitySequence.class))
				return true;
			if(index == 2 && operand.getClass().isAssignableFrom(Variable.class))
				return true;
			if(index == 3 && operand.getClass().isAssignableFrom(StructuralStatement.class)){
				return !((StructuralStatement) operand).containsAnActionStatement() 
						&& ((StructuralStatement) operand).isTypeChecked();
			}
			if(index == 4 && operand.getClass().isAssignableFrom(Kind.class))
				return Kind.isValidKind((Kind) operand);
		}
			return false;
	}

	@Override
	public void execute() {
		for(Entity entity: ((EntitySequence) getEntities()).getAllEntities()){
			((Variable) getLocalVar()).setValue(entity); 
			getBody().execute();
		}
	}
	public void setShip(Entity ship) throws IllegalOperandException{
		setOperandAt(1, EntitySequence.getEntitySequence( ship.getRealValue().getWorld(), getKind()));
		getBody().setShip( ship );
	}
	@Override
	public String toString(){
		return "For each " + getOperandAt(4) + "\nof  " + getOperandAt(1) + "\ndo " + getOperandAt(3);
	}
	@Override
	public boolean isTypeChecked() {
		return canHaveAsOperandAt(2,getLocalVar()) 
				&& canHaveAsOperandAt(3, getBody());
				
	}
	
		
		 

}
