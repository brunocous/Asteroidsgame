package asteroids.model.programs.statements;


import asteroids.model.*;


public class Fire extends ShipActionStatement {
	
	@Override
	public boolean execute() {
		System.out.println("Schiet!");
		try{ getProgram().getShip().fireObject(new Bullet(getProgram().getShip()));
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
