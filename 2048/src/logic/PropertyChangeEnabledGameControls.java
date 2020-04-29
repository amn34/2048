package logic;

import java.beans.PropertyChangeListener;


public interface PropertyChangeEnabledGameControls extends GameControls{

	
	String PROPERTY_EXAMPLE = "THIS IS AN EXAMPLE";
	
	String PROPERTY_BOARD = "board";
	
	String PROPERTY_GAME_OVER = "game over";
	
	
	void addPropertyChangeListener(PropertyChangeListener theListener);
	
	void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    void removePropertyChangeListener(PropertyChangeListener theListener);
    
    void removePropertyChangeListener(String thePropertyName, 
            PropertyChangeListener theListener);
	 
}
