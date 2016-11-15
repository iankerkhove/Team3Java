package controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.GUIFrame;
import panels.*;

public class GUIController {

	// frame
	private static GUIFrame frame;
	// navbar
	private static NavPanel nav;
	// all panels
	private static StartPanel start;
	private static RouteberekeningPanel route;
	private static TreinopzoekingPanel trein;
	private static StationboardPanel station;
	private static BiljetPanel biljet;
	private static NieuwAbonnementPanel abonnement;
	private static VerlengAbonnementPanel verlengAbonnement;

	public static void start() {
		// Make frame after performing all other tasks
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUIFrame();
					frame.setVisible(true);
					frame.setTitle("NMBSTeam - Login");
					login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void login() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginPanel l = new LoginPanel();
				frame.getContentPane().add(l, BorderLayout.CENTER);
				frame.setContentPane(frame.getContentPane());
				LoginController.login(l);
			}
		});
	}

	public static void showApp() {
		// Make frame after performing all other tasks
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.getContentPane().removeAll();
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
		// startpanel
		start = new StartPanel();
		// start listening for actions on navbar
		startListeningOnNav();
		// create startframe
		frame.getContentPane().add(nav, BorderLayout.WEST);
		frame.getContentPane().add(start, BorderLayout.CENTER);
		frame.setContentPane(frame.getContentPane());
	}

	public static void startListeningOnNav() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				nav.getBtnRouteZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startRouteberekening();
					}
				});

				nav.getBtnTreinZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startTreinopzoeking();
					}
				});

				nav.getBtnStationZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startStationsbord();
					}
				});

				nav.getBtnBiljetKoop().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startKoopBiljet();
					}
				});
				nav.getBtnAbonnementKoop().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startKoopAbonnement();
					}
				});
				nav.getBtnAbonnementVerlengen().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						startVerlengAbonnement();
					}
				});
			}
		});
	}

	private static void startRouteberekening() {
		route = new RouteberekeningPanel();
		frame.setTitle("NMBSTeam - Bereken route");
		frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
		frame.getContentPane().add(route);
		frame.setContentPane(frame.getContentPane());
		RouteberekeningController.startListening(route);
	}

	private static void startTreinopzoeking() {
		trein = new TreinopzoekingPanel();
		frame.setTitle("NMBSTeam - Zoek trein");
		frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
		frame.getContentPane().add(trein);
		frame.setContentPane(frame.getContentPane());
		TreinopzoekingController.startListening(trein);
	}

	private static void startStationsbord() {
		station = new StationboardPanel();
		frame.setTitle("NMBSTeam - Bekijk het stationsbord");
		frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
		frame.getContentPane().add(station);
		frame.setContentPane(frame.getContentPane());
		StationsbordController.startListening(station);
	}

	private static void startKoopBiljet() {
		biljet = new BiljetPanel();
		frame.setTitle("NMBSTeam - Koop Biljet");
		frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
		frame.getContentPane().add(biljet);
		frame.setContentPane(frame.getContentPane());
		KoopBiljetController.startListening(biljet);
	}
	
	private static void startKoopAbonnement() {
		abonnement = new NieuwAbonnementPanel();
		frame.setTitle("NMBSTeam - Koop Abonnement");
		frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
		frame.getContentPane().add(abonnement);
		frame.setContentPane(frame.getContentPane());
		KoopAbonnementController.startListening(abonnement);
	}
	
	private static void startVerlengAbonnement() {
		verlengAbonnement = new VerlengAbonnementPanel();
		frame.setTitle("NMBSTeam - Koop Abonnement");
		frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
		frame.getContentPane().add(verlengAbonnement);
		frame.setContentPane(frame.getContentPane());
		VerlengAbonnementController.startListening(verlengAbonnement);
	}

	public static GUIFrame getFrame() {
		return frame;
	}
}