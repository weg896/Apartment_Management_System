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
import java.awt.event.*;
import javax.swing.*;

public class NotificationTestPanel extends JPanel {
    public static JPanel pn1;
    public static JPanel pn2;
    public static JPanel pn3;
    public static JPanel pn4;
    public static JPanel pn5;
    public NotificationTestPanel (int userID){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            pn1 = new NotificationTestUserInfoPanel(userID);   
       
            pn2 = new JPanel();
            pn2.setLayout(new BoxLayout(pn2, BoxLayout.PAGE_AXIS));
            pn3 = new JPanel();
         
            pn4 = new JPanel();

            add (pn1);
            add (pn2);
            add (pn3);
            add (pn4);
    }    

}
