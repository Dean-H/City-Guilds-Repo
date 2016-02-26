import javax.swing.JApplet;


public class HitAndMissApplet extends JApplet {

	/**
	 * Create the applet.
	 */
	public HitAndMissApplet() {
		HitAndMissGUI panel = new HitAndMissGUI();
		setContentPane(panel);
	}
	
	public void init(){
		setSize(550, 300);
	}

}
