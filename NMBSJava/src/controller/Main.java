package controller;

import model.Routeberekening;;

public class Main {

	public static void main(String[] args) {

		Routeberekening r = new Routeberekening("Brussel-Zuid", "Geraardsbergen");
		
		// krijg van/naar terug
		System.out.println("Van: " + r.getStepOn() + "\nNaar: " + r.getStepOff());
		
		// krijg ID van de eerste trein van de eerste route
		System.out.println(r.getRoutes().get(0).getTrains().get(0).getFullId());
		
		// krijg geplande vertrektijd van eerste trein van de eerste route
		System.out.println(r.getRoutes().get(0).getTrains().get(0).getTime().getDeparture());
		
		// krijg aantal te nemen treinen tijdens de eerste route
		System.out.println(r.getRoutes().get(0).getTrains().size());
	}
}
