package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Prijsberekening.TypeKeuze;
import dao.CustomerDAO;
import dao.DiscountDAO;
import dao.RouteDAO;
import dao.StationDAO;
import dao.SubscriptionDAO;
import dao.TypeTicketDAO;
import gui.DiscountAutoCompletor;
import gui.GUIDateFormat;
import model.Customer;
import model.Discount;
import model.Route;
import model.Station;
import model.Subscription;
import model.TypeTicket;
import panels.VerlengAbonnementPanel;

public class VerlengAbonnementController {
	private static String customerID = "";
	private static String railCardID = "";

	public static void startListening(VerlengAbonnementPanel abonnement) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				DiscountDAO discountHandler = new DiscountDAO();
				ArrayList<Discount> discountList = discountHandler.selectAll();

				HashMap<String, UUID> kortingMap = new HashMap<String, UUID>();
				for (Discount d : discountList) {
					kortingMap.put(d.getName(), d.getDiscountID());
				}
				abonnement.getBtnMeerInfo().setEnabled(false);
				abonnement.getBtnVerzenden().setEnabled(false);
				try {
					String startDatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
					Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
					c.add(Calendar.MONTH, 1);
					startDatum = GUIDateFormat.objectToString(c);
					abonnement.getLblVervaldatumResult().setText(startDatum);
				} catch (ParseException pe) {
					pe.printStackTrace();
				}

				abonnement.getBtnZoek().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnMeerInfo().setEnabled(true);

						String stringRailCardID = abonnement.getTxtAbonnementsNummer().getText();

						CustomerDAO daoCustomer = new CustomerDAO();
						ArrayList<Customer> list = daoCustomer.selectAll();

						for (int i = 0; i < list.size(); i++) {
							if (stringRailCardID.equals(list.get(i).getRailCard().getRailCardID().toString())) {
								/*customerID = list.get(i).getCustomerID().toString();
								CustomerDAO cdao = new CustomerDAO();
								Customer c = cdao.selectOne(customerID);
								railCardID = c.getRailCardID().toString();*/
								abonnement.getLblKLantenNummerResult().setText(list.get(i).getCustomerID().toString());
							}
						}

