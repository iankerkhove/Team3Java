package dao;

import dao.AddressDAO;
public class InitDatabase {
	public InitDatabase() {
		AddressDAO.createTable();
	}
}
