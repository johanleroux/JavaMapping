package uj.accse.structures;

public class Vertex {

	private String label;
	public final Coordinate coordinate;
	private double distance;
	private Vertex prev;

	public Vertex(String label) {
		this.label = label;
		this.coordinate = new Coordinate();
		this.setDistance(Double.MAX_VALUE);
		this.setPrev(null);
	}

	public String toString() {
		return label;

	}

	public String getLabel() {
		return this.label;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}
}
