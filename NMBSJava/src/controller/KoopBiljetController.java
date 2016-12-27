package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
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
	private static String prijs;

	public static void startListening(BiljetPanel biljet)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				biljet.getBtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						van = (String) biljet.getTxtVan().getSelectedItem();
						naar = (String) biljet.getTxtVan().getSelectedItem();
						naar = (String) biljet.getTxtNaar().getSelectedItem();

						begindatum = biljet.getDteGaanDatum().getJFormattedTextField().getText();
						einddatum = biljet.getDteTerugDatum().getJFormattedTextField().getText();
						int typeHeenTerugSelectedMnem = biljet.getGrpHeenTerug().getSelection().getMnemonic();
						String soortBiljet = biljet.getCboBiljet().getSelectedItem().toString();
						typeKlasseSelectedMnem = biljet.getGrpKlasseTicket().getSelection().getMnemonic();
						prijs = biljet.getLblPrijs().getText();

						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkDate(begindatum)
								&& DateTimeConverter.checkDate(einddatum) && typeHeenTerugSelectedMnem != 0
								&& typeKlasseSelectedMnem != 0 && !soortBiljet.equals(null)) {
							readRouteID();
							int temp = createTicket();
							if (temp == 200) {
								System.out.println("Juist biljet");
							} else {
								System.err.println(routeID);
								System.err.println("Fout in API, statuscode: " + temp);
							}
						} else {
							System.err.println("Fout biljet");
						}

					}
				});
				biljet.getCboBiljet().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (biljet.getCboBiljet().getSelectedItem().equals("Standaardbiljet")) {
							biljet.getDteTerugDatum().setEnabled(false);
							// biljet.getLblPrijs().setText("€ 10");
							if (biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 15");
							} else if ((biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 10");
							}
							if (biljet.getRdbKlasseVerhoging().isSelected()) {
								biljet.getLblPrijs().setText("€ 2");
							}
						}
						if (biljet.getCboBiljet().getSelectedItem().equals("GoPass 1")) {
							// biljet.getLblPrijs().setText("€ 6");
							if (biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 10");
							} else if ((biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 6");
							}
							if (biljet.getRdbKlasseVerhoging().isSelected()) {
								biljet.getLblPrijs().setText("€ 1");
							}
						}
						if (biljet.getCboBiljet().getSelectedItem().equals("Weekendbiljet")) {
							// biljet.getLblPrijs().setText("€ 12.5");
							if (biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 20");
							} else if ((biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 12.5");
							}
							if (biljet.getRdbKlasseVerhoging().isSelected()) {
								biljet.getLblPrijs().setText("€ 2");
							}
						}
						if (biljet.getCboBiljet().getSelectedItem().equals("Seniorenbiljet")) {
							// biljet.getLblPrijs().setText("€ 5");
							if (biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 8");
							} else if ((biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 5");
							}
							if (biljet.getRdbKlasseVerhoging().isSelected()) {
								biljet.getLblPrijs().setText("€ 1");
							}
						}
						if (biljet.getCboBiljet().getSelectedItem().equals("Biljet Kind")) {
							// biljet.getLblPrijs().setText("€ 5");
							if (biljet.getRdbHeenTerug().isSelected() && (biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 8");
							} else if ((biljet.getRdbEersteKlasse().isSelected()
									|| biljet.getRdbTweedeKlasse().isSelected())) {
								biljet.getLblPrijs().setText("€ 5");
							}
							if (biljet.getRdbKlasseVerhoging().isSelected()) {
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
		try {
			readStationID();
			JSONObject temp = new JSONObject(
					URLCon.readUrl("http://nmbs-team.tk/api/route/" + vanID + "/" + naarID, "GET"));
			System.err.println(temp.toString(4));

			if (temp.has("StatusCode")) {
				int statusCode = temp.getInt("StatusCode");
				if (statusCode == 404) {
					createRoute();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readStationID() {
		try {
			JSONArray json = new JSONArray(URLCon.readUrl("http://nmbs-team.tk/api/station", "GET"));

			boolean goTroughVan = false;
			boolean goTroughNaar = false;

			String temp = "";

			for (int i = 0; i < json.length(); i++) {

				temp = json.getJSONObject(i).getString("Name");

				if (van.toUpperCase().equals(temp.toUpperCase())) {
					vanID = json.getJSONObject(i).getInt("StationID");
					goTroughVan = true;
				}

				if (naar.toUpperCase().equals(temp.toUpperCase())) {
					naarID = json.getJSONObject(i).getInt("StationID");
					goTroughNaar = true;
				}
			}

			if (goTroughVan && goTroughNaar) {
				// createRoute();
			} else {
				System.err.println("Een van de stations werd niet gevonden.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int createRoute() {
		try {
			JSONObject temp = new JSONObject(URLCon.readUrl(
					"http://nmbs-team.tk/api/route/create?DepartureStationID=" + vanID + "&ArrivalStationID=" + naarID,
					"POST"));
			System.out.println(temp.toString(4));
			int statusCode = temp.getInt("StatusCode");
			if (statusCode == 200) {
				routeID = temp.getInt("RouteID");
			}
			return statusCode;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 500;
	}

	public static int createTicket() {
		try {
			JSONObject temp = new JSONObject(
					URLCon.readUrl(
							"http://nmbs-team.tk/api/ticket/create?RouteID=" + routeID + "&Date=" + begindatum
									+ "&TypeTicketID=1" + "&ValidFrom=" + begindatum + "&ValidUntil=" + einddatum,
							"POST"));
			System.out.println(temp.toString(4));
			int statusCode = temp.getInt("StatusCode");
			return statusCode;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 500;
	}
}
