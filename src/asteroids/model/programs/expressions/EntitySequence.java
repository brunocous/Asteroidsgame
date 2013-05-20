package asteroids.model.programs.expressions;

import java.util.LinkedList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

import asteroids.Error.IllegalOperandException;
import asteroids.model.*;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;
import asteroids.model.programs.type.Type;

public class EntitySequence extends Expression implements IComposedStructure{

	private List<Entity> entities;
	private static final Type TYPE = Type.ANY;
	
	public EntitySequence(){
		entities = null;
	}
	public EntitySequence(List<Entity> entities)throws IllegalOperandException{
		addAllEntities(entities);
	}
	public void addAllEntities(List<Entity> entities) throws IllegalOperandException{
		for(Entity ent: entities){
			addAsEntity(ent);
		}
		
	}
	
	public Type getType(){
		return TYPE;
	}
	public void addAsEntity(@Raw Entity ent) throws IllegalOperandException{
		setOperandAt(getNbOperands()+1, ent);
	}
	@Override
	public boolean hasAsSubEntry(IEntry subEntry) {
		if(subEntry.getClass().isAssignableFrom(Entity.class))
			return hasAsEntity((Entity) subEntry);
		return false;
	}

	public List<SpaceObject> getRealValue(){
		List<SpaceObject> result = new LinkedList<SpaceObject>();
		for(Entity ent: getAllEntities()){
			result.add(ent.getRealValue());
		}
		return result;
	}
	@Override
	public IEntry getOperandAt(int index) throws IndexOutOfBoundsException {
		if(index >0 && index <= getNbOperands())
		return getAllEntities().get(index - 1);
		else throw new IndexOutOfBoundsException();
	}
	public List<Entity> getAllEntities(){
		return entities;
	}
	@Override
	public int getNbOperands() {
		return this.entities.size();
	}
	@Override
	public void setOperandAt(int index, IEntry operand)
			throws IllegalOperandException {
		if(!canHaveAsOperandAt(index, operand))
			throw new IllegalOperandException();
		getAllEntities().add(index-1, (Entity) operand);
		
	}
	public boolean hasAsEntity(Entity entity){
		return getAllEntities().contains(entity);
	}
	@Override
	public boolean canHaveAsOperandAt(int index, IEntry operand) {
		return (!operand.hasAsSubEntry(this) && (index > 0) 
				&& (index <= getNbOperands() +1) 
				&& operand != null
				&& operand.getClass().isAssignableFrom(Entity.class));
	}
	public static EntitySequence makeEntitySequence(World world, Type type){
		     EntitySequence result = new EntitySequence();
	        for(SpaceObject obj: world.getAllSpaceObjects()){
	        	if(obj.getClass().isAssignableFrom(type.getClassReference()))
		         try{result.addAsEntity(new Entity(obj));
		         
		         }catch(IllegalOperandException e){
		        	 assert !result.canHaveAsOperandAt(result.getNbOperands() + 1, new Entity(obj));
		         }
		        }
		      return result;
		    }
	@Override
	public boolean isTypeChecked() {
		return true;
	}
	@Override
	public Expression getValue() {
		return this;
	}
	
	@Override
	public void setShip(Entity ship){
		assert true;
	}

}
