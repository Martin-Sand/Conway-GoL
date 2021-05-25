package com.mvc;

import java.util.ArrayList;

public class Grid {
	//Holder verdiene til alle cellene
	private ArrayList<ArrayList<Integer>> list;
	//Ekstra liste som oppdateres basert på list
	//Blir hovedliste etter at hele er oppdatert
	private ArrayList<ArrayList<Integer>> updateList;
	
	//antall kolonner og rader
	private int x;
	private int y;
	
	//Reglene for grid
	private Rules rules;
	
	//konstruktør
	public Grid(int x, int y, int[] lives) {
		this.x = x;
		this.y = y;
		this.list = grid();
		this.rules = new Rules(lives);
	}
	
	//Henter listen
	public ArrayList<ArrayList<Integer>> getList(){
		return this.list;
	}
	
	//Henter verdien til rad y, kolonne x
	public int getGridIndex(int x, int y) {
		return list.get(y).get(x);
	}
	
	//Lager en ny liste med bare 0-er
	public ArrayList<ArrayList<Integer>> grid(){
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>(this.y);
		int i = 0; int j;
		while(i < y) {
			j = 0;
			ret.add(new ArrayList<Integer>());
			while(j < x) {
				ret.get(i).add(0);
				j++;
			}
			i++;
			
		}
		
		return ret;
	}
	
	//Setter kolonne y, rad x lik veriden v
	public void setGridIndex(int x, int y, int v) {
		list.get(y).set(x, v);
	}
	
	//Returner en string som representerer list
	public String toString() {
		String string = "";
		for(int i = 0; i < this.y; i ++) {
			for(int j = 0; j < this.x; j++) {
				string = string + (this.list.get(i).get(j)).toString() + " ";
			}
		string = string +  "\n";
		}
	
	return string;
	}
	
	//Erstatter list med en ny list av bare 0-er
	public void resetGrid() {
		list = grid();
	}
	
	//Sjekker antall naboer
	public int checkNeighbours(int x0, int y0) {
		int n = 0;
		int v;
		for(int i = y0 - 1; i < y0 + 2; i++) {
			for(int j = x0-1; j < x0 + 2; j++) {
				try {
					v = this.list.get(i).get(j);
					if(i != y0 || j != x0) {
						n += v;
					}
				}
				catch (Exception exc) {
				}
			}
		}
		
		return n; 
	}
	
	//
	public void evolveGrid() {
		this.updateList = grid();
		//Antall naboer
		int n;
		//Ny verdi for nåværende celle
		int v;
		//Verdi nåværende celle
		int k;
		for(int i = 0; i < this.y; i ++) {
			for(int j = 0; j < this.x; j++) {
				//System.out.println(this.list.get(i).get(j));
				n = this.checkNeighbours(j,i);
				k = this.list.get(i).get(j);
				v = this.rules.checkRules(n, k);
				this.updateList.get(i).set(j, v);
			}
		}
		
		this.list = new ArrayList<>(this.updateList);
		//System.out.print(this.toString());
	}
	
	//Setter list lik gitt list l
	public void setList(ArrayList<ArrayList<Integer>> l) {
		list = l;	
	}
	
	//Henter antall kolonner
	public int getX() {
		return x;
	}
	
	//Henter antall rader
	public int getY() {
		return y;
	}
	
	public class Rules {

		int[] lives;
		
		public Rules(int[] lives){
			this.lives = lives;
		}
		
		public int checkRules(int n, int k) {
			if(k == 1) {
				for (int element : this.lives) {
				    if (element == n) {
				        return 1;
				    }
				}
				return 0;
			}
			else {
				if(n == this.lives[1]) {
					return 1;
				}
				else {
					return 0;
				}
			}
			
		}
		
		
		

	}
}
