package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import org.json.JSONArray;

import controller.APIController.APIUrl;
import controller.APIController.RequestType;
import gui.LangageHandler;
import model.api.Stationboard;
import panels.StationboardPanel;
import services.APIThread;
import services.ThreadListener;

public class StationsbordController
{
	public static void startListening(StationboardPanel station)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				station.getBtnZoek().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						String stationName = (String) station.getTxtStation().getSelectedItem();

						if (!stationName.equals("")) {

							HashMap<String, String> params = new HashMap<String, String>();
							APIThread t = new APIThread(APIUrl.TRAINTRACKS, "stationboard/" + stationName, RequestType.GET, params);
							ThreadListener listener = new ThreadListener() {

								@Override
								public void setResult(JSONArray data)
								{
									if (data != null)
									{
										Stationboard s = new Stationboard(data);
										s.setStation(stationName);
										String ss = s.toString();
										if (!ss.contains("null")) {
											station.getLblResult().setText("<html>"
													+ ss.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>")
													+ "</html>");
										}
									}
									else
									{
										LangageHandler.chooseLangageLbl(station.getLblResult(),"verzoek");
										//station.getLblResult().setText("Dit verzoek kon niet verwerkt worden.");
									}
								}
								
							};
							t.setListener(listener);
							t.start();
							
						}
						else {
							LangageHandler.chooseLangageLbl(station.getLblResult(),"foutRes");
							//station.getLblResult().setText("Formulier werd niet correct ingevuld.");
						}

					}
				});
			}
		});
	}
}
