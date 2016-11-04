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

	public static void start() {
		// Make frame after performing all other tasks
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUIFrame();
					frame.setVisible(true);
					frame.setTitle("NMBSTeam - Start");

					// initialize basic components on frame
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

	}

	public static void startListeningOnNav() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				nav.getBtnRouteZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						route = new RouteberekeningPanel();
						
						frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
						frame.getContentPane().add(route);
						frame.setContentPane(frame.getContentPane());
						
						RouteberekeningController.startListening(route);
					}
				});

				nav.getBtnTreinZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						trein = new TreinopzoekingPanel();
						
						frame.getContentPane().remove(frame.getContentPane().getComponentCount() - 1);
						frame.getContentPane().add(trein);
						frame.setContentPane(frame.getContentPane());
						
						TreinopzoekingController.startListening(trein);
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
}