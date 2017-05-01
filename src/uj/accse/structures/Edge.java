package uj.accse.structures;

/**
 * This model can be seen as a road linking to points. The model has a source
 * which is from where the road links and a destination to the point where it
 * connects. The model will also contain a couple of attributes defining the
 * roads estimated speed, congestion level, etc.
 * 
 * @author Johan le Roux (201577296)
 */
public class Edge {

	private Vertex source, destination;
	private double weight;

	/**
	 * Constructor
	 * 
	 * @param source
	 * @param destination
	 */
	public Edge(Vertex source, Vertex destination) {
		this(source, destination, 1);
	}

	/**
	 * Constructor
	 * 
	 * @param source
	 * @param destination
	 * @param speed
	 */
	public Edge(Vertex source, Vertex destination, double speed) {
		this.source = source;
		this.destination = destination;
		this.weight = 1;
	}

	/**
	 * Return Source Vertex
	 * 
	 * @return
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * Return Destination Vertex
	 * 
	 * @return
	 */
	public Vertex getDestination() {
		return destination;
	}

	/**
	 * Return Weight
	 * 
	 * @return
	 */
	public int getWeight() {
		return (int) this.weight;
	}
}
