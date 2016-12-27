package gui;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

@SuppressWarnings("serial")
public class GUIDateFormat extends AbstractFormatter {

	private static String timePattern = "HH:mm";
	private static String datePattern = "dd/MM/yyyy";
	private static SimpleDateFormat timeFormatter = new SimpleDateFormat(timePattern);
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	private static String rawTimePattern = "HHmm";
	private static String rawDatePattern = "ddMMyy";
	private static SimpleDateFormat rawTimeFormatter = new SimpleDateFormat(rawTimePattern);
	private static SimpleDateFormat rawDateFormatter = new SimpleDateFormat(rawDatePattern);

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

	public static Object stringToObject(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	public static String objectToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}

	public static Calendar dateToCalendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	public static Date calendarToDate(Calendar c) {
		return c.getTime();
	}

	public static String getDate() {
		String ss = dateFormatter.format(new Date());
		return ss;
	}

	public static String getTime() {
		String ss = timeFormatter.format(new Date());
		return ss;
	}

	public static String getRawDate() {
		return rawDateFormatter.format(new Date());
	}

	public static String getRawTime() {
		return rawTimeFormatter.format(new Date());
	}

	public static String getRawDate(String s) throws ParseException {
		Calendar temp = Calendar.getInstance();
		temp.setTime((Date) dateFormatter.parseObject(s));
		return rawDateFormatter.format(temp.getTime());
	}

	public static String getRawTime(String s) throws ParseException {
		Calendar temp = Calendar.getInstance();
		temp.setTime((Date) timeFormatter.parseObject(s));
		return rawTimeFormatter.format(temp.getTime());
	}

}