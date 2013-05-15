package asteroids.model.programs.expressions;

import java.util.LinkedList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Raw;

import asteroids.Error.IllegalOperandException;
import asteroids.model.SpaceObject;
import asteroids.model.programs.IComposedStructure;
import asteroids.model.programs.IEntry;

public class EntitySequence extends EntityRepresentation implements IComposedStructure{

	private List<Entity> entities;
	
	public EntitySequence(List<Entity> entities)throws IllegalOperandException{
		addAllEntities(entities);
	}
	public void addAllEntities(List<Entity> entities) throws IllegalOperandException{
		for(Entity ent: entities){
			addAsEntity(ent);
		}
		
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

	@Override
	public SpaceObject getSpaceObject() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<SpaceObject> getAllSpaceObjects(){
		List<SpaceObject> result = new LinkedList<SpaceObject>();
		for(Entity ent: getAllEntities()){
			result.add(ent.getSpaceObject());
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

}
