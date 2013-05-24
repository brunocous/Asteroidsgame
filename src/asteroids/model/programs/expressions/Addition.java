package asteroids.model.programs.expressions;

public class Addition extends BinaryMath {

	public Addition(Expression leftExpression, Expression rightExpression) {

		super(leftExpression, rightExpression);

	}

	public Double getRealValue() {

		DoubleLiteral constant1 = (DoubleLiteral) (getOperandAt(1).getValue());
		DoubleLiteral constant2 = (DoubleLiteral) (getOperandAt(2).getValue());
		return constant1.getRealValue() + constant2.getRealValue();

	}

}
