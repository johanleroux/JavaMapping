package uj.accse.gui;

import java.awt.*;
import javax.swing.*;

import uj.accse.structures.*;
import uj.accse.variables.Global;

/**
 * MapPanel for Displaying Map
 * 
 * @author Johan le Roux (201577296)
 *
 */
public class MapPanel extends JPanel {
	private static final long serialVersionUID = 3483335523000181440L;

	/**
	 * PaintComponent
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 720, 720);

		// Loop through nodes
		if (Global.nodes != null && !Global.nodes.isEmpty())
			g.setColor(Color.BLACK);

		for (Vertex node : Global.nodes)
			g.fillOval((int) Math.abs(node.coordinate.getLon()) - 1, (int) Math.abs(node.coordinate.getLat()) - 1, 3,
					3);

		// Loop through roads
		if (Global.roads != null && !Global.roads.isEmpty()) {
			g.setColor(Color.BLACK);
			for (Edge edge : Global.roads) {
				int sourceX = (int) edge.getSource().coordinate.getLon();
				int sourceY = (int) edge.getSource().coordinate.getLat();
				int destinationX = (int) edge.getDestination().coordinate.getLon();
				int destinationY = (int) edge.getDestination().coordinate.getLat();

				g.drawLine(sourceX, sourceY, destinationX, destinationY);
			}
		}

		// Loop through path (navigation route)
		if (Global.path != null && !Global.path.isEmpty()) {
			g.setColor(Color.RED);
			Vertex prev = Global.path.get(0);
			for (Vertex node : Global.path) {
				int sourceX = (int) prev.coordinate.getLon();
				int sourceY = (int) prev.coordinate.getLat();
				int destinationX = (int) node.coordinate.getLon();
				int destinationY = (int) node.coordinate.getLat();

				g.drawOval((int) node.coordinate.getLon() - 1, (int) node.coordinate.getLat() - 1, 3, 3);

				g.drawLine(sourceX, sourceY, destinationX, destinationY);

				prev = node;
			}
		}

		// Set Source and Target Destination
		if (!Global.nodes.isEmpty()) {
			g.setColor(Color.GREEN);
			Vertex source = Global.nodes.get(Global.source);
			g.fillOval((int) source.coordinate.getLon() - 5, (int) source.coordinate.getLat() - 5, 10, 10);
			g.setColor(Color.BLUE);
			Vertex target = Global.nodes.get(Global.target);
			g.fillOval((int) target.coordinate.getLon() - 5, (int) target.coordinate.getLat() - 5, 10, 10);
		}
	}
}
