package uj.accse.variables;

import uj.accse.algorithms.MapHandler;
import uj.accse.structures.ArrayList;
import uj.accse.structures.Edge;
import uj.accse.structures.Vertex;

/**
 * Global Variables
 * 
 * @author Johan le Roux (201577296)
 *
 */
public class Global {
	public static ArrayList<Vertex> nodes;
	public static ArrayList<Edge> roads;
	public static ArrayList<Vertex> path;

	public static double latMax, latMin, lonMax, lonMin;
	public static String mapFile = "";
	public static MapHandler maphandler;

	public static int source, target, screenWidth, screenHeight;

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

		screenWidth = 1000;
		screenHeight = 720;

		maphandler = new MapHandler(Global.nodes, Global.roads);
	}

}
