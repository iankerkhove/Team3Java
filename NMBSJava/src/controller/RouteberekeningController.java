package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import api.RouteberekeningAPI;
import panels.RouteberekeningPanel;

public class RouteberekeningController {
	public static void startListening(RouteberekeningPanel route) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				route.getBtnZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RouteberekeningAPI r;
						String van = (String) route.getTxtVan().getSelectedItem();
						String naar = (String) route.getTxtNaar().getSelectedItem();
						String datum = route.getDatePicker().getJFormattedTextField().getText();
						String tijd = route.getTimePicker().getText();
						
						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkTime(tijd) && DateTimeConverter.checkDate(datum)) {
							
							int tempInt = Integer.parseInt(DateTimeConverter.getEpoch(datum, tijd));
							tempInt += 300;
							String tempString = "";
							tempString += tempInt;
							
							r = new RouteberekeningAPI(van, naar, tempString);
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
