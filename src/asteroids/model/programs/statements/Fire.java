package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.*;

public class Fire extends ActionStatement {

	private Entity ship;
	
	public Fire(Entity entity) throws IllegalOperandException{
		setOperandAt(1,entity);
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return ship;
		else throw new IndexOutOfBoundsException();
	}

	@Override
	public int getNbOperands() {
		return 1;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(canHaveAsOperandAt(index, operand))
			this.ship = (Entity) operand;
		else throw new IllegalOperandException();

	}

	@Override 
	public boolean canHaveAsOperandAt(int index, IEntry operand){
		if(super.canHaveAsOperandAt(index, operand) && index == 1 
				&& operand.getClass().isAssignableFrom(Entity.class))
				return ((Entity) operand).getSpaceObject().getClass().isAssignableFrom(Ship.class);
		else return false;
	}
	@Override
	public void execute() {
		Ship ship = (Ship) ((Entity) getOperandAt(1)).getSpaceObject();
		try{ ship.fireObject(new Bullet(ship));
		}catch (Exception e){
			assert false;
		}

	}
	@Override
	public String toString(){
		return getOperandAt(1) + " fires a bullet!!";
	}

}
