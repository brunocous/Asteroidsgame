package asteroids.model.programs.statements;

import asteroids.Error.IllegalOperandException;
import asteroids.model.Ship;
import asteroids.model.programs.IEntry;

public class Thrust extends ShipActionStatement {

	private boolean enable;
	
	public Thrust(boolean enable) throws IllegalOperandException{ 
		super();
		this.enable = enable;
	}
	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index ==1)
			 return getShip();
		else throw new IndexOutOfBoundsException();
	}
	public boolean isEnabled(){
		return enable;
	}

	@Override
	public void execute() {
		Ship tship = (Ship) getShip().getRealValue();
		tship.setEnableThruster(isEnabled());
	}
	@Override
	public String toString(){
		return getOperandAt(1) + " sets enable thrusters to " + isEnabled();
	}

}
