package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import panels.LoginPanel;
import services.APIThread;
import services.ThreadListener;

public class LoginController
{

	private static UUID staffID;
	private static String token;
	private static int statuscode;

	private static LoginPanel loginPanel;

	public static void login(LoginPanel l)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				loginPanel = l;
				l.getBtnLogin().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						//readUrl(l);
						String usrn = l.getTxtUsername().getText().replaceAll("&", "%26");
						String password = l.getTxtPassword().getText().replaceAll("&", "%26");
						
						HashMap<String, String> params = new HashMap<String, String>();
						params.put("username", usrn);
						params.put("password", password);
						
						APIThread g3API = new APIThread(APIUrl.G3, "staff/login", RequestType.POST, params);
						ThreadListener listener = new ThreadListener() {

							@Override
							public void setResult(JSONArray data)
							{
								JSONObject json = data.getJSONObject(0);
								
								statuscode = json.getInt("StatusCode");
								if (statuscode == 200) {
									token = json.getString("Api_token");
									staffID = UUID.fromString(json.getString("StaffID"));
								}

								if (statuscode == 200) {
									loginPanel.getLblResult().setText("");
									GUIController.getFrame().getContentPane().removeAll();
									GUIController.showApp();
									CacheExistingSations.cache();
									CacheTicketTypes.cache();
									CachePassTypes.cache();
								}
								else {
									loginPanel.getLblResult().setText("Fout, probeer opnieuw!");
									loginPanel.getTxtUsername().setText("");
									loginPanel.getTxtPassword().setText("");
								}
							}
							
						};
						
						g3API.settListener(listener);
						g3API.start();

					}
				});
			}
		});
	}
	
	public static UUID getStaffID()
	{
		return staffID;
	}

	public static String getToken()
	{
		return token;
	}

	public static int getStatuscode()
	{
		return statuscode;
	}

	public static void clearCreds()
	{
		token = "";
		staffID = UUID.randomUUID();
	}

}