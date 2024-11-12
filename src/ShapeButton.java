import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Class to create JButtons with non-Rectangular shapes
 * Remix from harryjoy's 8/21/2011 code: https://harryjoy.me/2011/08/21/different-button-shapes-in-swing/
 * @author Joel Bianchi
 * @version 11/11/2024
 */

public class ShapeButton extends JButton {

	Shape shape;
	int shapeType;					//1 = circle, 2 = triangleUp, 3 = triangleLeft, 4 = triangleRight, 5 = Rectangle
	Polygon polygon;
	int[] xPoints;
	int[] yPoints;
	int bWidth;
	int bHeight;
	String text;
	

	// Constructor 1: Sets default button size
	public ShapeButton(String text, int shapeType){
		this(text, shapeType, 150,150);
	}

	// Constructor 2
	public ShapeButton(String text, int shapeTyp, int bWidth, int bHeight){
		super(text);
		this.text = text;
		this.shapeType = shapeType;
		setButtonSize(bWidth, bHeight);
		setContentAreaFilled(false);
		arraySetup();
	}

	public void setButtonSize(int bWidth, int bHeight){

		//save button width & height as fields in ShapeButton class
		this.bWidth = bWidth;
		this.bHeight = bHeight;

		//set size in jButton superclass vars
		setPreferredSize(new Dimension(bWidth, bHeight));
		System.out.println("Button WxH: " + bWidth + " x " + bHeight + "\t" + text + "\t!");
		
		setContentAreaFilled(false);
		arraySetup();

	}

	public void resetButtonShape(int shapeType){
			this.shapeType = shapeType;
			
			//setButtonSize();
			setContentAreaFilled(false);
			arraySetup();
	}

	private void arraySetup(){
		if (shapeType ==2){	//setup UP triangle

			xPoints = new int[3];
			yPoints =	new int[3];

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

	protected void paintBorder(Graphics g) {

		g.setColor(getForeground());

		if(shapeType == 1){
			g.drawOval(0, 0, getSize().width-1, getSize().height-1);
		} else if(shapeType >= 2){
			arraySetup();
			g.drawPolygon(xPoints, yPoints, xPoints.length);
		}
	}

	public boolean contains(int x, int y) {

		if(shapeType == 1){

			if (shape == null || !shape.getBounds().equals(getBounds())) {	 
				shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
				return shape.contains(x, y);
			}
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

}