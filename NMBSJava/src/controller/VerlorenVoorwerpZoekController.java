package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import panels.VerlorenVoorwerpZoekPanel;

public class VerlorenVoorwerpZoekController {
	
	private static ArrayList<String> desc;
	
	private static JSONArray json;
	
	public static void startListening(VerlorenVoorwerpZoekPanel verlorenVoorwerpZoek) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				verlorenVoorwerpZoek.getBtnToonAlles().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						readUrl();
						desc = new ArrayList<String>();
						for (int i = 0;i<json.length();i++) {
							String v = json.getJSONObject(i).getString("TrainID") + " " +json.getJSONObject(i).getString("Description") +" "+ json.getJSONObject(i).getString("Date");
							desc.add(v);
						}
						String ss = "";
						for (String v: desc) {
							ss += v + "\n";
						}
						verlorenVoorwerpZoek.getLblResultat().setText("<html>"
								+ ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
								+ "</html>");
					}
				});
				
				verlorenVoorwerpZoek.getBtnZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						readUrl();
						
						String treinNummer = verlorenVoorwerpZoek.getTxtTreinNummer().getText();
						
						
						String ss=null;
						String error ="Er werd geen voorwerp in deze trein teruggevonden!";
						for (int i = 0;i<json.length();i++) {
							if (json.getJSONObject(i).getString("TrainID").equals(treinNummer)) {
								ss = json.getJSONObject(i).getString("TrainID") + " " +json.getJSONObject(i).getString("Description") +" "+ json.getJSONObject(i).getString("Date");							
								verlorenVoorwerpZoek.getLblResultat().setText("<html>"
										+ ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
										+ "</html>");
								System.out.println(ss);
							}
						}
						
						if (ss == null){
							verlorenVoorwerpZoek.getLblResultat().setText("<html>"
									+ error.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
									+ "</html>");
						}
					}
				});
				
				
				
				
				
			}
		});
	}
	
	private static void readUrl() {

		BufferedReader reader = null;
		try {
			URL url = new URL("http://nmbs-team.tk/api/" + "lostObject");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			connection.setRequestProperty("Authorization", "Bearer " + LoginController.getToken());
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			json = new JSONArray(buffer.toString());
			
			/*for (int i = 0;i<json.length();i++) {
				json.getJSONObject(i).getString("Description");
			}*/
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
