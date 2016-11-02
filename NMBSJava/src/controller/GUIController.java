package controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.GUIFrame;
import api.RouteberekeningAPI;
import panels.*;

public class GUIController {

	private static GUIFrame frame;
	private static NavPanel nav;
	private static StartPanel start;
	private static RouteberekeningPanel route;
	private static TreinopzoekingPanel trein;
	private static StationboardPanel station;

	public static void startInterface() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUIFrame();
					frame.setVisible(true);
					frame.setTitle("NMBSTeam - Start");

					init();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void init() {
		// fixed navbar
		nav = new NavPanel();
		startNavigationListeners();

		// changing panels
		route = new RouteberekeningPanel();
		station = new StationboardPanel();
		trein = new TreinopzoekingPanel();
		start = new StartPanel();

		// startframe
		frame.getContentPane().add(nav, BorderLayout.WEST);
		frame.getContentPane().add(start, BorderLayout.CENTER);

	}

	public static void startNavigationListeners() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				nav.getBtnRouteZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
						frame.getContentPane().add(route);
						frame.setContentPane(frame.getContentPane());
						startRouteListener();
					}
				});

				nav.getBtnTreinZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
						frame.getContentPane().add(trein);
						frame.setContentPane(frame.getContentPane());
					}
				});

				nav.getBtnStationZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
						frame.getContentPane().add(station);
						frame.setContentPane(frame.getContentPane());
					}
				});
			}
		});
	}

	public static void startRouteListener() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				route.getBtnZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RouteberekeningAPI r;
						String van = route.getTxtVan().getText();
						String naar = route.getTxtNaar().getText();
						String datum = route.getDatePicker().getJFormattedTextField().getText();
						String tijd = route.getTimePicker().getText();
						
						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkTime(tijd) && DateTimeConverter.checkDate(datum)) {
							
							r = new RouteberekeningAPI(van, naar, DateTimeConverter.getEpoch(datum, tijd));
							String ss = r.toString();
							if (!ss.contains("null")) {
								route.getLblResult().setText("<html>"
										+ ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
										+ "</html>");
							} else
								route.getLblResult().setText("Dit verzoek kon niet verwerkt worden.");
						} else {
							route.getLblResult().setText("Formulier werd niet correct ingevuld.");
						}

					}
				});
			}
		});
	}
}

// EventQueue.invokeLater(new Runnable() {
// public void run() {
// RouteberekeningAPI r = new RouteberekeningAPI("Geraardsbergen",
// "Brussel-Zuid");
// r.toString();
// }
// });