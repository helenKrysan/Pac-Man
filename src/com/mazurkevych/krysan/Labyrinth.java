package com.mazurkevych.krysan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Labyrinth extends JPanel {

	private static final long serialVersionUID = 1L;

	public static byte objectsPresent[][];
	public static int turn;
	public static int pos;
	static BufferedImage imageClyde;
	static BufferedImage imagePinky;
	static BufferedImage imageInky;
	static BufferedImage imageBlinky;
	static String gameMode;
	static GameSounds sounds;

	public Labyrinth() {
		// 1 - wall
		// 0 - coin
		// 2 - empty square
		// 3 - big coin
		// 4 - Pac-Man
		// 5 - Blinky
		// 6 - Pinky
		// 7 - Inky
		// 8 - Clyde
		objectsPresent = new byte[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 3, 1, 0, 1, 5, 2, 6, 1, 0, 1, 3, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 2, 2, 2, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 7, 2, 8, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 3, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		turn = 0;
		pos = 0;
		sounds = new GameSounds();
		try {
			imageClyde = ImageIO.read(new File("clyde.png"));
			imageBlinky = ImageIO.read(new File("blinky.png"));
			imagePinky = ImageIO.read(new File("pinky.png"));
			imageInky = ImageIO.read(new File("inky.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		gameMode = "game";
	}

    private void pacManMouth(int x, int y, int turn, Graphics g){
    	
		
	}
	
	public void paint(Graphics g) {

		int x = 50;
		int y = 50;
		g.setColor(Color.BLACK);
		g.fillRect(0, 30, 500, 20);
		g.fillRect(250, 440, 200, 30);
		g.setColor(Color.WHITE);
	//	g.setFont(new Font("Times New Roman",23,Font.BOLD));
		
		g.drawString("SCORE: "+((Integer)Game.score).toString(), 200, 40);
		g.drawString("LIFE: "+((Byte)PacMan.life).toString(), 350, 40);
	
		if(gameMode.equals("game")){
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 23; j++) {
				if (objectsPresent[i][j] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect(x, y, 25, 25);
				} else if (objectsPresent[i][j] == 0) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.CYAN);
					g.fillOval(x + 9, y + 9, 7, 7);
				} else if (objectsPresent[i][j] == 4) {
//					pacManMouth(x,y,turn,g);
			//		for(int k = 0; k<25; k=k+5){
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25, 25);
						g.setColor(Color.YELLOW);
						g.fillArc(x+7, y + 7, 17, 17, 50-pos + turn, 280+2*pos);
			//		}

				} else if (objectsPresent[i][j] == 5) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.RED);
					// g.fillOval(x + 7, y + 7, 17, 17);
					g.drawImage(imageBlinky, x + 7, y + 7, this);
				} else if (objectsPresent[i][j] == 6) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.PINK);
					// g.fillOval(x + 7, y + 7, 17, 17);
					g.drawImage(imagePinky, x + 7, y + 7, this);
				} else if (objectsPresent[i][j] == 7) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.CYAN);
					g.drawImage(imageInky, x + 7, y + 7, this);
					// g.fillOval(x + 7, y + 7, 17, 17);
				} else if (objectsPresent[i][j] == 8) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.ORANGE);
					// g.fillOval(x + 7, y + 7, 17, 17);
					g.drawImage(imageClyde, x + 7, y + 7, this);
				} else if (objectsPresent[i][j] == 3) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.BLUE);
					g.fillOval(x + 7, y + 7, 11, 11);
				} else if (objectsPresent[i][j] == 2) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
				}
				x = x + 25;
			}
			y = y + 25;
			x = 50;
		}
		} else if(gameMode.equals("gameWin")){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700, 500);
			g.setColor(Color.WHITE);
			g.drawString("LEVEL UP!", 330, 240);	
			g.drawString("PRESS ENTER TO CONTINUE", 260, 450);
		}  else if(gameMode.equals("pause")){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700, 500);
			g.setColor(Color.WHITE);
			g.drawString("PAUSE", 330, 240);
			g.drawString("PRESS ENTER TO CONTINUE", 260, 450);
		} else if(gameMode.equals("gameOver")){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700, 500);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER!", 330, 240);
			g.drawString("PRESS ENTER TO RESTART", 260, 450);
		}
//		Labyrinth.sounds.nomNom();
	}

}
