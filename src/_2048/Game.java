package _2048;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import _2048.gameobject.GameObject;
import _2048.gameobject.Player;
import _2048.util.ImageLoader;

public class Game implements KeyListener{
	
	private Player player;
	private double gravity=0.3;
	
	public void init() {
		player = new Player(100, 100, 100, 100);
		player.velocity.x=3;
	}
	
	public void update() {
		player.update();
		player.velocity.y+=gravity;
	}
	
	public void render(Graphics gr) {
		player.render(gr);
		gr.drawImage(ImageLoader.getInstance().bird,100,100,null);
	}
	
	public void reset() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			player.jump(5);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
