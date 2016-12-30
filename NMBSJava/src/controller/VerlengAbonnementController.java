package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.CustomerDAO;
import gui.GUIDateFormat;
import gui.LangageHandler;
import model.Customer;
import panels.VerlengAbonnementPanel;

public class VerlengAbonnementController {
private static int customerID=-1;
	
	public static void startListening(VerlengAbonnementPanel abonnement){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				
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
						ArrayList<Customer>list = daoCustomer.selectAll();
						
						for (int i = 0; i < list.size(); i++) {
							if(stringRailCardID.equals(list.get(i).getRailCard().getRailCardID().toString()))
							{
								customerID = Integer.parseInt(list.get(i).getCustomerID().toString());
								abonnement.getLblKLantenNummerResult().setText(list.get(i).getCustomerID().toString());
							}
						}
						/*try {
							JSONArray customers = new JSONArray(
									URLCon.readUrl("http://nmbs-team.tk/api/customer", "GET"));

							for (int i = 0; i < customers.length(); i++) {
								JSONObject object = customers.getJSONObject(i);

								if (railCardID == object.getJSONObject("RailCard").getInt("CardID")) {
									String  stringCustomerID = Integer.toString(object.getInt("CustomerID"));
									abonnement.getLblKLantenNummerResult().setText(stringCustomerID);
									customerID = Integer.parseInt(stringCustomerID);		
								}
							}
						} catch (JSONException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {  
							e1.printStackTrace();
						}*/
						abonnement.getBtnMeerInfo().setEnabled(true);
					}
				});
				
				
				
				
				
				abonnement.getBtnMeerInfo().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(!abonnement.getLblKLantenNummerResult().getText().equals("")){
							
							CustomerDAO daoCustomer = new CustomerDAO();
							ArrayList<Customer>list = daoCustomer.selectAll();
							
							for (int i = 0; i < list.size(); i++) {
								if(customerID == Integer.parseInt(list.get(i).getCustomerID().toString()))
								{
									JFrame frame = new JFrame();
									JOptionPane.showMessageDialog(frame,
											"Naam: " +list.get(i).getLastName() +"\n"+
											"Voornaam: "+ list.get(i).getFirstName()+"\n"+
											"Geboortedatum: "+list.get(i).getBirthDate()+"\n"+
											"E-mail: "+list.get(i).getEmail()+"\n"+
											"Straat + nr: "+ list.get(i).getAddress().getStreet()+" "+list.get(i).getAddress().getNumber()+"\n"+
											"Postcode: "+list.get(i).getAddress().getZipCode()+"\n"+
											"Gemeente: "+list.get(i).getAddress().getCity()+"\n",
										    "Meer info",
										    JOptionPane.PLAIN_MESSAGE);
								}
							}
							
							
							
						/*JSONObject customer;
						try {
							customer = new JSONObject(URLCon.readUrl("http://nmbs-team.tk/api/customer/"+customerID, "GET"));
							JSONObject address = customer.getJSONObject("Address");

							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame,
									"Naam: " +customer.getString("FirstName")+"\n"+
									"Voornaam: "+customer.getString("LastName")+"\n"+
									"Geboortedatum: "+customer.getString("BirthDate")+"\n"+
									"E-mail: "+customer.getString("Email")+"\n"+
									"Straat + nr: "+address.getString("Street")+" "+address.getString("Number")+"\n"+
									"Postcode: "+address.getString("ZipCode")+"\n"+
									"Gemeente: "+address.getString("City")+"\n",
								    "Meer info",
								    JOptionPane.PLAIN_MESSAGE);
						} catch (JSONException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}*/
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
							startDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblVervaldatumResult().setText(startDatum);
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
							startDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblVervaldatumResult().setText(startDatum);
						} catch (ParseException pe) {
							pe.printStackTrace();
						}
					}
				});
				
				
				abonnement.getBtnValideer().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String abonnementsNummer = abonnement.getTxtAbonnementsNummer().getText();
						String treinkaart = abonnement.getCbxTreinkaart().getSelectedItem().toString();
						String korting = abonnement.getCbxDiscount().getSelectedItem().toString();
						String duur = abonnement.getCbxDuur().getSelectedItem().toString();
						String startdatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
						String station1 = abonnement.getTxtStation1().getSelectedItem().toString();
						String station2 = abonnement.getTxtStation2().getSelectedItem().toString();
						
						if(!abonnementsNummer.equals("") && !treinkaart.equals(null) && 
						!korting.equals(null) && !duur.equals(null) && DateTimeConverter.checkDate(startdatum) &&
						!station1.equals("") && !station2.equals("") && customerID!=-1){
								abonnement.getLblBedrag().setText("100");
								
								LangageHandler.chooseLangageLbl(abonnement.getLblFoutmelding(), "form");
								//abonnement.getLblFoutmelding().setText("Het formulier is correct");
								abonnement.getBtnVerzenden().setEnabled(true);
						}else{
							LangageHandler.chooseLangageLbl(abonnement.getLblFoutmelding(), "formNc");
							//abonnement.getLblFoutmelding().setText("Het formulier is niet volledig");
						}							
					}
				});
				
				abonnement.getBtnVerzenden().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					}
				});
				
				abonnement.getTxtAbonnementsNummer().getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblKLantenNummerResult().setText("");
						abonnement.getBtnMeerInfo().setEnabled(false);
						customerID=-1;
					}

					public void removeUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblKLantenNummerResult().setText("");
						abonnement.getBtnMeerInfo().setEnabled(false);
						customerID=-1;
					}

					public void insertUpdate(DocumentEvent e) {
						abonnement.getBtnVerzenden().setEnabled(false);
						abonnement.getLblKLantenNummerResult().setText("");
						abonnement.getBtnMeerInfo().setEnabled(false);
						customerID=-1;
					}
				});
				
			}
		});
	}

}
