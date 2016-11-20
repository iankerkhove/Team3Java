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

	private static String van;
	private static String naar;
	private static int routeID;
	private static String begindatum;
	private static String einddatum;
	private static int typeKlasseSelectedMnem;
	private static int vanID;
	private static int naarID;

	public static void startListening(BiljetPanel biljet) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				biljet.getBtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						van = (String) biljet.getTxtVan().getSelectedItem();
						naar = (String) biljet.getTxtNaar().getSelectedItem();
						begindatum = biljet.getDteGaanDatum().getJFormattedTextField().getText();
						einddatum = biljet.getDteTerugDatum().getJFormattedTextField().getText();
						int typeHeenTerugSelectedMnem = biljet.getGrpHeenTerug().getSelection().getMnemonic();
						String soortBiljet = biljet.getCboBiljet().getSelectedItem().toString();
						typeKlasseSelectedMnem = biljet.getGrpKlasseTicket().getSelection().getMnemonic();
						
						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkDate(begindatum)
								&& DateTimeConverter.checkDate(einddatum) && typeHeenTerugSelectedMnem != 0
								&& typeKlasseSelectedMnem != 0 && !soortBiljet.equals(null)) {
							readRouteID();
							int temp = createTicket();
							if (temp == 200) {
								System.out.println("Juist biljet");
							} else {
								System.err.println("Fout in API, statuscode: " + temp);
							}
						} else {
							System.err.println("Fout biljet");
						}

					}
				});
				biljet.getCboBiljet().addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if(biljet.getCboBiljet().getSelectedItem().equals("Standaardbiljet")){
							biljet.getDteTerugDatum().setEnabled(false);
							//biljet.getLblPrijs().setText("€ 10");
							if(biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 15");
							}
							else if((biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 10");
							}
							if(biljet.getRdbKlasseVerhoging().isSelected()){
								biljet.getLblPrijs().setText("€ 2");
							}
						}
						if(biljet.getCboBiljet().getSelectedItem().equals("GoPass 1")){
							//biljet.getLblPrijs().setText("€ 6");
							if(biljet.getRdbHeenTerug().isSelected()&&(biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 10");
							}
							else if((biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 6");
							}
							if(biljet.getRdbKlasseVerhoging().isSelected()){
								biljet.getLblPrijs().setText("€ 1");
							}
						}
						if(biljet.getCboBiljet().getSelectedItem().equals("Weekendbiljet")){
							//biljet.getLblPrijs().setText("€ 12.5");
							if(biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 20");
							}
							else if((biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 12.5");
							}
							if(biljet.getRdbKlasseVerhoging().isSelected()){
								biljet.getLblPrijs().setText("€ 2");
							}
						}
						if(biljet.getCboBiljet().getSelectedItem().equals("Seniorenbiljet")){
							//biljet.getLblPrijs().setText("€ 5");
							if(biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 8");
							}
							else if((biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 5");
							}
							if(biljet.getRdbKlasseVerhoging().isSelected()){
								biljet.getLblPrijs().setText("€ 1");
							}
						} 
						if(biljet.getCboBiljet().getSelectedItem().equals("Biljet Kind")){
							//biljet.getLblPrijs().setText("€ 5");
							if(biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 8");
							}
							else if((biljet.getRdbEersteKlasse().isSelected()||biljet.getRdbTweedeKlasse().isSelected())){
								biljet.getLblPrijs().setText("€ 5");
							}
							if(biljet.getRdbKlasseVerhoging().isSelected()){
								biljet.getLblPrijs().setText("€ 0");
							}
						}
						//
						
					}
					
				});
			}
		});
	}

	public static void readRouteID() {
		
		BufferedReader reader = null;
		try {
			readStationID();
			URL url = new URL("http://nmbs-team.tk/api/route/" + vanID + "/" + naarID);
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

			// JSONArray jsonTemp = new JSONArray(buffer.toString());

			JSONObject temp = new JSONObject(buffer.toString());

			int statusCode = temp.getInt("StatusCode");
			if (statusCode == 404) {
				createRoute();
			}
			// zien hoe de volledige json er uit ziet
			// System.out.println(json.toString(4));
			/*
			 * String temp = ""; String route = van + " - " + naar; for (int i =
			 * 0; i < jsonTemp.length(); i++) { temp +=
			 * jsonTemp.getJSONObject(i).getJSONObject("DepartureStation").
			 * getString("Name"); temp += " - "; temp +=
			 * jsonTemp.getJSONObject(i).getJSONObject("ArrivalStation").
			 * getString("Name");
			 * 
			 * if (route.equals(temp)) { routeID =
			 * jsonTemp.getJSONObject(i).getInt("RouteID");
			 * System.out.println(routeID); } else { temp = ""; readStationID();
			 * } }
			 */

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

	public static void readStationID() {

		BufferedReader reader = null;
		try {
			URL url = new URL("http://nmbs-team.tk/api/station");
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

			JSONArray json = new JSONArray(buffer.toString());
			// zien hoe de volledige json er uit ziet

			boolean goTroughVan = false;
			boolean goTroughNaar = false;

			String temp = "";

			for (int i = 0; i < json.length(); i++) {

				temp = json.getJSONObject(i).getString("Name");

				if (van.equals(temp)) {
					vanID = json.getJSONObject(i).getInt("StationID");
					goTroughVan = true;
				}

				if (naar.equals(temp)) {
					naarID = json.getJSONObject(i).getInt("StationID");
					goTroughNaar = true;
				}
			}

			if (goTroughVan && goTroughNaar) {
				//createRoute();
			} else {
				System.err.println("Een van de stations werd niet gevonden.");
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

	public static int createRoute() {

		BufferedReader reader = null;
		try {
			URL url = new URL(
					"http://nmbs-team.tk/api/route/create?DepartureStationID=" + vanID + "&ArrivalStationID=" + naarID);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
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

			JSONObject temp = new JSONObject(buffer.toString());

			System.out.println(temp.toString(4));
			//

			int statusCode = temp.getInt("StatusCode");
			if (statusCode == 200) {
				routeID = temp.getInt("RouteID");
			}
			return statusCode;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		// readRouteID();

		return 500;
	}

	public static int createTicket() {

		BufferedReader reader = null;
		try {
			URL url = new URL("http://nmbs-team.tk/api/ticket/create?RouteID=" + routeID + "&Date=" + begindatum
					+ "&Price=10&ValidFrom=" + begindatum + "&ValidUntil=" + einddatum + "&ComfortClass="
					+ typeKlasseSelectedMnem);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
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

			JSONObject temp = new JSONObject(buffer.toString());
			System.out.println(temp.toString(4));
			int statusCode = temp.getInt("StatusCode");
			return statusCode;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 500;
	}
}
