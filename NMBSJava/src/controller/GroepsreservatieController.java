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

import gui.GUIDateFormat;
//import model.Groepsreservatie;
import panels.GroepsReservatiePanel;

public class GroepsreservatieController {

	private static String van;
	private static String naar;
	private static String doordatum;
	private static String terugdatum;
	private static String naam;
	private static String verantwoordelijke;
	private static String trein;
	private static String aantalReizigers;
	private static String doortime;
	private static String terugtime;
	
	
	public static void startListening(GroepsReservatiePanel reservatie) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				reservatie.getDoorTerug().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (reservatie.getDoorTerug().isSelected()) {
							reservatie.getDteTerugDatum().getJFormattedTextField().setText(GUIDateFormat.getDate());
							reservatie.getDteTerugDatum().setEnabled(true);
							reservatie.getTimeTerug().setEnabled(true);
							reservatie.getTerugpanel().setEnabled(true);
						}
					}
				});
				reservatie.getBtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						van = (String) reservatie.getTxtVan().getSelectedItem();
						naar = (String) reservatie.getTxtVan().getSelectedItem();
						doordatum = reservatie.getDteGaanDatum().getJFormattedTextField().getText();
						terugdatum = reservatie.getDteTerugDatum().getJFormattedTextField().getText();
						naam = reservatie.getTxtGroepsnaam().getText();
						verantwoordelijke = reservatie.getTxtNaamVerantwoordelijke().getText();
						trein = reservatie.getCboTrein().getSelectedItem().toString();
						aantalReizigers =(String) reservatie.getPersonen().getValue();
						doortime= reservatie.getTimeGaan().getText();
						terugtime= reservatie.getTimeTerug().getText();

						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkDate(doordatum)
								&& DateTimeConverter.checkDate(terugdatum) && !naam.equals("")
								&& !verantwoordelijke.equals("") && !trein.equals(null)) {
							// implementatie
						}
					}

				});
			}
		});

	}

}
