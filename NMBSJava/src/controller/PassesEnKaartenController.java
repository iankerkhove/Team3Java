package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import panels.PassesEnKaartenPanel;

import org.json.JSONException;
import org.json.JSONObject;

public class PassesEnKaartenController {
	public static void startListening(PassesEnKaartenPanel passes){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					passes.getBtnPrint().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String startDatum = passes.getDteStartDatum().getJFormattedTextField().getText();
							int typePass = passes.getCbxPassType().getSelectedIndex();
							int mnem = passes.getGrpKlasses().getSelection().getMnemonic();

							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();
							String date = dateFormat.format(cal);

							String base = "http://nmbs-team.tk/api/pass/create";
							String url = base + "?TypePassID="+typePass+"&Date="+date+"&StartDate="+startDatum+"&ComfortClass="+mnem;

							JSONObject temp;
							try {
								temp = new JSONObject(URLCon.readUrl(url, "POST"));
								int var1 = temp.getInt("StatusCode");
								if (var1 == 200) {
									passes.getLblRes().setText("Pass aangemaakt");
								} else {
									passes.getLblRes().setText("Pass niet aangemaakt; kijk de velden na en probeer opnieuw");
								}
							} catch (JSONException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
				catch(Exception pe){
					pe.printStackTrace();
				}
			}
		});		
	}
}
