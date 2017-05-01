package uj.accse.algorithms;

import uj.accse.structures.ArrayList;
import uj.accse.structures.Edge;
import uj.accse.structures.Graph;
import uj.accse.structures.Vertex;
import uj.accse.variables.Global;
//import uj.accse.structures.HashTable;
import java.util.Hashtable; // Use as replacement for my HashTable as it doesn't want to work

public class Dijkstra {
	private final Graph graph;
	private final ArrayList<Edge> roads;
	private final Vertex source;
	private final Vertex target;
	private ArrayList<Vertex> unvisited;
	private ArrayList<Vertex> visited;
	private Hashtable<Vertex, Vertex> predecessors;

	public Dijkstra(Graph graph, Vertex source, Vertex target) {
		// Make a local copy of the map data
		this.graph = new Graph(graph.getVertices(), graph.getEdges());
		this.roads = new ArrayList<Edge>(graph.getEdges());

		// Set source and target node
		this.source = source;
		this.target = target;

		// Set source's distance to 0
		this.source.setDistance(0);

		// Create a set nodes
		this.predecessors = new Hashtable<Vertex, Vertex>();
		this.visited = new ArrayList<Vertex>();
		this.unvisited = new ArrayList<Vertex>();
		this.unvisited.add(this.source);

		while (unvisited.size() > 0) {
			Vertex node = getLeastDistance(unvisited);
			visited.add(node);
			unvisited.remove(this.unvisited.indexOf(node));

			findMinimalDistances(node);
		}

		this.setPath();
	}

	private Vertex getLeastDistance(ArrayList<Vertex> vertices) {
		Vertex min = null;

		for (Vertex vertex : vertices) {
			if (min == null)
				min = vertex;
			else if (vertex.getDistance() < min.getDistance())
				min = vertex;
		}

		return min;
	}

	private void findMinimalDistances(Vertex node) {
		ArrayList<Vertex> neighbors = getNeighbors(node);
		for (Vertex target : neighbors) {
			if (target.getDistance() > node.getDistance() + getDistance(node, target)) {
				target.setDistance(node.getDistance() + getDistance(node, target));
				this.predecessors.put(target, node);
				this.unvisited.add(target);
			}
		}
	}

	private ArrayList<Vertex> getNeighbors(Vertex node) {
		ArrayList<Vertex> neighbors = new ArrayList<Vertex>();

		for (Vertex x : graph.neighbors(node))
			if (!visited.contains(x))
				neighbors.add(x);

		return neighbors;
	}

	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : this.roads) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		return -1;
	}

	public void setPath() {
		System.out.println("Generate path");
		Global.path.clear();
		Vertex step = target;

		System.out.println("Predecessor: " + predecessors.size());

		// check if a path exists
		if (predecessors.get(step) == null) {
			return;
		}

		Global.path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			Global.path.add(step);
		}
	}
}
