package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {
	public static String getEpoch(String d, String t) {
		String str = d + " " + t;
		long epoch = 0;
		if (checkTime(t) && checkDate(d)) {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date;
			try {
				date = df.parse(str);
				epoch = date.getTime() / 1000;
				return Long.toString(epoch);
			} catch (ParseException e) {
				System.err.println("cannot parse");
				e.printStackTrace();
			}
		} else {
			System.out.println("Ongeldige invoer in getEpoch()");
		}
		return Long.toString(epoch);
	}

	public static String getReadableFromEpoch(String e) {
		return Integer.parseInt(e) / 60 + "";
	}

	public static String getDateString(String epoch) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(new Date(Long.parseLong(epoch) * 1000));
	}

	public static String getTimeString(String epoch) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(new Date(Long.parseLong(epoch) * 1000));
	}

	public static boolean checkDate(String s) {
		return s.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d");

	}

	public static boolean checkTime(String s) {
		return s.matches("\\d\\d:\\d\\d") || s.matches("\\d:\\d\\d");

	}
}