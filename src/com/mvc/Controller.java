package com.mvc;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Controller implements MouseListener{
	//Størrelsen på brettet og hvilke antall naboer som er bra
	int x; int y; int[] lives;
	
	//grid inneholder dataene som representerer spillet
	Grid grid;
	
	//Viser spillet
	View view;
	
	//Brukes til å hindre dobbeltrykk
	long time;
	
	//Holder styr på sliderverdien
	int speed;
	
	//Kjører oppdatering av grid og view
	javax.swing.Timer t;
	
	
	
	
	public Controller(int x, int y, int[] lives) {
		
		speed = 100;
		this.x = x;
		this.y = y;
		this.lives = lives;
		createSim();
		
	}
	
	//Setter i gang simulering
	public void initSim() {
		view.f = true;
		t.start();
	}
	
	//Lager et nytt vindu for ny simulering
	public void newView() {
		t.stop();
		view.dispose();
		createSim();
	}
	
	
	public void createSim() {
		//Nytt grid lages og sendes inn konstruksjonen av nytt view.
		grid = new Grid(x, y, lives);
		view = new View(grid);
		time = System.currentTimeMillis();
		
		view.slider.addChangeListener(s -> changeSpeed());
		view.panel.addMouseListener(this);
		view.startBtn.addActionListener(e -> initSim());
		view.clearBtn.addActionListener(e -> newView());
		view.startBtn.setActionCommand("start");
		view.setVisible(true);
		
		t = new javax.swing.Timer(speed, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	view.placeCells();
				view.revalidate();	
	        }
	     });
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Bruker denne for å unngå dobbeltrykk
		if(System.currentTimeMillis() > 50 + time) {
			// TODO Auto-generated method stub
			int w = view.panel.getWidth();
	        int h = view.panel.getHeight();
			int x=e.getX()/(w/grid.getX());
	        int y=e.getY()/(h/grid.getY());
	        int v = grid.getGridIndex(x, y);
	        
	        if(v > 0) {
	        	v = 0;
	        }
	        else {v = 1;}
	        
	        grid.setGridIndex(x, y, v);;
	     
	        //Oppdaterer view
	        view.placeCells();
	        view.revalidate();
	        
	        //resetter tiden
	        time = System.currentTimeMillis();
	        
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Endrer pausen til "t" basert på slider sin verdi
	public void changeSpeed() {
		this.speed = view.slider.getValue();
		
		t.stop();
        t.setDelay(speed);
        t.start();
	}
	
	public static void main(String[] args) {
		
	}
}
