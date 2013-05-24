package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.Ship;
import asteroids.model.programs.IEntry;

public class Thrust extends ShipActionStatement {

	private boolean enable;
	
	public Thrust(boolean enable){ 
		super();
		this.enable = enable;
	}
	
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index ==1)
			 return getShip();
		else throw new IndexOutOfBoundsException();
	}
	@Basic
	public boolean isEnabled(){
		return enable;
	}

	@Override
	public boolean execute() {
		Ship tship = (Ship) getShip().getRealValue();
		tship.setEnableThruster(isEnabled());
		return true;
	}
	@Override
	public String toString(){
		return getOperandAt(1) + " sets enable thrusters to " + isEnabled();
	}
	@Override 
	public boolean equals(Object other){
		return super.equals(other) && ((Thrust) other).isEnabled() == this.isEnabled();
	}

}
