package model;

import java.io.*;

/**
 * Deze klasse verzorgt eenvoudige invoer vanaf het toetsenbord.
 */
public class Input {

	/**
	 * Deze methode drukt de boodschap message op het scherm en leest daarna een
	 * integer getal van de invoer. Wanneer meerdere integer getallen na elkaar
	 * gevraagd worden, wordt de boodschap slechts eenmaal getoond indien de
	 * invoer op 1 lijn staat. Bij foutieve invoer wordt een bericht op de
	 * uitvoer geschreven en als resultaat 0 teruggegeven.
	 * 
	 * Wat je hieronder ziet is HTML code, ingepakt tussen twee tags. Dit wordt
	 * herkend door javadoc. Je kan dus ook HTML in je javadoc commentaar
	 * invoegen. Kijk maar eens naar de gegenereerde documentatie.
	 * 
	 * <code>
	 * Voorbeeld:<br><br>
	 * &nbsp;&nbsp;for (int i=0; i<3; i++) {<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println(Input.readInt("Geef een getal"));<br>
	 * &nbsp;&nbsp;}<br>
	 * <br>
	 * Dit geeft:<br><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;Geef een getal<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;1 2 3<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;1<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;2<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;3<br>
	 * <br>
	 * Of:<br><br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;Geef een getal<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;1<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;1<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;Geef een getal<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;2<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;2<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;Geef een getal<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;3<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;3<br>
	 * </code>
	 * 
	 * @param message
	 *            Het te tonen bericht
	 */
	public static int readInt(String message) {
		if (tokens == null || !tokens.hasMoreTokens())
			System.out.println(message);
		return readInt();
	}

	/**
	 * Deze methode leest een integer getal van de invoer. Bij foutieve invoer
	 * wordt een bericht op de uitvoer geschreven en als resultaat 0
	 * teruggegeven.
	 */
	public static int readInt() {
		try {
			String temp = nextToken();
			return Integer.parseInt(temp);
		} catch (IOException ioe) {
			System.out.println("Error reading integer number");
			return 0;
		} catch (NumberFormatException nfe) {
			System.out.println("Error reading integer number");
			return 0;
		}
	}

	/**
	 * Deze methode drukt de boodschap message op het scherm en leest daarna een
	 * double getal van de invoer. Bij foutieve invoer wordt een bericht op de
	 * uitvoer geschreven en als resultaat 0 teruggegeven.
	 * 
	 * @param message
	 *            Het te tonen bericht
	 */
	public static double readDouble(String message) {
		System.out.println(message);
		return readDouble();
	}

	/**
	 * Deze methode leest een double getal van de invoer. Bij foutieve invoer
	 * wordt een bericht op de uitvoer geschreven en als resultaat 0
	 * teruggegeven.
	 */
	public static double readDouble() {
		try {
			String temp = nextToken();
			return Double.parseDouble(temp);
		} catch (IOException ioe) {
			System.out.println("Error reading double number");
			return 0;
		} catch (NumberFormatException nfe) {
			System.out.println("Error reading double number");
			return 0;
		}
	}

	/**
	 * Deze methode drukt de boodschap message op het scherm en leest daarna een
	 * float getal van de invoer. Bij foutieve invoer wordt een bericht op de
	 * uitvoer geschreven en als resultaat 0 teruggegeven.
	 * 
	 * @param message
	 *            Het te tonen bericht
	 */
	public static double readFloat(String message) {
		System.out.println(message);
		return readFloat();
	}

	/**
	 * Deze methode leest een float getal van de invoer. Bij foutieve invoer
	 * wordt een bericht op de uitvoer geschreven en als resultaat 0
	 * teruggegeven.
	 */
	public static double readFloat() {
		try {
			String temp = nextToken();
			return Float.parseFloat(temp);
		} catch (IOException ioe) {
			System.out.println("Error reading float number");
			return 0;
		} catch (NumberFormatException nfe) {
			System.out.println("Error reading float number");
			return 0;
		}
	}

	/**
	 * Deze methode drukt de boodschap message op het scherm en leest daarna een
	 * karakter van de invoer. Bij foutieve invoer wordt een bericht op de
	 * uitvoer geschreven en als resultaat ' ' (spatie) teruggegeven.
	 * 
	 * @param message
	 *            Het te tonen bericht
	 */
	public static char readChar(String message) {
		System.out.println(message);
		return readChar();
	}

	/**
	 * Deze methode leest een karakter van de invoer. Bij foutieve invoer wordt
	 * een bericht op de uitvoer geschreven en als resultaat ' ' (spatie)
	 * teruggegeven.
	 */
	public static char readChar() {
		try {
			String temp = in.readLine();
			return temp.charAt(0);
		} catch (IOException ioe) {
			System.out.println("Error reading character");
			return ' ';
		}
	}

	/**
	 * Deze methode drukt de boodschap message op het scherm en leest daarna een
	 * string van de invoer.
	 * 
	 * @param message
	 *            Het te tonen bericht
	 */
	public static String readString(String message) {
		System.out.println(message);
		return readString();
	}

	/**
	 * Deze methode leest een string van de invoer
	 */
	public static String readString() {
		try {
			return in.readLine();
		} catch (IOException ioe) {
			System.out.println("Error reading string");
			return "";
		}
	}

	private static java.util.StringTokenizer tokens;

	private static String nextToken() throws IOException {
		while (tokens == null || !tokens.hasMoreElements()) {
			tokens = new java.util.StringTokenizer(in.readLine());
		}
		return tokens.nextToken();
	}

	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));
}