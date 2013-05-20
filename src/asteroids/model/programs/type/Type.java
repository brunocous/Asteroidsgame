package asteroids.model.programs.type;

import java.util.Arrays;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.SpaceObject;
import asteroids.model.programs.IEntry;

public enum Type implements IGeneralType,IEntry {
	DOUBLE{
		public Class<?> getClassReference(){
			return GeneralType.DOUBLE.getClassReference();
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.DOUBLE;
		}
	},
	BOOLEAN{
		public Class<?> getClassReference(){
			return GeneralType.BOOLEAN.getClassReference();
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.BOOLEAN;
		}
	},
	SHIP{
		public Class<?> getClassReference(){
			return Ship.class;
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.ENTITY;
		}
	},
	ASTEROID{
		public Class<?> getClassReference(){
			return Asteroid.class;
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.ENTITY;
		}
	},
	BULLET{
		public Class<?> getClassReference(){
			return Bullet.class;
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.ENTITY;
		}
	},
	NULL{
		public Class<?> getClassReference(){
			return null;
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.ENTITY;
		}
	},
	ANY{
		public Class<?> getClassReference(){
			return SpaceObject.class;
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.ENTITY;
		}
	};
	public abstract GeneralType getGeneralType();
		
	@Override
	public boolean hasAsSubEntry(IEntry entry){
		return false;
	}
	public static boolean isValidType(Type type){
		return Arrays.asList(Type.values()).contains(type);
	}

	


}
