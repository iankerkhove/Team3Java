package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.LangageHandler;

public abstract class Popup {

	private String tekst;
	private String titel;

	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}

	public static void warningMessage (String tekst, String titel){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,
				LangageHandler.chooseLangage(tekst),
				LangageHandler.chooseLangage(titel),
				JOptionPane.WARNING_MESSAGE);
	}

	public static void plainMessage (String tekst, String titel){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,
				LangageHandler.chooseLangage(tekst),
				LangageHandler.chooseLangage(titel),
				JOptionPane.PLAIN_MESSAGE);
	}

	public static void errorMessage (String tekst, String titel){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,
				LangageHandler.chooseLangage(tekst),
				LangageHandler.chooseLangage(titel),
				JOptionPane.ERROR_MESSAGE);
	}
}
