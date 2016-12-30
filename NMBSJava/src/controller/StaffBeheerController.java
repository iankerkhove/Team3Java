package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import panels.StaffBeheerPanel;
import model.Station;
import model.Address;
import model.Staff;
import dao.StationDAO;
import gui.Popup;
import dao.AddressDAO;
import dao.StaffDAO;

public class StaffBeheerController {
	public static void startListening(StaffBeheerPanel staff) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				staff.getBtnVoegToe().addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String voornaam = staff.getTxtVoornaam().getText();
						String achternaam = staff.getTxtAchternaam().getText();
						String datum = staff.getDatePicker().getJFormattedTextField().getText();
						String email = staff.getTxtEmail().getText();
						String straat = staff.getTxtStraat().getText();
						String gemeente = staff.getTxtGemeente().getText();
						String station = (String) staff.getTxtStation().getSelectedItem();
						String username = staff.getTxtUsername().getText();
						String password = staff.getTxtPassword().getText();
						int rechten = 0;


						if(voornaam.equals("") || achternaam.equals("") || email.equals("")|| 
								straat.equals("") || staff.getTxtNummer().getText().equals("")|| staff.getTxtPostcode().getText().equals("")||gemeente.equals("") ||
								staff.getTxtPostcode().toString().equals("") || username.equals("") || password.equals(""))
						{
							Popup.warningMessage("WarningPopup", "WarningPopupTitel");
							System.out.println(password);
							System.out.println("testestest");
						}
						else
						{



							int nummer = 0;
							try {
								nummer=Integer.parseInt(staff.getTxtNummer().getText());
							}catch (NumberFormatException e1){
								Popup.warningMessage("nummerWarningPopup", "WarningPopupTitel");			
							} 

							int postcode = 0;
							try {
								postcode = Integer.parseInt(staff.getTxtPostcode().getText());
							}catch (NumberFormatException e1){
								Popup.warningMessage("postcodeWarningPopup", "WarningPopupTitel");	
							} 


							if(staff.getRdbJa().isSelected())
							{
								rechten = 1;
							}
							else if(staff.getRdbNee().isSelected())
							{
								rechten = 0;
							}

							Address adres = new Address(straat,nummer,gemeente,postcode,null);
							AddressDAO adresD = new AddressDAO();
							adresD.insert(adres);
							//ArrayList<Address> addressList = new ArrayList<Address>();
							//addressList = adresD.selectAll();

							/*Address adres2 = new Address();

							for (int i = 0 ; i < addressList.size(); i++)
							{
								if (adres.equals(addressList.get(i)));
								{
									adres2 = adres;
								}
							}*/

							Station stat= new Station();
							StationDAO statD = new StationDAO();
							ArrayList<Station> statList = new ArrayList<Station>();
							statList = statD.selectAll();

							for (int i = 0; i <statList.size() ;i++)
							{
								if(station.equals(statList.get(i).getStationName()))
								{
									stat = statList.get(i);
								}
							}

							
							
							Staff staff = new Staff(adres,stat,voornaam,achternaam,username,password,rechten,datum,email);
							staff.setAddressID(adres.getAddressID());
							staff.setStationID(stat.getStationID());
							
							StaffDAO staffD = new StaffDAO();
							
							
							staffD.insert(staff);							
						}
					}
				});
			}
		});

	}
}
