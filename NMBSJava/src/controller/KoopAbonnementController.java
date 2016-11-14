package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				String treinkaart = abonnement.getCbxTreinkaart().getSelectedItem().toString();
				String startDatum = abonnement.getDteStartDatum().getJFormattedTextField().getText();
		//		int klasse = abonnement.getGrpKlasses().getSelection().getMnemonic();
		//		int vastTraject = abonnement.getGrpJaNee().getSelection().getMnemonic();
		//      String station1 = abonnement.getTxtStation1().getText();
		//		String station2 = abonnement.getTxtStation2().getText();
				

                abonnement.getbtnPrint().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abonnement.getLblDuur().setText("6 maanden");						
			
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