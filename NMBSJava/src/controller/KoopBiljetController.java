package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import model.Ticket;
import panels.BiljetPanel;

public class KoopBiljetController {
	public static void startListening(BiljetPanel biljet) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				biljet.getBtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String van = biljet.getTxtVan().getText();
						String naar = biljet.getTxtNaar().getText();
						String begindatum = biljet.getDteGaanDatum().getJFormattedTextField().getText();
						String einddatum = biljet.getDteTerugDatum().getJFormattedTextField().getText();
						int typeHeenTerugSelectedMnem = biljet.getGrpHeenTerug().getSelection().getMnemonic();
						String soortBiljet = biljet.getCboBiljet().getSelectedItem().toString();
						int typeKlasseSelectedMnem = biljet.getGrpKlasseTicket().getSelection().getMnemonic();

						if (!van.equals("") && !naar.equals("") && DateTimeConverter.checkDate(begindatum) 
								&& DateTimeConverter.checkDate(einddatum) && typeHeenTerugSelectedMnem != 0 
								&& typeKlasseSelectedMnem != 0 && !soortBiljet.equals(null)) {
							System.out.println("Juist biljet");
							// Ticket s = new Ticket(routeID, date, price, validFrom, validUntil, comfortClass, ticketID);
							// van, naar, prijs, begindatum, einddatum, typeHeenTerugSelectedMnem, soortBiljet, typeKlasseSelectedMnem
						} else {
							System.err.println("Fout biljet");
						}

					}
				});
			}
		});
	}
}
