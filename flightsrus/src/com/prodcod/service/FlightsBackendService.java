package com.prodcod.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prodcod.domain.Airport;
import com.prodcod.domain.Flight;
import com.prodcod.domain.Plane;
import com.prodcod.domain.FlightPath;

public interface FlightsBackendService {

	/**
	 * Returns List of all possible destinations
	 * @return
	 */
	public List<Airport> getDestinations();
	
	/**
	 * Return all departure airports for given destination
	 * @param desintation
	 * @return
	 */
	public List<Airport> getDepartureAirports(final String destination);
	
	
	/**
	 * Returns all flights for a given route.
	 * @param departure
	 * @param destination
	 * @return
	 */
//	public List<Flight> getFlightsForRoute(final String destination);
	
	
	/**
	 * Returns all departure airports for each destination
	 * Map<k,V> where k = destination airport
	 * and V = List<String>, a collection of departure airports
	 * @return
	 */
//	public Map<String, List<String>> getAllRoutes();
	public String getAllRoutes();
	
	
	/**
	 * Returns list of flights servicing this route
	 * @param departure
	 * @param destination
	 * @param fromDate
	 * @param toDate
	 * @param numAdults
	 * @param numChildren
	 * @return
	 */
	public String getSelectedFlights(final String departure, final String destination, final Date fromDate,final Date toDate, final int numAdults, final int numChildren);

}
