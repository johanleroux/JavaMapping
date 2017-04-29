package uj.accse.structures;

import java.util.LinkedList;
import uj.acsse.map.*;

public class Global {
	public static ArrayList<Vertex> nodes;
	public static ArrayList<Edge> roads;
	public static LinkedList<Vertex> path;

	public static double latMax, latMin, lonMax, lonMin;
	public static String mapFile = "";
	public static MapHandler maphandler;

	public Global() {
		nodes = new ArrayList<Vertex>();
		roads = new ArrayList<Edge>();
		path = new LinkedList<Vertex>();

		latMax = Integer.MIN_VALUE;
		latMin = Integer.MAX_VALUE;
		lonMax = Integer.MIN_VALUE;
		lonMin = Integer.MAX_VALUE;

		maphandler = new MapHandler(Global.nodes, Global.roads);
		mapFile = "data/small.osm";
		maphandler.parseDocument(mapFile);
	}

}
