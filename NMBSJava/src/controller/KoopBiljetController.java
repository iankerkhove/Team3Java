package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

//import model.Ticket;
import panels.BiljetPanel;

public class KoopBiljetController {

	private static JSONArray json;
	private static String van;
	private static String naar;

	public static void startListening(BiljetPanel biljet) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				biljet.getBtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						van = biljet.getTxtVan().getText();
						naar = biljet.getTxtNaar().getText();
						String begindatum = biljet.getDteGaanDatum().getJFormattedTextField().getText();
						String einddatum = biljet.getDteTerugDatum().getJFormattedTextField().getText();
						int typeHeenTerugSelectedMnem = biljet.getGrpHeenTerug().getSelection().getMnemonic();
						String soortBiljet = biljet.getCboBiljet().getSelectedItem().toString();
						int typeKlasseSelectedMnem = biljet.getGrpKlasseTicket().getSelection().getMnemonic();

						readUrl();

						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkDate(begindatum)
								&& DateTimeConverter.checkDate(einddatum) && typeHeenTerugSelectedMnem != 0
								&& typeKlasseSelectedMnem != 0 && !soortBiljet.equals(null)) {
							System.out.println("Juist biljet");

							// TODO TICKET AANMAKEN MET CORRECTE CONSTRUCTOR,
							// KLASSE AANPASSEN

							// Ticket s = new Ticket(routeID, date, price,
							// validFrom, validUntil, comfortClass, ticketID);
							// van, naar, prijs, begindatum, einddatum,
							// typeHeenTerugSelectedMnem, soortBiljet,
							// typeKlasseSelectedMnem
						} else {
							System.err.println("Fout biljet");
						}

					}
				});
			}
		});
	}

	public static void readUrl() {

		BufferedReader reader = null;
		try {
			URL url = new URL("http://nmbs-team.tk/api/route");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			System.out.println(LoginController.getToken());
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

			String temp = "";

			for (int i = 0; i < json.length(); i++) {
				temp += json.getJSONObject(i).getJSONObject("DepartureStation").getString("Name");
				temp += " - ";
				temp += json.getJSONObject(i).getJSONObject("ArrivalStation").getString("Name");
				temp += "\n";
			}

			System.out.println(temp);
			String route = van + " - " + naar;
			for (int i = 0; i < json.length(); i++) {
				int res = json.getJSONObject(i).getInt("RouteID");
				System.out.println(res);
			}
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
