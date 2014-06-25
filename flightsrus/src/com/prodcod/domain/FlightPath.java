package com.prodcod.domain;

import java.io.Serializable;

public class FlightPath implements Serializable {

	private static final long serialVersionUID = -1918951818315019573L;

	private Airport departure;
	private Airport destination;

	//The flight servicing this route.
//	private Flight flight;
	
	public FlightPath() {
		
	}

//	public FlightPath(final Airport from, final Airport to, final Flight flight) {
	public FlightPath(final Airport from, final Airport to) {
		this.departure = from;
		this.destination = to;
//		this.flight = flight;
	}

	public Airport getDeparture() {
		return departure;
	}

	public void setDeparture(Airport departure) {
		this.departure = departure;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

//	public Flight getFlight() {
//		return flight;
//	}
//
//	public void setFlight(Flight flight) {
//		this.flight = flight;
//	}

	@Override
	public String toString() {
		return "FlightPath [departure=" + departure + ", destination="
				+ destination + "]";
//				+ destination + ", flight=" + flight + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departure == null) ? 0 : departure.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
//		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
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
		FlightPath other = (FlightPath) obj;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
//		if (flight == null) {
//			if (other.flight != null)
//				return false;
//		} else if (!flight.equals(other.flight))
//			return false;
		return true;
	}
	
}
