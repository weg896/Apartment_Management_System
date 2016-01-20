/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael Lucki
 */
package SchedulerGUI;

import java.awt.*;
import javax.swing.*;

public class NotificationTestFrame extends JFrame{
    public NotificationTestFrame (int userID)
	{
		setTitle("Testing Notification System");
		setSize(300, 600);
		JPanel ntPanel = new NotificationTestPanel(userID);
		add(ntPanel);
	}

	public static final long serialVersionUID = 1;
}
