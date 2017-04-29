import java.awt.*;
import javax.swing.*;
import uj.accse.gui.*;
import uj.accse.structures.*;

public class Main {

	public static void main(String[] args) {
		// Set Default Global Variables
		new Global();

		// Main UI Frame
		JFrame frame = new JFrame("Navigation mapping system - 201577296");
		frame.setLayout(new BorderLayout());

		// MapPanel Component
		MapPanel map = new MapPanel();
		map.setPreferredSize(new Dimension(720, 720));
		map.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// UI Component
		UIPanel ui = new UIPanel(map);
		ui.setPreferredSize(new Dimension(280, 100));

		// Adding Components to Frame
		frame.add(map, BorderLayout.CENTER);
		frame.add(ui, BorderLayout.LINE_END);

		// Setting UI Frame
		frame.setSize(1000, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
