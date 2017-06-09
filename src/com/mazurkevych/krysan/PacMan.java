package com.mazurkevych.krysan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import sun.font.CreatedFontTracker;

public class PacMan implements KeyListener {

	Timer frameTimer;
	Labyrinth lab = new Labyrinth();
	public static int x = 13;
	public static int y = 11;
	public static int lastx = 13;
	public static int lasty = 11;
	static JFrame f;
	static Ghost blinky = new Ghost("Blinky");
	// public Circle pacMan;

	public PacMan() {
		// k = g.fillArc(x + 7, y + 7,17,17,30,300);

		// pacMan = new Circle();
		f = new JFrame();
		f.setSize(700, 500);
		f.add(lab);
		f.setBackground(Color.BLACK);
		f.setVisible(true);
		f.setResizable(false);
		lab.addKeyListener(this);
		lab.setFocusable(true);
	}

	public void pacManMove() {
		this.x--;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		int extraCoordinate;
		
		if (keyCode == KeyEvent.VK_O) {
			lab.repaint();
		}
		
		if (keyCode == KeyEvent.VK_LEFT) {
			extraCoordinate = y - 1;
			if (lab.objectsPresent[x][extraCoordinate] != 1) {
				lasty = y;
				lastx = x;
				y--;
				lab.objectsPresent[lastx][lasty] = 2;
				lab.objectsPresent[x][y] = 4;
//				lab.repaint();
			}
		}

		else if (keyCode == KeyEvent.VK_RIGHT) {
			extraCoordinate = y + 1;
			if (lab.objectsPresent[x][extraCoordinate] != 1) {
				lasty = y;
				lastx = x;
				y++;
				lab.objectsPresent[lastx][lasty] = 2;
				lab.objectsPresent[x][y] = 4;
//				lab.repaint();
			}
		}

		else if (keyCode == KeyEvent.VK_UP) {
			extraCoordinate = x - 1;
			if (lab.objectsPresent[extraCoordinate][y] != 1) {
				lasty = y;
				lastx = x;
				x--;
				lab.objectsPresent[lastx][lasty] = 2;
				lab.objectsPresent[x][y] = 4;
//				lab.repaint();
			}
		}

		else if (keyCode == KeyEvent.VK_DOWN) {
			extraCoordinate = x + 1;
			if (lab.objectsPresent[extraCoordinate][y] != 1) {
				lasty = y;
				lastx = x;
				x++;
				lab.objectsPresent[lastx][lasty] = 2;
				lab.objectsPresent[x][y] = 4;
//				lab.repaint();
			}
		}
		lab.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	

	public static void main(String[] args) {
		PacMan pacman = new PacMan();
		Thread blinkyThread = new Thread(blinky);
		blinkyThread.start();
    
	}

}
