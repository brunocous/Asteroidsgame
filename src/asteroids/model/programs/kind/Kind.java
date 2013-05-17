package asteroids.model.programs.kind;

import java.util.Arrays;

import asteroids.model.programs.IEntry;

public enum Kind implements IEntry{
	ANY,SHIP,ASTEROID,BULLET;

	@Override
	public boolean hasAsSubEntry(IEntry subEntry) {
		return this == subEntry;
	}
	public static boolean isValidKind(Kind kind){
		return Arrays.asList(values()).contains(kind);
	}
}
