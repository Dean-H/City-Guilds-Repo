import java.awt.Color;
import java.awt.Graphics;


/*
 * A circle class used to hold all the information for the cirlces used in the game.
 */
public class Circle {
	
	//The x and y co-ordinate of where they are placed on the Panel
	private int x;	
	private int y;
	
	//The radius (size) of the circle and the color of the circle.
	private int radius;
	private Color color;
	
	//Wether or not the circle is visible and if it has a filled color or not.
	private boolean isFilled = false;
	private boolean isVisible = false;
	
	//Constructor which takes all of the information required to make a circle
	public Circle(int newX, int newY, int newRadius, Color newColor, boolean newIsFilled){
		x = newX;
		y = newY;
		radius = newRadius;
		color = newColor;
		isFilled = newIsFilled;
	}
	
	/*
	 * The draw method of the circle which is what is called to actually display the circle
	 * if the circle is filled it draws a filled circle, otherwise it draws an empty circle.
	 * This method is called from the paintComponent method in our GamePanel, when the circles
	 * are required to be drawn on the game panel.
	 */
	public void draw(Graphics g){
		g.setColor(color);
		
		if(isFilled){
			g.fillOval(x, y, radius * 2, radius * 2);
		}else{
			g.drawOval(x, y, radius * 2, radius * 2);
		}
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
}
