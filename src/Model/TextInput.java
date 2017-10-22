package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

public class TextInput implements ActionListener {

	private JTextField textField;
	private EventDateTimeLocation eventDateTimeLocation;
	private EventDateTimeLocation eventDateTimeLocationRemindMe;
	private DefaultListModel<String> model;
	private DefaultListModel<String> model2;
	private String cap;

	public TextInput(JTextField textField, DefaultListModel<String> model, DefaultListModel<String> model2) {

		this.textField = textField;
		this.model = model;
		this.model2 = model2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String textFieldValue = textField.getText();

		eventDateTimeLocation = new EventDateTimeLocation(textFieldValue);
		textFieldValue = textFieldValue.toLowerCase();

		Pattern pattern = Pattern.compile("(?i)Remind me to (.*)");
		Matcher matcher = pattern.matcher(textFieldValue);
		
		
		/**
		 * If we detect 'remind me to' in the user input we know he wants to create a reminder:
		 *  - if the user inputs a date, we precise the date in the wanted format
		 *  - if he inputs a time, we precise it in the wanted format
		 *  - the rest of the input is the reminder itself
		 * If we don't detect 'remind me to' we know the user is created an event,
		 * in which case we identify and change the formats of the date, time and location, if the user has specified them
		 * The rest is the event itself
		 */
		if (matcher.find()) {
			textFieldValue.replace("(?i)Remind me to ", "");
			String str = matcher.group(1);
			cap = str.substring(0, 1).toUpperCase() + str.substring(1);
			eventDateTimeLocationRemindMe = new EventDateTimeLocation(cap);

			if (!(eventDateTimeLocationRemindMe.date().equals("-"))
					&& !(eventDateTimeLocationRemindMe.time().equals("-"))) {
				if (!(eventDateTimeLocationRemindMe.location().equals("-"))) {
					model2.addElement("Time:" + eventDateTimeLocationRemindMe.time() + " | Date:"
							+ eventDateTimeLocationRemindMe.date() + " | " + eventDateTimeLocationRemindMe.event() + " "
							+ eventDateTimeLocation.location());
				} else {
					model2.addElement("Time:" + eventDateTimeLocationRemindMe.time() + " | Date:"
							+ eventDateTimeLocationRemindMe.date() + " | " + eventDateTimeLocationRemindMe.event());
				}
			} else if (!(eventDateTimeLocationRemindMe.time().equals("-"))) {
				if (!(eventDateTimeLocationRemindMe.location().equals("-"))) {
					model2.addElement("Time:" + eventDateTimeLocationRemindMe.time() + " | "
							+ eventDateTimeLocationRemindMe.event() + " " + eventDateTimeLocation.location());
				} else {
					model2.addElement("Time:" + eventDateTimeLocationRemindMe.time() + " | "
							+ eventDateTimeLocationRemindMe.event());
				}
			} else if (!(eventDateTimeLocationRemindMe.date().equals("-"))) {
				if (!(eventDateTimeLocationRemindMe.location().equals("-"))) {
					model2.addElement("Date:" + eventDateTimeLocationRemindMe.date() + " | "
							+ eventDateTimeLocationRemindMe.event() + " " + eventDateTimeLocation.location());
				} else {
					model2.addElement("Date:" + eventDateTimeLocationRemindMe.date() + " | "
							+ eventDateTimeLocationRemindMe.event());
				}
			} else {
				model2.addElement(cap);
			}

		} else {

			model.addElement("Event:" + eventDateTimeLocation.event() + " | Date:" + eventDateTimeLocation.date()
					+ " | Time:" + eventDateTimeLocation.time() + " | Location:" + eventDateTimeLocation.location());

		}

	}
}
