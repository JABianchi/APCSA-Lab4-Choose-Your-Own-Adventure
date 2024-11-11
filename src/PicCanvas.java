import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Class to display resized pictures in a JFrame
 * @author Joel Bianchi
 * @version 12/11/2020
 */

public class PicCanvas extends Canvas {

	private String imgPath;
	private int x_offset;
	private int y_offset;
	private String defaultImg = "images/forest.jpg";
	private int PIC_WIDTH;
	private int PIC_HEIGHT;

	public PicCanvas() {
		imgPath = defaultImg;
		x_offset = 0;
		y_offset = 0;
	}

	public PicCanvas(String imgPath, int x_offset, int y_offset) {
		this.imgPath = imgPath;
		this.x_offset = x_offset;
		this.y_offset = y_offset;
		PIC_WIDTH = 600;
		PIC_HEIGHT = 400;
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(PIC_WIDTH, PIC_HEIGHT);
	}

	public void updateSize(){
		PIC_WIDTH = (int) getSize().getWidth();
		PIC_HEIGHT = (int) getSize().getHeight();
		//System.out.println("Update Size: " + getPreferredSize() );
	}

	public void changePic(String imgPath) {
		//this.imgPath = "/" + imgPath; //PickCode version
		this.imgPath = imgPath; //VSCode version
		repaint();
	}

	public void paint(Graphics g) {
		//System.out.println("pic painting...");
		try {
			BufferedImage bImage = ImageIO.read(new File(imgPath));
			bImage = resizeImage(bImage, PIC_WIDTH, PIC_HEIGHT);
			g.drawImage(bImage, x_offset,y_offset,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		//System.out.println("resizing image");
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}

}