						abonnement.getBtnMeerInfo().setEnabled(true);
					}
				});

				abonnement.getBtnMeerInfo().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!abonnement.getLblKLantenNummerResult().getText().equals("")) {

							CustomerDAO daoCustomer = new CustomerDAO();
							ArrayList<Customer> list = daoCustomer.selectAll();

							for (int i = 0; i < list.size(); i++) {
								if (customerID == list.get(i).getCustomerID().toString()) {
									JFrame frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "Naam: " + list.get(i).getLastName() + "\n"
											+ "Voornaam: " + list.get(i).getFirstName() + "\n" + "Geboortedatum: "
											+ list.get(i).getBirthDate() + "\n" + "E-mail: " + list.get(i).getEmail()
											+ "\n" + "Straat + nr: " + list.get(i).getAddress().getStreet() + " "
											+ list.get(i).getAddress().getNumber() + "\n" + "Postcode: "
											+ list.get(i).getAddress().getZipCode() + "\n" + "Gemeente: "
											+ list.get(i).getAddress().getCity() + "\n", "Meer info",
											JOptionPane.PLAIN_MESSAGE);
								}
							}
						}
					}
				});

				abonnement.getDteStartdatum().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String startDatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
							Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
							c.add(Calendar.MONTH, 1);
							String vervalDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblVervaldatumResult().setText(vervalDatum);
						} catch (ParseException pe) {
							pe.printStackTrace();
						}
					}
				});

				abonnement.getCbxDuur().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String duur = abonnement.getCbxDuur().getSelectedItem().toString();
							String startDatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
							Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
							switch (duur) {
							case "1 maand":
								c.add(Calendar.MONTH, 1);
								break;
							case "3 maanden":
								c.add(Calendar.MONTH, 3);
								break;
							case "12 maanden":
								c.add(Calendar.MONTH, 12);
								break;
							}
							String vervalDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblVervaldatumResult().setText(vervalDatum);
						} catch (ParseException pe) {
							pe.printStackTrace();
						}
					}
				});

				abonnement.getBtnValideer().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String abonnementsNummer = abonnement.getTxtAbonnementsNummer().getText();
						String treinkaart = (String) abonnement.getCbxTreinkaart().getSelectedItem();
						String korting = (String) abonnement.getCbxDiscount().getSelectedItem();
						String duur = (String) abonnement.getCbxDuur().getSelectedItem();
						String startdatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
						String station1 = (String) abonnement.getTxtStation1().getSelectedItem();
						String station2 = (String) abonnement.getTxtStation2().getSelectedItem();

						if (!abonnementsNummer.equals("") && !duur.equals(null)
								&& DateTimeConverter.checkDate(startdatum) && !station1.equals("")
								&& !station2.equals("") && customerID.equals("")) {

							StationDAO handler = new StationDAO();
							Station s1 = handler.selectOneOnName(station1);
							Station s2 = handler.selectOneOnName(station2);
							int klasse = abonnement.getGrpKlasses().getSelection().getMnemonic();

							TypeTicketDAO handler2 = new TypeTicketDAO();
							ArrayList<TypeTicket> list = handler2.selectAll();
							String ticketTypeID = "";
							for (int i = 0; i < list.size(); i++) {
								if (list.get(i).getName().contains("Standaardticket")
										&& list.get(i).getComfortClass() == klasse) {
									ticketTypeID = list.get(i).getTypeTicketID().toString();
								}
							}
							if (!ticketTypeID.equals("")) {
								abonnement.getLblBedrag().setText(Double.toString(
										Prijsberekening.berekenPrijs(s1, s2, TypeKeuze.TICKET, ticketTypeID)));
								abonnement.getLblFoutmelding().setText("Het formulier is correct");
								abonnement.getBtnVerzenden().setEnabled(true);
							} else {
								abonnement.getLblFoutmelding().setText("Niet gevonden");
							}
						} else {
							abonnement.getLblFoutmelding().setText("Het formulier is niet volledig");
						}
					}
				});

				abonnement.getBtnVerzenden().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String abonnementsNummer = abonnement.getTxtAbonnementsNummer().getText();
						String treinkaart = (String) abonnement.getCbxTreinkaart().getSelectedItem();
						String korting = (String) abonnement.getCbxDiscount().getSelectedItem();
						String duur = (String) abonnement.getCbxDuur().getSelectedItem();
						String startdatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
						String station1 = (String) abonnement.getTxtStation1().getSelectedItem();
						String station2 = (String) abonnement.getTxtStation2().getSelectedItem();
						String eindDatum = abonnement.getLblVervaldatumResult().getText();
						if (!abonnementsNummer.equals("") && !duur.equals(null)
								&& DateTimeConverter.checkDate(startdatum) && !station1.equals("")
								&& !station2.equals("") && customerID.equals("")) {

							StationDAO handler = new StationDAO();
							Station s1 = handler.selectOneOnName(station1);
							Station s2 = handler.selectOneOnName(station2);
							int klasse = abonnement.getGrpKlasses().getSelection().getMnemonic();

							TypeTicketDAO handler2 = new TypeTicketDAO();
							ArrayList<TypeTicket> list = handler2.selectAll();
							String ticketTypeID = "";
							for (int i = 0; i < list.size(); i++) {
								if (list.get(i).getName().contains("Standaardticket")
										&& list.get(i).getComfortClass() == klasse) {
									ticketTypeID = list.get(i).getTypeTicketID().toString();
								}
							}
							
							if (!ticketTypeID.equals("")) {
								RouteDAO r = new RouteDAO();
								Route route = r.selectOneOnRoute(station1, station2);
								UUID routeID;
								if (route != null) {
									routeID = route.getRouteID();
								} else {
									System.out.println("Route bestaat niet");
									route = new Route(s1.getStationID(), s2.getStationID());
									routeID = route.getRouteID();
									r.insert(route);
								}
								DiscountDAO handler4 = new DiscountDAO();
								Discount d = handler4.selectOne(kortingMap.get(korting).toString());
								abonnement.getLblBedrag().setText(Double.toString(
										Prijsberekening.berekenPrijs(s1, s2, TypeKeuze.TICKET, ticketTypeID)));
								abonnement.getLblFoutmelding().setText("Het formulier is correct");
								abonnement.getBtnVerzenden().setEnabled(true);
								

								SubscriptionDAO handler3 = new SubscriptionDAO();
								Subscription s = handler3.selectOne(UUID.fromString(abonnementsNummer).toString());
								//s.setRailCardID(UUID.fromString(railCardID));
								s.setRouteID(routeID);
								s.setDiscountID(d.getDiscountID());
								s.setValidFrom(startdatum);
								s.setValidUntil(eindDatum);
								s.update();
								handler3.insert(s);
							} else {
								abonnement.getLblFoutmelding().setText("Niet gevonden");
							}
						} else {
							abonnement.getLblFoutmelding().setText("Het formulier is niet volledig");
						}

					}
				});

				abonnement.getTxtAbonnementsNummer().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblKLantenNummerResult().setText("");
						abonnement.getBtnMeerInfo().setEnabled(false);
						customerID = "";
					}

					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblKLantenNummerResult().setText("");
						abonnement.getBtnMeerInfo().setEnabled(false);
						customerID = "";
					}

					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblKLantenNummerResult().setText("");
						abonnement.getBtnMeerInfo().setEnabled(false);
						customerID = "";
					}
				});

			}
		});
	}

}
