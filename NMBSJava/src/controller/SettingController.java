package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import model.SettingsSingleton;
import services.InitDatabaseRunnable;

/**
 * 
 * Code taken from http://www.mkyong.com/java/java-properties-file-examples/
 * @author Ludovic
 *
 */

public class SettingController
{
	private static Properties properties;

	public static void start()
	{
		properties = new Properties();
		InputStream input = null;
		
		try {

			File f = new File("config.properties");
			f.createNewFile();
			input = new FileInputStream(f);

			// load a properties file
			properties.load(input);

			SettingsSingleton settings = SettingsSingleton.getSettings();
			String firstTime = properties.getProperty("FirstTime");
			
			//Set settings from file to singleton here
			
			if (firstTime != null)
				if (firstTime.equals("True"))
					return;
			
			Thread initdb = new Thread(new InitDatabaseRunnable());
			initdb.run();				
			
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void save()
	{
		OutputStream output = null;
		
		try {
	
			output = new FileOutputStream("config.properties");
	
			SettingsSingleton settings = SettingsSingleton.getSettings();
			
			// set the properties value
			properties.setProperty("FirstTime", settings.getFirstTime());
	
			// save properties to project root folder
			properties.store(output, null);
	
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
		}
		
	}
	
}
