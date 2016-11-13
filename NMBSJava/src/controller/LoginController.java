package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import panels.LoginPanel;

public class LoginController {

	private static int staffID;
	private static String token;
	private static int statuscode;
	private static JSONObject json;

	public static void login(LoginPanel l) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				l.getBtnLogin().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						readUrl(l);
						if (statuscode == 200) {
							l.getLblResult().setText("");
							GUIController.getFrame().getContentPane().removeAll();
							GUIController.showApp();
						} else {
							l.getLblResult().setText("Fout, probeer opnieuw!");
							l.getTxtUsername().setText("");
							l.getTxtPassword().setText("");
						}
					}
				});
			}
		});
	}

	private static void readUrl(LoginPanel l) {
		String usrn = l.getTxtUsername().getText().replaceAll("&", "%26");
		String password = l.getTxtPassword().getText().replaceAll("&", "%26");
		// boolean chAdmin = l.getChAdmin().isSelected();

		BufferedReader reader = null;
		try {
			URL url = new URL("http://nmbs-team.tk/api/staff/login?username=" + usrn + "&password=" + password);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			json = new JSONObject(buffer.toString());
			statuscode = json.getInt("StatusCode");
			if (statuscode == 200) {
				token = json.getString("Api_token");
				staffID = json.getInt("StaffID");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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