package logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.Stack;

import models.Block;
import structures.SizedStack;




//TODO add a game over




public class Game implements PropertyChangeEnabledGameControls{

	
    /**
     * The game grid 
     */
    private Block[][] myBoard;
	
    /**
     * Manager for Property Change Listeners. 
     */
    private final PropertyChangeSupport myPcs;
	
    /**
     * Random number generator to decide where to place new blocks 
     */
    private Random myRandom;

    /**
     * Previous player states, stores up to 5 reverts
     */
    private Stack<Block[][]> myPreviousState;
    
	public Game(final Block[][] theBoard) {
		myBoard = theBoard.clone();
		myPcs = new PropertyChangeSupport(this);
		myRandom = new Random();
		//stores up to 5 previous game states
		myPreviousState = new SizedStack<Block[][]>(5);
		
		//initial board state, need to mirror this in the GUI class  
		initBoard();
	}
	
	
	/**
	 * Starts the game with two blocks on the board. 
	 */
	private void initBoard() {
		newBlock();
		newBlock();
	}
	
	
	
	@Override
	public void moveUp() {
		boolean changed = false;
		for(int j = 0; j < myBoard[0].length; j++) {
			for(int i = 0; i  < myBoard.length; i++) {
				if(i != 0 && myBoard[i][j] != null) {
					//if the board is shifted then 
					if(shiftUp(i,j)) {
						changed = true;
					}	
				}
			}
		}
		moveComplete(changed);
	}	

	//combines the blocks on the top first
	//returns whether or not the board changed
	private boolean shiftUp(int i, int j) {
		boolean changed = false;
		for(int y = i; y > 0; y--) {	
			if(myBoard[y][j] != null) {
				if(myBoard[y-1][j] == null) {
					myBoard[y-1][j] = myBoard[y][j];
					myBoard[y][j] = null;
					changed = true;
				} else if(myBoard[y-1][j].getNumber() == myBoard[y][j].getNumber()) {
					myBoard[y-1][j].combine();
					myBoard[y][j] = null;
					changed = true;
					break;
				} else {
					break;
				}
			}
		}
		return changed;
	}
	
	@Override
	public void moveDown() {
		boolean changed=  false;
		for(int i = myBoard.length - 1; i >= 0; i--) {
			for(int j = 0; j < myBoard[i].length; j++) {
				if(i != myBoard.length - 1 && myBoard[i][j] != null) {
					//if the board is shifted then 
					if(shiftDown(i,j)) {
						changed = true;
					}	
				}
			}
		}
		moveComplete(changed);
	}
	
	//combines the blocks on the bottom first 
	private boolean shiftDown(int i, int j) {
		boolean changed = false;
		
		for(int y = i; y < myBoard.length - 1; y++) {
			//check if there is a block
			if (myBoard[y][j] != null) {
				//if the space below is empty then move it
				if(myBoard[y+1][j] == null) {
					myBoard[y+1][j] = myBoard[y][j];
					myBoard[y][j] = null;
					changed =  true;
				//other wise check if it can combine, otherwise stop its movement
				} else if (myBoard[y+1][j].getNumber() == myBoard[y][j].getNumber()) {
					myBoard[y+1][j].combine();
					myBoard[y][j] = null;
					changed = true;
					break;
				} else {
					break;
				}
			}
		}
		return changed;
	}
	
	@Override
	public void moveLeft() {
		boolean changed = false;
		for(int j = 0; j < myBoard[0].length; j++) {
			for(int i = 0; i < myBoard.length; i++) {
				if(j != 0 && myBoard[i][j] != null) {
					//if the board is shifted then 
					if(shiftLeft(i,j)) {
						changed = true;
					}	
				}
			}
		}
		moveComplete(changed);
	}
	
