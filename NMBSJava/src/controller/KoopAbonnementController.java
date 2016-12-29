package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.CustomerDAO;
import dao.RouteDAO;
import dao.StationDAO;
import dao.SubscriptionDAO;
import gui.GUIDateFormat;
import model.Address;
import model.Customer;
import model.RailCard;
import model.Route;
import model.Station;
import model.Subscription;
import panels.NieuwAbonnementPanel;

public class KoopAbonnementController {
	private static String station1;
	private static String station2;
	private static String vanID;
	private static String naarID;
	private static String routeID;
	private static String railcardID;

	
	public static void startListening(NieuwAbonnementPanel abonnement) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				abonnement.getBtnVerzenden().setEnabled(false);
				try {
					String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
					Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
					c.add(Calendar.MONTH, 1);
					startDatum = GUIDateFormat.objectToString(c);
					abonnement.getLblBerekendeVervaldatum().setText(startDatum);
				} catch (ParseException pe) {
					pe.printStackTrace();
				}

				abonnement.getBtnCustomerID().addActionListener(new ActionListener() {
					// automatisch customer gegevens invullen adhv customerID
					@Override
					public void actionPerformed(ActionEvent e) {
						Boolean check = false;
						abonnement.getLblFoutmelding().setText("");
						String customerIDString = abonnement.getTxtCustomerID().getText();
						CustomerDAO daoCustomer = new CustomerDAO();
						ArrayList<Customer>list = daoCustomer.selectAll();
						for (int i = 0; i < list.size(); i++) {
							if(customerIDString.equals(list.get(i).getCustomerID().toString()))
							{
								abonnement.setTxtVoornaam(list.get(i).getFirstName());
								abonnement.setTxtNaam(list.get(i).getLastName());
								abonnement.setTxtEmail(list.get(i).getEmail());
								abonnement.setDteGeboorteDatum(list.get(i).getBirthDate());
								abonnement.setTxtGemeente(list.get(i).getAddress().getCity());
								abonnement.setTxtStraat(list.get(i).getAddress().getStreet());
								abonnement.setTxtNummer(Integer.toString(list.get(i).getAddress().getNumber()));
								abonnement.setTxtPostcode(Integer.toString(list.get(i).getAddress().getZipCode()));
								check = true;
							}
						}
						if(check == false)
						{
							abonnement.getLblFoutmelding().setText("Klantennummer niet gevonden.");
						}
					}
				});

