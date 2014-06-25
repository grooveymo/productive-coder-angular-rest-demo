package com.prodcod.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Flight implements Serializable{

	private static final long serialVersionUID = -6564367454984130336L;

	private static final String QUOTES = "\"";
	private static final String COMMA = ", ";
	
	private final String fromPlace;
	private final String toPlace;
	private final Date fromDate;
	private final Date toDate;
	private final Plane plane;
	
	final Random random = new Random();
	private int outgoingDeparture;
	private int outgoingArrival;
	private int returningDeparture;
	private int returningArrival;

	private int childPrice = random.nextInt(100);
	private int adultPrice = childPrice + random.nextInt(200);
	
	private int totalCost;
	
	public Flight(final String fromPlace, final String toPlace, Date fromDate, Date toDate, Plane plane, int numAdults, int numChildren) {
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.plane = plane;
		
		outgoingDeparture = random.nextInt(12);
		outgoingArrival = outgoingDeparture + 6;

		returningDeparture = random.nextInt(12);
		returningArrival = returningDeparture + 6;
	
		totalCost = (numAdults * adultPrice) + (numChildren * childPrice);
	}

	public String asJson() {
		
		final StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		
		sb.append(QUOTES).append("flightNumber").append(QUOTES).append(" : ").append(QUOTES).append(plane.getFlightNumber()).append(QUOTES);
		sb.append(COMMA);

		sb.append(QUOTES).append("departFrom").append(QUOTES).append(" : ").append(QUOTES).append(fromPlace).append(QUOTES);
		sb.append(COMMA);

		sb.append(QUOTES).append("arriveAt").append(QUOTES).append(" : ").append(QUOTES).append(toPlace).append(QUOTES);
		sb.append(COMMA);

		sb.append(QUOTES).append("outgoing_departure").append(QUOTES).append(" : ").append(QUOTES).append(getOutgoingDeparture()).append(QUOTES);
		sb.append(COMMA);
		
		sb.append(QUOTES).append("outgoing_arrival").append(QUOTES).append(" : ").append(QUOTES).append(getOutgoingArrival()).append(QUOTES);
		sb.append(COMMA);
		
		sb.append(QUOTES).append("returning_departure").append(QUOTES).append(" : ").append(QUOTES).append(getReturningDeparture()).append(QUOTES);
		sb.append(COMMA);
		
		sb.append(QUOTES).append("returning_arrival").append(QUOTES).append(" : ").append(QUOTES).append(getReturningArrival()).append(QUOTES);
		sb.append(COMMA);
		
		sb.append(QUOTES).append("totalCost").append(QUOTES).append(" : ").append(QUOTES).append(totalCost).append(QUOTES);

		sb.append("}");
				
		return sb.toString();
	}
	public String getFromPlace() {
		return fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public Plane getPlane() {
		return plane;
	}
	
	public String getOutgoingDeparture() {
		return outgoingDeparture + ":00";
	}
	public String getOutgoingArrival() {
		return outgoingArrival + ":00";
	}
	
	public String getReturningDeparture() {
		return returningDeparture + ":00";
	}

	public String getReturningArrival() {
		return returningArrival + ":00";
	}

}
