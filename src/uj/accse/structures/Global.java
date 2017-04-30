package uj.accse.structures;

import uj.accse.algorithms.MapHandler;

public class Global {
	public static ArrayList<Vertex> nodes;
	public static ArrayList<Edge> roads;
	public static ArrayList<Vertex> path;

	public static double latMax, latMin, lonMax, lonMin;
	public static String mapFile = "";
	public static MapHandler maphandler;

	public static int source, target;

	public Global() {
		nodes = new ArrayList<Vertex>();
		roads = new ArrayList<Edge>();
		path = new ArrayList<Vertex>();

		latMax = Integer.MIN_VALUE;
		latMin = Integer.MAX_VALUE;
		lonMax = Integer.MIN_VALUE;
		lonMin = Integer.MAX_VALUE;

		source = 0;
		target = 0;

		maphandler = new MapHandler(Global.nodes, Global.roads);
		// mapFile = "data/small.osm";
		// maphandler.parseDocument(mapFile);
	}

}
