package uj.accse.structures;
import java.util.List;
public class Graph {
	private final ArrayList<Vertex> vertexes;
	private final ArrayList<Edge> edges;

	public Graph(ArrayList<Vertex> nodes, ArrayList<Edge> edges) {
		this.vertexes = nodes;
		this.edges = edges;
	}

	public ArrayList<Vertex> getVertexes() {
		return vertexes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
}