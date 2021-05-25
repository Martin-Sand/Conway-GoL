package com.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class View extends JFrame{
		Grid grid;
		JPanel panel;
		JPanel btnPanel;
		JButton startBtn;
		JButton clearBtn;
		JSlider slider;
		long time;
		boolean f;
		
		
		
		
	public View(Grid g) {
		grid = g;
		panel = new JPanel(new GridLayout(grid.getY(), grid.getX()));
		f = false;
		btnPanel = new JPanel(new GridLayout(1, 3));
		startBtn = new JButton("Start");
		clearBtn = new JButton("Clear");
		slider = new JSlider(0, 2000, 100);
		time = System.currentTimeMillis();
		
		btnPanel.add(startBtn);
		btnPanel.add(clearBtn);
		btnPanel.add(slider);
		this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		
		//panel.setSize(1900, 1000);
		this.add(panel);		
		placeCells();
		
	}
	
	public void placeCells() {
		panel.removeAll();
		if(f) {
			grid.evolveGrid();
		}
		int y = grid.getY();
		int x = grid.getX();
		int v;
		JPanel cell;
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++){
				cell = new JPanel();
				cell.setSize(new Dimension(10, 10));
				cell.setPreferredSize(new Dimension(10, 10));
				cell.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				v = grid.getGridIndex(j, i);
				if(v > 0) {
					cell.setForeground(Color.red);
					cell.setBackground(Color.red);
				}
				else {
					cell.setForeground(Color.WHITE);
					cell.setBackground(Color.WHITE);
				}
				
				panel.add(cell);
			}
		
		}
		this.pack();
	}
	
}
