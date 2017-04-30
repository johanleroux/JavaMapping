package uj.accse.algorithms;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import uj.accse.structures.ArrayList;
import uj.accse.structures.Edge;
import uj.accse.structures.Global;
import uj.accse.structures.Graph;
import uj.accse.structures.Vertex;

public class MapHandler extends DefaultHandler {
	private Integer prevNode;
	private long time;

	public MapHandler(ArrayList<Vertex> nodes, ArrayList<Edge> roads) {
		this.prevNode = null;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "node":
			processNode(attributes);
			break;

		case "way":
			prevNode = null;
			break;

		case "nd":
			processWay(attributes);
			break;

		default:
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "node":
			break;
		case "way":
			break;

		default:
			break;
		}
	}

	public void parseDocument(String mapFile) {
		time = System.currentTimeMillis();
		System.out.println("Parsing map: " + mapFile);
		Global.nodes.clear();
		Global.roads.clear();
		Global.path.clear();

		Global.latMax = Integer.MIN_VALUE;
		Global.latMin = Integer.MAX_VALUE;
		Global.lonMax = Integer.MIN_VALUE;
		Global.lonMin = Integer.MAX_VALUE;

		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();
			sp.parse(mapFile, this);
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public void endDocument() {
		long benchmark = System.currentTimeMillis() - time;
		System.out.println("Map Parsed in " + benchmark + "ms");
		System.out.println("# of Nodes: " + Global.nodes.size());
		System.out.println("# of Roads: " + Global.roads.size());
		System.out.println("===================================================");
	}

	/**
	 * Process a node on a map
	 * 
	 * @param attributes
	 */
	public void processNode(Attributes attributes) {
		Vertex node = new Vertex(attributes.getValue("id"));

		double lat = Math.abs(Double.parseDouble(attributes.getValue("lat")));
		double lon = Math.abs(Double.parseDouble(attributes.getValue("lon")));

		node.coordinate.setLat(lat);
		node.coordinate.setLon(lon);

		Global.latMax = lat > Global.latMax ? lat : Global.latMax;
		Global.latMin = lat < Global.latMin ? lat : Global.latMin;
		Global.lonMax = lon > Global.lonMax ? lon : Global.lonMax;
		Global.lonMin = lon < Global.lonMin ? lon : Global.lonMin;

		Global.nodes.add(node);
	}

	private void processWay(Attributes attributes) {
		Integer curNode = Global.nodes.indexOf(new Vertex(attributes.getValue("ref")));
		if (prevNode != null && prevNode != -1 && curNode != -1) {
			Edge lane = new Edge((Vertex) Global.nodes.get(prevNode), (Vertex) Global.nodes.get(curNode));
			Global.roads.add(lane);
		}

		prevNode = curNode;
	}

	public void processNavigation() {
		System.out.println("Dikstra");
		Graph graph = new Graph(Global.nodes, Global.roads);

//		System.out.println("Source: " + Global.source);
//		System.out.println("Target: " + Global.target);
		Dijkstra dijkstra = new Dijkstra(graph, Global.nodes.get(Global.source), Global.nodes.get(Global.target));
		dijkstra = null;
		System.out.println("Path " + Global.path.size());
		System.out.println("===================================================");
	}
}
