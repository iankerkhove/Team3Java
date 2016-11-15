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
				
			}
		});
	}

}
