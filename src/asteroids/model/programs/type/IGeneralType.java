package asteroids.model.programs.type;

import asteroids.model.programs.expressions.Entity;

public interface IGeneralType {
	Class<?> getClassReference();
	
	public enum GeneralType implements IGeneralType{
		DOUBLE(double.class),BOOLEAN(boolean.class),ENTITY(Entity.class);
		
		private final Class<?> refClass;
		
		private GeneralType(final Class<?> refClass){
			this.refClass = refClass;
		}
		public Class<?> getClassReference(){
			return refClass;
		}
	}
}
