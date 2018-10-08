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

	private int xCoor = 10, yCoor = 10, size = 5; //size << 처음 길이 
	
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

	public void tick() {
		if (snake.size() == 0) {
			b = new Bodypart(xCoor, yCoor, 10);
			snake.add(b);
		}

		ticks++;
		if (ticks > 250000) {
			if (right)
				xCoor++;
			if (left)
				xCoor--;
			if (down)
				yCoor++;
			if (up)
				yCoor--;
			ticks = 0;

			b = new Bodypart(xCoor, yCoor, 10);
			snake.add(b);

			if (snake.size() > size) {
				snake.remove(0);
			}
		}

		if (apples.size() == 0) {
			
			int xCoor = random.nextInt(49);
			int yCoor = random.nextInt(49);

			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}

		for (int i = 0; i < apples.size(); i++) {
			if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {

				size++;
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

		if (xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) { // 벽과 충돌
			System.out.println("game over");
			stop();
		}

	}

	public void paint(Graphics gr) {
		gr.clearRect(0, 0, WIDTH, HEIGHT);

		gr.setColor(Color.black);
		gr.fillRect(0, 0, WIDTH, HEIGHT);

//		for (int i = 0; i < WIDTH / 10; i++) {
//			gr.drawLine(i * 10, 0, i * 10, HEIGHT);
//		}
//		for (int i = 0; i < HEIGHT / 10; i++) {
//			gr.drawLine(0, i * 10, WIDTH, i * 10);
//		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(gr);
		}
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(gr);
		}
	}

	public void run() {
		while (ruuning) {
			tick();
			repaint();
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
