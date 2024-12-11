import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * An Adventure class for students to create a Choose-Your-Own-Adventure Lab
 * Designed for usage after Unit 4 of the AP Computer Science A curriculum
 * Topics to emphasize include: String methods, if-else structures, using Java methods with parameters & returns, loops
 * @author Joel Bianchi
 * @version 12/11/2024
 * Latest Update: Can change background color behind buttons & picture
 */
public class Adventure{

	private int numButtons;
	private String lastButtonPushed;
	private int totalButtonPushes;

	private String leftButtonText = "LEFT BUTTON";
	private String rightButtonText = "RIGHT BUTTON";
	private String leftButtonText2 = "LEFT2 BUTTON";
	private String rightButtonText2 = "RIGHT2 BUTTON";
	private String descriptionText = "Welcome to the your very own Choose-Your-Own-Adventure Game!";
	private String statusText = "Adventure Status Loading...";
	
	private JFrame f;
	private int fWidth;
	private int fHeight;
	private JTextPane topDescription;

	private JPanel leftPanel;
	private AdventureButton leftButton;
	private AdventureButton leftButton2;
	
	private JPanel rightPanel;
	private AdventureButton rightButton;
	private AdventureButton rightButton2;
	
	private JPanel centerPanel;
	private PicCanvas bgImage;
	private JPanel bottomPanel;
	private JTextPane bottomStatusBar;

	private int descriptionTextSize;
	private String descriptionTextFont;
	private Color descriptionTextColor;

	private final String defaultImage = "images/forest.jpg";
	private final String defaultIcon = "images/etech.jpg";


	/*-------------------------------------------------------*/
	/*----- ADVENTURE SCREEN CONSTRUCTORS -------------------*/
	/*-------------------------------------------------------*/

	/**
	 * Adventure Class Constructor #1: Default
	 */

	public Adventure(){
		this(800, 600, 2);
	}
	
	/**
	 * Adventure Class Constructor #2: Choose screen size with 2 buttons
	 * @param widthPixels how many pixels wide (x-axis) the screen should be
	 * @param heightPixels how many pixels high (y-axis) the screen should be
	 */
	public Adventure(int widthPixels, int heightPixels){
		this(widthPixels, heightPixels, 2);
	}
	
	/**
	 * Adventure Class Constructor #3: Choose 4 buttons with default resolution
	 * @param numButtons choose 2 or 4 buttons for the Adventure
	 */
	public Adventure(int numButtons){
		this(800, 600, numButtons);
	}

