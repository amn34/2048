package view;

import static logic.PropertyChangeEnabledGameControls.PROPERTY_BOARD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import logic.PropertyChangeEnabledGameControls;
import models.Block;

public class GamePanel extends JPanel implements PropertyChangeListener, KeyListener{
	
    /**
     * The UID of this class (to avoid warnings).
     */
    private static final long serialVersionUID = 4269666L;
    
    /**
     * The font used by this panel.
     */
    private static final Font FONT = new Font("SansSerif", Font.BOLD, 9);
    
    /**
     * The stroke used for painting.
     */
    private static final BasicStroke STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT,
                                                              BasicStroke.JOIN_MITER, 2,
                                                              new float[] {2, 2, 2, 2}, 0);
    /**
     * The size in pixels of a side of one "square" on the grid.
     */
    private static final int SQUARE_SIZE = 150;
    
    
    /**
     * The game grid 
     */
    private Block[][] myBoard;
    
    /**
     * Controller for the game. 
     */
    private PropertyChangeEnabledGameControls myGame;
    
    /**
     * Construct a panel for the game to abe drawn on. 
     * @param theWidth width of the 2D grid of Terrain that defines the map
     * @param theHeight height of the 2D grid of Terrain that defines the map
     */
    public GamePanel(final int theWidth, final int theHeight,
    		final PropertyChangeEnabledGameControls theGame) {
        super();
        
        //4x4 grid 
        myBoard = new Block[4][4];
        myGame = theGame;
        setPreferredSize(new Dimension(theWidth * SQUARE_SIZE,
                                       theHeight * SQUARE_SIZE));
        setBackground(Color.BLACK);
        setFont(FONT);
        
        addKeyListener(this);        
    }
    
    /**
     * Focus on the panel to allow it to receive key inputs 
     * 
     */
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    } 
    

    /**
     * Paints this panel on the screen with the specified Graphics object.
     * 
     * @param theGraphics The Graphics object.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2 = (Graphics2D) theGraphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(STROKE);
        // draw game board map
        drawBoard(g2);
    }
    
    /**
     * Draws the game board with the specified Graphics2D object. 
     * @param theGraphics the Graphics2D object. 
     */
    private void drawBoard(final Graphics2D theGraphics) {
    	
    	for(int i = 0; i < myBoard.length; i++) {
    		for(int j = 0; j < myBoard[i].length; j++) {
    			if(myBoard[i][j] != null) {
        			drawBlock(theGraphics, i, j, myBoard[i][j]);
    			}
    		}
    	} 	
    }
    
   /**
    * Draws the block at its specified position. 
    * @param theGraphics the Graphics2D object
    * @param i row-number
    * @param j column-number
    * @param theBlock block to be drawn 
    */
    private void drawBlock(Graphics2D theGraphics, int i, int j, Block theBlock) {

    	final URL url  = GamePanel.class.getResource("/" + theBlock.getImageFileName() + ".png");
    	ImageIcon imgIcon = new ImageIcon(url);

        final Image img = imgIcon.getImage();
        theGraphics.drawImage(img, j * SQUARE_SIZE, i * SQUARE_SIZE,
                     SQUARE_SIZE, SQUARE_SIZE, this);

    }
    
	@Override
	public void propertyChange(final PropertyChangeEvent theEvent) {
		switch(theEvent.getPropertyName()) {
			case PROPERTY_BOARD:
				addNotify(); //gives focus back to the panel so it can hear key inputs
				myBoard = (Block[][]) theEvent.getNewValue();
				repaint();
				
		}
		
	}

	@Override
	public void keyPressed(KeyEvent theKeyPress) {
		int keyCode = theKeyPress.getKeyCode();
		switch(keyCode) {
			case KeyEvent.VK_UP:
				//handle up
				myGame.moveUp();
				break;

			case KeyEvent.VK_DOWN:
				//handle down
				myGame.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				//handle left
				myGame.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				//handle right
				myGame.moveRight();
				break;
			default:
				break;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {} //do nothing	

	@Override
	public void keyTyped(KeyEvent arg0) {} //do nothing
	
	
} //end class GamePanel
