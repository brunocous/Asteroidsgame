package asteroids.model.programs.expressions;


import asteroids.Error.IllegalMaxSpeedException;
import asteroids.Error.IllegalPositionException;
import asteroids.Error.IllegalRadiusException;
import asteroids.model.Ship;


public class GetY extends DoubleRepresentation {
	
	private EntityRepresentation subExpression;
	
	public GetY(EntityRepresentation subExpression) throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		
		setSubExpression(subExpression);
		
	}
	
	public void setSubExpression(EntityRepresentation subexpression) throws IllegalMaxSpeedException, IllegalPositionException, IllegalRadiusException{
		
		if(canHaveAsSubExpression(subexpression)){
			this.subExpression= subexpression;
		}
		else{
			this.subExpression= new Entity(new Ship());
		}
	}
	
	public boolean canHaveAsSubExpression(EntityRepresentation subexpression){
		return true;
		//TODO implementeren
	}
	

	public boolean isMutable(){
		return false;
	}

	@Override
	public double getJavaDouble() {
		
		return subExpression.getSpaceObject().getPos().getY();
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {

		return (subExpression.equals(expression));
	}


	
	
	

}
