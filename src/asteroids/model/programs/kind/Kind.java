package asteroids.model.programs.kind;

import java.util.Arrays;

import asteroids.model.*;
import asteroids.model.programs.IEntry;

public enum Kind implements IEntry{
	ANY{
		public Class<?> getClassReference(){
			return SpaceObject.class;
		}
	}
	,SHIP{ 
		public Class<?> getClassReference(){
			return Ship.class;
		}
	}
	,ASTEROID{
		public Class<?> getClassReference(){
			return Asteroid.class;
		}
	}
	,BULLET{
		public Class<?> getClassReference(){
			return Bullet.class;
		}
	};
	

	@Override
	public boolean hasAsSubEntry(IEntry subEntry) {
		return this == subEntry;
	}
	public static boolean isValidKind(Kind kind){
		return Arrays.asList(values()).contains(kind);
	}
	public abstract Class<?> getClassReference();
	@Override
	public boolean isTypeChecked() {
		return true;
	}
}
