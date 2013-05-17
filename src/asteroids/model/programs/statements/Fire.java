package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.expressions.Entity;
import asteroids.model.*;

public class Fire extends ActionStatement {

	private Entity ship = null;
	
	public Fire(){
		
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index == 1)
			return getShip();
		else throw new IndexOutOfBoundsException();
	}

	public Entity getShip(){
		return ship;
	}
	@Override
	public int getNbOperands() {
		return 1;
	}

	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(index == 1)
			setShip((Entity) operand);

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
		Ship ship = (Ship) getShip().getSpaceObject();
		try{ ship.fireObject(new Bullet(ship));
		}catch (Exception e){
			assert false;
		}

	}
	@Override
	public String toString(){
		return getOperandAt(1) + " fires a bullet!!";
	}
	@Override
	public void setShip(Entity ship) throws IllegalOperandException{
		if(canHaveAsOperandAt(1,ship))
		this.ship = ship;
		else throw new IllegalOperandException();
	}
	

}
