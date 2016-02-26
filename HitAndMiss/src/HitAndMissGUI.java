import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;


public class HitAndMissGUI extends JPanel {
	
	String[] randomColorCode = new String[3];	//an array used to hold the randomly generated color code
	
	Circle[] userChosenCircles = new Circle[3];	//an array used to hold the three user chosen circles for each guess
	Circle[] answerCircles = new Circle[3];		//an array to hold the circles generated from the random color code array
	
	ArrayList<Circle> circles = new ArrayList<Circle>(); //an array list used to hold all of the guess circles
	
	
	int circleCounter = 0;	//a counter for the circles so we know which one we're currently using to guess in
	int numGuesses = 1;		//a counter to check what our current guess is
	int colorGuesses = 0;	//a counter used for counting the three color guesses in each guess
	
	boolean gameOver = false;
	
	int x = 350;
	int y = 20;
	int radius = 10;
	
	private JLabel lbl2ndGuess;
	private JLabel lbl3rdGuess;
	private JLabel lbl4thGuess;
	private JLabel lbl5thGuess;
	private JLabel lbl6thGuess;
	private JLabel lblGuessHiddenCode;
	
	

	/**
	 * Create the panel.
	 */
	public HitAndMissGUI() {
		setLayout(null);
		randomColorGenerator();	//initializes our randomly chosen color
		initializeGame();		//does the first initialize game method
		generateEmptyCircles(); //Creates our first three guess circles
		
		
		JButton btnRed = new JButton("Red");
		btnRed.setBounds(10, 27, 89, 23);
		btnRed.addActionListener(myListener);
		add(btnRed);
		
		JButton btnBlue = new JButton("Blue");
		btnBlue.setBounds(93, 27, 89, 23);
		btnBlue.addActionListener(myListener);
		add(btnBlue);
		
		JButton btnGreen = new JButton("Green");
		btnGreen.setBounds(180, 27, 89, 23);
		btnGreen.addActionListener(myListener);
		add(btnGreen);
		
		JButton btnYellow = new JButton("Yellow");
		btnYellow.setBounds(53, 49, 89, 23);
		btnYellow.addActionListener(myListener);
		add(btnYellow);
		
		JButton btnBlack = new JButton("Black");
		btnBlack.setBounds(141, 49, 89, 23);
		btnBlack.addActionListener(myListener);
		add(btnBlack);
		
		JLabel lbl1stGuess = new JLabel("1st Guess");
		lbl1stGuess.setBounds(450, 20, 60, 14);
		add(lbl1stGuess);
		
		lbl2ndGuess = new JLabel("2nd Guess");
		lbl2ndGuess.setBounds(450, 53, 60, 14);
		lbl2ndGuess.setVisible(false);
		add(lbl2ndGuess);
		
		lbl3rdGuess = new JLabel("3rd Guess");
		lbl3rdGuess.setBounds(450, 82, 60, 14);
		lbl3rdGuess.setVisible(false);
		add(lbl3rdGuess);
		
		lbl4thGuess = new JLabel("4th Guess");
		lbl4thGuess.setBounds(450, 113, 60, 14);
		lbl4thGuess.setVisible(false);
		add(lbl4thGuess);
		
		lbl5thGuess = new JLabel("5th Guess");
		lbl5thGuess.setBounds(450, 142, 60, 14);
		lbl5thGuess.setVisible(false);
		add(lbl5thGuess);
		
		lbl6thGuess = new JLabel("6th Guess");
		lbl6thGuess.setBounds(450, 173, 60, 14);
		lbl6thGuess.setVisible(false);
		add(lbl6thGuess);
		
		lblGuessHiddenCode = new JLabel("Guess the hidden code : ");
		lblGuessHiddenCode.setBounds(200, 233, 150, 14);
		add(lblGuessHiddenCode);
		
		JLabel lblHitsAndMisses = new JLabel("HITS AND MISSES");
		lblHitsAndMisses.setForeground(Color.BLUE);
		lblHitsAndMisses.setBackground(Color.BLUE);
		lblHitsAndMisses.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHitsAndMisses.setBounds(192, 258, 158, 31);
		add(lblHitsAndMisses);
		
		JLabel lblChooseAColor = new JLabel("Choose a color");
		lblChooseAColor.setBounds(81, 11, 101, 14);
		add(lblChooseAColor);
		
	}
	
	/*
	 * This method is used to initialize the game at the begging, and what it does is place
	 * the three answer circles in an array for us to use later to display the colors inside of them
	 */
	private void initializeGame(){
		for(int i = 0; i < answerCircles.length; ++i){
			answerCircles[i] = new Circle(x, 230, radius, Color.WHITE, true);
			x += radius * 2 + 10;
		}
		
		x = 350;	//resets the x co-ordinate after it has finished with it 
	}
	
