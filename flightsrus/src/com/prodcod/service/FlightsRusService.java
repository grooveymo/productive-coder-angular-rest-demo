package com.prodcod.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.prodcod.domain.Airport;

@Path("/flightsRusService")
public class FlightsRusService {

	private static final Logger logger = Logger.getLogger(FlightsRusService.class);
	
	private static final FlightsBackendService backendService = new FlightsBackendServiceImpl();

	/**
	 * Access via
	 *    http://localhost:8080/RESTflightsRus/flightsRusService/destinations
	 * @return
	 */
	@GET
	@Path("/destinations")
//	@Produces("application/*")
	@Produces("application/json")
	public List<Airport> getDestinations() {
		
		final List<Airport> destinations = backendService.getDestinations();
		logger.info("SERVER destinations: " + destinations);

		return destinations;
	}

	/**
	 * Access via
	 *    http://localhost:8080/RESTflightsRus/flightsRusService/departures/Algarve
	 * @return
	 */
	@GET
	@Path("/departures/{destination}")
	@Produces("application/*")
//	public List<Airport> getDepartureAirports((@PathParam("destination") String destination) {
	public List<Airport> getDepartureAirports(@PathParam("destination") String destination) {
		
		final List<Airport> departureAirports = backendService.getDepartureAirports(destination);
		logger.info("SERVER destinations: " + departureAirports);

		return departureAirports;
	}
	

	
	/**
	 * Access via
	 *    http://localhost:8080/RESTflightsRus/flightsRusService/flights/Algarve
	 * @return
	 */
//	@GET
//	@Path("/flights/{destination}")
//	@Produces("application/*")
//	public List<Flight> getFlights(@PathParam("destination") String destination) {
//		
//		final List<Flight> flights = backendService.getFlightsForRoute(destination);
//		logger.info("SERVER destinations: " + flights);
//
//		return flights;
//	}

	//=============================================================================================

	
	@GET
	@Path("/allroutes")
	@Produces("application/json")
//	public Map<String, List<String>> getAllRoutes() {
	public String getAllRoutes() {
	
		return backendService.getAllRoutes();
	}

	
	/**
	 * Access via
	 *    http://localhost:8080/RESTflightsRus/flightsRusService/flightoptions/?departure='Birmingham'&destination='Algarve'&fromDate='12-06-13'&toDate='12-06-13'
	 * @return
	 */
	@GET
	@Path("/flightoptions")
	@Produces("application/json")
	public String getFlightOptions(@QueryParam("departure") String departure, 
								   @QueryParam("destination") String destination,
								   @QueryParam("fromDate") String fromDateIn,
								   @QueryParam("toDate") String toDateIn,
									@QueryParam("numAdults") int numAdults,
									@QueryParam("numChildren") int numChildren) {
	
		System.out.println("fromDateIn: " + fromDateIn);

		final Date fromDate = convertToDate(fromDateIn);
		final Date toDate = convertToDate(toDateIn);
		System.out.println("fromDate: " + fromDate);
		System.out.println("toDate: " + toDate);
		
		System.out.println("Form submitted successfully ==> depart: " + departure + " dest: " + destination + " departing: " + fromDate + " returning: " + toDate);
		return backendService.getSelectedFlights(departure, destination, fromDate, toDate, numAdults, numChildren);
		
	}
	
	//String dateStr = "Mon Jun 18 00:00:00 IST 2012";
	//DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

	
	//Tue Jun 17 2014 00:00:00 GMT+0100 (BST) 
	public Date convertToDate(final String dateString) {
		    try {
//		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		        DateFormat df = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
		        Date date = df.parse(dateString);
		        return date;
		    } catch (ParseException e) {
		        //WebApplicationException ...("Date format should be yyyy-MM-dd'T'HH:mm:ss", Status.BAD_REQUEST);
		    	System.out.println("Error with date");
		    }
		    
		    return null;
	}
	
	public static void main(final String[] args) {
		FlightsRusService service = new FlightsRusService();
		
		final String dateString = "Tue Jun 24 2014 00:00:00 GMT+0100 (BST) ";//"Tue Jun 17 2014 00:00:00 GMT+0100 (BST)";
		Date d = service.convertToDate(dateString);
		
		System.out.println("ddd: " + d);
	}
}
