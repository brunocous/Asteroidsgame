package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.Ship;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.BooleanRepresentation;
import asteroids.model.programs.expressions.Entity;

public class Thrust extends ActionStatement {

	private Entity ship = null;
	private BooleanRepresentation enable;
	
	public Thrust(Entity ship, BooleanRepresentation enable) throws IllegalOperandException{ 
		setOperandAt(2,enable);
	}
	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index ==1)
			 return ship;
		if(index == 2)
			return enable;
		else throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNbOperands() {
		return 2;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index,operand))
			throw new IllegalOperandException();
		if(index == 1)
			this.ship = (Entity) operand;
		if(index == 2)
			this.enable = (BooleanRepresentation) operand;
		

	}

	@Override
	public void execute() {
		Ship tship = (Ship) ((Entity) getOperandAt(1)).getSpaceObject();
		tship.setEnableThruster(((BooleanRepresentation) getOperandAt(2)).getJavaBoolean());
	}
	@Override
	public String toString(){
		return getOperandAt(1) + " sets enable thrusters to " + getOperandAt(2);
	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand)){
			if(index == 1 && operand.getClass().isAssignableFrom(Entity.class))
				return true;
			if(index == 2 && operand.getClass().isAssignableFrom(BooleanRepresentation.class))
				return true;
		}
		return false;
	}

	@Override
	public void setShip(Entity ship) throws IllegalOperandException {
		setOperandAt(1, ship);
		
	}

}
