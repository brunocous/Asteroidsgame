package asteroids.model.programs.statements;


import asteroids.model.*;


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
	public boolean isTypeChecked() {
		return true;
	}
}
