package asteroids.Error;

import asteroids.model.programs.IEntry;

public class IllegalOperandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1352437978950722334L;

	public IllegalOperandException(IEntry targetOperand, IEntry Operand){
		this.setTargetOperand(targetOperand);
		this.setOperand(Operand);
	}
	
	public IEntry getTargetOperand() {
		return targetOperand;
	}
	public void setTargetOperand(IEntry targetOperand) {
		this.targetOperand = targetOperand;
	}

	public IEntry getOperand() {
		return Operand;
	}

	public void setOperand(IEntry operand) {
		Operand = operand;
	}

	private IEntry targetOperand;
	private IEntry Operand;
	
	
}
