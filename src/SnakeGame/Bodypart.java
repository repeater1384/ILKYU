package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class Bodypart {

	private int xCoor, yCoor, width, height;

	public Bodypart(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = tileSize;
		this.height = tileSize;
	}

	public void tick() {

	}

	public void draw(Graphics gr) {
		gr.setColor(Color.yellow);
		gr.fillRect(xCoor * width, yCoor * height, width, height);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	
}
