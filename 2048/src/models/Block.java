package models;

public class Block {

	private int myNumber;

	/**
	 * Creates a block
	 * 90% chance to be a 2 
	 * 10% chance to be a 4
	 */
	public Block() {
		double rand = Math.random();
		if(rand < 0.8) {
			myNumber = 2;
		} else {
			myNumber = 4;
		}
	}
	
	public Block(int num) {
		myNumber = num;
	}
	
	
	public int getNumber() {
		return myNumber;
	}
	
	public void combine() {
		myNumber = myNumber * 2;
	}
	
	@Override
	public String toString() {
		return Integer.toString(myNumber);
	}
	
	
	/**
	 * Not an overriden method 
	 * I was too lazy to find how to do it properly
	 * @param theBlock Block to compare
	 * @return if this block is equal to the other block
	 */
	public boolean equals(Block theBlock) {
		return myNumber == theBlock.getNumber();
	}
	
	
	public String getImageFileName() {
		return Integer.toString(myNumber);
	}
	
}
