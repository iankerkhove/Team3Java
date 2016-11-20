package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import api.StationboardAPI;
import panels.StationboardPanel;

public class StationsbordController {
	public static void startListening(StationboardPanel station){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				station.getBtnZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StationboardAPI s;
						String stationName = (String) station.getTxtStation().getSelectedItem();
						
						if (!stationName.equals("")) {
							
							s = new StationboardAPI(stationName);
							String ss = s.toString();
							if (!ss.contains("null")) {
								station.getLblResult().setText("<html>"
										+ ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
										+ "</html>");
							} else
								station.getLblResult().setText("Dit verzoek kon niet verwerkt worden.");
						} else {
							station.getLblResult().setText("Formulier werd niet correct ingevuld.");
						}

					}
				});
			}
		});
	}
}
