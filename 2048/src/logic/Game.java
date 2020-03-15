package logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import models.Block;

public class Game implements PropertyChangeEnabledGameControls{

	
	
	
	
    /**
     * The game grid 
     */
    private Block[][] myBoard;
	
    /**
     * Manager for Property Change Listeners. 
     */
    private final PropertyChangeSupport myPcs;
	
	
	public Game(final Block[][] theBoard) {
		myBoard = theBoard.clone();
		myPcs = new PropertyChangeSupport(this);
	}
	
	
	
	
	@Override
	public void moveUp() {
		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard[i].length; j++) {
				if(i != 0) {
					shiftUp(i, j);
				}
			}
		}
		fireBoardChange();	
	}


	//combines the blocks on the top first
	private void shiftUp(int i, int j) {
		for(int y = i; y > 0; y--) {
			if(myBoard[y-1][j] == null) {
				myBoard[y-1][j] = myBoard[y][j];
				myBoard[y][j] = null;
			} else if(myBoard[y-1][j].getNumber() == myBoard[y][j].getNumber()) {
				myBoard[y-1][j].combine();
				myBoard[y][j] = null;
			} else {
				break;
			}
		}
	}
	
	
	
	@Override
	public void moveDown() {
		for(int i = myBoard.length - 1; i >= 0; i--) {
			for(int j = 0; j < myBoard[i].length; j++) {
				if(i != myBoard.length) {
					shiftDown(i, j);
				}
			}
		}
		fireBoardChange();
	}
	
	//combines the blocks on the bottom first 
	private void shiftDown(int i, int j) {
		
		for(int y = i; y < myBoard.length - 1; y++) {
			if(myBoard[y+1][j] == null) {
				myBoard[y+1][j] = myBoard[y][j];
				myBoard[y][j] = null;
			} else if (myBoard[y+1][j].getNumber() == myBoard[y][j].getNumber()) {
				myBoard[y+1][j].combine();
				myBoard[y][j] = null;
			} else {
				break;
			}
		}
	}
	
	@Override
	public void moveLeft() {
		
		for(int j = 0; j < myBoard[0].length; j++) {
			for(int i = 0; i < myBoard.length; i++) {
				if(j != 0) {
					shiftLeft(i,j);
				}
			}
		}
		fireBoardChange();
	}
	
	//combines the blocks on the left first 
	private void shiftLeft(int i, int j) {
		for(int x = j; x > 0; x--) {
			if(myBoard[i][x-1] == null) {
				myBoard[i][x-1] = myBoard[i][x];
				myBoard[i][x] = null;
			} else if (myBoard[i][x-1].getNumber() == myBoard[i][x].getNumber()) {
				myBoard[i][x-1].combine();
				myBoard[i][x] = null;
			} else {
				break;
			}
		}
	}
	

	@Override
	public void moveRight() {
		for(int j = myBoard[0].length - 1; j >= 0 ; j++) {
			for(int i = 0; i < myBoard.length; i++) {
				if(j != 0) {
					shiftRight(i,j);
				}
			}
		}
		fireBoardChange();
	}
	
	private void shiftRight(int i, int j) {
		for(int x = j; x < myBoard[i].length; x++) {
			if(myBoard[i][x+1] == null) {
				myBoard[i][x+1] = myBoard[i][x];
				myBoard[i][x] = null;
			} else if (myBoard[i][x+1].getNumber() == myBoard[i][x].getNumber()) {
				myBoard[i][x+1].combine();
				myBoard[i][x] = null;
			} else {
				break;
			}
		}
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
