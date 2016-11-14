package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import gui.GUIDateFormat;
import panels.NieuwAbonnementPanel;

public class KoopAbonnementController {
	public static void startListening(NieuwAbonnementPanel abonnement){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				String naam = abonnement.getTxtNaam().getText();
				String voornaam = abonnement.getTxtVoornaam().getText();
				String geboorteDatum = abonnement.getDteGeboorteDatum().getJFormattedTextField().getText();
				String email = abonnement.getTxtEmail().getText();
				String telefoonnummer = abonnement.getTxtTelefoonnr().getText();
				String straatEnNummer = abonnement.getTxtStraatEnNummer().getText();
				String postcode = abonnement.getTxtPostcode().getText();
				String gemeente = abonnement.getTxtGemeente().getText();
				String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
		//		int klasse = abonnement.getGrpKlasses().getSelection().getMnemonic();
		//      String station1 = abonnement.getTxtStation1().getText();
		//		String station2 = abonnement.getTxtStation2().getText();
				abonnement.getLblStation1().setVisible(false);
				abonnement.getLblStation2().setVisible(false);
				abonnement.getTxtStation1().setVisible(false);
				abonnement.getTxtStation2().setVisible(false);
				
				abonnement.getRdbJa().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(abonnement.getRdbJa().isSelected())
						{
							abonnement.getLblStation1().setVisible(true);
							abonnement.getLblStation2().setVisible(true);
							abonnement.getTxtStation1().setVisible(true);
							abonnement.getTxtStation2().setVisible(true);
						}	
						
					}
				});
				
				abonnement.getRdbNee().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(abonnement.getRdbNee().isSelected())
						{
							abonnement.getLblStation1().setVisible(false);
							abonnement.getLblStation2().setVisible(false);
							abonnement.getTxtStation1().setVisible(false);
							abonnement.getTxtStation2().setVisible(false);
						}	
						
					}
				});
				
                abonnement.getBtnPrint().addActionListener(new ActionListener() {
                	
					public void actionPerformed(ActionEvent ae) {
						
											
						try{
							String treinkaart = abonnement.getCbxTreinkaart().getSelectedItem().toString();
							String duur = abonnement.getCbxDuur().getSelectedItem().toString();
							String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
							
							Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
							
							switch(duur){
							case "1 maand": c.add(Calendar.MONTH, 1);break;
							case "3 maanden": c.add(Calendar.MONTH, 3);break;
							case "12 maanden": c.add(Calendar.MONTH, 12);break;
							}
							
							startDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblBerekendeVervaldatum().setText(startDatum);
						
						}
						catch(ParseException pe)
						{
							pe.printStackTrace();
						}
					
			
					/*	if(vastTraject==1)
						{
							if (!naam.equals("") && !voornaam.equals("") && DateTimeConverter.checkDate(geboorteDatum) 
									&& !email.equals("") && !telefoonnummer.equals("") && !straatEnNummer.equals("")
									&& !postcode.equals("") && !gemeente.equals("") && !treinkaart.equals(null) 
									&& DateTimeConverter.checkDate(startDatum) && klasse!= 0 && vastTraject!=0
									&& !station1.equals("") && station2.equals("")){}
							else {System.err.println("Het formulier is niet volledig ingevuld.");}
						}
						else{
							if (!naam.equals("") && !voornaam.equals("") && DateTimeConverter.checkDate(geboorteDatum) 
									&& !email.equals("") && !telefoonnummer.equals("") && !straatEnNummer.equals("")
									&& !postcode.equals("") && !gemeente.equals("") && !treinkaart.equals(null) 
									&& DateTimeConverter.checkDate(startDatum) && klasse!= 0 && vastTraject!=0){}
							else {System.err.println("Het formulier is niet volledig ingevuld.");}
						}*/
                    }
                });
            }
        });
    }
}