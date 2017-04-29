package uj.accse.gui;

import java.awt.*;
import javax.swing.*;
import uj.accse.structures.*;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 3483335523000181440L;

	public void paintComponent(Graphics g) {
		System.out.println("PAINTING");
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 720, 720);
		g.setColor(Color.BLACK);

		if (!Global.nodes.isEmpty()) {
			// Loop through render list
			for (Vertex node : Global.nodes) {
				// System.out.println(node.coordinate.getLat());
				// System.out.println(node + " -> " + node.coordinate.getLon() +
				// " | " + node.coordinate.getLat());
				g.drawOval((int) Math.abs(node.coordinate.getLon()), (int) Math.abs(node.coordinate.getLat()), 3, 3);
			}

			for (Edge edge : Global.roads) {
				int sourceX = (int) edge.getSource().coordinate.getLon();
				int sourceY = (int) edge.getSource().coordinate.getLat();
				int destinationX = (int) edge.getDestination().coordinate.getLon();
				int destinationY = (int) edge.getDestination().coordinate.getLat();

				g.drawLine(sourceX, sourceY, destinationX, destinationY);
			}

			g.setColor(Color.RED);

			if (Global.path != null && Global.path.size() > 0) {
				Vertex prev = Global.path.getFirst();
				for (Vertex node : Global.path) {
					int sourceX = (int) prev.coordinate.getLon();
					int sourceY = (int) prev.coordinate.getLat();
					int destinationX = (int) node.coordinate.getLon();
					int destinationY = (int) node.coordinate.getLat();

					g.drawOval((int) node.coordinate.getLon(), (int) node.coordinate.getLat(), 3, 3);

					g.drawLine(sourceX, sourceY, destinationX, destinationY);

					prev = node;
				}
			}
		}
	}
}
