package controller;

import gui.GUIFrame;

public class GUIController {

	public static void startGUI() {
		try {
			GUIFrame frame = new GUIFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
