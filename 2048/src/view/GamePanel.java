package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import models.Block;

public class GamePanel extends JPanel {
	
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
    private static final int SQUARE_SIZE = 40;
    
    
    /**
     * The game grid 
     */
    private Block[][] myBoard;
    
    /**
     * Construct a new Panel.
     * @param theWidth width of the 2D grid of Terrain that defines the map
     * @param theHeight height of the 2D grid of Terrain that defines the map
     */
    public GamePanel(final int theWidth, final int theHeight) {
        super();
        
        //4x4 grid 
        myBoard = new Block[4][4];
        setPreferredSize(new Dimension(theWidth * SQUARE_SIZE,
                                       theHeight * SQUARE_SIZE));
        setBackground(Color.GREEN);
        setFont(FONT);
        
        
        //TODO Remove this
        //testing
        myBoard[0][1] = new Block();
        myBoard[3][3] = new Block();
    }
    
    // Instance Methods 

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
			final int topy = i * SQUARE_SIZE; //determines the y position of the square
    		for(int j = 0; j < myBoard[i].length; j++) {
    			final int leftx = j * SQUARE_SIZE; //determines the x position of the block
    			if(myBoard[i][j] != null) {
        			drawBlock(theGraphics, i, j, myBoard[i][j]);
    			}
    		}
    	} 	
    }
    
    private void drawBlock(Graphics2D theGraphics, int i, int j, Block theBlock) {
        final String imageFilename = "icons//" + theBlock.getImageFileName() + ".png";
        ImageIcon imgIcon = new ImageIcon(imageFilename);

        if (imgIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            imgIcon = new ImageIcon(getClass().getResource(imageFilename));
        }

        final Image img = imgIcon.getImage();
        theGraphics.drawImage(img, i * SQUARE_SIZE, j * SQUARE_SIZE,
                     SQUARE_SIZE, SQUARE_SIZE, this);

    }
    
    
    private void drawBorderLines(final Graphics2D theGraphics, 
    							 final int theI, final int theJ) {
    	//TODO Draw borders and stuff 
    	for(int i = 0; i < myBoard.length; i++) {
    		//draw the vertical/horizontal lines 
    	}
    }
} //end class GamePanel