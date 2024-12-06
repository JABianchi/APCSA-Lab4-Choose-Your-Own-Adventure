import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.geom.*;

/**
 * Buttons to use with the Adventure class in a Choose-Your-Own-Adventure Game
 * Builds off of JButtons, adding Cirucular and Polygonal shaped buttons
 * Remix from harryjoy's 8/21/2011 code: https://harryjoy.me/2011/08/21/different-button-shapes-in-swing/
 * @author Joel Bianchi
 * @version 12/6/2024-3
 */
public class AdventureButton extends JButton {

	Shape shape;
	int shapeType;	//1 = circle, 2 = triangleUp, 3 = triangleLeft, 4 = triangleRight, 5 = Rectangle
	Polygon polygon;
	int[] xPoints;
	int[] yPoints;
	int buttonWidth;
	int buttonHeight;
	String buttonText;
	Color buttonColor;
	Color buttonTextColor;


	/**
	 * AdventureButton Class Constructor #1 - uses default sizes of 150x150 for button
	 * @param buttonText what text should display inside the button
	 * @param shapeType the type of shape for the button:
	 * 		1 = circle, 
	 * 		2 = triangleUp, 
	 * 		3 = triangleLeft, 
	 * 		4 = triangleRight, 
	 * 		5 = Rectangle
	 */
	public AdventureButton(String buttonText, int shapeType){
		this(buttonText, shapeType, 150,150);
	}

	/**
	 * AdventureButton Class Constructor #2 - set button size
	 * @param buttonText what text should display inside the button
	 * @param shapeType the type of shape for the button:
	 * 		1 = circle, 
	 * 		2 = triangleUp, 
	 * 		3 = triangleLeft, 
	 * 		4 = triangleRight, 
	 * 		5 = Rectangle
	 * @param buttonWidth maximum width for a button
	 * @param buttonHeight height of a button
	 */
	public AdventureButton(String buttonText, int shapeType, int buttonWidth, int buttonHeight){
		this(buttonText, shapeType, buttonWidth, buttonHeight, Color.GREEN, Color.BLACK);
	}

	/**
	 * AdventureButton Class Constructor #3 - full constructor with colors
	 * @param buttonText what text should display inside the button
	 * @param shapeType the type of shape for the button:
	 * 		1 = circle, 
	 * 		2 = triangleUp, 
	 * 		3 = triangleLeft, 
	 * 		4 = triangleRight, 
	 * 		5 = Rectangle
	 * @param buttonWidth maximum width for a button
	 * @param buttonHeight height of a button
	 * @param buttonColor 
	 * @param buttonTextColor
	 */
	public AdventureButton(String buttonText, int shapeType, int bWidth, int bHeight, Color buttonColor, Color buttonTextColor){
		super(buttonText);
		this.shapeType = shapeType;
		setButtonSize(bWidth, bHeight);
		this.buttonColor = buttonColor;
		this.buttonTextColor = buttonTextColor;
		setButtonText(buttonText);
		setContentAreaFilled(false);
		arraySetup();
		setBorder(new LineBorder(Color.BLACK));
	}

	/**
	 * Sets the Button Text
	 * @param buttonText
	 */
	public void setButtonText(String buttonText){
		this.buttonText = buttonText;
		setHtmlText();
	}

	/**
	 * Sets the Button Color
	 * @param buttonColor
	 */
	public void setButtonColor(Color buttonColor){
		this.buttonColor = buttonColor;
		setHtmlText();
	}

	/**
	 * Sets the Button Text Color
	 * @param buttonTextColor
	 */
	public void setButtonTextColor(Color buttonTextColor){
		this.buttonTextColor = buttonTextColor;
		setHtmlText();
	}

	/** [PRIVATE METHOD]
	 * Sets the jButton text with HTML formatting
	 */
	private void setHtmlText(){

		String htmlText = "<html><p style=\"";

		htmlText += "color:";
		htmlText += getHtmlColorString(this.buttonTextColor);
		htmlText += ";";

		// htmlText += "font:";
		// htmlText += buttonTextSize + "px";
		// htmlText += ";";

		// htmlText += "font-family:";
		// htmlText += buttonTextFont;
		// htmlText += ";";
		
		htmlText += "text-align: center";
		htmlText += "\">";
		htmlText += buttonText;
		htmlText += "</p></html>";

		//System.out.println(htmlText);
		setText(htmlText);
	}

