package controller;

import java.awt.EventQueue;

import gui.GUIFrame;
import gui.RouteberekeningPanel;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame frame = new GUIFrame();
					frame.setVisible(true);
					RouteberekeningPanel pane = new RouteberekeningPanel();
					pane.setVisible(true);
					frame.getContentPane().add(pane);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}