package MainMenuGUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadImage {

	public ReadImage(){}
	
	public BufferedImage tx_120_30(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_120_30.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

	public BufferedImage tx_220_30(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_220_30.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

	public BufferedImage tx_220_30_grey(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_220_30_grey.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

	public BufferedImage tx_420_30(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_420_30.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

	public BufferedImage tx_220_160(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_420_280.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

	public BufferedImage tx_220_160_grey(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_220_160_grey.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

	public BufferedImage tx_420_280(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/tx_420_280.png")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}
	
	public BufferedImage bgClientSide(){
		BufferedImage img = null;
	    try { img = ImageIO.read(new File("image/bgClientSide.jpg")); }
	    catch (IOException e) { e.printStackTrace(); }
	    return img;
	}

}
