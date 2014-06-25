package com.prodcod.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.prodcod.domain.Airport;
import com.prodcod.domain.Flight;
import com.prodcod.domain.Plane;
import com.prodcod.domain.FlightPath;
import com.prodcod.domain.FlightRoutes;

public class FlightsBackendServiceImpl implements FlightsBackendService{

	//Simulate persistence storage
	//	private Map<String, String> routes = new ConcurrentHashMap<Integer, Order>();

	//	private AtomicInteger customerIdCounter = new AtomicInteger();

	private static final String[] destinationAirports = new String[]{"Algarve", "Barcelona", "Costa Del Sol", "Majorca", "Tenerife"};
	private static final String[] departureAirports = new String[]{"Birmingham", "Bristol", "East Midlands", "London", "Newcastle"};
	
	private static final List<Plane> planesList = new ArrayList<Plane>();
	private static final List<Airport> destinationList = new ArrayList<Airport>();
	
	static {
		final Plane swift1 = new Plane("SWFT001", "Swift Tours");
		final Plane swift2 = new Plane("SWFT002", "Swift Tours");
		final Plane swift3 = new Plane("SWFT003", "Swift Tours");
		final Plane swift4 = new Plane("SWFT004", "Swift Tours");				
		planesList.add(swift1);
		planesList.add(swift2);
		planesList.add(swift3);
		planesList.add(swift4);
		
		final Plane transcorp1 = new Plane("TC_001", "transcorp");
		final Plane transcorp2 = new Plane("TC_002", "transcorp");
		final Plane transcorp3 = new Plane("TC_003", "transcorp");
		planesList.add(transcorp1);
		planesList.add(transcorp2);
		planesList.add(transcorp3);

		
	}
	private Random random = new Random();
	
	private FlightRoutes flightRoutes = new FlightRoutes();

	public FlightsBackendServiceImpl() {

		//Create Flight Routes between Departure and Destination Airports
		for(int c = 0; c < destinationAirports.length; c++) {

			final Airport destination = new Airport(destinationAirports[c]);
			
			destinationList.add(destination);
			
			int numDeparts = random.nextInt(departureAirports.length);		

			if(numDeparts == 0) {
				numDeparts = 1;
			}
			
			final Set<Airport> departuresSet = new HashSet<Airport>();
			while(departuresSet.size() < numDeparts) {
				final Airport departure = new Airport(departureAirports[random.nextInt(departureAirports.length)]);
				departuresSet.add(departure);				
			}
			
			for (Airport departureAirport : departuresSet) {
//				Flight flight = flightsList.get(random.nextInt(flightsList.size()));
//				flightRoutes.addRoute(departureAirport, destination, flight);
				flightRoutes.addRoute(departureAirport, destination);
				
				System.out.println("departure: " + departureAirport + " destination: " + destination);
			}
			
		}
		
		//assign airlines to flight routes
	}

	@Override
	public List<Airport> getDestinations() {
		return destinationList;
	}

	@Override
	public List<Airport> getDepartureAirports(String destination) {
		
		final List<FlightPath> flightPaths = flightRoutes.getFlightPaths(destination);

		final List<Airport> departures = new ArrayList<Airport>();
		
		for (FlightPath flightPath : flightPaths) {
			departures.add(flightPath.getDeparture());
		}
		return departures;				
	}

//	@Override
//	public List<Flight> getFlightsForRoute(final String destination) {
//
//		final List<FlightPath> flightPaths = flightRoutes.getFlightPaths(destination);
//
//		final List<Flight> flights = new ArrayList<Flight>();
//		
//		for (FlightPath flightPath : flightPaths) {
//			flights.add(flightPath.getFlight());
//		}
//		return flights;				
//	}


	@Override
//	public Map<String, List<String>> getAllRoutes() {
	public String getAllRoutes() {
		final StringBuffer sb = new StringBuffer();
		sb.append("[");

		final Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		Map<Airport, List<FlightPath>> in = flightRoutes.getAllRoutes();
		
		final Iterator it = in.entrySet().iterator();
		int count1 = 0;
		
		while(it.hasNext()) {
			
			count1++;
			Map.Entry<Airport, List<FlightPath>> pairs = (Map.Entry<Airport, List<FlightPath>>)it.next();
			final String destination = pairs.getKey().getName();
			final List<FlightPath> flightPaths = pairs.getValue();
			

			sb.append("{ \"name\" : ");
			sb.append("\"");
			sb.append(destination);
			sb.append("\"");
			sb.append(",");
			sb.append("\"departures\" : [");
			
			final List<String> departures = new ArrayList<String>();
			int count2 = 0;
			for(FlightPath fp : flightPaths) {
				count2++;
				departures.add(fp.getDeparture().getName());
				sb.append("{");
				sb.append("\"name\" : ");
				sb.append("\"" + fp.getDeparture().getName() + "\"");
				if(count2 < flightPaths.size()) {
					sb.append("},");					
				}
				else {
					sb.append("}");					
				}
			}
			map.put(destination, departures);
			
			if(count1 < in.size()) {
				sb.append("]},");
			}
			else {
				sb.append("]}");
			}

		}
//		return map;
		
		sb.append("]");

		return sb.toString();
	}
	
	@Override
	public String getSelectedFlights(final String departure, final String destination, final Date fromDate,final Date toDate, final int numAdults, final int numChildren) {

		final StringBuffer sb = new StringBuffer();
		sb.append("[");

		final List<Flight> flightOptions = new ArrayList<Flight>();
		
		//for this flight path - create numerous flights
		final int numberServicingFlights = random.nextInt(planesList.size());
		
		for(int i = 0; i < numberServicingFlights; i++) {
			
			Plane plane = planesList.get(random.nextInt(planesList.size()));
			final Flight flight = new Flight(departure, destination, fromDate, toDate, plane, numAdults, numChildren);
			
			flightOptions.add(flight);
			
			
			sb.append(flight.asJson());
			
			if(i < numberServicingFlights - 1) {
				sb.append(",");				
			}

		}
		

		
		
		sb.append("]");
		
		return sb.toString();
	}
	
	public static void main (final String[] args) {
		FlightsBackendService service = new FlightsBackendServiceImpl();
		
//		//test getDestinations
//		List<Airport> destinations = service.getDestinations();
//		int count = 0;
//		for(Airport destination : destinations) {
//			System.out.println("Destination #"+(++count) + " " + destination);
//		}
//
//		
//		//test getDepartureAirports
//		final String destination = "Algarve";
//		
//		List<Airport> departureAirports = service.getDepartureAirports(destination);
//
//		int count2 = 0;
//		for(Airport departureAirport : departureAirports) {
//			System.out.println("Destination #"+(++count2) + " " + destination + " departure from : " + departureAirport);
//		}
//		
//		//test getFlightsForRoute
//		
//		List<Flight> flights = service.getFlightsForRoute(destination);
//
//		int count3 = 0;
//		for(Flight f : flights) {
//			System.out.println("Destination : " + destination + " flight : " + f);
//		}

		//test getAllRoutes
		final String json = service.getAllRoutes();
		
		System.out.println("json : " + json);
		
	}

}
