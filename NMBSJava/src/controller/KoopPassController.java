package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.PassDAO;
import dao.TypePassDAO;
import gui.GUIDateFormat;
import model.Pass;
import model.TypePass;
import panels.PassPanel;

public class KoopPassController {
	
	public static void startListening(PassPanel pass)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				pass.getBtnVerkoop().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tpString = (String) pass.getAutPass().getSelectedItem();
						String startDate = pass.getDteDate().getJFormattedTextField().getText();
						
						TypePassDAO tpHandler = new TypePassDAO();
						TypePass tp = tpHandler.selectOneOnName(tpString);
						
						PassDAO pHandler = new PassDAO();
						Pass p = new Pass(tp.getTypePassID(), GUIDateFormat.getDate(), startDate, 1);
						pHandler.insert(p);
						
						System.out.println("Te betalen: " + tp.getPrice() + " euro");
					}
				});
			}
		});
	}
	
}
