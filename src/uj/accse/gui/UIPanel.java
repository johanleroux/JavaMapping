package uj.accse.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import uj.accse.variables.Global;

/**
 * UIPanel Drawing Components
 * 
 * @author Johan le Roux (201577296)
 *
 */
public class UIPanel extends JPanel {
	private static final long serialVersionUID = 3769102736338594974L;

	// Setup UI Components
	JLabel lblImport, lblSource, lblTarget;
	JButton btnImport, btnNavigate;
	JComboBox<String> cmSource, cmTarget;
	MapPanel map;

	/**
	 * Constructor
	 * 
	 * @param map
	 */
	public UIPanel(MapPanel map) {
		this.map = map;
		@SuppressWarnings("unused")
		GroupLayout gl = new GroupLayout(this);

		// Import UI
		lblImport = new JLabel(
				"<html>Map Data: <br/>(stored under the data folder and can also be downloaded from OpenStreetMap)</html>");
		lblImport.setPreferredSize(new Dimension(270, 75));
		btnImport = new JButton("Import");
		btnImport.setPreferredSize(new Dimension(270, 30));

		// Source Node UI
		lblSource = new JLabel("Source Node: (green)");
		lblSource.setPreferredSize(new Dimension(270, 30));
		cmSource = new JComboBox<String>();
		cmSource.setPreferredSize(new Dimension(270, 30));
		cmSource.addItem("Empty");

		// Target Node UI
		lblTarget = new JLabel("Target Node: (blue)");
		lblTarget.setPreferredSize(new Dimension(270, 30));
		cmTarget = new JComboBox<String>();
		cmTarget.setPreferredSize(new Dimension(270, 30));
		cmTarget.addItem("Empty");

		// Navigation Process UI
		btnNavigate = new JButton("Navigate");
		btnNavigate.setPreferredSize(new Dimension(270, 30));
		btnNavigate.setEnabled(false);

		// Add UI Components to Panel
		this.add(lblImport);
		this.add(btnImport);
		this.add(lblSource);
		this.add(cmSource);
		this.add(lblTarget);
		this.add(cmTarget);
		this.add(btnNavigate);

		// Import Click Action
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImport(e);
			}
		});

		// Navigation Click Action
		btnNavigate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.maphandler.processNavigation();
				map.repaint();
			}
		});

		// Source Node Change Action
		cmSource.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Global.source = cmSource.getSelectedIndex();
				map.repaint();
			}
		});

		// Target Node Change Action
		cmTarget.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Global.target = cmTarget.getSelectedIndex();
				map.repaint();
			}
		});
	}

	/**
	 * Import Click Action
	 * 
	 * @param e
	 */
	public void btnImport(ActionEvent e) {
		// FileChooser for selecting OSM map data
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OpenStreetMap Files", "osm");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/data"));

		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				String fileName = file.toString();
				// Parse Document
				Global.maphandler.parseDocument(fileName);
				// Repaint Map Data
				map.repaint();
				this.generateComboboxes();
				btnNavigate.setEnabled(true);
				JOptionPane.showMessageDialog(this, "OSM Map Data Successfully Loaded", "Map Data",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "OSM Map Data Failed Loading", "Map Data",
						JOptionPane.WARNING_MESSAGE);
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Generate Source and Target Comboboxes
	 */
	public void generateComboboxes() {
		cmSource.removeAllItems();
		cmTarget.removeAllItems();
		for (int i = 0; i < Global.nodes.size(); i++) {
			cmSource.addItem("Node " + (i + 1));
			cmTarget.addItem("Node " + (i + 1));
		}
	}
}
