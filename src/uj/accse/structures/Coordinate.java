package uj.accse.structures;

import uj.accse.variables.Global;

/**
 * Coordinate Data Structure
 * 
 * @author Johan le Roux (201577296)
 *
 */
public class Coordinate {
	private double lat, lon;

	/**
	 * Constructor
	 */
	public Coordinate() {
		this(0, 0);
	}

	/**
	 * Constructor
	 * 
	 * @param lat
	 * @param lon
	 */
	public Coordinate(double lat, double lon) {
		this.setLat(lat);
		this.setLon(lon);
	}

	/**
	 * Return Latitude
	 * 
	 * @return
	 */
	public double getLat() {
		return (lat - Global.latMin) * ((double) Global.screenHeight / ((double) Global.latMax - Global.latMin));
	}

	/**
	 * Set Latitude
	 * 
	 * @param lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Return Longitude
	 * 
	 * @return
	 */
	public double getLon() {
		return (lon - Global.lonMin) * ((double) Global.screenHeight / ((double) Global.lonMax - Global.lonMin));
	}

	/**
	 * Set Longitude
	 * 
	 * @param lon
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
}