	/**
	 * Sets the size of the button
	 * @param buttonWidth
	 * @param buttonHeight
	 */
	public void setButtonSize(int buttonWidth, int buttonHeight){

		//save button width & height as fields in ShapeButton class
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;

		//set size in jButton superclass vars
		setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		System.out.println("Button WxH: " + buttonWidth + " x " + buttonHeight + "\t" + buttonText + "\t!");
		
		setContentAreaFilled(false);
		arraySetup();
	}

	/**
	 * Sets the shape of the button
	 * @param shapeType Determines the shape of the button:
	 * 1 = Circle, 
	 * 2 = UpTriangle, 
	 * 3 = LeftTriangle, 
	 * 4 = RightTriangle, 
	 * 5 = Rectangle, 
	 */
	public void setButtonShape(int shapeType){
		this.shapeType = shapeType;
		setContentAreaFilled(false);
		arraySetup();
	}

	/** [PRIVATE METHOD]
	 * Fills in arrays of points for outlines of shapes
	 */
	private void arraySetup(){
		if (shapeType ==2){	//setup UP triangle

			xPoints = new int[3];
			yPoints = new int[3];

			xPoints[0] = getSize().width/2;
			xPoints[1] = 0;
			xPoints[2] = getSize().width;
			yPoints[0] = 0;
			yPoints[1] = getSize().height;
			yPoints[2] = getSize().height;

		} else if (shapeType == 3) {	//setup LEFT triangle

			xPoints = new int[3];
			yPoints =	new int[3];

			xPoints[0] = 0;
			xPoints[1] = getWidth();
			xPoints[2] = getWidth();
			yPoints[0] = getHeight()/2;
			yPoints[1] = getHeight();
			yPoints[2] = 0;

		} else if (shapeType == 4){	//setup RIGHT triangle

			xPoints = new int[3];
			yPoints =	new int[3];

			xPoints[0] = 0;
			xPoints[1] = 0;
			xPoints[2] = getWidth();
			yPoints[0] = 0;
			yPoints[1] = getHeight();
			yPoints[2] = getHeight()/2;

		} else if (shapeType == 5){ //setup RECTANGLE

			xPoints = new int[4];
			yPoints = new int[4];

			xPoints[0] = 0;
			xPoints[1] = getWidth();
			xPoints[2] = getWidth();
			xPoints[3] = 0;
			yPoints[0] = getHeight();
			yPoints[1] = getHeight();
			yPoints[2] = 0;
			yPoints[3] = 0;
		}

	}

	/** 
	 * Accessor method that overrides superclass getWidth() method
	 * @return user-described width of the button
	 */
	public int getWidth(){
		return this.buttonWidth;
	}
    /** 
	 * Accessor method that overrides superclass getWidth() method
	 * @return user-described height of the button
	 */
	public int getHeight(){
		return this.buttonHeight;
	}

	/** [PROTECTED]
	 * Paints fill for circular or polygonal buttons on the screen
	 * @param g
	 */
	protected void paintComponent(Graphics g) {
			
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		
		if(shapeType == 1){
			g.fillOval(0, 0, getSize().width-1, getSize().height-1);
		} else if(shapeType >= 2){
			arraySetup();
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		} 

		super.paintComponent(g);
	}

	/** [PROTECTED]
	 * Paints borders of shapes
	 */
	protected void paintBorder(Graphics g) {

		g.setColor(getForeground());

		if(shapeType == 1){
			g.drawOval(0, 0, getSize().width-1, getSize().height-1);
		} else if(shapeType >= 2){
			arraySetup();
			g.drawPolygon(xPoints, yPoints, xPoints.length);
		}
	}

	/**
	 * Checks if a specific pixel on the screen is contained
	 * within a circular or polygonal shape
	 * @return true if point contained in shape, false otherwise
	 */
	public boolean contains(int x, int y) {

		if(shapeType == 1){

			if (shape == null || !shape.getBounds().equals(getBounds())) {	 
				shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
			}
			return shape.contains(x, y);
		}

		else if(shapeType >= 2 ){

			arraySetup();
			if (polygon == null || !polygon.getBounds().equals(getBounds())) {
				polygon = new Polygon(xPoints,yPoints, xPoints.length);
			}
			return polygon.contains(x, y);
		}

		return true;
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
	

}