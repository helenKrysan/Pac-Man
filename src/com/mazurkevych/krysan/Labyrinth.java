package com.mazurkevych.krysan;

import java.awt.Color;
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
	static BufferedImage imageClyde;

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
		 try {                
	          imageClyde = ImageIO.read(new File("clyde.png"));
	       } catch (IOException ex) {
	           ex.printStackTrace();
	       }
	}

	public void paint(Graphics g) {

		int x = 50;
		int y = 50;

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
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.YELLOW);
					g.fillArc(x + 7, y + 7, 17, 17, 30 + turn, 300);
					// g.fillArc(x + 7, y + 7, 17, 17, 210, 300);

				} else if (objectsPresent[i][j] == 5) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.RED);
					g.fillOval(x + 7, y + 7, 17, 17);
				} else if (objectsPresent[i][j] == 6) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.PINK);
					g.fillOval(x + 7, y + 7, 17, 17);
				} else if (objectsPresent[i][j] == 7) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 25, 25);
						g.setColor(Color.CYAN);
						g.fillOval(x + 7, y + 7, 17, 17);
				} else if (objectsPresent[i][j] == 8) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
					g.setColor(Color.ORANGE);
//					g.fillOval(x + 7, y + 7, 17, 17);
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
	}

}
