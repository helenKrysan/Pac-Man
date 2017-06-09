package com.mazurkevych.krysan;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Ghost implements Runnable {
    
	private static final KeyEvent VK_O = null;
	Labyrinth lab = new Labyrinth();
	int[][] shortest = new int[15][23];
	String name;
	int ghostx;
	int ghosty;
	int destx;
	int desty;
	byte currSquare;

	public Ghost(String name) {
		this.name = name;
		if (name.equals("Blinky")) {
			ghostx = 8;
			ghosty = 11;
			destx = PacMan.x;
			desty = PacMan.y;
			currSquare = 2;
		}

	}

	public void move() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(79); 
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		destx = PacMan.x;
		desty = PacMan.y;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 23; j++) {
				if (lab.objectsPresent[i][j] == 1) {
					shortest[i][j] = -2;
				} else {
					shortest[i][j] = -1;
				}
			}
		}
		if (name.equals("Blinky")) {
			blinkyMoveWave(destx, desty, 0);
			int place = shortest[ghostx][ghosty];
			if (shortest[ghostx - 1][ghosty] == place - 1) {
				lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghostx = ghostx - 1;
				currSquare = lab.objectsPresent[ghostx][ghosty];
				lab.objectsPresent[ghostx][ghosty] = 5;
				place--;
			} else if (shortest[ghostx + 1][ghosty] == place - 1) {
				lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghostx = ghostx + 1;
				currSquare = lab.objectsPresent[ghostx][ghosty];
				lab.objectsPresent[ghostx][ghosty] = 5;
				place--;
			} else if (shortest[ghostx][ghosty - 1] == place - 1) {
				lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghosty = ghosty - 1;
				currSquare = lab.objectsPresent[ghostx][ghosty];
				lab.objectsPresent[ghostx][ghosty] = 5;
				place--;
			} else if (shortest[ghostx][ghosty + 1] == place - 1) {
				lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghosty = ghosty + 1;
				currSquare = lab.objectsPresent[ghostx][ghosty];
				lab.objectsPresent[ghostx][ghosty] = 5;
				place--;
				
			}
			lab.repaint();
		}
	}

	private void blinkyMoveWave(int x, int y, int d) {
		if ((shortest[x][y] != -1) && (shortest[x][y] < d)) {
			return;
		} else {
			shortest[x][y] = d;
			blinkyMoveWave(x + 1, y, d + 1);
			blinkyMoveWave(x - 1, y, d + 1);
			blinkyMoveWave(x, y + 1, d + 1);
			blinkyMoveWave(x, y - 1, d + 1);
		}
	}
	@Override
	public void run() {
		while (true) {
           try {  
            move();
			Thread.sleep(1000);
			
		} catch (InterruptedException e){
			e.printStackTrace();
		}
			
			
			
		}
	}
}