package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

    public GameGUI() {
    	super(TITLE);
    	
    	//myGame.start()
    	initGUI();
    	setVisible(true);
    	

    }
    
    
    /**
     * Sets up the GUI
     */
    private void initGUI() {
    	final GamePanel panel = new GamePanel(4, 4);
    	//game.addPropertyChangeLIstener(panel);
    	
    	
    	final Container masterPanel = new JPanel(new BorderLayout());
    	masterPanel.add(panel, BorderLayout.CENTER);
    	add(masterPanel);
    	pack();
    	
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
    	
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
