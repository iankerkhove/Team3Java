package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panels.PasPrijzenAanPanel;
import dao.TypeTicketDAO;
import model.TypeTicket;

public class PasPrijzenAanController {
	public static void startListening(PasPrijzenAanPanel prijzen) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prijzen.getBtnWijzig().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							boolean isNieuw = true;

							String typeTicket = prijzen.getTxtNewTypeTicket().getText();
							if (typeTicket.equals("")) {
								isNieuw = false;
								typeTicket = (String) prijzen.getTxtTypeTicket().getSelectedItem();
							}
							int mnem = prijzen.getGrpKlasses().getSelection().getMnemonic();
							Double nieuwePrijs = Double.parseDouble(prijzen.getTxtNieuwePrijs().getText());

							if (!(nieuwePrijs < 0)) {
								if (isNieuw) {
									TypeTicketDAO handler = new TypeTicketDAO();
									handler.insert(new TypeTicket(typeTicket, nieuwePrijs, mnem));
								}
								else {
									TypeTicketDAO handler = new TypeTicketDAO();
									TypeTicket tt = handler.selectOneOnName(typeTicket, mnem);
									handler.updatePrice(tt.getTypeTicketID().toString(), tt.getName(), nieuwePrijs);
								}
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}