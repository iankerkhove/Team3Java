package controller;

import model.RouteberekeningAPI;

public class Main {

	public static void main(String[] args) {
		RouteberekeningAPI r = new RouteberekeningAPI("Brussel-Zuid", "Geraardsbergen");
		System.out.println(r.getRoutes().get(0).getTrains().get(0).getFullId());
	}
}