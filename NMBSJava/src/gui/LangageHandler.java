package gui;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public abstract class LangageHandler {
	
	private static String taal;

	static public String getTaal() {
		return taal;
	}

	static public void setTaal(String t) {
		taal = t;
	}
	
	public static String bundleFR(String t){
	String s;
	Locale frenchLocale = new Locale("fr", "FR");
	ResourceBundle bundle = ResourceBundle.getBundle("gui/bundle", frenchLocale);
	s = bundle.getString(t);
	return s;
	}
	
	public static String bundleEN(String t){
	String s;
	Locale englishLocale = new Locale("en", "EN");
	ResourceBundle bundle = ResourceBundle.getBundle("gui/bundle", englishLocale);
	s = bundle.getString(t);
	return s;
	}
	
	public static String bundleNL(String t){
	String s;
	Locale dutchLocale = new Locale("nl", "NL");
	ResourceBundle bundle = ResourceBundle.getBundle("gui/bundle", dutchLocale);
	s = bundle.getString(t);
	return s;
	}

	
	public static void chooseLangageLbl(JLabel l,String s) {
		if(taal == "Français")
		{
			l.setText(bundleFR(s));
		}
		else if (taal == "English")
		{
			l.setText(bundleEN(s));
		}
		else if (taal == "Nederlands")
		{
			l.setText(bundleNL(s));
		}
	}
	
	public static void chooseLangageBtn(JButton b, String s) {
		if(taal == "Français")
		{
			b.setText(bundleFR(s));
		}
		else if (taal == "English")
		{
			b.setText(bundleEN(s));
		}
		else if (taal == "Nederlands")
		{
			b.setText(bundleNL(s));
		}
	}
	
	public static void chooseLangageRdb(JRadioButton b, String s) {
		if(taal == "Français")
		{
			b.setText(bundleFR(s));
		}
		else if (taal == "English")
		{
			b.setText(bundleEN(s));
		}
		else if (taal == "Nederlands")
		{
			b.setText(bundleNL(s));
		}
	}
	
	public static String chooseLangageCmb(String s)
	{
		String vertal = "";
		if(taal == "Français")
		{
			vertal = bundleFR(s);
		}
		else if (taal == "English")
		{
			vertal = bundleEN(s);
		}
		else if (taal == "Nederlands")
		{
			vertal = bundleNL(s);
		}
		return vertal;
	}
}