	/**
	 * Adventure Class Constructor #4: 
	 * @param widthPixels how many pixels wide (x-axis) the screen should be
	 * @param heightPixels how many pixels high (y-axis) the screen should be
	 * @param numButtons choose 2 or 4 buttons for the Adventure
	 */
	public Adventure(int widthPixels, int heightPixels, int numButtons){

		this.numButtons = numButtons;

		//Test method to get sound working
		//jsSound();

		//set the width and height of the Adventure screen
		this.fWidth = widthPixels;
		this.fHeight = heightPixels;

		//initialize important data variables
		this.lastButtonPushed = "NONE";
		this.totalButtonPushes = 0;
		this.descriptionTextSize = 20;
		this.descriptionTextColor = Color.BLACK;
		this.descriptionTextFont = "sans-serif";

		// creating instance of JFrame
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitleText("APCSA Choose Your Own Adventure Game!");
		setTitleIcon(defaultIcon);

		//creating Description & Status TextFields
		bottomPanel = new JPanel();
		bottomStatusBar = new JTextPane();
		setStatusBar(statusText);
		bottomPanel.add(bottomStatusBar);

		topDescription = new JTextPane();
		topDescription.setEditable(false);
		topDescription.setContentType("text/html");
		printDescription(descriptionText);

		// creating L & R Button Panels
		int bWidth = 150;
		int bHeight = 150;
		//int bCenterHeight = fHeight/2; 

		leftPanel = new JPanel();
        leftPanel.add(Box.createVerticalStrut(5));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftButton = new AdventureButton(leftButtonText, 1, bWidth, bHeight);
		leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftPanel.add(leftButton);

		rightPanel = new JPanel();
		rightPanel.add(Box.createVerticalStrut(5));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightButton = new AdventureButton(rightButtonText, 1, bWidth, bHeight);
		rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		rightPanel.add(rightButton);

		if(numButtons == 4){
			leftPanel.add(Box.createVerticalStrut(10));
			rightPanel.add(Box.createVerticalStrut(10));

			leftButton2 = new AdventureButton(leftButtonText2, 1, bWidth, bHeight);
			leftButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
			leftButton2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			leftPanel.add(leftButton2);

			rightButton2 = new AdventureButton(rightButtonText2, 1, bWidth, bHeight);
			rightButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightButton2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			rightPanel.add(rightButton2);
		}

		//Add event listeners for Buttons
		leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recordButtonPush("LEFT");
			}
		});
		rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recordButtonPush("RIGHT");
			}
		});

		if(numButtons ==4){
			leftButton2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					recordButtonPush("LEFT2");
				}
			});
			
			rightButton2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					recordButtonPush("RIGHT2");
				}
			});
		}

		//Setup Background Image
		centerPanel = new JPanel();
		bgImage =new PicCanvas(defaultImage, 0, 0); 
		//System.out.println("Init for bg" + bgImage);
		centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		centerPanel.add(bgImage);

		//Adding Components to the JFrame
		f.add(leftPanel,BorderLayout.WEST);
		f.add(rightPanel,BorderLayout.EAST); 
		f.add(centerPanel,BorderLayout.CENTER);
		f.add(topDescription,BorderLayout.NORTH);
		f.add(bottomPanel,BorderLayout.SOUTH);

		f.pack();

		bgImage.updateSize();
		System.out.println("Size of CENTER: " + bgImage.getSize() );

		//Visualize the Screen
		f.setSize(fWidth,fHeight);
		f.setVisible(true);

		//initialize default buttons
		setButtonShape(1);

	}

	/*-------------------------------------------------------*/
	/*----- METHODS THAT GIVE ACCESS TO ADVENTURE INFO ------*/
	/*-------------------------------------------------------*/
	
	/**
	 * Tells you which was the last Button pushed as a String
	 * Use the resetLastButtonPushed() method to reset this value
	 * @return "LEFT", "RIGHT", "LEFT2", "RIGHT2", or "NONE"
	 * 			(Note: This does NOT return the Button Text you added!)
	 */
	public String getLastButtonPushed(){
		return lastButtonPushed;
	}

	/**
	 * Keeps track of button clicks throughout Adventure
	 * @return int for total button pushes
	 */
	public int getTotalButtonPushes(){
		return this.totalButtonPushes;
	}

	/**
	 * Resets all the Button Pushes back to zero
	 */
	public void resetTotalButtonPushes(){
		this.totalButtonPushes = 0;
	}


	/*-------------------------------------------------------*/
	/*----- ADVENTURE VISUALIZATION METHODS -----------------*/
	/*-------------------------------------------------------*/
	
	/**
	 * Makes the Adventure window full screen
	 */
	void setFullscreen(){
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); //make the default window fullscreen
		f.setVisible(true);
	}

	/**
	 * Changes the central background picture for each Adventure Choice
	 * @param imgPath String for the path where image is located.
	 * 				If stored in the images folder, use a forward slash
	 * 				ie. "/images/forest.jpg"
	 */
	void setBackground(String imgPath){
		bgImage.changePic(imgPath);	
	}

	/**
	 * Sets Title bar text for Game Name, Author, and useful info
	 * @param titleText the text in the Title Bar at top of Screen
	 */
	public void setTitleText(String titleText){
		f.setTitle(titleText);
	}

	/**
	 * Changes the icon at the top of the Title Bar
	 * @param iconPath String to path where icon is located.
	 * 				If stored in the images folder, use a forward slash
	 * 				ie. "/images/etech.ico"
	 */
	public void setTitleIcon(String iconPath){
		ImageIcon icon = new ImageIcon(iconPath);
		f.setIconImage(icon.getImage());
	}

	/**
	 * Prints a new message in the Adventure Description rectangle at top of screen
	 * @param newDescription String description to help user understandthe current Moment 
	 * 						(Typically NOT listing the 2 or 4 choices here)  
	 */
	public void printDescription(String newDescription){
		descriptionText = newDescription;
		String html = "<html><p style=\"";

		html += "color:";
		html += getHtmlColorString(descriptionTextColor);
		html += ";";

		html += "font:";
		html += descriptionTextSize + "px";
		html += ";";

		html += "font-family:";
		html += descriptionTextFont;
		html += ";";
		
		html += "text-align: center";
		html += "\">";
		html += descriptionText;
		html += "</p></html>";

		topDescription.setText(html);
		//System.out.println(html);
	}

	/**
	 * Changes the color of the background of the Description box
	 * @param descriptionColor the desired color from Java's Color class 
	 * 				(ie. <code>Color.BLUE</code> or <code>new Color(255,0,0)</code>)
	 */
	public void setDescriptionColor(Color descriptionColor){
		topDescription.setBackground(descriptionColor);
	}
	
	/**
	 * Changes the size of the text in the Description
	 * @param descriptionTextSize font size for description text (ie. 15)
	 */
	public void setDescriptionTextSize(int descriptionTextSize){
		this.descriptionTextSize = descriptionTextSize;
		printDescription(descriptionText);
	}

	/**
	 * Changes the color of the text in the Description box
	 * @param descriptionTextColor the desired color from Java's Color class 
	 * 				(ie. <code>Color.BLACK</code> or <code>new Color(255,0,0)</code>)
	 */
	public void setDescriptionTextColor(Color descriptionTextColor){
		this.descriptionTextColor = descriptionTextColor;
	}

	/**
	 * Changes the font style of the Description box
	 * @param fontStyles Can use "serif", "sans-serif", or "courier"
	 */
	public void setDescriptionTextFont(String fontStyle){
		descriptionTextFont = fontStyle.toLowerCase();
	}

	/**
	 * Changes the text wording on a specific button
	 * @param buttonName Use "LEFT", "RIGHT", "LEFT2" or "RIGHT2"
	 * @param newText the text to display on the button
	 */
	public void setButtonText(String buttonName, String newText){
		AdventureButton sb = getButton(buttonName);
		if(sb != null){
			sb.setButtonText(newText);
		}
		leftPanel.repaint();
        rightPanel.repaint();
	}
	
	/**
	 * Changes the background color filled in for a specific button
	 * @param buttonName Use "LEFT", "RIGHT", "LEFT2" or "RIGHT2"
	 * @param color the desired color from Java's Color class 
	 * 				(ie. <code>Color.BLACK</code> or <code>new Color(255,0,0)</code>)
	 */
	public void setButtonColor(String buttonName, Color color){
		AdventureButton sb = getButton(buttonName);
		if(sb != null){
			sb.setBackground(color);
		}
	}
	
	/**
	 * Changes the background color filled in for ALL buttons
	 * @param color the desired color from Java's Color class 
	 * 				(ie. <code>Color.BLACK</code> or <code>new Color(255,0,0)</code>)
	 */
	public void setButtonColor(Color color){
		setButtonColor("left", color);
		setButtonColor("right", color);
		if(numButtons == 4){
			setButtonColor("left2", color);
			setButtonColor("right2", color);	
		}
	}

	/**
	 * Changes the shape of a specific Button
	 * @param buttonName Use "LEFT", "RIGHT", "LEFT2" or "RIGHT2"
	 * @param shapeNum Determines the shape of the button:
	 * 1 = Circle, 
	 * 2 = UpTriangle, 
	 * 3 = LeftTriangle, 
	 * 4 = RightTriangle, 
	 * 5 = Rectangle, 
	 */
	public void setButtonShape(String buttonName, int shapeNum){
		AdventureButton sb = getButton(buttonName);
		if(sb != null){
			sb.setButtonShape(shapeNum);
		}
	}

	/**
	 * Changes the shape of ALL Buttons
	 * @param shapeNum Determines the shape of the button:
	 * 1 = Circle, 
	 * 2 = UpTriangle, 
	 * 3 = LeftTriangle, 
	 * 4 = RightTriangle, 
	 * 5 = Rectangle, 
	 */
	public void setButtonShape(int shapeNum){
		setButtonShape("left", shapeNum);
		setButtonShape("right", shapeNum);
		if(numButtons == 4){
			setButtonShape("left2", shapeNum);
			setButtonShape("right2", shapeNum);
		}
	}

	/**
	 * Changes the size dimensions for a specific button
	 * @param buttonName Use "LEFT", "RIGHT", "LEFT2" or "RIGHT2"
	 * @param widthPixels maximum width of the button
	 * 					(Panel will automatically adjust, 
	 * 					but button will only expand to max width if needed.)
	 * @param heightPixels height of button 
	 */
	public void setButtonSize(String buttonName, int widthPixels, int heightPixels){
		AdventureButton sb = getButton(buttonName);
		if(sb != null){
			sb.setButtonSize(widthPixels, heightPixels);
		}
	}

	/**
	 * Changes the size dimensions for ALL buttons
	 * @param widthPixels maximum width of the button
	 * 					(Panel will automatically adjust, 
	 * 					but button will only expand to max width if needed.)
	 * @param heightPixels height of button 
	 */
	public void setButtonSize(int widthPixels, int heightPixels){
		setButtonSize("left", widthPixels, heightPixels);
		setButtonSize("right", widthPixels, heightPixels);
		if(numButtons == 4){
			setButtonSize("left2", widthPixels, heightPixels);
			setButtonSize("right2", widthPixels, heightPixels);
		}
	}

	/**
	 * Changes the font style for a specific button
	 * @param buttonName Use "LEFT", "RIGHT", "LEFT2" or "RIGHT2"
	 * @param font the desired front from the Java Font class 
	 * 				https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html
	 * 				(ie. <code>new Font("Arial", Font.PLAIN, 25)</code>)
	 */
	public void setButtonFont(String buttonName, Font font){
		AdventureButton sb = getButton(buttonName);
		if(sb != null){
			sb.setFont(font);
		}
	}

	/**
	 * Changes the font style for ALL buttons
	 * @param font the desired front from the Java Font class 
	 * 				https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html
	 * 				(ie. <code>new Font("Arial", Font.PLAIN, 25)</code>)
	 */
	public void setButtonFont(Font font){
		setButtonFont("left", font);
		setButtonFont("right", font);
		if(numButtons == 4){
			setButtonFont("left2", font);
			setButtonFont("right2", font);
		}
	}

	/**
	 * Changes the text color for a specific button
	 * @param buttonName Use "LEFT", "RIGHT", "LEFT2" or "RIGHT2"
	 * @param textColor the desired color from Java's Color class 
	 * 				(ie. <code>Color.BLACK</code> or <code>new Color(255,0,0)</code>)
	 */
	public void setButtonTextColor(String buttonName, Color textColor){
		AdventureButton sb = getButton(buttonName);
		if(sb != null){
			sb.setButtonTextColor(textColor);
		}
	}

	/**
	 * Changes the text color for ALL buttons
	 * @param textColor the desired color from Java's Color class 
	 * 				(ie. <code>Color.BLACK</code> or <code>new Color(255,0,0)</code>)
	 */
	public void setButtonTextColor(Color textColor){
		setButtonTextColor("left", textColor);
		setButtonTextColor("right", textColor);
		if(numButtons == 4){
			setButtonTextColor("left2", textColor);
			setButtonTextColor("right2", textColor);	
		}
	}

	/**
	 * Places a new String in the Adventure Status bar
	 * @param statusText complete String to be displayed at bottom of Adventure screen
	 */
	public void setStatusBar(String statusText){
		bottomStatusBar.setText(statusText);
	}

	/**
	 * Erases the entire Adventure Status Bar text
	 */
	public void clearStatusBar(){
		bottomStatusBar.setText("");
	}

    /*
     * Changes the background color behind the button panels and center picture panel -Thomas Dillon, 2024
     * @param color the Color object to put in the background (ie. Color.BLACK)
     */
    public void setBackgroundColor(Color color){
        rightPanel.setBackground(color);
        rightPanel.repaint();
        leftPanel.setBackground(color);
        leftPanel.repaint();
        centerPanel.setBackground(color);
        centerPanel.repaint();
    }

	/**
	 * Generates a pop-up Question for the Adventurer
	 * @param questionText String prompt for the user
	 * 						(Hint: Use new line escape characters (\n) in your String
	 * 						if it is too long to fit neatly into the box.)
	 * @return a String with the Adventurer's answer 
	 */ 
	public String popupQuestion(String questionText){
		return JOptionPane.showInputDialog(f, questionText, "Make your choice!", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Generates a pop-up message... The Adventurer cannot type into a
	 * @param questionText String prompt for the user
	 * 						(Hint: Use new line escape characters (\n) in your String
	 * 						if it is too long to fit neatly into the box.
	 */
	public void popupMessage(String messageText){
		JOptionPane.showMessageDialog(f, messageText, "Important message!", JOptionPane.PLAIN_MESSAGE);
	}

	/*-------------------------------------------------------*/
	/*----- HELPER METHODS ----------------------------------*/
	/*-------------------------------------------------------*/

	/**
	 * Pauses the Adventure while waiting for a Button to be Pushed
	 */
	public void waitForButtonPush(){
		lastButtonPushed = "NONE";	//reset the Button choice to "NONE"
		while(true){
			if(!getLastButtonPushed().equals("NONE")){
				System.out.println(getLastButtonPushed() + " button was clicked.");
				return;
			} else {
				System.out.print(".");
				Adventure.pause(100);
			}
		}
	}

	/**
	 * Pauses the Java processor for a certain amount of time
	 */
	public static void pause(final int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (final Exception e) {
			// ignore
		}
	}

	/**
	 * Closes the Adventure screen window and ends Java process
	 */
	public void closeAdventure(){
		f.dispose();
	}

	/** [PRIVATE METHOD]
	 * Records the last button pushed and total number of pushes
	 * @param buttonInitial the button just pushed
	 */
	private void recordButtonPush(String buttonInitial){
		lastButtonPushed = buttonInitial;
		totalButtonPushes++;
	}

	/** [PRIVATE METHOD]
	 * Converts a Java Color object into an HTML color String
	 * @param color Java Color object
	 * @return HTML hex color String
	 */
	private String getHtmlColorString(Color color) {
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());

		return "#" + 
			(red.length() == 1? "0" + red : red) +
			(green.length() == 1? "0" + green : green) +
			(blue.length() == 1? "0" + blue : blue);	
	}

	/** [PRIVATE METHOD] 
	 * Returns a specific ShapeButton object based on a String input
	 * Checks for errors in the String for a button
	 */
	private AdventureButton getButton(String buttonName){
		AdventureButton sb = null;
		buttonName = buttonName.toLowerCase();
		if(buttonName.equals("left")){
			sb = leftButton;
		}
		else if(buttonName.equals("right")){
			sb = rightButton;
		}
		else if(buttonName.equals("left2")){
			sb = leftButton2;
		}
		else if(buttonName.equals("right2")){
			sb = rightButton2;
		}
		else{
			sb = null;
			System.out.println("Error with a button naming method!  Incorrect button name used --> " + buttonName + ".  Try \"left\", \"right\", \"left2\", or \"right2\".");
		}
		return sb;
	}

	/*-------------------------------------------------------*/
	/*----- DEPRECATED METHODS - DO NOT USE -----------------*/
	/*-------------------------------------------------------*/

	public void setLeftButtonText(String newText){
		setButtonText("LEFT", newText);
	}
	public void setRightButtonText(String newText){
		setButtonText("RIGHT", newText);
	}
	public void setLeftButtonText2(String newText){
		setButtonText("LEFT2", newText);
	}
	public void setRightButtonText2(String newText){
		setButtonText("RIGHT2", newText);
	}
	public void setLeftButtonColor(Color color){
		setButtonColor("LEFT", color);
	}
	public void setRightButtonColor(Color color){
		setButtonColor("RIGHT", color);
	}
	public void setLeftButtonColor2(Color color){
		setButtonColor("LEFT2", color);
	}
	public void setRightButtonColor2(Color color){
		setButtonColor("RIGHT2", color);
	}
	public void setLeftButtonShape(int shapeNum){
		setButtonShape("LEFT", shapeNum);
	}
	public void setRightButtonShape(int shapeNum){
		setButtonShape("RIGHT", shapeNum);
	}
	public void setLeftButtonShape2(int shapeNum){
		setButtonShape("LEFT2", shapeNum);
	}
	public void setRightButtonShape2(int shapeNum){
		setButtonShape("RIGHT2", shapeNum);
	}
	public void setLeftButtonSize(int w, int h){
		setButtonSize("LEFT", w, h);
	}
	public void setRightButtonSize(int w, int h){
		setButtonSize("RIGHT", w, h);
	}
	public void setLeftButtonSize2(int w, int h){
		setButtonSize("LEFT2", w, h);
	}
	public void setRightButtonSize2(int w, int h){
		setButtonSize("RIGHT2", w, h);
	}
	public void setLeftButtonFont(Font f){
		setButtonFont("LEFT", f);
	}
	public void setRightButtonFont(Font f){
		setButtonFont("RIGHT", f);
	}
	public void setLeftButtonFont2(Font f){
		setButtonFont("LEFT2", f);
	}
	public void setRightButtonFont2(Font f){
		setButtonFont("RIGHT2", f);
	}


}