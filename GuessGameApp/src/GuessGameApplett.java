import javax.swing.JApplet;


public class GuessGameApplett extends JApplet {

	/**
	 * Create the applet.
	 */
	public GuessGameApplett() {
		GamePanel panel = new GamePanel();
		setContentPane(panel);
	}
	
	
	public void init(){		
		setSize(800, 400);
	}

}
