package asteroids.model.programs;

import java.util.LinkedList;
import java.util.List;

import asteroids.model.programs.expressions.*;
import asteroids.model.programs.parsing.ProgramFactory;
import asteroids.model.programs.statements.Statement;
import be.kuleuven.cs.som.annotate.Raw;

public class ProgramFactoryImpl<E extends Expression, S extends Statement, T extends Expression> implements ProgramFactory{

	@Override
	public E createDoubleLiteral(int line, int column, double d) {
		return (E) new Constant(d);
	}

	@Override
	public E createBooleanLiteral(int line, int column, boolean b) {
		return (E) new Bool(b);
	}

	@Override
	public Expression createAnd(int line, int column, E e1, E e2) {
		return (E) new LogicAnd((BooleanRepresentation) e1, (BooleanRepresentation) e2);
	}

	@Override
	public Object createOr(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createNot(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createNull(int line, int column) {
		return (E) new Null();
	}

	@Override
	public Object createSelf(int line, int column) {
		return (E) new Self(null);
	}

	@Override
	public Object createGetX(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetY(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetVX(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetVY(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetRadius(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createVariable(int line, int column, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createLessThan(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGreaterThan(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createLessThanOrEqualTo(int line, int column, Object e1,
			Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGreaterThanOrEqualTo(int line, int column, Object e1,
			Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createEquality(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createInequality(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAdd(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSubtraction(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createMul(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createDivision(int line, int column, Object e1, Object e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSqrt(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetDirection(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSin(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createCos(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createEnableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createDisableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createFire(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createTurn(int line, int column, Object angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAssignment(int line, int column, String variable,
			Object rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createIf(int line, int column, Object condition, Object then,
			Object otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createWhile(int line, int column, Object condition,
			Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createForeach(int line, int column, ForeachType type,
			String variableName, Object body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSkip(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSequence(int line, int column, List statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createPrint(int line, int column, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createEntityType() {
		// TODO Auto-generated method stub
		return null;
	}

}
