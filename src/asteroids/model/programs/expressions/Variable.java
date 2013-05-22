package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.Program;
import asteroids.model.programs.type.IGeneralType.GeneralType;
import asteroids.model.programs.type.Type;

public class Variable extends Expression {

	private Expression value;
	private final String name;
	private Type type;

	public Variable(String name) {

		if (!canHaveAsName(name)) {
			this.name = "defaultName";
		} else {
			this.name = name;
		}
		this.value = null;
		this.type = null;
	}

	public Variable(String name, Type type) {
		this(name);
		this.type = type;
	}

	public void setValue(Expression value) {
		if (value != null) {
			if (canHaveAsValue(value)) {
				this.value = value.getValue();
			}
		} else {
			this.value = null;
		}
	}

	public boolean canHaveAsValue(Expression value) {

		return true;

	}

	public String getName() {
		return name;
	}

	public boolean canHaveAsName(String name) {
		return name != null && name != "";
	}

	public Expression getValue() {
		return value;
	}

	@Override
	public Object getRealValue() {
		if (getValue().getType() == Type.DOUBLE) {
			return (double) getValue().getRealValue();
		} else if (getValue().getType() == Type.BOOLEAN) {
			return (boolean) getValue().getRealValue();
		} else if (getValue().getType().getGeneralType() == GeneralType.ENTITY) {
			return (SpaceObject) getValue().getRealValue();
		} else {
			return null;
		}
	}

	@Override
	public boolean hasAsSubEntry(IEntry subEntry) {

		return getValue() != null && getValue().hasAsSubEntry(subEntry);
	}

	@Override
	public boolean isTypeChecked() {
		return getValue().isTypeChecked();
	}

	public void setType(Type type) {
		if (Type.isValidType(type))
			this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);

		if (getValue() != null) {
			getValue().setProgram(program);

			if (getValue() instanceof Variable) {
				setValue(program.getVariable(((Variable) getValue()).getName()));
			}
		}
	}

}