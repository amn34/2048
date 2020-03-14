package logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import models.Block;

public class Game implements PropertyChangeEnabledGameControls{

	Block[][] myBoard; 
	
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
		
	}

	//TODO IDK 
	
	private void shiftUp(int i, int j) {
		for(int x = i; x > 0; x--) {
			if(myBoard[x][j] == null) {
				
			}
		}
		
		
		
	}
	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener theListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener theListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(String thePropertyName, PropertyChangeListener theListener) {
		// TODO Auto-generated method stub
		
	}

}