package com.mazurkevych.krysan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game implements KeyListener {
	static JFrame f;
	static int score;
	static Ghost blinky = new Ghost("Blinky");
	static PacMan pacman = new PacMan();
	static Thread pacmanThread;
	static Labyrinth lab = new Labyrinth();
	static Thread ghostsThread;
	static Ghost clyde = new Ghost("Clyde");
//	static Thread clydeThread;
	static Ghost pinky = new Ghost("Pinky");
	static Ghost inky = new Ghost("Inky");

	public Game() {
		f = new JFrame();
		f.setSize(700, 500);
		f.add(lab);
		f.setBackground(Color.BLACK);
		f.setVisible(true);
		f.setResizable(false);
		lab.addKeyListener(this);
		lab.setFocusable(true);
	}

	public static void main(String[] args) {
		Game game = new Game();
		pacmanThread = new Thread(pacman);
		pacmanThread.setDaemon(true);
		pacmanThread.start();
		ghostsThread = new Thread(blinky);
		ghostsThread.setDaemon(true);
		ghostsThread.start();
		ghostsThread = new Thread(clyde);
		ghostsThread.setDaemon(true);
		ghostsThread.start();
		ghostsThread = new Thread(pinky);
		ghostsThread.setDaemon(true);
		ghostsThread.start();
		ghostsThread = new Thread(inky);
		ghostsThread.setDaemon(true);
		ghostsThread.start();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			PacMan.direction = 'l';
		}

		else if (keyCode == KeyEvent.VK_RIGHT) {
			PacMan.direction = 'r';
		}

		else if (keyCode == KeyEvent.VK_UP) {
			PacMan.direction = 'u';
		
		}

		else if (keyCode == KeyEvent.VK_DOWN) {
			PacMan.direction = 'd';
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
