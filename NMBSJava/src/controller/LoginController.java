package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.json.JSONObject;

import controller.UrlConWorker;
import controller.UrlConWorker.APIUrl;
import controller.UrlConWorker.RequestType;
import dao.StaffDAO;
import model.Staff;
import panels.LoginPanel;
import services.JBcryptVerifier;

public class LoginController {

	private static UUID staffID;
	private static String token;
	private static int rechten;

	private static LoginPanel loginPanel;
	private static UrlConWorker urlConWorker;

	public static void login(LoginPanel l) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				l.getBtnLogin().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verify(l);
					}
				});
			}
		});
	}

	private static void verify(LoginPanel l) {
		String usrn = l.getTxtUsername().getText().replaceAll("&", "%26");
		String password = l.getTxtPassword().getText().replaceAll("&", "%26");
		boolean chAdmin = l.getChAdmin().isSelected();

		StaffDAO handler = new StaffDAO();
		Staff s = handler.selectOneOnUsername(usrn);
		if (s != null) {
			if (JBcryptVerifier.checkPassword(password, s.getPassword())){
				if(chAdmin){
					if (s.getRights() == 1) {
						rechten = 1;
						token = s.getApiToken();
						staffID = s.getStaffID();
						l.getLblResult().setText("");
						GUIController.getFrame().getContentPane().removeAll();
						GUIController.showApp();
					} else {
						l.getLblResult().setText("Geen admin rechten, probeer opnieuw!");
					}
				} else {
					rechten = 0;
					token = s.getApiToken();
					staffID = s.getStaffID();
					l.getLblResult().setText("");
					GUIController.getFrame().getContentPane().removeAll();
					GUIController.showApp();
				}
			} else {
				l.getLblResult().setText("Fout password, probeer opnieuw!");
				l.getTxtPassword().setText("");
			}
		} else {
			l.getLblResult().setText("User ongeldig, probeer opnieuw!");
			l.getTxtUsername().setText("");
			l.getTxtPassword().setText("");
		}
	}

	@SuppressWarnings("unused")
	@Deprecated
	private static void readUrl(LoginPanel l) {
		String usrn = l.getTxtUsername().getText().replaceAll("&", "%26");
		String password = l.getTxtPassword().getText().replaceAll("&", "%26");
		// boolean chAdmin = l.getChAdmin().isSelected();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("username", usrn);
		params.put("password", password);

		urlConWorker = new UrlConWorker(APIUrl.G3, "staff/login", RequestType.POST, params);

		urlConWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {

					try {
						JSONObject json = urlConWorker.get();

						int statuscode = json.getInt("StatusCode");
						if (statuscode == 200) {
							token = json.getString("Api_token");
							staffID = UUID.fromString(json.getString("StaffID"));
						}

						if (statuscode == 200) {
							l.getLblResult().setText("");
							GUIController.getFrame().getContentPane().removeAll();
							GUIController.showApp();
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

	public static UUID getStaffID() {
		return staffID;
	}

	public static String getToken() {
		return token;
	}

	public static void clearCreds() {
		token = "";
		staffID = UUID.randomUUID();
	}

	public static int getRechten() {
		return rechten;
	}
}