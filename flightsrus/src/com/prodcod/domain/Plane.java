package com.prodcod.domain;

import java.io.Serializable;

public class Plane implements Serializable{

	private static final long serialVersionUID = -6564367454984130336L;

	private String flightNumber;
	private String company;
	
	//filtering options
	private boolean mealsSupplied;
	private boolean stopOver;
	
	public Plane() {
		
	}
	
	public Plane(final String flightNumber, final String company) {
		this.flightNumber = flightNumber;
		this.company = company;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isMealsSupplied() {
		return mealsSupplied;
	}

	public void setMealsSupplied(boolean mealsSupplied) {
		this.mealsSupplied = mealsSupplied;
	}

	public boolean isStopOver() {
		return stopOver;
	}

	public void setStopOver(boolean stopOver) {
		this.stopOver = stopOver;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", company=" + company
				+ ", mealsSupplied=" + mealsSupplied + ", stopOver=" + stopOver
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + (mealsSupplied ? 1231 : 1237);
		result = prime * result + (stopOver ? 1231 : 1237);
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
		Plane other = (Plane) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (mealsSupplied != other.mealsSupplied)
			return false;
		if (stopOver != other.stopOver)
			return false;
		return true;
	}
	
	
}
