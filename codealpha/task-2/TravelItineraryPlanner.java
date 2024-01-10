package com.CodeAlpha.task1;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelItineraryPlanner {

	private String startDate;
	private String endDate;
	private String transportation;
	private String accomodation;
	private ArrayList<String> destinations;

//	parameterized constructor
	public TravelItineraryPlanner(ArrayList<String> destinations, String startDate, String endDate,
			String transportation, String accomodation) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.transportation = transportation;
		this.accomodation = accomodation;
		this.destinations = destinations;
	}

//	Getters and Setters
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public String getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}

	public ArrayList<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations.add(destinations);
	}

	public static void getMap(String destination) {
		System.out.println("Generating best route map for your destination " + destination + " .....");
	}

	public static void getWeather(String destination) {
		System.out.println("Getting weather details from your destination " + destination + " .....");
	}

	public static void getBudget(String destination) {
		System.out.println("Generating best budget for your destination " + destination + " .....");
	}

//	travel plan generating method
	public void generateTravelPlan() {
		System.out.println("Generating your TravelItineraryPlanner....");
		for (String destination : destinations) {
			System.out.println();
			System.out.println("Destination: " + destination);
			System.out.println("From: " + startDate + " To: " + endDate);
			System.out.println("Transportation mode: " + transportation);
			System.out.println("Accomodation : " + accomodation);
			getWeather(destination);
			getMap(destination);
			getBudget(destination);
			System.out.println();
			System.out.println("================");
		}
	}

//	taking destinations form user
	private static ArrayList<String> getDestination(Scanner scanner) {
		System.out.print("Enter the number of destinations: ");
		int numOfDestinations = scanner.nextInt();
		ArrayList<String> destinations = new ArrayList<String>();
		for (int i = 0; i < numOfDestinations; i++) {
			System.out.print("Enter the " + (i + 1) + " Destination name: ");
			destinations.add(scanner.next());
		}
		return destinations;
	}

//	taking dates from user
	private static String getDate(Scanner scanner) {
		return scanner.next();
	}

//	main method
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

//		input
		ArrayList<String> destinations = getDestination(scanner);
		System.out.print("Enter the start date(DD/MM/YYYY): ");
		String startDate = getDate(scanner);
		System.out.print("Enter the end date(DD/MM/YYYY): ");
		String endDate = getDate(scanner);
		System.out.print("Enter the preferred mode of transportation: ");
		String transportaion = scanner.next();
		System.out.print("Enter the preferred accomodation: ");
		String accomodation = scanner.next();

//		creating object of travelItineraryPlanner class and calling parameterized constructor
		TravelItineraryPlanner travelItineraryPlanner = new TravelItineraryPlanner(destinations, startDate, endDate,
				transportaion, accomodation);
		System.out.println();

//		calling generateTravelPlan
		travelItineraryPlanner.generateTravelPlan();

//		closing scanner
		scanner.close();
	}

}
