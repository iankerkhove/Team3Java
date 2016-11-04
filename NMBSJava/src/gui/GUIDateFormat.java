package gui;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

@SuppressWarnings("serial")
public class GUIDateFormat extends AbstractFormatter {

	private static String timePattern = "HH:mm";
	private static String datePattern = "dd/MM/yyyy";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	private static SimpleDateFormat timeFormatter = new SimpleDateFormat(timePattern);

	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

	public static String getDate() {
		String ss = dateFormatter.format(new Date());
		return ss;
	}

	public static String getTime() {
		String ss = timeFormatter.format(new Date());
		return ss;
	}

}
