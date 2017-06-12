package com.mazurkevych.krysan;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Ghost implements Runnable {

	int[][] shortest = new int[15][23];
	String name;
	int ghostx;
	int ghosty;
	int destx;
	int desty;
	byte currSquare;
	char behavior;
	int pinkyOffsetX;
	int pinkyOffsetY;
	int inkyOffsetX;
	int inkyOffsetY;
	int time;

	// behavior:
	// c - chase
	// w - wander
	// f - flee

	public Ghost(String name) {
	  
		this.name = name;
		time = 700-Game.level*200;
		if (name.equals("Blinky")) {
			ghostx = 7;
			ghosty = 10;
			destx = PacMan.x;
			desty = PacMan.y;
			currSquare = 2;
			behavior = 'c';
		}

		else if (name.equals("Pinky")) {
			ghostx = 7;
			ghosty = 12;
			destx = PacMan.x;
			desty = PacMan.y;
			currSquare = 2;
			behavior = 'c';
		}
		else if (name.equals("Inky")) {
			ghostx = 9;
			ghosty = 10;
			destx = PacMan.x;
			desty = PacMan.y;
			currSquare = 2;
			behavior = 'c';
		}

		if (name.equals("Clyde")) {
			ghostx = 9;
			ghosty = 12;
			destx = PacMan.x;
			desty = PacMan.y;
			currSquare = 2;
			behavior = 'c';
		}

	}

	private void zero() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 23; j++) {
				if (Game.lab.objectsPresent[i][j] == 1) {
					shortest[i][j] = -2;
				} else {
					shortest[i][j] = -1;
				}
			}
		}
	}
	
	public void chooseInkyOffset(int xPacMan, int yPacMan, int xBlinky, int yBlinky){
		inkyOffsetX = 2*xPacMan-xBlinky;
		inkyOffsetY = 2*yPacMan-yBlinky;
		while(inkyOffsetX < 1) inkyOffsetX++;
		while(inkyOffsetY < 1) inkyOffsetY++;
		while(inkyOffsetX > 13) inkyOffsetX--;
		while(inkyOffsetY > 22) inkyOffsetY--;
		while (Game.lab.objectsPresent[inkyOffsetX][inkyOffsetY] == 1) inkyOffsetY--;
	}

	public void choosePinkyOffset(int x, int y) {
		pinkyOffsetX = 4;
		pinkyOffsetY = 4;
		boolean f = true;
		switch (PacMan.direction) {
		case 'l': {
			while (f) {
				if (y - pinkyOffsetX < 1) {
					while (y - pinkyOffsetX < 1)
						pinkyOffsetX--;
					f = false;
				} else {
					if (Game.lab.objectsPresent[x][y - pinkyOffsetX] != 1) {
						f = false;
					} else {
						pinkyOffsetX--;
					}
				}

			}
			break;
		}
		case 'r': {
			while (f) {
				if (y + pinkyOffsetX > 21) {
					while (y + pinkyOffsetX > 21)
						pinkyOffsetX--;
					f = false;
				} else {
					if (Game.lab.objectsPresent[x][y + pinkyOffsetX] != 1) {
						f = false;
					} else {
						pinkyOffsetX--;
					}
				}

			}
			break;
		}
		case 'u': {
			while (f) {
				if (x - pinkyOffsetX < 1) {
					while (x - pinkyOffsetX < 1)
						pinkyOffsetX--;
					if (y - pinkyOffsetY < 1) {
						while (y - pinkyOffsetY < 1)
							pinkyOffsetY--;
						f = false;
					} else {
						if (Game.lab.objectsPresent[x - pinkyOffsetX][y - pinkyOffsetY] != 1) {
							f = false;
						} else {
							pinkyOffsetY--;
						}
					}
				} else {
					if (y - pinkyOffsetY < 1) {
						while (y - pinkyOffsetY < 1)
							pinkyOffsetY--;
						f = false;
					} else {
						if (Game.lab.objectsPresent[x - pinkyOffsetX][y - pinkyOffsetY] != 1) {
							f = false;
						} else {
							pinkyOffsetY--;
						}
					}
				}

			}
			break;
		}
		case 'd': {
			while (f) {
				if (x + pinkyOffsetX > 13) {
					while (x + pinkyOffsetX > 13) {
						pinkyOffsetX--;
					}
					f = false;
				} else {
					if (Game.lab.objectsPresent[x + pinkyOffsetX][y] != 1) {
						f = false;
					} else {
						pinkyOffsetX--;
					}
				}

			}
			break;

		}
		}
	}

	public void move() {
		int place;
		destx = PacMan.x;
		desty = PacMan.y;
		zero();
		if (name.equals("Blinky")) {
			if (behavior == 'c') {
				moveWave(destx, desty, 0);

			} else if (behavior == 'w') {
				moveWave(1, 1, 0);
			} else if (behavior == 'f') {
				Game.lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghostx = 7;
				ghosty = 10;
			}
		}

		if (name.equals("Pinky")) {
			if (behavior == 'c') {
				choosePinkyOffset(destx, desty);
				if (PacMan.direction == 'r')
					moveWave(destx, desty + pinkyOffsetX, 0);
				else if (PacMan.direction == 'l')
					moveWave(destx, desty - pinkyOffsetX, 0);
				else if (PacMan.direction == 'u')
					moveWave(destx - pinkyOffsetX, desty - pinkyOffsetY, 0);
				else if (PacMan.direction == 'd')
					moveWave(destx + pinkyOffsetX, desty, 0);

			} else if (behavior == 'w') {
				zero();
				moveWave(1, 21, 0);
			} else if (behavior == 'f') {
				Game.lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghostx = 7;
				ghosty = 12;
			}
		}
		
		if (name.equals("Inky")) {
			if (behavior == 'c') {
				
				if (PacMan.direction == 'r')
					chooseInkyOffset(destx, desty+2, Game.blinky.ghostx, Game.blinky.ghosty);
				else if (PacMan.direction == 'l')
					chooseInkyOffset(destx, desty-2, Game.blinky.ghostx, Game.blinky.ghosty);
				else if (PacMan.direction == 'u')
					chooseInkyOffset(destx-2, desty, Game.blinky.ghostx, Game.blinky.ghosty);
				else if (PacMan.direction == 'd')
					chooseInkyOffset(destx+2, desty, Game.blinky.ghostx, Game.blinky.ghosty);
			    moveWave(inkyOffsetX, inkyOffsetY, 0);

			} else if (behavior == 'w') {
				zero();
				moveWave(13, 1, 0);
			} else if (behavior == 'f') {
				Game.lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghostx = 9;
				ghosty = 10;
			}
		}


		if (name.equals("Clyde")) {
			if (behavior == 'c') {
				moveWave(destx, desty, 0);
				place = shortest[ghostx][ghosty];
				if (place < 7) {
					zero();
					moveWave(13, 21, 0);
				}
			} else if (behavior == 'w') {
				moveWave(13, 21, 0);
			} else if (behavior == 'f') {
				Game.lab.objectsPresent[ghostx][ghosty] = currSquare;
				ghostx = 9;
				ghosty = 12;
				
			}
		}
		place = shortest[ghostx][ghosty];
		
		if(behavior != 'f'){		
		Game.lab.objectsPresent[ghostx][ghosty] = currSquare;
		if (shortest[ghostx - 1][ghosty] == place - 1) {
			ghostx = ghostx - 1;
		} else if (shortest[ghostx + 1][ghosty] == place - 1) {
			ghostx = ghostx + 1;
		} else if (shortest[ghostx][ghosty - 1] == place - 1) {
			ghosty = ghosty - 1;
		} else if (shortest[ghostx][ghosty + 1] == place - 1) {
			ghosty = ghosty + 1;
		}
		}
		switch (Game.lab.objectsPresent[ghostx][ghosty]) {
		case 0:
		case 2:
		case 3: {
			currSquare = Game.lab.objectsPresent[ghostx][ghosty];
			break;
		}
		case 4: {
			PacMan.life--;
			break;
		}
		case 5: {
			currSquare = Game.blinky.currSquare;
			break;
		}
		case 6: {
			currSquare = Game.pinky.currSquare;
			break;
		}
		case 7: {
			currSquare = Game.inky.currSquare;
			break;
		}
		case 8: {
			currSquare = Game.clyde.currSquare;
			break;
		}
		}

		if (name.equals("Blinky"))
			Game.lab.objectsPresent[ghostx][ghosty] = 5;
		else if (name.equals("Pinky"))
			Game.lab.objectsPresent[ghostx][ghosty] = 6;
		else if (name.equals("Inky"))
			Game.lab.objectsPresent[ghostx][ghosty] = 7;
		else if (name.equals("Clyde"))
			Game.lab.objectsPresent[ghostx][ghosty] = 8;
		place--;
			
		Game.lab.repaint();
	}

	private void moveWave(int x, int y, int d) {
		if ((shortest[x][y] != -1) && (shortest[x][y] < d)) {
			return;
		} else {
			shortest[x][y] = d;
			moveWave(x + 1, y, d + 1);
			moveWave(x - 1, y, d + 1);
			moveWave(x, y + 1, d + 1);
			moveWave(x, y - 1, d + 1);
		}
	}

	@Override
	public void run() {
		int timeChase = 0;
		int timeWander = 0;
		int timeFlee = 0;
		while (true) {
			try {
		/*		if (Thread.interrupted()) {
					return;
				} */
				if (Labyrinth.gameMode.equals("gameOver")||Labyrinth.gameMode.equals("gameWin")){
					return;
				}
				if (PacMan.fleeMode){
					if (timeFlee < 5000){
						timeFlee = timeFlee + time;
						behavior = 'f';
					} else {
						PacMan.fleeMode = false;
						timeFlee = 0;
					}
				} else 
				if (timeChase < 25000) {
					timeChase = timeChase + time;
					timeWander = 0;
					behavior = 'c';
				} else if (timeWander < 15000) {
					timeWander = timeWander + time;
					behavior = 'w';
				} else {
					timeChase = 0;
					behavior = 'c';
				}
				move();
				Thread.sleep(time);
				Game.lab.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}