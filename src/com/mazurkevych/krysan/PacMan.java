package com.mazurkevych.krysan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import sun.font.CreatedFontTracker;

public class PacMan implements Runnable {

	public static int x;
	public static int y;
	public static int lastx;
	public static int lasty;
	public static char direction; 
	public static boolean fleeMode;
	public static byte life;
	

	public PacMan() {
		PacMan.direction = 'r';
		this.life = 3;
		this.x = 13;
		this.y = 11;
		this.lastx = 13;
		this.lasty = 11;
		fleeMode = false;
//		Labyrinth.sounds.newGame();
	}

	public void pacManMove() {
		int extraCoordinate;

		if (PacMan.direction == 'l') {
			extraCoordinate = y - 1;
			if (Game.lab.objectsPresent[x][extraCoordinate] != 1) {
				lasty = y;
				lastx = x;
				y--;
				Game.lab.turn = 180;
				// lab.repaint();
			}
		}

		else if (PacMan.direction =='r') {
			extraCoordinate = y + 1;
			if (Game.lab.objectsPresent[x][extraCoordinate] != 1) {
				lasty = y;
				lastx = x;
				y++;
				Game.lab.turn = 0;
				// lab.repaint();
			}
		}

		else if (PacMan.direction == 'u') {
			extraCoordinate = x - 1;
			if (Game.lab.objectsPresent[extraCoordinate][y] != 1) {
				lasty = y;
				lastx = x;
				x--;
				Game.lab.turn = 90;
				// lab.repaint();
			}
		}

		else if (PacMan.direction == 'd') {
			extraCoordinate = x + 1;
			if (Game.lab.objectsPresent[extraCoordinate][y] != 1) {
				lasty = y;
				lastx = x;
				x++;
				Game.lab.turn = 270;
				// lab.repaint();
			}
		}
		Game.lab.objectsPresent[lastx][lasty] = 2;
		switch(Game.lab.objectsPresent[x][y]){
		case 0: {
			Game.score += 50;
			break;
		}
		case 3: {
			Game.score += 150;
			PacMan.fleeMode = true;
			break;
		}
		case 5: case 6: case 7: case 8: {
			life--;
			break;
		}
		}
		if (life <= 0) {
			Labyrinth.gameMode = "gameOver";
//			Labyrinth.sounds.death();
		}
		if(Game.score%8300==0){
			Labyrinth.gameMode = "gameWin";
		}
		Game.lab.objectsPresent[x][y] = 4;
		if(Game.lab.pos == 60){
			Game.lab.pos = 0;
		} else {
			Game.lab.pos += 15;
		}
		Game.lab.repaint();
	}

	
	
	@Override
	public void run() {
		while (true) {
			try {
				if (Thread.interrupted()) {
					return;
				}
				if (Labyrinth.gameMode.equals("gameOver")||Labyrinth.gameMode.equals("gameWin")){
					return;
				}
				pacManMove();
				Thread.sleep(300);
				Game.lab.repaint();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
	}
	

}
