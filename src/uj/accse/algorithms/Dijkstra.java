package uj.accse.algorithms;

import java.util.HashMap;
import java.util.Map;

import uj.accse.structures.ArrayList;
import uj.accse.structures.Edge;
import uj.accse.structures.Global;
import uj.accse.structures.Graph;
import uj.accse.structures.Vertex;

public class Dijkstra {
	private final ArrayList<Vertex> nodes;
	private final ArrayList<Edge> roads;
	private final Vertex source;
	private final Vertex target;
	private ArrayList<Vertex> unvisited;
	private ArrayList<Vertex> visited;
	private Map<Vertex, Vertex> predecessors;

	public Dijkstra(Graph graph, Vertex source, Vertex target) {
		// Make a local copy of the map data
		this.nodes = new ArrayList<Vertex>(graph.getVertexes());
		this.roads = new ArrayList<Edge>(graph.getEdges());

		// Set source and target node
		this.source = source;
		this.target = target;

		// Set source's distance to 0
		this.source.setDistance(0);

		// Create a set nodes
		this.predecessors = new HashMap<Vertex, Vertex>();
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

	private Vertex getLeastDistance(ArrayList<Vertex> vertexes) {
		Vertex min = null;

		for (Vertex vertex : vertexes) {
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
		for (Edge edge : this.roads) {
			if (edge.getSource().equals(node) && !visited.contains(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
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

		System.out.println("hi");
		Global.path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			Global.path.add(step);
		}
	}
}