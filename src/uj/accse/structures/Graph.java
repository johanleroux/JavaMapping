package uj.accse.structures;

/**
 * Graph Data Structure Implementation
 * 
 * @author Johan le Roux (201577296)
 *
 */
public class Graph {
	private final ArrayList<Vertex> vertices;
	private final ArrayList<Edge> edges;

	/**
	 * Constructor
	 */
	public Graph() {
		this(new ArrayList<Vertex>(), new ArrayList<Edge>());
	}

	/**
	 * Constructor
	 * 
	 * @param vertices
	 * @param edges
	 */
	public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	/**
	 * Return all vertices
	 * 
	 * @return
	 */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * Return all edges
	 * 
	 * @return
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * Check whether there is an edge from vertex x to vertex y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean adjacent(Vertex x, Vertex y) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(x) && edge.getDestination().equals(y))
				return true;
			if (edge.getDestination().equals(x) && edge.getSource().equals(y))
				return true;
		}
		return false;
	}

	/**
	 * List all vertices of vertex x
	 * 
	 * @param x
	 * @return
	 */
	public ArrayList<Vertex> neighbors(Vertex x) {
		ArrayList<Vertex> neighbors = new ArrayList<Vertex>();

		for (Edge edge : edges)
			if (edge.getSource().equals(x))
				neighbors.add(edge.getDestination());

		return neighbors;
	}

	/**
	 * Add vertex if it doesn't yet exists
	 * 
	 * @param x
	 * @return
	 */
	public Vertex add_vertex(Vertex x) {
		vertices.add(x);
		return x;
	}

	/**
	 * Remove vertex if it exists
	 * 
	 * @param x
	 * @return
	 */
	public Vertex remove_vertex(Vertex x) {
		int i;
		if ((i = vertices.indexOf(x)) != -1)
			vertices.remove(i);
		return x;
	}

	/**
	 * Add edge if it doesn't yet exists
	 * 
	 * @param x
	 * @return
	 */
	public Edge add_edge(Edge x) {
		edges.add(x);
		return x;
	}

	/**
	 * Remove edge if it doesn't yet exists
	 * 
	 * @param x
	 * @return
	 */
	public Edge remove_edge(Edge x) {
		int i;
		if ((i = edges.indexOf(x)) != -1)
			edges.remove(i);
		return x;
	}

}