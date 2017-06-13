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
	static BufferedImage imageClydeL, imageClydeR, imageClydeU, imageClydeD;
	static BufferedImage imagePinkyL, imagePinkyR, imagePinkyU, imagePinkyD;
	static BufferedImage imageInkyL, imageInkyR, imageInkyU, imageInkyD;
	static BufferedImage imageBlinkyL, imageBlinkyR, imageBlinkyU, imageBlinkyD;
	static String gameMode;
	static GameSounds sounds;
	static int dimension = 3;

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
			imageClydeR = ImageIO.read(new File("clyde.png"));
			imageClydeL = ImageIO.read(new File("clyde_l.png"));
			imageClydeU = ImageIO.read(new File("clyde_u.png"));
			imageClydeD = ImageIO.read(new File("clyde_d.png"));
			imageBlinkyR = ImageIO.read(new File("blinky.png"));
			imageBlinkyL = ImageIO.read(new File("blinky_l.png"));
			imageBlinkyU = ImageIO.read(new File("blinky_u.png"));
			imageBlinkyD = ImageIO.read(new File("blinky_d.png"));
			imagePinkyR = ImageIO.read(new File("pinky.png"));
			imagePinkyL = ImageIO.read(new File("pinky_l.png"));
			imagePinkyU = ImageIO.read(new File("pinky_u.png"));
			imagePinkyD = ImageIO.read(new File("pinky_d.png"));
			imageInkyR = ImageIO.read(new File("inky.png"));
			imageInkyL = ImageIO.read(new File("inky_l.png"));
			imageInkyU = ImageIO.read(new File("inky_u.png"));
			imageInkyD = ImageIO.read(new File("inky_d.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		gameMode = "game";
	}

	private void pacManMouth(int x, int y, int turn, Graphics g) {

	}

	public void paint(Graphics g) {

		int x = 50 * dimension;
		int y = 50 * dimension;
		g.setColor(Color.BLACK);
		g.fillRect(0 * dimension, 30 * dimension, 700 * dimension, 20 * dimension);
		g.fillRect(250 * dimension, 440 * dimension, 200 * dimension, 30 * dimension);
		g.setColor(Color.WHITE);
		g.drawLine(5*dimension, 5*dimension, 695*dimension, 5*dimension);
		g.drawLine(5*dimension, 5*dimension, 5*dimension, 478*dimension);
		g.drawLine(695*dimension, 478*dimension, 695*dimension, 5*dimension);
		g.drawLine(695*dimension, 478*dimension, 5*dimension, 478*dimension);
		g.setColor(Color.GREEN);
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.4F);
		g.setFont(newFont);
		g.drawString("SCORE: " + ((Integer) Game.score).toString(), 150 * dimension, 40 * dimension);
		g.drawString("LIFE: " + ((Byte) PacMan.life).toString(), 300 * dimension, 40 * dimension);
		g.drawString("LEVEL: " + ((Integer) (Game.level + 1)).toString(), 500 * dimension, 40 * dimension);

		if (gameMode.equals("game")) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 23; j++) {
					if (objectsPresent[i][j] == 1) {
						g.setColor(Color.WHITE);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
					} else if (objectsPresent[i][j] == 0) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
						g.setColor(Color.CYAN);
						g.fillOval(x + 9 * dimension, y + 9 * dimension, 7 * dimension, 7 * dimension);
					} else if (objectsPresent[i][j] == 4) {
						// pacManMouth(x,y,turn,g);
						// for(int k = 0; k<25; k=k+5){
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25, 25);
						g.setColor(Color.YELLOW);
						g.fillArc(x + 7 * dimension, y + 7 * dimension, 17 * dimension, 17 * dimension, 50 - pos + turn,
								280 + 2 * pos);
						// }

					} else if (objectsPresent[i][j] == 5) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
						g.setColor(Color.RED);
						// g.fillOval(x + 7, y + 7, 17, 17);
						switch (Game.blinky.ghostDirection) {
						case 'r':
							g.drawImage(imageBlinkyR, x, y, this);
							break;
						case 'l':
							g.drawImage(imageBlinkyL, x, y, this);
							break;
						case 'd':
							g.drawImage(imageBlinkyD, x, y, this);
							break;
						case 'u':
							g.drawImage(imageBlinkyU, x, y, this);
							break;
						}
					} else if (objectsPresent[i][j] == 6) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
						g.setColor(Color.PINK);
						switch (Game.pinky.ghostDirection) {
						case 'r':
							g.drawImage(imagePinkyR, x, y, this);
							break;
						case 'l':
							g.drawImage(imagePinkyL, x, y, this);
							break;
						case 'd':
							g.drawImage(imagePinkyD, x, y, this);
							break;
						case 'u':
							g.drawImage(imagePinkyU, x, y, this);
							break;
						}
					} else if (objectsPresent[i][j] == 7) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
						g.setColor(Color.CYAN);
						switch (Game.inky.ghostDirection) {
						case 'r':
							g.drawImage(imageInkyR, x, y, this);
							break;
						case 'l':
							g.drawImage(imageInkyL, x, y, this);
							break;
						case 'd':
							g.drawImage(imageInkyD, x, y, this);
							break;
						case 'u':
							g.drawImage(imageInkyU, x, y, this);
							break;
						}
					} else if (objectsPresent[i][j] == 8) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
						g.setColor(Color.ORANGE);
						switch (Game.clyde.ghostDirection) {
						case 'r':
							g.drawImage(imageClydeR, x, y, this);
							break;
						case 'l':
							g.drawImage(imageClydeL, x, y, this);
							break;
						case 'd':
							g.drawImage(imageClydeD, x, y, this);
							break;
						case 'u':
							g.drawImage(imageClydeU, x, y, this);
							break;
						}
					} else if (objectsPresent[i][j] == 3) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
						g.setColor(Color.BLUE);
						g.fillOval(x + 7 * dimension, y + 7 * dimension, 11 * dimension, 11 * dimension);
					} else if (objectsPresent[i][j] == 2) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25 * dimension, 25 * dimension);
					}
					x = x + 25 * dimension;
				}
				y = y + 25 * dimension;
				x = 50 * dimension;
			}
		} else if (gameMode.equals("gameWin")) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700 * dimension, 500 * dimension);
			g.setColor(Color.GREEN);
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 10.4F));
			g.drawString("LEVEL UP!", 230 * dimension, 240 * dimension);
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 4.0F));
	//		g.drawString("LIFES ARE RECOVERED!", 220 * dimension, 340 * dimension);
			g.setColor(Color.RED);		
			g.drawString("!MONSTER SPEED INCREASED!", 210 * dimension, 300 * dimension);
			g.setColor(Color.WHITE);
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 2.4F));
			g.drawString("PRESS ENTER TO CONTINUE", 260 * dimension, 450 * dimension);
			Labyrinth.sounds.nomNomStop();
		} else if (gameMode.equals("pause")) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700 * dimension, 500 * dimension);
			g.setColor(Color.RED);
			g.drawString("PAUSE", 330 * dimension, 240 * dimension);
			g.setColor(Color.WHITE);
			g.drawString("PRESS ENTER TO CONTINUE", 290 * dimension, 450 * dimension);
			Labyrinth.sounds.nomNomStop();
		} else if (gameMode.equals("gameOver")) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700 * dimension, 500 * dimension);
			g.setColor(Color.RED);
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 10.4F));
			g.drawString("GAME OVER!", 230 * dimension, 240 * dimension);
			g.setColor(Color.WHITE);		
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 4.0F));
			g.drawString("YOUR SCORE : " + Game.score, 250 * dimension, 300 * dimension);
			g.drawString("YOU REACH LEVEL : " + (Game.level+1), 250 * dimension, 340 * dimension);
			g.setFont(currentFont.deriveFont(currentFont.getSize() * 2.4F));
			g.drawString("PRESS ENTER TO RESTART", 280 * dimension, 450 * dimension);
			Labyrinth.sounds.nomNomStop();
		}

	}

}
