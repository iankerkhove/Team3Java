package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.json.JSONObject;

import controller.UrlConWorker.RequestType;
import panels.LoginPanel;

public class LoginController {

	private static int staffID;
	private static String token;
	private static int statuscode;
	
	private static UrlConWorker urlConWorker;

	public static void login(LoginPanel l) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				l.getBtnLogin().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						readUrl(l);
						
					}
				});
			}
		});
	}
	


	private static void readUrl(LoginPanel l) {
		String usrn = l.getTxtUsername().getText().replaceAll("&", "%26");
		String password = l.getTxtPassword().getText().replaceAll("&", "%26");
		// boolean chAdmin = l.getChAdmin().isSelected();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", usrn);
		params.put("password", password);
		
		urlConWorker = new UrlConWorker("staff/login", RequestType.POST, params);
		
		urlConWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				
				if("state".equals(evt.getPropertyName())
						&& SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
					
					try {
						JSONObject json = urlConWorker.get();
						
						statuscode = json.getInt("StatusCode");
						if (statuscode == 200) {
							token = json.getString("Api_token");
							staffID = json.getInt("StaffID");
						}
						
						if (statuscode == 200) {
							l.getLblResult().setText("");
							GUIController.getFrame().getContentPane().removeAll();
							GUIController.showApp();
							CacheExistingSations.cache();
						} else {
							l.getLblResult().setText("Fout, probeer opnieuw!");
							l.getTxtUsername().setText("");
							l.getTxtPassword().setText("");
						}
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
			}
			
		});
		
		urlConWorker.execute();
	}

	public static int getStaffID() {
		return staffID;
	}

	public static String getToken() {
		return token;
	}

	public static int getStatuscode() {
		return statuscode;
	}

}