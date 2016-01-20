package MainMenuGUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * self designed menu button
 * img1 appears when mouse is not on the button
 * img2 appears when mouse is on the button
 * @author YUQING TAN
 */

public class MenuButton extends JButton{

	private static final long serialVersionUID = 1L;
	private BufferedImage img1;
	private BufferedImage img2;

	public MenuButton(String str, final BufferedImage img1, final BufferedImage img2){
		super(str);
		this.img1 = img1;
		this.img2 = img2;
		setting();
	}
	
	public MenuButton(String str, int dim){
		super(str);
		selectSize(dim);
		setting();
	}

	private void setting(){
        setBorderPainted(false); 
        setContentAreaFilled(false); 
        setFocusPainted(false); 
        setOpaque(false);
		setHorizontalTextPosition(JButton.CENTER);
		setForeground(Color.WHITE);
		setIcon(new ImageIcon(img1));
		addMouseListener(new MouseAdapter(){
        	public void mouseEntered(MouseEvent evt){
        		setIcon(new ImageIcon(img2));
        		}
        	});
        addMouseListener(new MouseAdapter(){
        	public void mouseExited(MouseEvent evt){
        		setIcon(new ImageIcon(img1));
        		}
        	});
	}
	
	private void selectSize(int dim){
		if (dim == 180){
    		try {
    			img1 = ImageIO.read(new File("image/bt_180_30a.png"));
    			img2 = ImageIO.read(new File("image/bt_180_30b.png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
    	else if (dim == 150){
    		try {
    			img1 = ImageIO.read(new File("image/bt_150_30a.png"));
    			img2 = ImageIO.read(new File("image/bt_150_30b.png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}else if (dim == 120){
    		try {
    			img1 = ImageIO.read(new File("image/bt_120_24a.png"));
    			img2 = ImageIO.read(new File("image/bt_120_24b.png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}else if (dim == 100){
    		try {
    			img1 = ImageIO.read(new File("image/bt_100_24a.png"));
    			img2 = ImageIO.read(new File("image/bt_100_24b.png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}else if (dim == 60){
    		try {
    			img1 = ImageIO.read(new File("image/bt_60_24a.png"));
    			img2 = ImageIO.read(new File("image/bt_60_24b.png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}else{
    		try {
    			img1 = ImageIO.read(new File("image/bt_150_30a.png"));
    			img2 = ImageIO.read(new File("image/bt_150_30b.png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
	}
}