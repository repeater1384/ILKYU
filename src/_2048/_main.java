package _2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class _main extends JFrame implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;

	private Thread thread;

	private boolean ruuning;
	
	private Game game;

	public _main() {

		windowSetting();
		setEventListener();
		init();
		
		start();
	}
	
	class GamePanel extends JPanel{
		
		public void paint(Graphics gr) {
			gr.clearRect(0, 0, WIDTH, HEIGHT);
			
			game.render(gr);
		}
	}

	private void windowSetting() {
		this.add(new GamePanel());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setTitle("Flappy Yongho");
		setVisible(true);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setEventListener() {
		addKeyListener(this);
		setFocusable(true);
	}
	
	private void init() {
		game=new Game();
		game.init();
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

	public void run() {
		try {
			while (ruuning) {
				update();
				render();
				Thread.sleep(1000 / 60);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		game.update();
	}

	public void render() {
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent yee) {
		game.keyTyped(yee);
	}

	public static void main(String[] args) {
		new _main();
	}
}
