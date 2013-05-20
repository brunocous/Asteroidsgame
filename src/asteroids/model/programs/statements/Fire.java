package asteroids.model.programs.statements;

import java.util.Map;

import asteroids.model.*;
import asteroids.model.programs.type.Type;

public class Fire extends ShipActionStatement {
	
	@Override
	public boolean execute() {
		Ship ship = (Ship) getShip().getRealValue();
		try{ ship.fireObject(new Bullet(ship));
		}catch (Exception e){
			assert true;
		}
		return true;
	}
	@Override
	public String toString(){
		return getOperandAt(1) + " fires a bullet!!";
	}
	@Override
	public boolean isTypeChecked(Map<String, Type> globals) {
		return true;
	}
}
