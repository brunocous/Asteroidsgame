package asteroids.model.programs;

import java.util.List;

import asteroids.model.programs.expressions.*;
import asteroids.model.programs.parsing.ProgramFactory;
import asteroids.model.programs.statements.*;
import asteroids.model.programs.type.*;

public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type>{

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(d);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(b);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return new LogicAnd(e1,e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return new LogicOr(e1,e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new Negation(e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new Null();
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new Self();
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetX(e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetY(e);
	}

	@Override
	public Expression createGetVX(int line, int column, Expression e) {
		return new GetVX(e);
	}

	@Override
	public Expression createGetVY(int line, int column, Expression e) {
		return new GetVY(e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(e);
	}

	@Override
	public Expression createVariable(int line, int column, String name) {
		return new Variable(name);
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThan(e1,e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new GreaterThan(e1,e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqualTo(e1,e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new GreaterThanOrEqualTo(e1,e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		
		return new Equals(e1,e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new NotEqualTo(e1,e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Addition(e1,e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Substraction(e1,e2);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiplication(e1,e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(e1,e2);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new Sqrt(e);
	}

	@Override
	public Expression createGetDirection(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return new Sine(e);
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return new Cosine(e);
	}

	@Override
	public Statement createEnableThruster(int line, int column) {
		return new Thrust(true);
	}

	@Override
	public Statement createDisableThruster(int line, int column) {
		return new Thrust(false);
	}

	@Override
	public Statement createFire(int line, int column) {
		return new Fire();
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(angle);
	}

	@Override
	public Statement createAssignment(int line, int column, String variable,
			Expression rhs) {
		return new Assignement(variable, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new IfThenElse(condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new While(condition,body);
	}

	@Override
	public Statement createForeach(int line, int column,
			asteroids.model.programs.parsing.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return new Foreach(convertForeachType(type),variableName,body);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip();
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(e);
	}

	@Override
	public Type createDoubleType() {
		return Type.DOUBLE;
	}

	@Override
	public Type createBooleanType() {
		return Type.BOOLEAN;
	}

	@Override
	public Type createEntityType() {
		return Type.ANY;
	}
	private static Type convertForeachType(ForeachType type){
		Type result;
		switch(type){
		case ANY:
			result = Type.ANY; 
			break;
		case BULLET:
			result = Type.BULLET; 
			break;
		case SHIP:
			result = Type.SHIP; 
			break;
		case ASTEROID:
			result = Type.ASTEROID; 
			break;
		default: result = null; break;
		}
		return result;
	}


}
