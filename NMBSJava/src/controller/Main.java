package controller;

import model.RouteberekeningAPI;

public class Main {

	public static void main(String[] args) {

		try {
			RouteberekeningAPI.readUrl("https://traintracks.online/api/Route/Geraardsbergen/Brussel-Zuid");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}