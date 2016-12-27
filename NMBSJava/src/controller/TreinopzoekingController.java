package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import api.TrainAPI;
import panels.TreinopzoekingPanel;;

public class TreinopzoekingController {
	public static void startListening(TreinopzoekingPanel trein) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				trein.getBtnZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TrainAPI t;
						String tId = trein.getTxtTrein().getText();
						if (!tId.equals("")) {
							t = new TrainAPI(tId);
							String ss = t.toString();
							if (!ss.contains("null")) {
								trein.getLblResult().setText("<html>" + ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
							} else
								trein.getLblResult().setText("Dit verzoek kon niet verwerkt worden.");
						} else {
							trein.getLblResult().setText("Formulier werd niet correct ingevuld.");
						}

					}
				});
			}
		});
	}
}