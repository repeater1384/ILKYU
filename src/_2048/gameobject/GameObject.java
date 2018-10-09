package _2048.gameobject;

import java.awt.Graphics;

import _2048.util.Vector2d;

public class GameObject {
	public Vector2d pos = new Vector2d();

	public double width = 100;
	public double height = 100;
	
	public GameObject() {
		this(0,0,100,100);
	}

	public GameObject(double x, double y, double width, double height) {
		super();
		this.pos.set(x, y);
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics gr) {
		gr.fillRect((int)pos.x, (int)pos.y, (int)width, (int)height);
	}

}
