package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import gui.GUIDateFormat;
import panels.VerlengAbonnementPanel;

public class VerlengAbonnementController {
	public static void startListening(VerlengAbonnementPanel abonnement){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				
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
				
				abonnement.getDteStartdatum().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try{
						String startDatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
						Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
						c.add(Calendar.MONTH, 1);
						startDatum = GUIDateFormat.objectToString(c);
						abonnement.getLblVervaldatumResult().setText(startDatum);
						}
						catch(ParseException pe)
						{
							pe.printStackTrace();
						}

						
					}
				});
				
				abonnement.getCbxDuur().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try{
							String duur = abonnement.getCbxDuur().getSelectedItem().toString();
							String startDatum = abonnement.getDteStartdatum().getJFormattedTextField().getText();
							Calendar c = GUIDateFormat.dateToCalendar((Date) GUIDateFormat.stringToObject(startDatum));
							switch(duur){
							case "1 maand": c.add(Calendar.MONTH, 1);break;
							case "3 maanden": c.add(Calendar.MONTH, 3);break;
							case "12 maanden": c.add(Calendar.MONTH, 12);break;
							}
							
							startDatum = GUIDateFormat.objectToString(c);
							abonnement.getLblVervaldatumResult().setText(startDatum);
						
						}
						catch(ParseException pe)
						{
							pe.printStackTrace();
						}
					}
				});
				
			}
		});
	}

}
