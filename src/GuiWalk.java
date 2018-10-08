import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GuiWalk extends JFrame implements Runnable {
	private Image human;
	
	int playerX = 0;
	int playerY = 0;

	final static int SCREEN_WIDTH = 250;
	final static int SCREEN_HEIGH = 300;

	boolean buf[] = new boolean[300];

	JPanel pane1 = new Mypanel();

	class Mypanel extends JPanel {

		public Mypanel() {
			addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent arg0) {

				}

				@Override
				public void keyReleased(KeyEvent e) {
					buf[e.getKeyCode()] = false;
				}

				@Override
				public void keyPressed(KeyEvent e) {
					buf[e.getKeyCode()] = true;

					switch (e.getKeyChar()) {
					case 'a':
						human = new ImageIcon("C:\\temp\\images\\LEFT.png").getImage();
						if (playerX > 0)
							playerX -= 8;

						repaint();
						break;
					case 'd':
						human = new ImageIcon("C:\\temp\\images\\RIGHT.png").getImage();
						if (playerX < SCREEN_WIDTH-38)
							playerX += 8;
						repaint();
						break;
					case 'w':
						if (playerY > 0)
							playerY -= 8;
						repaint();
						break;
					case 's':
						if (playerY < SCREEN_HEIGH)
							playerY += 8;
						repaint();
						break;
					}
				}
			});
			this.requestFocus();
			setFocusable(true);
		}

		public void paint(Graphics gp) {
			gp.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGH);

			gp.drawImage(human, playerX, playerY, null);
		}
	}

	public GuiWalk() {
		setSize(SCREEN_WIDTH, SCREEN_HEIGH);
		pane1.setSize(SCREEN_WIDTH, SCREEN_HEIGH);
		this.setBackground(Color.WHITE);

		add(pane1, BorderLayout.CENTER);
		setVisible(true);
	}

	public void run() {

	}

	public static void main(String[] args) {
		GuiWalk guiWalk = new GuiWalk();
		guiWalk.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Thread thread = new Thread(guiWalk);
		thread.start();
//		if (guiWalk.buf[97]) {
//			System.out.println("a°¡´©¸²");
//		}
	}

}