	//combines the blocks on the left first 
	private boolean shiftLeft(int i, int j) {
		boolean changed = false;
		for(int x = j; x > 0; x--) {
			
			if (myBoard[i][x] != null) {
				if(myBoard[i][x-1] == null) {
					myBoard[i][x-1] = myBoard[i][x];
					myBoard[i][x] = null;
					changed = true;
				} else if (myBoard[i][x-1].getNumber() == myBoard[i][x].getNumber()) {
					myBoard[i][x-1].combine();
					myBoard[i][x] = null;
					changed = true;
					break;
				} else {
					break;
				}
			}
		}
		return changed;
	}
	

	@Override
	public void moveRight() {
		boolean changed = false;
		for(int j = myBoard[0].length - 1; j >= 0 ; j--) {
			for(int i = 0; i < myBoard.length; i++) {
				if(j != myBoard[0].length - 1 && myBoard[i][j] != null) {
					//if the board is shifted then 
					if(shiftRight(i,j)) {
						changed = true;
					}	
				}
			}
		}
		moveComplete(changed);
	}
	
	private boolean shiftRight(int i, int j) {
		boolean changed = false;
		for(int x = j; x < myBoard[i].length - 1; x++) {
			if (myBoard[i][x] != null) {
				if(myBoard[i][x+1] == null) {
					myBoard[i][x+1] = myBoard[i][x];
					myBoard[i][x] = null;
					changed = true;
				} else if(myBoard[i][x+1].getNumber() == myBoard[i][x].getNumber()) {
					myBoard[i][x+1].combine();
					myBoard[i][x] = null;
					changed = true;
					break;
				}  else { //there is a block and it can't be moved so we stop movement
					break;
				}
			}
		}
		return changed;
	}
	
	
	private void moveComplete(final boolean changed) {
		if(changed) {
			myPreviousState.push(cloneBoard());
			newBlock();
			fireBoardChange();
		}
		checkGameOver();
	}
	
	//TODO finish to check for game over 
	private void checkGameOver() {
		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard[0].length; j++) {
				if(checkRight() || checkDown()) {
					return;
				}
			}
		}
		fireGameOver();
	}
	
	private Block[][] cloneBoard() {
		Block[][] temp = new Block[myBoard[0].length][myBoard.length];
		
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				if(myBoard[i][j] != null) {
					temp[i][j] = new Block(myBoard[i][j].getNumber());
				}
			}
		}
		
		return temp;
	}
	
	
	//TODO add check for game over 
	/**
	 * Checks if the block to the left has the same value
	 * @return whether the blocks are the same 
	 */
	private boolean checkRight() {
		return true;
	}
	
	//TODO add check for game over
	/**
	 * Checks if the block below has the same value
	 * @return whether the blocks are the same
	 */
	private boolean checkDown() {
		return true;
	}
	
	
	
	
	private void newBlock() {
		int i = myRandom.nextInt(myBoard.length);
		int j = myRandom.nextInt(myBoard[0].length);
		//keep cycling through values of i,j until we get a valid one 
		while(myBoard[i][j] != null) {
			i = myRandom.nextInt(myBoard.length);
			j = myRandom.nextInt(myBoard[0].length);
		}
		myBoard[i][j] = new Block();
	}
	
	public void start() {
		fireBoardChange();
	}
	
	/**
	 * Wipes the board clean. 
	 */
	public void clear() {
		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard[0].length; j++) {
				myBoard[i][j] = null;
			}
		}
		initBoard();
		fireBoardChange();
	}
	
	/**
	 * Reverts back one game state if available
	 */
	public void revert() {
		if(!myPreviousState.isEmpty()) {
			myBoard = myPreviousState.pop().clone();
		} 
		fireBoardChange();
	}
	
	
    /**
     * Inform PropertyChagneListeners of the current state of vehicles.
     */
    private void fireBoardChange() {
        myPcs.firePropertyChange(PROPERTY_BOARD, null, myBoard);
    }
    
    //TODO somehting that uses this 
	private void fireGameOver() {
		myPcs.firePropertyChange(PROPERTY_GAME_OVER, null, myBoard);
	}
    
    
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
    

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }
    
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
        
    }
}
