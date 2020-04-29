package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Game;
import logic.PropertyChangeEnabledGameControls;
import models.Block;

public class GameGUI extends JFrame implements ActionListener {

	
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 0;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
    /**
     * The window title.
     */
    private static final String TITLE = "2048";
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    
    
    private final JButton myResetButton;
    private final JButton myBackButton;
    
    /**
     * The logic for the simulation. 
     */
    private final PropertyChangeEnabledGameControls myGame;
    
    
    public GameGUI() {
    	super(TITLE);
    	//creates a 4x4 game
    	myGame = new Game(new Block[4][4]);
    	
    	myResetButton = new JButton("RESET");
    	myBackButton = new JButton("BACK");
    	
    	initButtons();
    	initGUI();
    	setVisible(true);
    }
    
    
    /**
     * Sets up the GUI
     */
    private void initGUI() {
    	
    	//main game in the center
    	final GamePanel panel = new GamePanel(4, 4, myGame);
    	myGame.addPropertyChangeListener(panel);
    	((Game) myGame).start();
    	
    	//bottom tool bar
    	final Container southPanel = new JPanel(new FlowLayout());
    	southPanel.add(myBackButton);
    	southPanel.add(myResetButton);
    	
    	final Container masterPanel = new JPanel(new BorderLayout());
    	masterPanel.add(panel, BorderLayout.CENTER);
    	masterPanel.add(southPanel, BorderLayout.SOUTH);
    	
    	
    	add(masterPanel);
    	pack();
    	
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
    	
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initButtons() {
    	myBackButton.addActionListener(theAction -> ((Game) myGame).revert());	
    	myResetButton.addActionListener(theAction -> ((Game) myGame).clear());
    }
    
}
