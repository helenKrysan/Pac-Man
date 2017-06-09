package com.mazurkevych.krysan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Labyrinth extends JPanel {

	private static final long serialVersionUID = 1L;

	public static byte objectsPresent[][];
	Graphics m;

	public Labyrinth() {
		// 1 - wall
		// 0 - coin
		// 2 - empty square
		// 3 - big coin
		// 4 - Pac-Man
		// 5 - ghost
		objectsPresent = new byte[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 3, 1, 0, 1, 5, 2, 2, 1, 0, 1, 3, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 2, 2, 2, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 2, 2, 2, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 3, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

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
				 //   g.fillOval(x + 7, y + 7, 17, 17);
				//	g.fillArc(x + 7, y + 7,150,150,30,300);
					g.fillArc(x + 7, y + 7, 17, 17, 30, 300);
			    } else if (objectsPresent[i][j] == 5) {
					g.setColor(Color.BLACK);
					g.fillRect(x, y, 25, 25);
				    g.setColor(Color.RED);
					g.fillOval(x + 7, y + 7, 17, 17);
					}  
				else if (objectsPresent[i][j] == 3) {
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



/*	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {

			f.getContentPane().repaint();
		}

		else if (keyCode == KeyEvent.VK_RIGHT) {
			f.getContentPane().repaint();
		}

		else if (keyCode == KeyEvent.VK_UP) {
			f.getContentPane().repaint();
		}

		else if (keyCode == KeyEvent.VK_DOWN) {
			f.getContentPane().repaint();

		} 

	}*/

}
