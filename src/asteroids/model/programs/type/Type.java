package asteroids.model.programs.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			return getGeneralType().getClassReference();
		}
		
		@Override
		public GeneralType getGeneralType(){
			return GeneralType.NULL;
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
	public static boolean isValidEntityType(Type type){
		List<Type> validTypes = new ArrayList<Type>();
		for(Type t: Type.values()){
			if(t.getGeneralType() == GeneralType.ENTITY)
				validTypes.add(t);
		}
		return validTypes.contains(type);
	}

	


}
