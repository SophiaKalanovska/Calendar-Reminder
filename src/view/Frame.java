package view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Model.TextInput;

public class Frame extends JFrame {
	// a JTabbedPane, two DefaultListModel<String>, two JList<String's and a JScrollPane declared.
	private JTabbedPane tabbed_pane;
	private JList<String> message;
	private JList<String> message2;
	private JTextField textField;
	private JScrollPane scrollPane;
	private DefaultListModel<String> model;
	private DefaultListModel<String> model2;
	
	// this class represents a JFrame.
	//  new instances are created for all the fields from above.
	public Frame() {
		//the constructor of the  super class is called 
		super("");
		model = new DefaultListModel<String>();
		model2 = new DefaultListModel<String>();
		tabbed_pane = new JTabbedPane();
		message = new JList<String>(model);
		//add mouseListner that gets the element(index) when being clicked once at the calendar part and delete it when clicked twice
		message.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList message = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = message.locationToIndex(evt.getPoint());
					model.remove(index);
				
				}
			}
		});

		message2 = new JList<String>(model2);
		//add mouseListner that gets the element(index) when being clicked once at the reminder part and delete it when clicked twice
		message2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList message2 = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = message2.locationToIndex(evt.getPoint());
					model2.remove(index);
				}
			}
		});
		// add a scroll pane to the message 
		scrollPane = new JScrollPane(message);
		
		//add two tabs, one calendar with the message jList and one with the reminder with the message2 JList
		tabbed_pane.addTab("Calendar", message);
		tabbed_pane.addTab("Reminder", message2);

		//initialise the JtextField
		textField = new JTextField();
		// add an ActionListener, that creates an instance of a class that implements ActionListener with arguments textField, getModel(), getModel2();
		textField.addActionListener(new TextInput(textField, getModel(), getModel2()));

		// an BorderLayout is chosen for this GUI
		setLayout(new BorderLayout());
		// the tabbed pane containing the both jLists is assign to the centre
		this.add(tabbed_pane, BorderLayout.CENTER);
		//the textField is assigned to the south
		this.add(textField, BorderLayout.SOUTH);

		try {
			FileInputStream reminder = new FileInputStream("Reminder File.txt");
			BufferedReader readReminder = new BufferedReader(new InputStreamReader(reminder));
			String rline;

			while ((rline = readReminder.readLine()) != null) {
				rline = rline.trim();
				model2.addElement(rline);
			}
			reminder.close();

		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

		// Fetches the data stored in the file and adds it to the calendar
		try {
			FileInputStream calendar = new FileInputStream("Calendar File.txt");
			BufferedReader readCalendar = new BufferedReader(new InputStreamReader(calendar));
			String cline;

			while ((cline = readCalendar.readLine()) != null) {
				cline = cline.trim();
				model.addElement(cline);
			}
			calendar.close();

		} catch (Exception e11) {
			System.err.println(e11.getMessage());
		}

		setPreferredSize(new Dimension(600, 500));
		this.pack();
		this.setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Saves the data stored in the reminder in a new file
				BufferedWriter reminderFile = null;
				try {
					reminderFile = new BufferedWriter(new FileWriter("Reminder File.txt"));
					for (int i = 0; i < model2.size(); i++) {
						reminderFile.write(model2.getElementAt(i));
						reminderFile.newLine();
					}
				} catch (IOException e1) {
					System.err.println(e1);
				} finally {
					if (reminderFile != null) {
						try {
							reminderFile.close();
						} catch (IOException e1) {
							System.err.println(e1);
						}
					}
				}

				// Saves the data stored in the calendar in a new file
				BufferedWriter calendarFile = null;
				try {
					calendarFile = new BufferedWriter(new FileWriter("Calendar File.txt"));
					for (int i = 0; i < model.size(); i++) {
						calendarFile.write(model.getElementAt(i));
						calendarFile.newLine();
					}
				} catch (IOException e1) {
					System.err.println(e1);
				} finally {
					if (calendarFile != null) {
						try {
							calendarFile.close();
						} catch (IOException e1) {
							System.err.println(e1);
						}
					}
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	/**
	 * Getter method of the model
	 */
	public DefaultListModel<String> getModel() {
		return model;
	}
	
	/**
	 * Getter method of the model2
	 */
	public DefaultListModel<String> getModel2() {
		return model2;
	}

}
