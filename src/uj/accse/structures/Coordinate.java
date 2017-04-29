package uj.accse.structures;

public class Coordinate {
	private double lat, lon;

	public Coordinate() {
		this(0, 0);
	}

	public Coordinate(double lat, double lon) {
		this.setLat(lat);
		this.setLon(lon);
	}

	public double getLat() {
		return (lat - Global.latMin) * ((double) 720 / ((double) Global.latMax - Global.latMin));
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return (lon - Global.lonMin) * ((double) 720 / ((double) Global.lonMax - Global.lonMin));
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
}
