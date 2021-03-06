package view;
import java.awt.EventQueue;

public final class GameMain {
	
	/**
	 * Private constructor to prevent instantiation
	 */
	private GameMain() {
		//do nothing
	}
	
	/**
	 * Constructs the main GUI window frame
	 * 
	 * @param theArgs Command line arguments (ignored).
	 */
	public static void main(String[] theArgs) {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI();     
            }
        });
	}
}
