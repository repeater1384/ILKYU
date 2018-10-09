package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;

	private Thread thread;

	private boolean ruuning;
	private boolean right = true, left = false, up = false, down = false;

	private Bodypart b;
	private ArrayList<Bodypart> snake;

	private Apple apple;
	private ArrayList<Apple> apples;

	private Random random;

	private int xCoor = 10, yCoor = 10, length = 10, size = 20;

	private int ticks = 0;

	public GamePanel() {
		setFocusable(true);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);

		snake = new ArrayList<Bodypart>();
		apples = new ArrayList<Apple>();

		random = new Random();

		start();
	}

	public void start() {
		ruuning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {

		ruuning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (right)
			xCoor++;
		if (left)
			xCoor--;
		if (down)
			yCoor++;
		if (up)
			yCoor--;
		ticks = 0;

		b = new Bodypart(xCoor, yCoor, size);
		snake.add(b);

		if (snake.size() > length) {
			snake.remove(0);
		}

		if (snake.size() == 0) {
			b = new Bodypart(xCoor, yCoor, size);
			snake.add(b);
		}

		ticks++;

		if (apples.size() == 0) {

			int xCoor = random.nextInt(WIDTH / size - 1);
			int yCoor = random.nextInt(HEIGHT / size - 1);

			apple = new Apple(xCoor, yCoor, size);
			apples.add(apple);
		}

		for (int i = 0; i < apples.size(); i++) {
			if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {

				length++;
				apples.remove(i);

			}
		}

		for (int i = 0; i < snake.size(); i++) { // 자신과 충돌
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					System.out.println("game over");
					stop();
				}
			}
		}

		if (xCoor < 0 || xCoor > WIDTH / size - 1 || yCoor < 0 || yCoor > HEIGHT / size - 1) { // 벽과 충돌
			System.out.println("game over");
			stop();
		}

	}

	public void paint(Graphics gr) {
		gr.clearRect(0, 0, WIDTH, HEIGHT);
		render(gr);
//		gr.setColor(Color.black);
//		gr.fillRect(0, 0, WIDTH, HEIGHT);

	}

	public void run() {
		while (ruuning) {
			try {
				update();
				repaint();
				Thread.sleep(1000 / 10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void render(Graphics gr) {

		for (int i = 0; i < WIDTH / size; i++) {
			gr.drawLine(i * size, 0, i * size, HEIGHT);
		}
		for (int i = 0; i < HEIGHT / size; i++) {
			gr.drawLine(0, i * size, WIDTH, i * size);
		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(gr);
		}
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(gr);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			down = false;
			up = false;
		}
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			down = false;
			up = false;
		}
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			right = false;
			left = false;
		}
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			right = false;
			left = false;
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