	/*
	 * This method is called to create three empty circles by just calling the createNewCircle method
	 * in a loop which will do it three times.
	 */
	private void generateEmptyCircles(){
		if(circleCounter < 18){
			for(int i = 0; i < 3; ++i){
				createNewCircle(Color.BLACK, false);
			}
		}
	}
	
	
	/*
	 * This method is used to generate the random color code at the start of the game.
	 * It uses the Random class and randomly selects colors from the color array defined.
	 */
	private void randomColorGenerator(){
		String[] colors = {"Blue", "Red", "Green", "Yellow", "Black"};
		Random randomGenerator = new Random();

		//A foor loop to fill my randomColorCode array with the randomly chosen colors
		for(int i = 0; i < randomColorCode.length; ++i){
			//Get a random int between 0 and the size of the array, in this case 5.
			int randomNum = randomGenerator.nextInt(colors.length);
			randomColorCode[i] = colors[randomNum];
			//System.out.println(randomColorCode[i]); //Println for showing the randomly generated code, for testing purposes.
		}
	}
	
	/*
	 * This method is used to create any new circles, by taking the color of the circle and wether or not
	 * it needs to be a filled circle.
	 */
	private void createNewCircle(Color color, boolean isFilled){		
		Circle circle = new Circle(x , y , radius , color, isFilled);
		circles.add(circle);
		
		if(circles.size() % 3 == 0 && circles.size() != 0){
			x = 350;
			y += radius*2 + 10;
		}else{
			x += radius*2 + 10;
		}
		
		repaint();
	}
	
	/*
	 * A method which will take a String and return the color value of the corresponding string
	 * if "Red" is passed in, it will return Color.RED etc etc
	 */
	private Color figureOutColor(String strColor){
		Color chosenColor = null;
		
		switch(strColor){
			case "Red" : chosenColor = Color.RED; break;
			case "Blue" : chosenColor = Color.BLUE; break;
			case "Green" : chosenColor = Color.GREEN; break;
			case "Black" : chosenColor = Color.BLACK; break;
			case "Yellow" : chosenColor = Color.YELLOW; break;
		}
		return chosenColor;
	}
	
	/*
	 * A quick method just for displaying the next label 
	 * depending on which guess the user is currently on.
	 */
	private void displayNextLabel(){
		switch(numGuesses){
			case 2 : lbl2ndGuess.setVisible(true); break;
			case 3 : lbl3rdGuess.setVisible(true); break;
			case 4 : lbl4thGuess.setVisible(true); break;
			case 5 : lbl5thGuess.setVisible(true); break;
			case 6 : lbl6thGuess.setVisible(true); break;
		}
	}
	
	/*
	 * A method to check and see if the user has won the game or not,
	 * returns true if they have won, and false if they have not won.
	 */
	private boolean hasWon(){
		boolean isWinner = true;
		
		for(int i = 0; i < answerCircles.length; ++i){
			if(userChosenCircles[i].getColor() != figureOutColor(randomColorCode[i])){
				isWinner = false;
			}
		}
		
		return isWinner;
	}
	
	/*
	 * We override the paintComponent method so that we can add in our own version of it to paint the circles
	 * onto the Panel. Before we add in our own implementation of the method we also call the super method that
	 * we are overriding so it can do whatever it needs to do first.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//A loop to go through the ArrayList of circles and call the draw method on each of them.
		for(Circle circle : circles){
			if(circle != null){
				circle.draw(g);
			}
		}
		//A loop to go through the array of answer circles and ensure that they are drawn also.
		for(Circle circ : answerCircles){
			circ.draw(g);
		}
	}
	
	/*
	 * A method we use to display the randomly generated color code in the answer cirlces we have defiend.
	 */
	private void displayAnswer(){
		for(int i = 0; i < 3; ++i){
			
			Color color = figureOutColor(randomColorCode[i]);
			
			answerCircles[i].setColor(color);
			answerCircles[i].setFilled(true);
		}
		repaint();
	}
	
	/*
	 * The ActionListener used on each of the buttons, since they essentially do the same thing
	 * we can use one ActionListener for all the buttons and just figure out which color was used.
	 */
	ActionListener myListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			
			//Check to see if the game is over or not.
			//If the game is over, nothing will happen on click.
			if(gameOver == true){
				return;
			}
			
			String buttonPressed = event.getActionCommand();
			
			//A variable just to hold the current circle that the user is guessing on.
			Circle circle = circles.get(circleCounter);
			Color userChosenColor = figureOutColor(buttonPressed);	//Gets the color value from the String
			
			circle.setColor(userChosenColor);	//Changes the color from default black on the empty circle to whatever the user chose
			circle.setFilled(true);	//sets the circle to filled.
			userChosenCircles[colorGuesses] = circle;	//adds the userchosen circle to the array we use for checking if they win or not.
			
			++circleCounter;	//increments counters
			++colorGuesses;
			repaint();			//repaints the circles to show the new color matching what the user chose
			
			if(colorGuesses == 3){	//if they have made 3 color guesses that means they've used one of their overall guesses
				++numGuesses;		//increments the number of guesses
				colorGuesses = 0;	//resets the colorguesses for the next guess
				if(hasWon() == true){	//checks to see if they have won.
					displayAnswer();	//if they have we display the answer
					lblGuessHiddenCode.setText("You win");
					gameOver = true;	//Sets the game to over if the user has guessed right.
				}else if(numGuesses == 7){	//checks to see if they've used all of their guesses
					displayAnswer();		//if they have it displays the answer
					lblGuessHiddenCode.setText("You lose");	//and lets the user know they have lost.
					gameOver = true;	//Sets the game to over if the user runs out of guesses.
				}
				generateEmptyCircles();	//we then display the next three circles
				displayNextLabel();		//and the next label for the next guess line.
			}
			
		}
	};
}
