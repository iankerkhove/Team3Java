package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLCon {
	
	private static BufferedReader reader;
	private static URL url;
	private static URLConnection connection;
	
	public static String readUrl(String urlString) throws Exception {
		reader = null;
		try {
			url = new URL(urlString); 
			connection = url.openConnection();
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			return buffer.toString();

		} finally {
			if (reader != null)
				reader.close();
		}
	}
}
