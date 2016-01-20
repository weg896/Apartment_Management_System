package MainMenuGUI;

import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * A panel with a prompt label and a text field for the entry of a data value.
 * The data value can be accessed as a String or an int value.  If accessed as
 * an int value, but it is invalid, the field value has "Error: " added at
 * its front, and a NumberFormatException exception is thrown.
 */
public class ValuePasswordPanel extends JPanel
{
	/**
	 * The field for the entry of the data value.
	 */
	private JPasswordField textField;
	
	/**
	 * The constructor to create the label and text field.
	 * @param prompt	the prompt to appear beside the text field
	 */
	public ValuePasswordPanel(String prompt, int space1, int space2, int txWid)
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

        BufferedImage img = null;
        if (txWid<150)
    		img = (new ReadImage()).tx_120_30();
        else if (txWid<250)
        	img = (new ReadImage()).tx_220_30();
        //else if (txWid<350)
        //	img = (new ReadImage()).tx_320_30();
        else
        	img = (new ReadImage()).tx_420_30();
		
		JLabel promptLabel = new JLabel(prompt);
		cons = spLayout.getConstraints(promptLabel);
        cons.setX(Spring.constant(space1));
        cons.setY(Spring.constant(5));
		add(promptLabel);
		
		textField = new JPasswordField(15);
		textField.setOpaque(false);
		textField.setBorder(null);
		cons = spLayout.getConstraints(textField);
        cons.setX(Spring.constant(space2));
        cons.setY(Spring.constant(5));
        cons.setWidth(Spring.constant(txWid));
        cons.setHeight(Spring.constant(25));
		add(textField);
		
		MenuButton bg = new MenuButton("",img,img);
		cons = spLayout.getConstraints(bg);
        cons.setX(Spring.constant(space2-10));
        cons.setY(Spring.constant(0));
        cons.setWidth(Spring.constant(txWid+20));
        cons.setHeight(Spring.constant(30));
        add(bg);

        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
        pCons.setConstraint(SpringLayout.SOUTH,Spring.constant(40));
        pCons.setConstraint(SpringLayout.EAST,Spring.constant(400));
	}
	
	/**
	 * Return the data entered as a String.
	 * @return	the data entered into the text field
	 */
	@SuppressWarnings("deprecation")
	public String getValueAsString()
	{
		return  textField.getText();
	}

	/**
	 * Return the data entered as an int.
	 * @precond	an integer value has been entered into the text field
	 * @return	the integer entered into the text field
	 */
	@SuppressWarnings("deprecation")
	public Long getValueAsLong()
	{
		
		String valueAsString = textField.getText();
		long value = -1;
		if (valueAsString != null && valueAsString.length() > 0) 
		{
			try
			{
				value = Long.parseLong(valueAsString);
			} catch (NumberFormatException e) 
			{
				textField.setText("Not an int: " + textField.getText());
				throw e;
			}
		}
		else
		{
			textField.setText("Empty field: " + textField.getText());
			throw new NumberFormatException("Didn't enter a value for the int");
		}
		return value;
	}

	public static final long serialVersionUID = 1;
}

