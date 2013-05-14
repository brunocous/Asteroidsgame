package asteroids.model.programs.expressions;


import java.util.ArrayList;

import asteroids.Util;
import asteroids.model.programs.types.BooleanLiteral;
import asteroids.model.programs.types.Type;


public class Equals extends BooleanRepresentation{

	
	private Expression leftExpression;
	private Expression rightExpression;

	public Equals(Expression leftExpression, Expression rightExpression){
		
		setSubExpressions(leftExpression, rightExpression);
		
	}
	
	public void setSubExpressions(Expression leftExpression,Expression rightExpression){
		
		if(canHaveAsExpressions(leftExpression,rightExpression)){
			this.leftExpression = leftExpression;
			this.rightExpression = rightExpression;
		}
		else{
			this.leftExpression= new Constant(0);
			this.rightExpression = new Constant(1);
		}
	}
	
	public boolean canHaveAsExpressions(Expression leftExpression,Expression rightExpression){
		
		return true;
		//TODO implementeren
	}
	

	
	public boolean isMutable(){
		
		return false;
		
	}

	@Override
	public boolean getJavaBoolean() {
		//TODO voorwaarden
		if(//leftExpression.getValue() geeft een Bool terug){
			return ((BooleanRepresentation)leftExpression).equals(((BooleanRepresentation)rightExpression));
	}
		else if(//leftExpression.getValue() geeft een Constant terug){
			return Util.fuzzyEquals(((DoubleRepresentation)leftExpression).equals((DoubleRepresentation)rightExpression));
	}
		else if(//leftExpression.getValue() geeft een Entity terug){
			return ((EntityRepresentation)leftExpression).equals((EntityRepresenation)rightExpression);
			}
	
			else return false;
	}

	@Override
	public boolean hasAsSubExpression(Expression expression) {
		
		return (leftExpression.equals(expression)||rightExpression.equals(expression));
	}




}

