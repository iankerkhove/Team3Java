package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import panels.PasPrijzenAanPanel;

import org.json.JSONException;
import org.json.JSONObject;

public class PasPrijzenAanController {
	public static void startListening(PasPrijzenAanPanel prijzen) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prijzen.getBtnWijzig().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean error = false;
							boolean isNieuw = true;

							String typeTicket = prijzen.getTxtNewTypeTicket().getText();
							if (typeTicket.equals("")) {
								isNieuw = false;
								typeTicket = (String) prijzen.getTxtTypeTicket().getSelectedItem();
							}

							int mnem = prijzen.getGrpKlasses().getSelection().getMnemonic();

							String nieuwePrijs = prijzen.getTxtNieuwePrijs().getText();
							if (nieuwePrijs.equals("")) {
								error = true;
							}

							String base = "http://nmbs-team.tk/api/typeTicket/";
							String url;
							JSONObject temp;

							if (!error) {
								try {
									if (isNieuw) {
										url = base + "create?Name="+typeTicket+"&Price="+nieuwePrijs+"&ComforClass"+mnem;

										temp = new JSONObject(URLCon.readUrl(url, "POST"));

									}

									else {
										int typeID = CachePassTypes.getTypeID(typeTicket);
										url = base + "update/"+ typeID + "?Name="+typeTicket+"&Price="+nieuwePrijs+"&ComforClass"+mnem;
										temp = new JSONObject(URLCon.readUrl(url, "PUT"));
									}
									int errorMessage = temp.getInt("StatusCode");
								} catch (JSONException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					});
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
