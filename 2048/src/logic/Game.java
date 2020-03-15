package logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

import models.Block;

public class Game implements PropertyChangeEnabledGameControls{

	
	
	//TODO Fix multiline combinations in one move 
	
	
	
    /**
     * The game grid 
     */
    private Block[][] myBoard;
	
    /**
     * Manager for Property Change Listeners. 
     */
    private final PropertyChangeSupport myPcs;
	
    private Random myRandom;

    
    
	public Game(final Block[][] theBoard) {
		myBoard = theBoard.clone();
		myPcs = new PropertyChangeSupport(this);
		myRandom = new Random();
		
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
	public void moveLeft() {
		boolean changed = false;
		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard[i].length; j++) {
				if(i != 0) {
					//if the board changed the set the flag to add a block
					if(shiftLeft(i, j)) {
						changed = true;
					}
				}
			}
		}
		if(changed) {
			newBlock();
		}
		fireBoardChange();	
	}	

	//combines the blocks on the top first
	//returns whether or not the board changed
	private boolean shiftLeft(int i, int j) {
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
					y = 0; //break the loop idk how to do this properly
				}
			}
		}
		return changed;
	}
	
	
	
	@Override
	public void moveRight() {
		boolean changed=  false;
		for(int i = myBoard.length - 1; i >= 0; i--) {
			for(int j = 0; j < myBoard[i].length; j++) {
				if(i != myBoard.length) {
					if(shiftRight(i, j)) {
						changed = true;
					}
				}
			}
		}
		if(changed) {
			newBlock();
		}
		fireBoardChange();
	}
	
	//combines the blocks on the bottom first 
	private boolean shiftRight(int i, int j) {
		boolean changed = false;
		for(int y = i; y < myBoard.length - 1; y++) {
			
			if (myBoard[y][j] != null) {
				if(myBoard[y+1][j] == null) {
					myBoard[y+1][j] = myBoard[y][j];
					myBoard[y][j] = null;
					changed =  true;
				} else if (myBoard[y+1][j].getNumber() == myBoard[y][j].getNumber()) {
					myBoard[y+1][j].combine();
					myBoard[y][j] = null;
					changed = true;
					y = myBoard.length; //break the loop idk how to do this properly
				}	
			}
		}
		return changed;
	}
	
	@Override
	public void moveUp() {
		boolean changed = false;
		for(int j = 0; j < myBoard[0].length; j++) {
			for(int i = 0; i < myBoard.length; i++) {
				if(j != 0) {
					if(shiftUp(i,j)) {
						changed = true;
					}
				}
			}
		}
		if(changed) {
			newBlock();
		}
		fireBoardChange();
	}
	
	//combines the blocks on the left first 
	private boolean shiftUp(int i, int j) {
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
					x = 0; //break the loop idk how to do this properly
				}	
			}
		}
		return changed;
	}
	

	@Override
	public void moveDown() {
		boolean changed = false;
		for(int j = myBoard[0].length - 1; j >= 0 ; j--) {
			for(int i = 0; i < myBoard.length; i++) {
				if(j != myBoard[0].length - 1) {
					if(shiftDown(i,j)) {
						changed = true;
					}
				}
			}
		}
		if(changed) {
			newBlock();
		}
		fireBoardChange();
	}
	
	private boolean shiftDown(int i, int j) {
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
					x = myBoard[i].length; //break the loop idk how to do this properly
				} 
			}
		}
		return changed;
	}
	
	private void newBlock() {
		
		int i = myRandom.nextInt(myBoard.length);
		int j = myRandom.nextInt(myBoard[0].length);
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
     * Inform PropertyChagneListeners of the current state of vehicles.
     */
    private void fireBoardChange() {
        myPcs.firePropertyChange(PROPERTY_BOARD, null, myBoard);
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
