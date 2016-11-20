package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;

import panels.VerlorenVoorwerpMaakPanel;
import controller.URLCon;

public class VerlorenVoorwerpMaakController {

	
	
	public static void startListening(VerlorenVoorwerpMaakPanel verlorenVoorwerpMaak) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				verlorenVoorwerpMaak.getBtnMaak().addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent e) {
						int station = verlorenVoorwerpMaak.getTxtStation().getSelectedIndex()+1;
						String treinNummer = verlorenVoorwerpMaak.getTxtTrein().getText();
						String omschrijving = verlorenVoorwerpMaak.getTxtOmschrijving().getText();
						String datum = verlorenVoorwerpMaak.getDatePicker().getJFormattedTextField().getText();
						
						String omschrijving2 = omschrijving.replaceAll(" ", "_");
						
						
						URLCon url = new URLCon();
						try {
							url.readUrl("http://nmbs-team.tk/api/lostObject/create?StationID=" +station +"&Description=" + omschrijving2 + "&Date=" + datum +"&TrainID=" + treinNummer, "POST");
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				});
			}
		});

	}
}
