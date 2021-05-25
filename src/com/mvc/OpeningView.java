package com.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class OpeningView extends JFrame{
	JPanel window;
	JPanel titlePanel;
	JPanel settingsPanel;
	JPanel runPanel;
	JComboBox<?> neighbours;
	JComboBox<?> rows;
	JComboBox<?> columns;
	int[] lives;
	int nRows;
	int nCols;
	
	
	public OpeningView() {
		this.setLayout(new GridLayout(3,1));
		this.setSize(420, 240);
		titlePanel = new JPanel();
		settingsPanel = new JPanel();
		runPanel = new JPanel();
		
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(settingsPanel);
		this.getContentPane().add(runPanel, BorderLayout.SOUTH);
		this.addHeader();
		this.addSettings();
		this.addButton();
		
		this.setVisible(true);
	}
	
	public void addHeader() {
		JLabel title = new JLabel();
		title.setSize(new Dimension(5, 5));
		title.setText("Welcome - Choose your settings");
		title.setFont(new Font("Calibri", Font.BOLD, 30));
		titlePanel.setSize(new Dimension(20, 20));
		title.setBorder(new EmptyBorder(15, 10, 0, 10));
		this.titlePanel.add(title, BorderLayout.CENTER);
	}
	
	public void addSettings() {
		settingsPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		settingsPanel.setMaximumSize(new Dimension(480, 100));
		settingsPanel.setLayout(new GridLayout(3, 2));
		JLabel nbtl = new JLabel("Optimal number of neighbours: ");
		JLabel colTitle = new JLabel("Number of columns: ");
		JLabel rowTitle = new JLabel("Number of rows: ");
			

		
		String neighbours_arr[] = {"1,2","2,3","3,4","4,5"};
		
		String sizes[] = {"10", "20", "50", "70", "100"};
		
		
		neighbours = new JComboBox(neighbours_arr);
		rows = new JComboBox(sizes);
		columns = new JComboBox(sizes);
		
		neighbours.setSelectedIndex(1);
		rows.setSelectedIndex(2);
		columns.setSelectedIndex(3);
		
		settingsPanel.add(nbtl);
		settingsPanel.add(neighbours);
		
		settingsPanel.add(rowTitle);
		settingsPanel.add(rows);
		
		settingsPanel.add(colTitle);		
		settingsPanel.add(columns);
		
	}
	
	public void addButton() {
		JButton run = new JButton("Run");
		run.addActionListener(e -> run());
		
		runPanel.setBorder(new EmptyBorder(15, 10, 15, 10));
		    
			
		run.setPreferredSize(new Dimension(60, 40));
		runPanel.add(run);
	}
	
	public int[] getNeighbours() {
		String neighb = (String) neighbours.getSelectedItem();
		String[] n_strings = neighb.split(",");
		int[] n_ret = Arrays.stream(n_strings).mapToInt(Integer::parseInt).toArray();
		
		return n_ret;
	}
	
	public int getRows() {
		String r = (String) rows.getSelectedItem();
		int r_ret =Integer.parseInt(r); 
		
		return r_ret;
	}
	
	public int getCols() {
		String c = (String) columns.getSelectedItem();
		int c_ret =Integer.parseInt(c); 
		
		return c_ret;
	}
	
	public void run() {
		this.lives = getNeighbours();
		this.nRows = getRows();
		this.nCols = getCols();
		
		this.dispose();
		
		Controller c = new Controller(nCols, nRows, lives);
	}

}
