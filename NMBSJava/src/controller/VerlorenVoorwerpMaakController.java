package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;

import org.json.JSONArray;

import gui.Popup;
import model.SettingsSingleton;
import panels.VerlorenVoorwerpMaakPanel;

public class VerlorenVoorwerpMaakController {

	//private static ArrayList<String> stat;

	private static JSONArray json;
	private static SettingsSingleton settings;

	public static void startListening(VerlorenVoorwerpMaakPanel verlorenVoorwerpMaak) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				settings = SettingsSingleton.getSettings();
				
				verlorenVoorwerpMaak.getBtnMaak().addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent e) {
						readUrl();
						JFrame frame = new JFrame();

						String station = verlorenVoorwerpMaak.getTxtStation().getSelectedItem().toString();
						String treinNummer = verlorenVoorwerpMaak.getTxtTrein().getText();
						String omschrijving = verlorenVoorwerpMaak.getTxtOmschrijving().getText();
						String datum = verlorenVoorwerpMaak.getDatePicker().getJFormattedTextField().getText();
						String omschrijving2 = omschrijving.replaceAll(" ", "_");
						String statId = "";


						if (treinNummer.equals("") ||omschrijving.equals(""))
						{
							Popup.warningMessage("voorwerpWarningPopup", "voorwerpWarningPopup");
						}
						else
						{
							for (int i = 0; i<json.length();i++)
							{
								if (station.equals(json.getJSONObject(i).getString("Name").toString()))
								{
									statId = json.getJSONObject(i).getString("StationID").toString();
								}							
							}

							URLCon url = new URLCon();
							try {
								url.readUrl("http://nmbs-team.tk/api/lostObject/create?StationID=" +statId +"&Description=" + omschrijving2 + "&Date=" + datum +"&TrainID=" + treinNummer, "POST");
								Popup.plainMessage("voorwerpPlainPopup", "voorwerpPlainPopupTitel");
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					}
				});
			}
		});
	}

	private static void readUrl() {

		BufferedReader reader = null;
		try {
			URL url = new URL("http://nmbs-team.tk/api/" + "station");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			connection.setRequestProperty("Authorization", "Bearer " + settings.getApiToken());
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