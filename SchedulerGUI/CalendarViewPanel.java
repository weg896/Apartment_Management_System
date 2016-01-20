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

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarViewPanel extends JPanel {
JLabel moLabel, yearLabel, dayLabel;
JButton previousButton, nextButton;
JTable calendarTable;
JComboBox cmbYear;
DefaultTableModel calendarModel; //Table model
ListSelectionModel selectionModel;
JScrollPane stblCalendar; //The scrollpane
int realYear, realMonth, realDay, currentYear, currentMonth;
int selectedCol, selectedRow, selectedDay;

public CalendarViewPanel(){

//Create controls
dayLabel = new JLabel ();
yearLabel = new JLabel("2013");
moLabel = new JLabel ("January");
previousButton = new JButton ("<<");
nextButton = new JButton (">>");
calendarModel = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
calendarTable = new JTable(calendarModel);
stblCalendar = new JScrollPane(calendarTable);


//Set border
setBorder(BorderFactory.createTitledBorder("Calendar"));

//Register action listeners
previousButton.addActionListener(new previousButton_Action());
nextButton.addActionListener(new nextButton_Action());

//Add controls to pane
add(dayLabel);
add(moLabel);
add(yearLabel);
add(previousButton);
add(nextButton);
add(stblCalendar);

//Set bounds
/*setBounds(0, 0, 320, 335);
moLabel.setBounds(160-moLabel.getPreferredSize().width/2, 25, 100, 25);
dayLabel.setBounds(10, 305, 80, 20);
previousButton.setBounds(10, 25, 50, 25);
nextButton.setBounds(260, 25, 50, 25);
stblCalendar.setBounds(10, 50, 300, 250);*/

//Get curr month/year
GregorianCalendar cal = new GregorianCalendar(); //Create calendar
realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
realMonth = cal.get(GregorianCalendar.MONTH); //Get month
realYear = cal.get(GregorianCalendar.YEAR); //Get year
currentMonth = realMonth; //Match month and year
currentYear = realYear;

//Add headers
String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
for (int i=0; i<7; i++){
calendarModel.addColumn(headers[i]);
}

calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

//No resize/reorder
calendarTable.getTableHeader().setResizingAllowed(false);
calendarTable.getTableHeader().setReorderingAllowed(false);

//Single cell selection
calendarTable.setColumnSelectionAllowed(true);
calendarTable.setRowSelectionAllowed(true);
calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//Set row/column count
calendarTable.setRowHeight(38);
selectionModel = calendarTable.getSelectionModel();
selectionModel.addListSelectionListener(new dateSelector());
calendarTable.getColumnModel().getSelectionModel().addListSelectionListener(new dateSelector());

calendarModel.setColumnCount(7);
calendarModel.setRowCount(6);


//Refresh calendar
updateCalendar (realMonth, realYear); //Refresh calendar
}

public void updateCalendar(int month, int year){
//Variables
String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
int nod, som; //Number Of Days, Start Of Month

//Allow/disallow buttons
previousButton.setEnabled(true);
nextButton.setEnabled(true);
if (month == 0 && year <= realYear-3){previousButton.setEnabled(false);} //Too early
if (month == 11 && year >= realYear+3){nextButton.setEnabled(false);} //Too late
moLabel.setText(months[month]); //Refresh the month label (at the top)
moLabel.setBounds(160-moLabel.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
yearLabel.setText(String.valueOf(year));
selectedDay = 1;
dayLabel.setText(String.valueOf(selectedDay));
//Clear table
for (int i=0; i<6; i++){
for (int j=0; j<7; j++){
calendarModel.setValueAt(null, i, j);
}
}

//Get first day of month and number of days
GregorianCalendar cal = new GregorianCalendar(year, month, 1);
nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
som = cal.get(GregorianCalendar.DAY_OF_WEEK);

selectedRow = 0;
selectedCol = (som-1)%7;
//Draw calendar
for (int i=1; i<=nod; i++){
int row = new Integer((i+som-2)/7);
int column  =  (i+som-2)%7;
calendarModel.setValueAt(i, row, column);
}

//Apply renderers
calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new calendarRenderer());
}


class calendarRenderer extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        setBackground(Color.white);
        if (value != null){
        if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
           setBackground(Color.gray);
        }
        
        }
        if ((row == selectedRow) && (column == selectedCol))
            setBackground(Color.lightGray);
        
            setBorder(null);
        setForeground(Color.black);
        return this;  
    }
}

class previousButton_Action implements ActionListener{
public void actionPerformed (ActionEvent e){
if (currentMonth == 0){ //Back one year
currentMonth = 11;
currentYear -= 1;
}
else{ //Back one month
currentMonth -= 1;
}
updateCalendar(currentMonth, currentYear);
}
}
class nextButton_Action implements ActionListener{
public void actionPerformed (ActionEvent e){
if (currentMonth == 11){ //Foward one year
currentMonth = 0;
currentYear += 1;
}
else{ //Foward one month
currentMonth += 1;
}
updateCalendar(currentMonth, currentYear);
}
}

class dateSelector implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        //Ignore extra messages.
        if (e.getValueIsAdjusting()) return;

        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (!lsm.isSelectionEmpty()) {            
            selectedRow = calendarTable.getSelectedRow();
            selectedCol = calendarTable.getSelectedColumn();
            selectedDay = (int)calendarTable.getValueAt(selectedRow, selectedCol);
            
            
            dayLabel.setText(String.valueOf(selectedDay));
            dayLabel.setVisible(true);    
            validate();
            repaint();
            
        }
    }
}
}



