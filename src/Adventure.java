import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.event.*;
import java.awt.*;

/**
 * An Adventure class for students to create a Choose-Your-Own-Adventure Lab
 * Designed for usage after Unit 4 of the AP Computer Science A curriculum
 * @author Joel Bianchi
 * @version 12/7/2022
 * Latest Update: Added methods to change Font on buttons
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
	private ShapeButton leftButton;
	private ShapeButton leftButton2;
	
	private JPanel rightPanel;
	private ShapeButton rightButton;
	private ShapeButton rightButton2;
	
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

	//Default Constructor
	public Adventure(){
		this(800, 600, 2);
	}

	//Constructor that allows a change to the Adventure screen dimensions
	public Adventure(int widthPixels, int heightPixels, int numButtons){

		this.numButtons = numButtons;

		//Test method to get sound working in replit
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
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		leftButton = new ShapeButton(leftButtonText, 1);
		leftButton.setPreferredSize(new Dimension(bWidth,bHeight));
		leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftPanel.add(leftButton);

		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightButton = new ShapeButton(rightButtonText, 1);
		rightButton.setPreferredSize(new Dimension(bWidth,bHeight));
		rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		rightPanel.add(rightButton);

		if(numButtons == 4){
			leftButton2 = new ShapeButton(leftButtonText2, 1);
			leftButton2.setPreferredSize(new Dimension(bWidth,bHeight));
			leftButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
			leftButton2.setAlignmentY(Component.CENTER_ALIGNMENT);
			leftPanel.add(leftButton2);

			rightButton2 = new ShapeButton(rightButtonText2, 1);
			rightButton2.setPreferredSize(new Dimension(bWidth,bHeight));
			rightButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
			rightButton2.setAlignmentY(Component.CENTER_ALIGNMENT);
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
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setVisible(true);


		//initialize buttons
		setLeftButtonShape(1);
		setRightButtonShape(1);

		if(numButtons==4){
			setLeftButtonShape(4);
			setRightButtonShape(3);
			setLeftButtonShape2(4);
			setRightButtonShape2(3);
		}


	}

  /*-------------------------------------------------------*/
	/*----- METHODS THAT GIVE ACCESS TO ADVENTURE INFO ------*/
  /*-------------------------------------------------------*/
	//Tells you which was the last Button pushed as a String
	// returns "RIGHT", "LEFT", or "NONE"
	// Use the resetLastButtonPushed method to reset this value
	public String getLastButtonPushed(){
		return lastButtonPushed;
	}

	//Reset the lastButtonPushed
	public void resetLastButtonPushed(){
		lastButtonPushed = "NONE";
	}

	//Returns the int for the Total Button pushes
	public int getTotalButtonPushes(){
		return this.totalButtonPushes;
	}

	//Resets all the Button Pushes back to zero
	public void resetTotalButtonPushes(){
		this.totalButtonPushes = 0;
	}

  /*-------------------------------------------------------*/
	/*----- ADVENTURE VISUALIZATION METHODS -----------------*/
  /*-------------------------------------------------------*/
	//Changes the central background picture for each Adventure Choice
	void setBackground(String imgPath){
		bgImage.changePic(imgPath);	
	}

	//Sets the text in the Title Bar at the top of the Screen
	public void setTitleText(String titleText){
		f.setTitle(titleText);
	}

	//Change the icon at the top of the Title Bar
  public void setTitleIcon(String iconPath){
    ImageIcon icon = new ImageIcon(iconPath);
    f.setIconImage(icon.getImage());
	}

	//Prints a new message in the Adventure Description rectangle
		public void printDescription(String newDescription){
		descriptionText = newDescription;
		String html = "<html><p style=\"";

		html += "color:";
		html += getHTMLColorString(descriptionTextColor);
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

	private String getHTMLColorString(Color color) {
    String red = Integer.toHexString(color.getRed());
    String green = Integer.toHexString(color.getGreen());
    String blue = Integer.toHexString(color.getBlue());

    return "#" + 
			(red.length() == 1? "0" + red : red) +
			(green.length() == 1? "0" + green : green) +
			(blue.length() == 1? "0" + blue : blue);  
	}

	//Changes the font style of the Description box
	//The paramter fontStyles can be: "serif", "sans-serif", or "courier"
	public void setDescriptionTextFont(String fontStyle){
		descriptionTextFont = fontStyle.toLowerCase();
	}

  //Changes the color of the background of the Description box
	public void setDescriptionColor(Color c){
		topDescription.setBackground(c);
	}

	//Change the color of the text in the Description
	public void setDescriptionTextColor(Color c){
		descriptionTextColor = c;
	}

	//Changes the size of the text in the Description
	public void setDescriptionTextSize(int size){
		descriptionTextSize = size;
		printDescription(descriptionText);
  }
	
	//Change the wording of the Left or Right buttons
	public void setLeftButtonText(String newText){
		this.leftButtonText = newText;
		this.leftButton.setText(this.leftButtonText);
	}
	public void setRightButtonText(String newText){
		this.rightButtonText = newText;
		this.rightButton.setText(this.rightButtonText);
	}
	public void setLeftButtonText2(String newText){
		this.leftButtonText2 = newText;
		this.leftButton2.setText(this.leftButtonText2);
	}
	public void setRightButtonText2(String newText){
		this.rightButtonText2 = newText;
		this.rightButton2.setText(this.rightButtonText2);
	}

	//Change the colors of the left or right buttons
	public void setLeftButtonColor(Color color){
		//this.leftButton.setOpaque(true);
		this.leftButton.setBackground(color);
	}
	public void setRightButtonColor(Color color){
		//this.rightButton.setOpaque(true);
		this.rightButton.setBackground(color);
	}
	public void setLeftButtonColor2(Color color){
		if(numButtons==4){
			//this.leftButton2.setOpaque(true);
			this.leftButton2.setBackground(color);
		}
	}
	public void setRightButtonColor2(Color color){
		if(numButtons==4){
			//this.rightButton2.setOpaque(true);
			this.rightButton2.setBackground(color);
		}
	}

	//Change the fonts of the left or right buttons
	//Check out the Java Font class to learn how to construct a Font:
	// https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html
	public void setRightButtonFont(Font font){
		this.rightButton.setFont(font);
	}
	public void setLeftButtonFont(Font font){
		this.leftButton.setFont(font);
	}
	public void setRightButtonFont2(Font font){
		this.rightButton2.setFont(font);
	}
	public void setLeftButtonFont2(Font font){
		this.leftButton2.setFont(font);
	}

	
	//Change the shape of the Left or Right Buttons
	//parameter shapeNum:
	//	1 = Circle
	//	2 = UpTriangle
	//	3 = RightTriangle
	//	4 = LeftTriangle
  //  5 = Rectangle
	public void setLeftButtonShape(int shapeNum){
		leftButton.resetButtonShape(shapeNum);
	}
	public void setRightButtonShape(int shapeNum){
		rightButton.resetButtonShape(shapeNum);
	}
	public void setLeftButtonShape2(int shapeNum){
		if(numButtons==4){
			leftButton2.resetButtonShape(shapeNum);
		}
	}
	public void setRightButtonShape2(int shapeNum){
		if(numButtons==4){
			rightButton2.resetButtonShape(shapeNum);
		}
	}

  //change the size dimensions of each button (width and height)
	public void setLeftButtonSize(int widthPixels, int heightPixels){
		this.leftButton.setPreferredSize(new Dimension(widthPixels, heightPixels));
		leftButton.updateButtonSize();
	}
	public void setRightButtonSize(int widthPixels, int heightPixels){
		this.rightButton.setPreferredSize(new Dimension(widthPixels, heightPixels));
		rightButton.updateButtonSize();
	}
	public void setLeftButtonSize2(int widthPixels, int heightPixels){
		if(numButtons==4){
			this.leftButton2.setPreferredSize(new Dimension(widthPixels, heightPixels));
			leftButton2.updateButtonSize();
		}
	}
	public void setRightButtonSize2(int widthPixels, int heightPixels){
		if(numButtons==4){
			this.rightButton2.setPreferredSize(new Dimension(widthPixels, heightPixels));
			rightButton2.updateButtonSize();
		}
	}

  //Place a new String in the Adventure Status bar
  public void setStatusBar(String statusText){
		bottomStatusBar.setText(statusText);
	}

	//Erase the entire Adventure Status Bar text
  public void clearStatusBar(){
		bottomStatusBar.setText("");
	}

	//Generates a pop-up Question for the Adventurer
	//return:
	//	A String with the Adventurer's answer 
  //Hint: Use new line escape characters (\n) in your String
  // if it is too long to fit neatly into the box.
	public String popupQuestion(String questionText){
		return JOptionPane.showInputDialog(f, questionText, "Make your choice!", JOptionPane.PLAIN_MESSAGE);
	}

	//Generates a pop-up message... The Adventurer cannot type into a
  //Hint: Use new line escape characters (\n) in your String
  // if it is too long to fit neatly into the box.
	public void popupMessage(String messageText){
		JOptionPane.showMessageDialog(f, messageText, "Important message!", JOptionPane.PLAIN_MESSAGE);
	}

  /*-------------------------------------------------------*/
  /*----- HELPER METHODS ----------------------------------*/
  /*-------------------------------------------------------*/
	//Pause the Adventure while waiting for a Button to be Pushed
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

//Pause the Java processor for a certain amount of time
	public static void pause(final int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (final Exception e) {
			// ignore
		}
	}

	private void recordButtonPush(String buttonInitial){
		lastButtonPushed = buttonInitial;
		totalButtonPushes++;
	}

	public void closeAdventure(){
		f.dispose();
	}


	

}