				abonnement.getTxtNaam().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtVoornaam().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtEmail().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtStraat().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtNummer().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtPostcode().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtGemeente().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtStation1().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getTxtStation2().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getDteGeboorteDatum().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getDteStartDatum().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
							Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
							c.add(Calendar.MONTH, 1);
							startDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblBerekendeVervaldatum().setText(startDatum);
						} catch (ParseException pe) {
							pe.printStackTrace();
						}
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getCbxDuur().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblBerekendeVervaldatum().setText(getVervalDatum(abonnement));

					}
				});

				abonnement.getRdbEersteKlasse().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getRdbTweedeKlasse().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getCbxDiscount().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getCbxTreinkaart().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
					}
				});

				abonnement.getBtnValideer().addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						if (correctFormulier(abonnement)) {
							// prijsberekening
							abonnement.getLblPrint().setText("100");
							abonnement.getBtnVerzenden().setEnabled(true);
						} else {
							abonnement.getLblPrint().setText("0");
						}
					}
				});

				abonnement.getBtnVerzenden().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (correctFormulier(abonnement)) {
							if (!customerCheck(abonnement)) 
							{
								createCustomer(abonnement);
							}
							readRouteID();
							createAbonnement(abonnement);			
						}
					}
				});
			}
		});
	}

	public static Boolean customerCheck(NieuwAbonnementPanel abonnement) {
		CustomerDAO daoCustomer = new CustomerDAO();
		ArrayList<Customer>list = daoCustomer.selectAll();
				
		RailCard railcard = new RailCard();
	
		//Controle op id	
			for (int i = 0; i < list.size(); i++) {
				
				if (list.get(i).getCustomerID().equals(abonnement.getTxtCustomerID().getText().toString())){		
					
					//Customer bestaat alreeds, dus railcardid zoeken, nodig voor abonnement te maken
					railcard = list.get(i).getRailCard();
					railcardID = railcard.getRailCardID().toString();
					
					return true; 
				}
			}
		//als bestaande customer niet gezocht is op id, dan wordt er controle uitgevoerd op emailadres
			String email = abonnement.getTxtEmail().toString();
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getEmail().toString().equals(email)){
					
					//Customer bestaat alreeds, dus railcardid zoeken, nodig voor abonnement te maken
					railcard = list.get(i).getRailCard();
					railcardID = railcard.getRailCardID().toString();
					
					return true; 
				}
			}
		
		return false; // customer bestaat niet, -> createCustomer()
	} 

	public static Boolean correctFormulier(NieuwAbonnementPanel abonnement) {
		String naam = abonnement.getTxtNaam().getText();
		String voornaam = abonnement.getTxtVoornaam().getText();
		String geboorteDatum = abonnement.getDteGeboorteDatum().getJFormattedTextField().getText();
		String email = abonnement.getTxtEmail().getText();
		String straat = abonnement.getTxtStraat().getText();
		String nummer = abonnement.getTxtNummer().getText();
		String postcode = abonnement.getTxtPostcode().getText();
		String gemeente = abonnement.getTxtGemeente().getText();
		String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
		String treinkaart = abonnement.getCbxTreinkaart().getSelectedItem().toString();
		String korting = abonnement.getCbxDiscount().getSelectedItem().toString();
		station1 = (String) abonnement.getTxtStation1().getSelectedItem();
		station2 = (String) abonnement.getTxtStation2().getSelectedItem();

		if (!naam.equals("") && !voornaam.equals("") && DateTimeConverter.checkDate(geboorteDatum) && !email.equals("")
				 && !straat.equals("") && !nummer.equals("") && !postcode.equals("")
				&& !gemeente.equals("") && !treinkaart.equals(null) && DateTimeConverter.checkDate(startDatum)
				&& !korting.equals(null) && !station1.equals("") && !station2.equals("")) {
			if (!email.contains("@")) {
				abonnement.getLblFoutmelding().setText("Geen geldig e-mailadres.");
				return false;
			}
			abonnement.getLblFoutmelding().setText("Het formulier is correct");
			return true;
		} else {
			abonnement.getLblFoutmelding().setText("Het formulier is niet volledig.");
			return false;
		}
	}

	public static String getVervalDatum(NieuwAbonnementPanel abonnement) {
		String vervalDatum = "";
		try {
			String duur = abonnement.getCbxDuur().getSelectedItem().toString();
			String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();

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

			startDatum = GUIDateFormat.objectToString(c);
			vervalDatum = startDatum;

		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return vervalDatum;

	}

	public static void readRouteID() {
		readStationID();
		RouteDAO daoRoute = new RouteDAO();
		ArrayList<Route> list = daoRoute.selectAll();
		boolean goTroughVan = false;
		boolean goTroughNaar = false;
		int positie = 0;
		for (int i = 0; i < list.size(); i++) {
			
		System.out.println("NaarID "+naarID);
			if (naarID.toString().equals(list.get(i).getArrivalStationID().toString())) {
				goTroughNaar = true;
				positie =i;
			}
			if(vanID.toString().equals(list.get(i).getDepartureStationID().toString()))
			{
				goTroughVan = true;
				positie = i;
			}
		}
		
		if(goTroughVan == false || goTroughNaar==false)
		{
			createRoute();
			System.out.println("oi");
		}else{
			routeID = list.get(positie).getRouteID().toString();
			System.out.println(routeID + "1");
		
		}
	}

	public static void readStationID() {

		StationDAO daoStation = new StationDAO();
		ArrayList<Station> list = daoStation.selectAll();
		
		boolean goTroughVan = false;
		boolean goTroughNaar = false;
		
		for (int i = 0; i < list.size(); i++) {

			if (station1.equals(list.get(i).getStationName())) {
				vanID = list.get(i).getStationID().toString();
				goTroughVan = true;
			}

			if (station2.equals(list.get(i).getStationName())) {
				naarID = list.get(i).getStationID().toString();
				goTroughNaar = true;
			}
		}
		
		if(goTroughNaar==false || goTroughVan==false)
		{
			System.err.println("Een van de stations werd niet gevonden.");
		}
		
	}

	public static void createRoute() {

		Route r = new Route();
		r.setArrivalStationID(UUID.fromString(naarID));
		r.setDepartureStationID(UUID.fromString(vanID));
		RouteDAO daoRoute = new RouteDAO();
		daoRoute.insert(r);
		routeID = r.getRouteID().toString();
		System.out.println(routeID + "2");

	}

	public static void createAbonnement(NieuwAbonnementPanel abonnement) {
		String vervalDatum = getVervalDatum(abonnement);
		//String vervalDatum = abonnement.getLblVervaldatum().getText();
		String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
	/*	DateFormat format = new SimpleDateFormat("dd, mm, yyyy");
		Date validFrom = null;
		Date validUntil = null;
		try {
			validFrom = format.parse(startDatum);
			validUntil = format.parse(vervalDatum);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		System.out.println(UUID.fromString(railcardID));
		System.out.println(routeID + "3");
		
		System.out.println(startDatum);

		//Subscription sub = new Subscription(UUID.fromString(railcardID), UUID.fromString(routeID), UUID.fromString(abonnement.getCbxDiscount().getSelectedItem().toString()),startDatum, vervalDatum);
		//SubscriptionDAO daoSubscription = new SubscriptionDAO();
		//daoSubscription.insert(sub);

	}

	public static void createCustomer(NieuwAbonnementPanel abonnement) {
		int nummer =  Integer.parseInt(abonnement.getTxtNummer().getText());
		int postcode =  Integer.parseInt(abonnement.getTxtPostcode().getText());
		String geboorteDatum = abonnement.getDteGeboorteDatum().getJFormattedTextField().getText();
		

		Address address = new Address(abonnement.getTxtStraat().getText(), nummer , abonnement.getTxtGemeente().getText(), postcode, "");
		
		RailCard railcard = new RailCard();
		railcardID = railcard.getRailCardID().toString();
		Customer customer = new Customer(abonnement.getTxtVoornaam().getText(), abonnement.getTxtNaam().getText(), geboorteDatum,  abonnement.getTxtEmail().getText(), address, railcard);
		
		CustomerDAO daoCustomer = new CustomerDAO();
		daoCustomer.insert(customer);
		
	}
}