package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import api.RouteberekeningAPI;
import api.TimeSelector;
import dao.ReservationDAO;
import gui.GUIDateFormat;
import gui.LangageHandler;
import gui.Popup;
import model.Reservation;
import panels.GroepsReservatiePanel;

public class GroepsreservatieController {

	private static String van;
	private static String naar;
	private static String datum;
	private static String trein;
	private static int aantalReizigers;
	private static String time;
	private static boolean vanOke = false, naarOke = false;
	private static double prijs;
	private static UUID route;
	public static void startListening(GroepsReservatiePanel reservatie) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				reservatie.getAutVan().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						vanOke = true;
						if (vanOke && naarOke) {
							zoekTreinen(reservatie);
						}
					}

				});

				reservatie.getAutNaar().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						naarOke = true;
						if (vanOke && naarOke) {
							zoekTreinen(reservatie);
						}
					}

				});

				reservatie.getBtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						van = (String) reservatie.getAutVan().getSelectedItem();
						naar = (String) reservatie.getAutNaar().getSelectedItem();
						datum = reservatie.getDteDate().getJFormattedTextField().getText();
						trein = reservatie.getCboTrein().getSelectedItem().toString();
						aantalReizigers = (int) reservatie.getAantPersonen().getValue();
						time = reservatie.getTmeTime().getText();

						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkDate(datum)
								 && !trein.equals(null)) {
							ReservationDAO res = new ReservationDAO();
							Reservation r = new Reservation(aantalReizigers, trein, prijs,datum, route);
							res.insert(r);
							System.out.println(LangageHandler.chooseLangage("resSucces"));
						}
					}

				});

			}

		});

	}

	private static void zoekTreinen(GroepsReservatiePanel reservatie) {
		van = (String) reservatie.getAutVan().getSelectedItem();
		naar = (String) reservatie.getAutNaar().getSelectedItem();
		datum = reservatie.getDteDate().getJFormattedTextField().getText();
		time = reservatie.getTmeTime().getText();
		RouteberekeningAPI r = new RouteberekeningAPI(van, naar, datum, time, TimeSelector.VERTREK);
		for (int i = 0; i < r.getConnections().size(); i++) {
			reservatie.getCboTrein().addItem(r.getConnections().get(i).getDeparture().getVehicle());
		}
	}
	
}