package asteroids.model.programs.statements;

import asteroids.model.*;

public class Fire extends ShipActionStatement {
	
	@Override
	public void execute() {
		Ship ship = (Ship) getShip().getRealValue();
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
