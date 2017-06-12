package com.mazurkevych.krysan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;

import sun.applet.Main;

public class Game implements KeyListener {
	private JFrame f;
	static int score;
	static Ghost blinky = new Ghost("Blinky");
	static Labyrinth lab = new Labyrinth();
	static PacMan pacman = new PacMan();
	static Thread pacmanThread;	
	static Thread ghostsThread;
	static Thread blinkyThread;
	static Ghost clyde = new Ghost("Clyde");
    static Thread clydeThread;
	static Ghost pinky = new Ghost("Pinky");
	static Ghost inky = new Ghost("Inky");
	static Thread pinkyThread;
	static Thread inkyThread;
	JLabel gScore;
	static int level;

	public Game() {
		f = new JFrame();
		f.setSize(700*Labyrinth.dimension, 500*Labyrinth.dimension);
		f.add(lab);
		f.setBackground(Color.BLACK);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lab.addKeyListener(this);
		lab.setFocusable(true);
		lab.requestFocus();
	}

	public static void main(String[] args) {
	 	new Game();
		pacmanThread = new Thread(pacman);
		pacmanThread.setDaemon(true);
		pacmanThread.start();
		blinkyThread = new Thread(blinky);
		blinkyThread.setDaemon(true);
		blinkyThread.start();
		clydeThread = new Thread(clyde);
		clydeThread.setDaemon(true);
		clydeThread.start();
		pinkyThread = new Thread(pinky);
		pinkyThread.setDaemon(true);
		pinkyThread.start();
		inkyThread = new Thread(inky);
		inkyThread.setDaemon(true);
		inkyThread.start();

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
		} else if ((keyCode == KeyEvent.VK_ENTER)&&(Labyrinth.gameMode.equals("gameWin")||Labyrinth.gameMode.equals("gameOver"))){
			int lastScore = Game.score;
			if(Labyrinth.gameMode.equals("gameWin")) Game.score = lastScore;
			else score = 0;
			int lastLevel = Game.level;
			if(Labyrinth.gameMode.equals("gameWin")) Game.level = lastLevel + 1;
			else level = 0;
			lab = new Labyrinth();
			f.add(lab);
			pacman = new PacMan();
			clyde = new Ghost("Clyde");
			blinky = new Ghost("Blinky");
			pinky = new Ghost("Pinky");
			inky = new Ghost("Inky");
			
			new Game();
	//		if(Labyrinth.gameMode.equals("gameWin")) Game.level = lastLevel + 1;
			Labyrinth.gameMode = "game";
			pacmanThread = new Thread(pacman);
			pacmanThread.setDaemon(true);
			pacmanThread.start();
			blinkyThread = new Thread(blinky);
			blinkyThread.setDaemon(true);
			blinkyThread.start();
			clydeThread = new Thread(clyde);
			clydeThread.setDaemon(true);
			clydeThread.start();
			pinkyThread = new Thread(pinky);
			pinkyThread.setDaemon(true);
			pinkyThread.start();
			inkyThread = new Thread(inky);
			inkyThread.setDaemon(true);
			inkyThread.start();
			
		}  else if ((keyCode == KeyEvent.VK_ENTER)&&(Labyrinth.gameMode.equals("pause"))){
			Labyrinth.gameMode = "game";
		}  else if ((keyCode == KeyEvent.VK_P)&&(Labyrinth.gameMode.equals("game"))){
			Labyrinth.gameMode = "pause";
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
	
/*	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Main.class.getResourceAsStream("pacman_beginning.wav" + url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}*/
} 
	
