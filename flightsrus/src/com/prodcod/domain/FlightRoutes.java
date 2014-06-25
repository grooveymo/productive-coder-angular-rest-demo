package com.prodcod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRoutes implements Serializable{

	private static final long serialVersionUID = 3475633453482705752L;

	//Map linking destinations to departures
	private Map<Airport, List<FlightPath>> routes;
	
	public FlightRoutes() {
		routes = new HashMap<Airport, List<FlightPath>>();
	}

//	public void addRoute(final Airport departure, final Airport destination, final Flight flight) {
	public void addRoute(final Airport departure, final Airport destination) {

//		final FlightPath flightPath = new FlightPath(departure,  destination, flight);
		final FlightPath flightPath = new FlightPath(departure,  destination);

		if (routes.get(destination) == null ) {
			final List<FlightPath> list = new ArrayList<FlightPath>();						
			list.add(flightPath);
			routes.put(destination,list);
		}
		else {
			routes.get(destination).add(flightPath);
		}
	}
	
	public List<FlightPath> getFlightPaths(final String destination) {
		final Airport destinationAirport = new Airport(destination);
		return routes.get(destinationAirport);
	}

	public Map<Airport, List<FlightPath>> getAllRoutes() {
		return routes;
	}
	
	@Override
	public String toString() {
		return "FlightRoutes [routes=" + routes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((routes == null) ? 0 : routes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightRoutes other = (FlightRoutes) obj;
		if (routes == null) {
			if (other.routes != null)
				return false;
		} else if (!routes.equals(other.routes))
			return false;
		return true;
	}

	
}
