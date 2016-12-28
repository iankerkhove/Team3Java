package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.TypePassDAO;
import dao.TypeTicketDAO;
import model.TypePass;
import model.TypeTicket;
import panels.PasPrijzenAanPanel;

public class PasPrijzenAanController {

	private static int keuze;

	public static void startListening(PasPrijzenAanPanel prijzen) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prijzen.getCboAanpasKeuze().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							keuze = prijzen.getCboAanpasKeuze().getSelectedIndex();
							prijzen.initAanpassen(keuze);
							prijzen.getRootPane().setContentPane(prijzen.getRootPane().getContentPane());
						}
					});

					prijzen.getBtnPasAan().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							/*
							 * cboAanpasKeuze.addItem("");
							 * cboAanpasKeuze.addItem("Nieuw TicketType maken");
							 * cboAanpasKeuze.addItem("TicketType aanpassen");
							 * cboAanpasKeuze.addItem("Nieuw PassType maken");
							 * cboAanpasKeuze.addItem("PassType aanpassen");
							 */

							switch (keuze) {
							case 0:
								break;
							case 1: {
								String name = prijzen.getTxtName().getText();
								Double price = Double.parseDouble(prijzen.getTxtPrice().getText());
								int comfortClass = prijzen.getGrpKlasse().getSelection().getMnemonic();
								if (!name.equals("") && price >= 0) {
									TypeTicketDAO handler = new TypeTicketDAO();
									handler.insert(new TypeTicket(name, price, comfortClass));
								} else {
									System.out.println("invalid");
								}
							}
								break;
							case 2: {
								String name = (String) prijzen.getAutTicketType().getSelectedItem();
								Double price = Double.parseDouble(prijzen.getTxtPrice().getText());
								Double oldPrice = Double.parseDouble(prijzen.getTxtOldPrice().getText());

								if (price != oldPrice) {
									TypeTicketDAO handler = new TypeTicketDAO();
									TypeTicket type = handler.selectOneOnName(name);
									type.setName(name);
									type.setPrice(price);
									handler.update(type);
								} else {
									System.out.println("prijs onveranderd");
								}
							}
								break;
							case 3: {
								String name = prijzen.getTxtName().getText();
								Double price = Double.parseDouble(prijzen.getTxtPrice().getText());

								if (!name.equals("") && price >= 0) {
									TypePassDAO handler = new TypePassDAO();
									handler.insert(new TypePass(name, price));
								} else {
									System.out.println("invalid");
								}
							}
								break;
							case 4: {
								String name = (String) prijzen.getAutPassType().getSelectedItem();
								Double price = Double.parseDouble(prijzen.getTxtPrice().getText());
								Double oldPrice = Double.parseDouble(prijzen.getTxtOldPrice().getText());

								if (price != oldPrice) {
									TypePassDAO handler = new TypePassDAO();
									TypePass type = handler.selectOneOnName(name);
									type.setName(name);
									type.setPrice(price);
									handler.update(type);
								} else {
									System.out.println("prijs onveranderd");
								}
							}
								break;
							default:
								break;
							}
						}
					});

					prijzen.getAutPassType().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (keuze == 4) {
								String naam = (String) prijzen.getAutPassType().getSelectedItem();

								System.out.println("'" + naam + "'");
								TypePassDAO handler = new TypePassDAO();
								TypePass type = handler.selectOneOnName(naam);

								prijzen.getTxtOldPrice().setText(Double.toString(type.getPrice()));
							} else {
								System.out.println("something went wrong");
							}
						}
					});

					prijzen.getAutTicketType().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (keuze == 2) {

								String naam = (String) prijzen.getAutTicketType().getSelectedItem();

								TypeTicketDAO handler = new TypeTicketDAO();
								TypeTicket type = handler.selectOneOnName(naam);

								prijzen.getTxtOldPrice().setText(Double.toString(type.getPrice()));
							} else {
								System.out.println("something went wrong");
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