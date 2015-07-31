import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class GamePanel extends JPanel {
	
	private JTextField wordDisplay;
	private JTextField guessDisplay;
	
	private String[] buttonTexts = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "START", "ANSWER"};
	
	private String[] guessWords = new String[]{"MEMORY", "COMPUTER", "PRINTER", "TROUSERS", "BUTTERCUP"};
	
	private int wordCounter = 0;	//This is the counter used for knowing which word/game we're currently on
	private int numLives = 8;		//This just creates a variable to hold the users lives/guesses reamining
	
	private char[] displayWord;		//This will hold the array the user will see
	private char[] originalWord;	//This will hold the originalWord the user has to guess
	
	private JPanel panel = this;	//Creating a reference to the panel from inside itself
	
	private int x = 10;
	private int y = 80;
	private int width = 90;
	private int height = 30;

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		setLayout(null);
		
		wordDisplay = new JTextField(24);	//Passing in 24 to ensure it is a 24char textfield
		wordDisplay.setBounds(200, 30, 240, 20);
		wordDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		wordDisplay.setBackground(Color.LIGHT_GRAY);
		wordDisplay.setEditable(false);
		add(wordDisplay);
		
		
		
		guessDisplay = new JTextField(3);
		guessDisplay.setEditable(false);
		guessDisplay.setBounds(370, 61, 70, 20);
		guessDisplay.setHorizontalAlignment(SwingConstants.RIGHT);	//setting to the right as required
		guessDisplay.setBackground(Color.LIGHT_GRAY);
		add(guessDisplay);
		
		JLabel lblGuessTheWord = new JLabel("GUESS THE WORD :");
		lblGuessTheWord.setBounds(200, 5, 119, 14);
		lblGuessTheWord.setForeground(Color.RED);
		add(lblGuessTheWord);
		
		JLabel lblGuessesRemaining = new JLabel("GUESSES REMAINING\r\n");
		lblGuessesRemaining.setBounds(225, 64, 135, 14);
		lblGuessesRemaining.setForeground(Color.RED);
		add(lblGuessesRemaining);
		
		
		/*
		 * A for loop used for creating buttons at certain positions calculated using the x, y, width and height
		 * don't worry about this if you can't understand it, we will cover more ways of placing the buttons.
		 * Stick to what you know first.
		 */
		for(int i = 0; i < buttonTexts.length; ++i){
			JButton button = new JButton(buttonTexts[i]);
			button.setBackground(Color.LIGHT_GRAY);
			button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			button.addActionListener(myActionListener);
			
			if(i % 7 == 0){
				y += height + 10;
				x = 10;
				button.setBounds(x += width, y, width, height);
			}else{
				button.setBounds(x += width, y, width, height);
			}
			
			add(button);
		}
	}
	
	
	ActionListener myActionListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			/*
			 * event.getActionCommand(); this method is just returning
			 * whatever is written on the button as a String for us
			 * to tell what button was pressed.
			 */
			String buttonPressed = event.getActionCommand();
			
			/*
			 * this if statement (conditional statement)
			 * Is going to decide based on what button was pressed
			 * what piece of code to execute.
			 */
			if(buttonPressed.equalsIgnoreCase("Start")){
				//This is where the program goes when the Start button is pressed.
				
				//The first thing the start button does is clear the textFields
				wordDisplay.setText("");
				guessDisplay.setText("");
				
				//assigning the values of our char arrays to be that of the current guess word
				String strWord = guessWords[wordCounter];
				originalWord = strWord.toCharArray();
				displayWord = strWord.toCharArray();
				
				++wordCounter;
				if(wordCounter == guessWords.length){
					wordCounter = 0;
				}
				//Increments the wordCounter but doesn't let it go above 4 so we don't go out of bounds
				
				
				//A loop for going through each element of our Array
				for(int i = 0; i < displayWord.length;++i){
					displayWord[i] = '*';			//Puts an asterisk inside each element
				}
				
				String display = new String(displayWord);	//Convert from a char[] to a String
				wordDisplay.setText(display);	//Displays the String in our TextField
				
				numLives = 8;
				guessDisplay.setText(numLives + "");	//concating the int numlives as a string to the textfield
				
			}else if(buttonPressed.equalsIgnoreCase("Answer")){	//Checks to see if the Answer button was pressed
				String display = new String(originalWord);	//Converts our originalWord char[] into a String
				wordDisplay.setText(display); 				//Sets the display to be the original word
				guessDisplay.setText("");					//clears the guesses remaining
			}else{
				
				/*
				 * Here we are converting the char[] into a String in order for us to check if the character the user
				 * has chosen is actually inside of the word or not. We do this by using the indexOf method, if the letter 
				 * is found in the word we are returned the index of the letter, if the letter does not contain the word 
				 * an index of -1 is returned. So this way we know if the letter the user has guessed is in the word or not.
				 */
				String strOriginalWord = new String(originalWord);
				
				int index = strOriginalWord.indexOf(buttonPressed);
				if(index == -1){
					--numLives;		//decrement a life
					guessDisplay.setText(numLives+"");	//redisplay a life
				}else{
					char characterChosen = buttonPressed.charAt(0);	//Gets the character chosen from the String on the button
					
					/*
					 * The following loop is used to iterate through the entire char[] holding the originalWord checking 
					 * to see if the char that the user selected is in that element, if it is, that element in the displayWord
					 * is replaced with the character they guessed, so replacing the asterisk with the actual letter
					 */
					for(int i = 0; i < originalWord.length; ++i){
						if(originalWord[i] == characterChosen){
							displayWord[i] = characterChosen;
						}
					}
				}
				//Here we simply convert the char[] into a String so that we can set the text of the textField to be it.
				String display = new String(displayWord);
				wordDisplay.setText(display);
			}
			
			/*
			 * Here we need to convert the char[]'s into Strings in order for us to check the equality
			 * to be able to tell if the user has won the game or not.
			 */
			String strOriginalWord = new String(originalWord);
			String strDisplayWord = new String(displayWord);
			
			/*
			 * This if statement checks to see if the numLives is 0, if it is the user has lost the game 
			 * if it is not, it checks to see if the users guess word is the same as the original word,
			 * and if the guess word and original word are the same, the user has won the game.
			 */
			if(numLives == 0){
				JLabel label = new JLabel("         You lose");	//Creating our label with you lose
				label.setForeground(Color.RED);			//Setting the font color to be Red
				JOptionPane.showMessageDialog(panel, label);	//Passing our label into the JOptionPane in order to make sure it displays the right font
			}else if(strOriginalWord.equals(strDisplayWord) && numLives > 0){	//Checking to see if the users guess is equal to the word they have to guess
				JLabel label = new JLabel("         You win");
				label.setForeground(Color.BLUE);
				JOptionPane.showMessageDialog(panel, label);
			}
			
		}
		
	};
}
