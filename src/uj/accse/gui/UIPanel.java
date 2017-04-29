package uj.accse.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import uj.accse.structures.Global;

public class UIPanel extends JPanel {
	private static final long serialVersionUID = 3769102736338594974L;

	JLabel lblImport;
	JButton btnImport;
	MapPanel map;

	public UIPanel(MapPanel map) {
		this.map = map;
		@SuppressWarnings("unused")
		GroupLayout gl = new GroupLayout(this);

		lblImport = new JLabel(
				"<html>Map Data: <br/>(stored under the data folder and can also be downloaded from OpenStreetMap)</html>");

		lblImport.setPreferredSize(new Dimension(270, 75));
		btnImport = new JButton("Import");
		btnImport.setPreferredSize(new Dimension(270, 30));

		this.add(lblImport);
		this.add(btnImport);

		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImport(e);
			}
		});
	}

	public void btnImport(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OpenStreetMap Files", "osm");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/data"));

		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				String fileName = file.toString();
				Global.maphandler.parseDocument(fileName);
				map.repaint();
				JOptionPane.showMessageDialog(this, "OSM Map Data Successfully Loaded", "Map Data",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "OSM Map Data Failed Loading", "Map Data",
						JOptionPane.WARNING_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}
