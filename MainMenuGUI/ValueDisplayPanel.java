package MainMenuGUI;

import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * self designed value display panel, contains a prompt, a value and a background picture for text area
 * @author YUQING TAN
 */
public class ValueDisplayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public ValueDisplayPanel(String prompt, String value, int space)
	{
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout.Constraints cons = null;
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

		BufferedImage img = (new ReadImage()).tx_220_30_grey();

		JLabel lbPrompt = new JLabel(prompt);
		cons = spLayout.getConstraints(lbPrompt);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(5));
        cons.setWidth(Spring.constant(120));
        cons.setHeight(Spring.constant(25));
		add(lbPrompt);

		JLabel lbValue = new JLabel(value);
		cons = spLayout.getConstraints(lbValue);
        cons.setX(Spring.constant(space));
        cons.setY(Spring.constant(5));
        cons.setWidth(Spring.constant(200));
        cons.setHeight(Spring.constant(25));
        add(lbValue);

		MenuButton bg = new MenuButton("",img,img);
		cons = spLayout.getConstraints(bg);
        cons.setX(Spring.constant(space-10));
        cons.setY(Spring.constant(0));
        cons.setWidth(Spring.constant(220));
        cons.setHeight(Spring.constant(30));
        add(bg);

        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
        pCons.setConstraint(SpringLayout.SOUTH,Spring.constant(40));
        pCons.setConstraint(SpringLayout.EAST,Spring.constant(400));
	}


}
