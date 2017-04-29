package uj.accse.structures;

public class Vertex {

	private String label;
	public final Coordinate coordinate;

	public Vertex(String label) {
		this.label = label;
		this.coordinate = new Coordinate();
	}

	public String toString() {
		return label;

	}

	public String getLabel() {
		return this.label;
	}
}
